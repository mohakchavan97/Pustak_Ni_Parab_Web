/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohakchavan.pustakniparab_web.MainServlets;

import com.mohakchavan.pustakniparab_web.Helpers.FirebaseHelpers.BaseHelper;
import com.mohakchavan.pustakniparab_web.Models.BaseData;
import com.mohakchavan.pustakniparab_web.Models.DashBoard.DashBoard;
import com.mohakchavan.pustakniparab_web.Models.DashBoard.TopBottomData;
import com.mohakchavan.pustakniparab_web.Models.Issues;
import com.mohakchavan.pustakniparab_web.Models.NewBooks;
import com.mohakchavan.pustakniparab_web.Models.SessionUser;
import com.mohakchavan.pustakniparab_web.Models.TimeStamps;
import com.mohakchavan.pustakniparab_web.StaticClasses.Constants;
import com.mohakchavan.pustakniparab_web.Helpers.SessionHelper;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.ui.Align;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.category.DefaultCategoryDataset;
import org.json.simple.JSONObject;

/**
 *
 * @author Mohak Chavan
 */

/*
//TODO If the number of requests in a day increases to a large number (i.e.: The download size for the basedata is increasing,
    then use the region named "Scheduler for BaseData Retrieval", which schedules the downloading of basedata.
    And also set the schedule period to 1-2 minutes.
 */
public class HomePage extends HttpServlet {

    private ServletContext context;

