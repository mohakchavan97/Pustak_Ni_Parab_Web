/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohakchavan.pustakniparab_web.MainServlets;

import com.google.firebase.database.DatabaseError;
import com.mohakchavan.pustakniparab_web.Helpers.FirebaseHelpers.BaseHelper;
import com.mohakchavan.pustakniparab_web.Helpers.FirebaseHelpers.NamesHelper;
import com.mohakchavan.pustakniparab_web.Models.Names;
import com.mohakchavan.pustakniparab_web.StaticClasses.Constants;
import com.mohakchavan.pustakniparab_web.Helpers.SessionHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mohak Chavan
 */
public class AllNames extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(final HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	response.setContentType("text/html;charset=UTF-8");
	PrintWriter out = response.getWriter();
	RequestDispatcher dispatchToAllNamesJSP = request.getRequestDispatcher("viewAllNames");

	SessionHelper sessionHelper = new SessionHelper(request);
	Map sessionMap = sessionHelper.checkSessionAndGetCurrentUser();
	if (!sessionMap.containsKey(Constants.ATTRIBUTE_KEY_NAMES.IS_SESSION_VALID) || !((Boolean) sessionMap.get(Constants.ATTRIBUTE_KEY_NAMES.IS_SESSION_VALID))) {
	    request.getRequestDispatcher(Constants.PATHS.JSP.LOGIN).forward(request, response);
	}

	try {
	    /* TODO output your page here. You may use following sample code. */
	    out.println("<!DOCTYPE html>");
	    out.println("<html>");
	    out.println("<head>");
	    out.println("<title>Servlet AllNames</title>");
	    out.println("</head>");
	    out.println("<body>");
//	    out.println("<h1>Servlet AllNames at " + request.getContextPath() + "</h1>");

	    final CountDownLatch latch = new CountDownLatch(1);
	    NamesHelper namesHelper = new NamesHelper(sessionHelper.isDeveloperMode());

	    namesHelper.getAllNamesOnce(new BaseHelper.onCompleteRetrieval() {
		@Override
		public void onComplete(Object data) {
		    List<Names> namesList = (List<Names>) data;
		    request.setAttribute(Constants.ATTRIBUTE_KEY_NAMES.ALL_NAMES_FOR_HTML, namesList);
		    latch.countDown();
		}
	    }, new BaseHelper.onFailure() {
		@Override
		public void onFail(Object data) {
		    System.err.println(((DatabaseError) data).toString());
		    request.setAttribute(Constants.ATTRIBUTE_KEY_NAMES.HAS_ERROR_WITH_DATA, Constants.ERRORS.SOME_ERROR_FULL);
		    latch.countDown();
		}
	    });
	    latch.await();
	    dispatchToAllNamesJSP.forward(request, response);

	    out.println("</body>");
	    out.println("</html>");
	} catch (InterruptedException ex) {
	    Logger.getLogger(AllNames.class.getName()).log(Level.SEVERE, null, ex);
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
