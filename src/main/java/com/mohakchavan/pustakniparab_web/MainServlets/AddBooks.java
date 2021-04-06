/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohakchavan.pustakniparab_web.MainServlets;

import com.mohakchavan.pustakniparab_web.Helpers.FirebaseHelpers.BaseHelper;
import com.mohakchavan.pustakniparab_web.Helpers.FirebaseHelpers.NewBooksHelper;
import com.mohakchavan.pustakniparab_web.Models.NewBooks;
import com.mohakchavan.pustakniparab_web.StaticClasses.Constants;
import java.io.IOException;
import java.io.PrintWriter;
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
public class AddBooks extends HttpServlet {

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
	RequestDispatcher dispatchToNewBooksJSP = request.getRequestDispatcher("newBooks");
	try {
	    /* TODO output your page here. You may use following sample code. */
	    out.println("<!DOCTYPE html>");
	    out.println("<html>");
	    out.println("<head>");
	    out.println("<title>Servlet NewBooks</title>");
	    out.println("</head>");
	    out.println("<body>");
//	    out.println("<h1>Servlet AddBooks at " + request.getContextPath() + "</h1>");

	    String donorName, totBooks, bookLang, donationDate;
	    donorName = request.getParameter(Constants.IDS.DONOR_NAME).trim().toUpperCase();
	    totBooks = request.getParameter(Constants.IDS.TOTAL_BOOKS).trim().toUpperCase();
	    bookLang = request.getParameter(Constants.IDS.BOOKS_LANGUAGE).trim().toUpperCase();
	    donationDate = request.getParameter(Constants.IDS.NEW_BOOK_DATE).trim().toUpperCase();

	    NewBooks newBooks = new NewBooks(donorName, totBooks, bookLang, donationDate);
	    NewBooksHelper newBooksHelper = new NewBooksHelper();
	    final CountDownLatch latch = new CountDownLatch(1);
	    newBooksHelper.addNewRecord(newBooks, new BaseHelper.onCompleteTransaction() {
		@Override
		public void onComplete(boolean committed, Object data) {
		    if (committed) {
			request.setAttribute(Constants.ATTRIBUTE_KEY_NAMES.IS_TRANSACTION_SUCCESS, data);
			latch.countDown();

		    } else {
			//Some error
			request.setAttribute(Constants.ATTRIBUTE_KEY_NAMES.HAS_TRNSCTN_ERROR_WITH_DATA, Constants.ERRORS.BOOKS_NOT_ADDED);
			latch.countDown();
		    }
		}
	    });

	    latch.await();
	    dispatchToNewBooksJSP.forward(request, response);

	    out.println("</body>");
	    out.println("</html>");
	} catch (InterruptedException ex) {
	    Logger.getLogger(AddBooks.class.getName()).log(Level.SEVERE, null, ex);
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