    /*//region Scheduler for BaseData Retrieval
    //info This technique is not posiible when user details are stored in session.
    private Timer timer;
    private class ScheduleBaseData extends TimerTask {

	@Override
	public void run() {
	    if (CurrentUser.isUserSet()) {
		getBaseDataAndSetCharts();
	    }
	}
    }
    //endregion*/
    @Override
    public void init(ServletConfig config) throws ServletException {
	context = config.getServletContext();

	File chartFolder = new File(context.getRealPath("/charts"));
	if (!chartFolder.exists()) {
	    chartFolder.mkdir();
	}

	/*//region Scheduler for BaseData Retrieval
	timer = new Timer();
	timer.schedule(new ScheduleBaseData(), 0, Constants.BASEDATA_PERIOD);
	//endregion*/
	super.init(config); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy() {
	/*//region Scheduler for BaseData Retrieval
	timer.cancel();
	//endregion*/

	super.destroy(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	response.setContentType("text/html;charset=UTF-8");

	RequestDispatcher dispatchToHomeJSP = request.getRequestDispatcher("homePage");
	try (PrintWriter out = response.getWriter()) {
	    /* TODO output your page here. You may use following sample code. */
	    out.println("<!DOCTYPE html>");
	    out.println("<html>");
	    out.println("<head>");
	    out.println("<title>Servlet HomePage</title>");
	    out.println("</head>");
	    out.println("<body>");
//	    out.println("<h1>Servlet HomePage at " + request.getContextPath() + "</h1>");

	    SessionHelper sessionHelper = new SessionHelper(request);
	    SessionUser currentUser = sessionHelper.getSessionUser();
	    if (currentUser != null && currentUser.isDeveloper()) {
		String isDeveloperMode = request.getParameter("isDeveloperMode").trim();
		if (isDeveloperMode != null && isDeveloperMode.equalsIgnoreCase("true")) {
		    sessionHelper.setDeveloperMode(true);
		} else {
		    sessionHelper.setDeveloperMode(false);
		}
	    } else {
		sessionHelper.setDeveloperMode(false);
	    }
	    
	    getImageDataConditionally(sessionHelper.isDeveloperMode());
	    dispatchToHomeJSP.forward(request, response);

	    out.println("</body>");
	    out.println("</html>");
	}
    }

    private void getImageDataConditionally(boolean developerMode) {
	final BaseHelper baseHelper = new BaseHelper(developerMode);
	final CountDownLatch latch = new CountDownLatch(1);
	baseHelper.getImagesAndDataTimeStamps(new BaseHelper.onCompleteRetrieval() {
	    @Override
	    public void onComplete(Object data) {
		if (data != null) {
		    if (!((TimeStamps) data).areImagesUpdated()) {
			baseHelper.getAllBaseData(new BaseHelper.onCompleteRetrieval() {
			    @Override
			    public void onComplete(Object data) {
				try {
				    setChartsUsingBaseData((BaseData) data);
				} catch (Exception ex) {
				    Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
				}
				latch.countDown();
			    }
			}, new BaseHelper.onFailure() {
			    @Override
			    public void onFail(Object data) {
				Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, data);
				latch.countDown();
			    }
			});
		    } else {
			latch.countDown();
		    }
		}
	    }
	}, new BaseHelper.onFailure() {
	    @Override
	    public void onFail(Object data) {
		Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, data);
		latch.countDown();
	    }
	});
	try {
	    latch.await();
	} catch (InterruptedException ex) {
	    Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    private void setChartsUsingBaseData(BaseData baseData) throws ParseException, Exception {

	Date localDate = Calendar.getInstance(TimeZone.getTimeZone(Constants.INDIAN_STANDARD_TIME)).getTime();
	final SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT, Locale.ENGLISH);
	final SimpleDateFormat monthYearFormat = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);
	int currMonthIssues = 0, currMonthBooks = 0, notReturned = 0, totalReturned = 0;
	HashMap<String, Integer> monthIssues = new HashMap<>();
	HashMap<String, Integer> monthNewBooks = new HashMap<>();
	HashMap<String, Integer> monthReturns = new HashMap<>();
	for (Issues issue : baseData.getIssuesList()) {
	    Date issDate = formatter.parse(issue.getIssueDate());
//		if (DateFormat.format("MMM yyyy", issDate).toString().contentEquals(DateFormat.format("MMM yyyy", localDate))) {
	    if (monthYearFormat.format(issDate).contentEquals(monthYearFormat.format(localDate))) {
		++currMonthIssues;
	    }
	    String currMonYear = monthYearFormat.format(issDate);
	    if (monthIssues.containsKey(currMonYear)) {
		monthIssues.put(currMonYear, monthIssues.get(currMonYear).intValue() + 1);
	    } else {
		monthIssues.put(currMonYear, 1);
	    }
	    if (issue.getIsReturned().contentEquals(Constants.YES) && issue.getRetDate() != null && !issue.getRetDate().isEmpty()) {
		Date retDate = formatter.parse(issue.getRetDate());
		String retMonthYear = monthYearFormat.format(retDate);
		if (monthReturns.containsKey(retMonthYear)) {
		    monthReturns.put(retMonthYear, monthReturns.get(retMonthYear).intValue() + 1);
		} else {
		    monthReturns.put(retMonthYear, 1);
		}
	    }
	    if (issue.getIsReturned().contentEquals(Constants.NO)) {
		++notReturned;
	    }
	    if (issue.getIsReturned().contentEquals(Constants.YES) && issue.getRetDate() != null && !issue.getRetDate().isEmpty()) {
		++totalReturned;
	    }
	}
	for (NewBooks book : baseData.getNewBooksList()) {
	    Date bookDate = formatter.parse(book.getRegisteredDate());
	    if (monthYearFormat.format(bookDate).contentEquals(monthYearFormat.format(localDate))) {
		currMonthBooks += Integer.parseInt(book.getTotalBooks());
	    }
	    String currMonYear = monthYearFormat.format(bookDate);
	    if (monthNewBooks.containsKey(currMonYear)) {
		monthNewBooks.put(currMonYear, (monthNewBooks.get(currMonYear).intValue() + Integer.parseInt(book.getTotalBooks())));
	    } else {
		monthNewBooks.put(currMonYear, Integer.parseInt(book.getTotalBooks()));
	    }
	}

	List<DashBoard> dashboardList = new ArrayList<>();
	dashboardList.add(new DashBoard(false, new TopBottomData[]{new TopBottomData(currMonthIssues,
	    new StringBuilder().append(monthYearFormat.format(localDate).split(" ")[0]).append("<br/>").append("Javak").toString()),
	    new TopBottomData(currMonthBooks,
	    new StringBuilder().append(monthYearFormat.format(localDate).split(" ")[0]).append("<br/>").append("Aavak").toString())
	}));
	dashboardList.add(new DashBoard(true, getImageLinkFromHashMap(monthIssues, "Monthly Javak")));
	dashboardList.add(new DashBoard(true, getImageLinkFromHashMap(monthNewBooks, "Monthly Aavak")));
	dashboardList.add(new DashBoard(false, new TopBottomData[]{new TopBottomData(baseData.getIssuesList().size(), "Total<br/>Javak"),
	    new TopBottomData(baseData.getTotalNewBooks(), "Total<br/>Aavak")}));
	dashboardList.add(new DashBoard(false, new TopBottomData[]{new TopBottomData(notReturned, "Total Not<br/>Returned"),
	    new TopBottomData(Math.round(((double) totalReturned / (double) baseData.getIssuesList().size()) * 100), "Return<br/>Ratio (%)")}));
	dashboardList.add(new DashBoard(true, getImageLinkFromHashMap(monthReturns, "Monthly Returns")));
//	request.setAttribute(Constants.ATTRIBUTE_KEY_NAMES.ALL_DASHBOARD_DATA, dashboardList);
	//Instead of passing the data in request, write this data to file and access that file while diplaying data.
	writeDashboardDataToDataFile(dashboardList);
    }

