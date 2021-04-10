/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohakchavan.pustakniparab_web.MainServlets;

import com.mohakchavan.pustakniparab_web.Helpers.FirebaseHelpers.BaseHelper;
import com.mohakchavan.pustakniparab_web.Models.BaseData;
import com.mohakchavan.pustakniparab_web.Models.CurrentUser;
import com.mohakchavan.pustakniparab_web.StaticClasses.Constants;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.jfree.data.category.DefaultCategoryDataset;

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

	    getBaseDataAndSetCharts();
	    dispatchToHomeJSP.forward(request, response);

	    out.println("</body>");
	    out.println("</html>");
	}
    }

    private void getBaseDataAndSetCharts() {
	BaseHelper baseHelper = new BaseHelper();
	final CountDownLatch latch = new CountDownLatch(1);
	baseHelper.getAllBaseData(new BaseHelper.onCompleteRetrieval() {
	    @Override
	    public void onComplete(Object data) {
		BaseData baseData = (BaseData) data;
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
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
	try {
	    latch.await();
	} catch (InterruptedException ex) {
	    Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
	}
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
