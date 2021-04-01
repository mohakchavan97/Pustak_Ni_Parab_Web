/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohakchavan.pustakniparab_web.MainServlets;

import com.mohakchavan.pustakniparab_web.Helpers.FirebaseHelpers.BaseHelper;
import com.mohakchavan.pustakniparab_web.Helpers.FirebaseHelpers.NamesHelper;
import com.mohakchavan.pustakniparab_web.Models.Names;
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
public class NewName extends HttpServlet {

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
	RequestDispatcher dispatchToAddNameJSP = request.getRequestDispatcher("addName");
	try {
	    /* TODO output your page here. You may use following sample code. */
	    out.println("<!DOCTYPE html>");
	    out.println("<html>");
	    out.println("<head>");
	    out.println("<title>Servlet NewName</title>");
	    out.println("</head>");
	    out.println("<body>");
//	    out.println("<h1>Servlet NewName at " + request.getContextPath() + "</h1>");

	    String fName, lName, blkFlat, strtName, area, contact;
	    fName = request.getParameter(Constants.IDS.ISSUER_FNAME).trim().toUpperCase();
	    lName = request.getParameter(Constants.IDS.ISSUER_LNAME).trim().toUpperCase();
	    blkFlat = request.getParameter(Constants.IDS.BLK_FLT).trim().toUpperCase();
	    strtName = request.getParameter(Constants.IDS.STRT_NAME).trim().toUpperCase();
	    area = request.getParameter(Constants.IDS.LOCAL_AREA).trim().toUpperCase();
	    contact = request.getParameter(Constants.IDS.ISSUER_CONTACT).trim().toUpperCase();

	    if (lName.isEmpty()) {
		lName = "";
	    }
	    if (blkFlat.isEmpty()) {
		blkFlat = "";
	    }

	    Names newName = new Names(fName, lName, blkFlat, strtName, area, contact);
	    addNameAndDispatch(request, response, newName, dispatchToAddNameJSP);

	    out.println("</body>");
	    out.println("</html>");
	} catch (IOException | InterruptedException | ServletException ex) {
	    Logger.getLogger(NewName.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    private void addNameAndDispatch(final HttpServletRequest request, HttpServletResponse response, Names newName, RequestDispatcher dispatchToAddNameJSP) throws InterruptedException, IOException, ServletException {
	NamesHelper namesHelper = new NamesHelper();
	final CountDownLatch latch = new CountDownLatch(1);
	namesHelper.addNewName(newName, new BaseHelper.onCompleteTransaction() {
	    @Override
	    public void onComplete(boolean committed, Object data) {
		if (committed) {
		    request.setAttribute(Constants.ATTRIBUTE_KEY_NAMES.IS_TRANSACTION_SUCCESS, data);
		    latch.countDown();

		} else {
		    //Some error
		    request.setAttribute(Constants.ATTRIBUTE_KEY_NAMES.HAS_TRNSCTN_ERROR_WITH_DATA, Constants.ERRORS.NAME_NOT_ADDED);
		    latch.countDown();
		}
	    }
	});
	latch.await();
	dispatchToAddNameJSP.forward(request, response);
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