    private String getImageLinkFromHashMap(HashMap<String, Integer> hashMap, String bottomLabel) {
	List<String> keys = new ArrayList<>(hashMap.keySet());
	Collections.sort(keys, new Comparator<String>() {
	    final SimpleDateFormat formatter = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);

	    @Override
	    public int compare(String o1, String o2) {
		try {
		    return formatter.parse(o1).compareTo(formatter.parse(o2));
		} catch (ParseException ex) {
		    throw new IllegalArgumentException(ex);
		}
	    }
	});
	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//	for (int i = keys.size() - 1, j = keys.size() <= 5 ? keys.size() : 5; i >= keys.size() - 5 && i >= 0 && j > 0; i--, j--) {
	for (int i = keys.size() <= 5 ? 0 : keys.size() - 5; i < keys.size(); i++) {
	    dataset.addValue(hashMap.get(keys.get(i)), "A", keys.get(i));
	}

	try {
	    JFreeChart chart = ChartFactory.createBarChart(bottomLabel, "", "", dataset, PlotOrientation.VERTICAL, true, true, false);

//	    chart.setBackgroundPaint(new Color(0.0F, 0.0F, 0.0F, 1.0F));
	    chart.setBackgroundImage(ImageIO.read(new File(getServletContext().getRealPath("/images/dashboard_background.png"))));
	    chart.setBackgroundImageAlignment(Align.FIT_HORIZONTAL);
	    chart.setBackgroundImageAlignment(Align.FIT_VERTICAL);
	    chart.setBackgroundImageAlpha(0.85f);
	    chart.getTitle().setPaint(Color.WHITE);
	    chart.getTitle().setPosition(RectangleEdge.BOTTOM);
	    chart.getTitle().setPadding(20, 0, 5, 0);
	    chart.getLegend().setVisible(false);
	    chart.setPadding(new RectangleInsets(20, 20, 20, 20));
	    chart.getPlot().setBackgroundAlpha(0.0F);

	    chart.getPlot().setOutlineVisible(false);
	    chart.setBorderVisible(false);
	    chart.getCategoryPlot().setRangeGridlinePaint(Color.BLACK);
//		chart.getCategoryPlot().setRangeGridlineStroke(new BasicStroke(1));
	    chart.getCategoryPlot().setDomainGridlinePaint(Color.BLACK);
	    chart.getCategoryPlot().setDomainGridlinesVisible(true);
//		chart.getCategoryPlot().setDomainGridlineStroke(new BasicStroke(1));

	    chart.getCategoryPlot().getRangeAxis().setAxisLineStroke(new BasicStroke(2));
	    chart.getCategoryPlot().getRangeAxis().setAxisLinePaint(Color.BLACK);
	    Font font = chart.getCategoryPlot().getRangeAxis().getTickLabelFont();
	    chart.getCategoryPlot().getRangeAxis().setTickLabelFont(new Font(font.getName(), Font.PLAIN, 18));
	    chart.getCategoryPlot().getRangeAxis().setTickLabelPaint(Color.BLACK);

	    chart.getCategoryPlot().getDomainAxis().setAxisLineStroke(new BasicStroke(2));
	    chart.getCategoryPlot().getDomainAxis().setAxisLinePaint(Color.BLACK);
	    font = chart.getCategoryPlot().getDomainAxis().getTickLabelFont();
	    chart.getCategoryPlot().getDomainAxis().setTickLabelFont(new Font(font.getName(), Font.PLAIN, 18));
	    chart.getCategoryPlot().getDomainAxis().setTickLabelPaint(Color.BLACK);

	    ((BarRenderer) chart.getCategoryPlot().getRenderer()).setBarPainter(new StandardBarPainter());
	    ((BarRenderer) chart.getCategoryPlot().getRenderer()).setMaximumBarWidth(0.10);
	    chart.getCategoryPlot().getRenderer().setSeriesPaint(0, Color.WHITE);

	    String filePath = "/charts/" + bottomLabel.replace(" ", "_") + ".png";
	    File demoFile = new File(context.getRealPath(filePath));

	    if (!demoFile.exists()) {
		demoFile.createNewFile();
	    } else {
		demoFile.delete();
		demoFile.createNewFile();
	    }
	    ChartUtils.saveChartAsPNG(demoFile, chart, 640, 480);

	    BufferedImage orgImage = ImageIO.read(demoFile);
	    int width = orgImage.getWidth();
	    int height = orgImage.getHeight();
	    int divider = 36;

	    BufferedImage bImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D graphics2D = bImage.createGraphics();
	    graphics2D.setComposite(AlphaComposite.Src);
//	    RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
//	    hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    graphics2D.setColor(Color.WHITE);
	    graphics2D.fill(new RoundRectangle2D.Float(0, 0, width, height, width / divider, height / divider));
//	    graphics2D.setClip(new RoundRectangle2D.Double(0, 0, width, height, width / 16, height / 16));
	    graphics2D.setComposite(AlphaComposite.SrcAtop);
	    graphics2D.drawImage(orgImage, 0, 0, null);
	    graphics2D.dispose();
	    ImageIO.write(bImage, "png", demoFile);

//	    return demoFile.getAbsolutePath();
	    return "." + filePath;
	} catch (IOException ex) {
	    Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
	}
	return null;

