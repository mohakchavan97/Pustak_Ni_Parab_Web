/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohakchavan.pustakniparab_web.MainServlets;

import com.mohakchavan.pustakniparab_web.Helpers.FirebaseHelpers.BaseAuthenticator;
import com.mohakchavan.pustakniparab_web.Helpers.FirebaseHelpers.BaseHelper;
import com.mohakchavan.pustakniparab_web.Models.CurrentUser2;
import com.mohakchavan.pustakniparab_web.Models.VerifiedUsers;
import com.mohakchavan.pustakniparab_web.StaticClasses.Constants;
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

/**
 *
 * @author Mohak Chavan
 */
public class LogUserIn extends HttpServlet {

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
	    final CurrentUser2 currentUser = baseAuthenticator.authenticateUserAndInitializeFirebase(idToken);
	    session.setAttribute(Constants.ATTRIBUTE_KEY_NAMES.USER_SESSION_DATA, currentUser);

	    final CountDownLatch latch = new CountDownLatch(1);
	    BaseHelper baseHelper = new BaseHelper();
	    baseHelper.getAllVerifiedUsers(
		    new BaseHelper.onCompleteRetrieval() {
		@Override
		public void onComplete(Object data) {
		    List<VerifiedUsers> users = (List<VerifiedUsers>) data;
		    for (VerifiedUsers user : users) {
			if (user.getUserUid().equals(currentUser.getuId())) {
			    //add session for verified user
			    session.setAttribute(Constants.ATTRIBUTE_KEY_NAMES.IS_CURRENT_USER_VERIFIED, true);
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

	    out.println("<script>"
		    + "window.onload=function (){"
		    + "document.location.href=\"Home\";"
		    + "};</script>");
//	    request.getRequestDispatcher("./mainPages/homePage.jsp").forward(request, response);
	    out.println("</body>");
	    out.println("</html>");

	} catch (InterruptedException ex) {
	    Logger.getLogger(LogUserIn.class.getName()).log(Level.SEVERE, null, ex);
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
