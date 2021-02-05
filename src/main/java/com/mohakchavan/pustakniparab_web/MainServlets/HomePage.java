/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohakchavan.pustakniparab_web.MainServlets;

import com.mohakchavan.pustakniparab_web.Helpers.FirebaseHelpers.BaseAuthenticator;
import com.mohakchavan.pustakniparab_web.Helpers.FirebaseHelpers.BaseHelper;
import com.mohakchavan.pustakniparab_web.Models.CurrentUser;
import com.mohakchavan.pustakniparab_web.Models.VerifiedUsers;
import com.mohakchavan.pustakniparab_web.StaticClasses.Constants;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Mohak Chavan
 */
public class HomePage extends HttpServlet {

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
	PrintWriter out = response.getWriter();
	try {
	    /* TODO output your page here. You may use following sample code. */
	    out.println("<!DOCTYPE html>");
	    out.println("<html>");
	    out.println("<head>");
	    out.println("<title>Home | Pustak Ni Parab</title>");
	    out.println("</head>");
	    out.println("<body>");

	    String idToken = request.getParameter("idToken").trim();
	    String accessToken = request.getParameter("accessToken").trim();
	    ServletContext context = request.getServletContext();
	    final HttpSession session = request.getSession(true);

	    BaseAuthenticator baseAuthenticator = new BaseAuthenticator(context);
	    baseAuthenticator.authenticateUserAndInitializeFirebase(idToken);

	    final CountDownLatch latch = new CountDownLatch(1);
	    BaseHelper baseHelper = new BaseHelper();
	    baseHelper.getAllVerifiedUsers(
		    new BaseHelper.onCompleteRetrieval() {
		@Override
		public void onComplete(Object data) {
		    List<VerifiedUsers> users = (List<VerifiedUsers>) data;
		    for (VerifiedUsers user : users) {
			if (user.getUserUid().equals(CurrentUser.getuId())) {
			    //add session for verified user
			    session.setAttribute(Constants.SESSION_KEY_NAMES.IS_CURRENT_USER_VERIFIED, true);
			}
		    }
		    latch.countDown();
		}
	    },
		    new BaseHelper.onFailure() {
		@Override
		public void onFail(Object data) {
		    //send error to client side via error attribute
		    latch.countDown();
		}
	    });
	    latch.await();

	    DefaultCategoryDataset data = new DefaultCategoryDataset();
	    data.addValue(1.0, "A", "A");
	    data.addValue(2.0, "A", "B");
	    data.addValue(3.0, "A", "C");
	    data.addValue(4.0, "A", "D");
	    JFreeChart chart = ChartFactory.createBarChart("Demo Chart", "X-axisLabel", "Y-axisLabel", data, PlotOrientation.VERTICAL, true, true, false);
	    chart.setBackgroundPaint(Color.yellow);
	    ChartUtils.saveChartAsJPEG(new File("./charts/barChart.jpeg"), chart, 640, 480);

//	    baseHelper.getAllBaseData(
//		    new BaseHelper.onCompleteRetrieval() {
//		@Override
//		public void onComplete(Object data) {
//		    
//		}
//	    },
//		    new BaseHelper.onFailure() {
//		@Override
//		public void onFail(Object data) {
//		    
//		}
//	    });
	    request.getRequestDispatcher("./mainPages/homePage.jsp").forward(request, response);
	    out.println("</body>");
	    out.println("</html>");
	} catch (IOException ex) {
	    Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
	    System.out.println(ex.toString());
	} catch (ServletException ex) {
	    Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
	    System.out.println(ex.toString());
	} catch (InterruptedException ex) {
	    Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
	    out.close();
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