	/*DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	dataset.addValue(1.0, "A", "A");
	dataset.addValue(2.0, "A", "B");
	dataset.addValue(3.0, "A", "C");
	dataset.addValue(4.0, "A", "D");
	JFreeChart chart = ChartFactory.createBarChart("Demo Chart", "X-axisLabel", "Y-axisLabel", dataset, PlotOrientation.VERTICAL, true, true, false);
	chart.setBackgroundPaint(Color.yellow);
	File demoFile = new File(context.getRealPath("/charts/demoChart.jpeg"));
	try {
	    if (!demoFile.exists()) {
		demoFile.createNewFile();
	    } else {
		demoFile.delete();
		demoFile.createNewFile();
	    }
	    ChartUtils.saveChartAsJPEG(demoFile, chart, 640, 480);
	} catch (IOException ex) {
	    Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
	}*/
    }

    private void writeDashboardDataToDataFile(List<DashBoard> dashboardList) throws IOException {
	File dashDataFile = new File(context.getRealPath(Constants.PATHS.DASHBOARD_DATA_PATH));
	if (!dashDataFile.exists()) {
	    dashDataFile.createNewFile();
	} else {
	    dashDataFile.delete();
	    dashDataFile.createNewFile();
	}
	BufferedWriter writer = new BufferedWriter(new FileWriter(dashDataFile));
	JSONObject jsonData = new JSONObject();
	for (int i = 0; i < dashboardList.size(); i++) {
	    jsonData.put(i, dashboardList.get(i).toString());
	}
	jsonData.writeJSONString(writer);
	writer.close();
	dashDataFile.setReadOnly();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
	return "Short description";
    }// </editor-fold>

}
