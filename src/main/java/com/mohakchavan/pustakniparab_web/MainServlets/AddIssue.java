/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohakchavan.pustakniparab_web.MainServlets;

import com.google.firebase.database.DatabaseError;
import com.mohakchavan.pustakniparab_web.Helpers.FirebaseHelpers.BaseHelper;
import com.mohakchavan.pustakniparab_web.Helpers.FirebaseHelpers.IssuesHelper;
import com.mohakchavan.pustakniparab_web.Helpers.FirebaseHelpers.NamesHelper;
import com.mohakchavan.pustakniparab_web.Models.Issues;
import com.mohakchavan.pustakniparab_web.Models.Names;
import com.mohakchavan.pustakniparab_web.StaticClasses.Constants;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
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
public class AddIssue extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(final HttpServletRequest request, final HttpServletResponse response)
	    throws ServletException, IOException {
	response.setContentType("text/html;charset=UTF-8");
	PrintWriter out = response.getWriter();
	RequestDispatcher dispatchToIssuesJSP = request.getRequestDispatcher("issues");

	out.println("<!DOCTYPE html>");
	out.println("<html>");
	out.println("<head>");
	out.println("<title>Servlet AddIssue</title>");
	out.println("</head>");
	out.println("<body>");
//	    out.println("<h1>Servlet AddIssue at " + request.getContextPath() + "</h1>");

	boolean isForAdd = false;
	Enumeration params = request.getParameterNames();
	while (params.hasMoreElements()) {
	    if (params.nextElement().toString().contentEquals("isForAdd")) {
		isForAdd = true;
		break;
	    } else {
		isForAdd = false;
	    }
	}
	NamesHelper namesHelper = new NamesHelper();

	if (!isForAdd) {
	    //Enable this code if request is from homepage.
	    try {

		redirectToIssuesJSP(request, response, namesHelper, dispatchToIssuesJSP);

	    } catch (IOException | InterruptedException | ServletException ex) {
		Logger.getLogger(AddIssue.class.getName()).log(Level.SEVERE, null, ex);
	    }

	} else if (isForAdd && request.getParameter("isForAdd").contentEquals(Constants.YES)) {
	    //Enable this code if request is for adding a issue
	    try {

		String book_name, auth_pub, iss_date, book_price;
		final String iss_name, iss_add, iss_id, iss_cont;
		book_name = request.getParameter(Constants.IDS.BOOK_NAME).trim().toUpperCase();
		auth_pub = request.getParameter(Constants.IDS.AUTHOR_PUBLISHER).trim().toUpperCase();
		iss_name = request.getParameter(Constants.IDS.ISSUER_NAME).trim().toUpperCase();
		iss_add = request.getParameter(Constants.IDS.ISSUER_ADDRESS).trim().toUpperCase();
		iss_date = request.getParameter(Constants.IDS.ISSUE_DATE).trim().toUpperCase();
		iss_cont = request.getParameter(Constants.IDS.ISSUER_CONTACT).trim().toUpperCase();
		iss_id = request.getParameter(Constants.IDS.SEL_NAME).trim().toUpperCase();
		book_price = request.getParameter(Constants.IDS.PRICE).trim().toUpperCase();

		final Issues newIssue = new Issues(book_name, book_price, auth_pub, iss_id, iss_name, iss_add, iss_cont, iss_date, Constants.NO, "");
		final CountDownLatch latch = new CountDownLatch(1);
		//For Verifying the name from cloud
		namesHelper.getNameDetails(iss_id, new BaseHelper.onCompleteRetrieval() {
		    @Override
		    public void onComplete(Object data) {
			if (data != null) {
			    Names name = (Names) data;
			    if (iss_name.contentEquals(name.returnFullName().toUpperCase())
				    && iss_add.contentEquals(name.returnFullAddress().toUpperCase())
				    && iss_cont.contentEquals(name.getContact().toUpperCase())) {

				IssuesHelper issuesHelper = new IssuesHelper();
				issuesHelper.addNewIssue(newIssue, new BaseHelper.onCompleteTransaction() {
				    @Override
				    public void onComplete(boolean committed, Object data) {
					if (committed) {
					    
					    latch.countDown();

					} else {
					    //Some error
					    latch.countDown();

					}
				    }
				});
			    } else {
				//name is not verified
				latch.countDown();
			    }
			}
		    }
		}, new BaseHelper.onFailure() {
		    @Override
		    public void onFail(Object data) {
			latch.countDown();
		    }
		});

		latch.await();
		redirectToIssuesJSP(request, response, namesHelper, dispatchToIssuesJSP);

		out.println("</body>");
		out.println("</html>");
	    } catch (InterruptedException ex) {
		Logger.getLogger(AddIssue.class.getName()).log(Level.SEVERE, null, ex);
	    }
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

    private void redirectToIssuesJSP(final HttpServletRequest request, HttpServletResponse response, NamesHelper namesHelper,
	    RequestDispatcher dispatcherForIssuesJSP) throws InterruptedException, ServletException, IOException {
	final CountDownLatch latch = new CountDownLatch(1);

	namesHelper.getAllNamesOnce(new BaseHelper.onCompleteRetrieval() {
	    @Override
	    public void onComplete(Object data) {
		List<Names> namesList = (List<Names>) data;
//			String allNamesHTML = "";
//			for (Names name : namesList) {
//			    allNamesHTML += "<option value=\"" + name.getSer_no() + "\">" + name.getSer_no() + "</option>";
//			}
		request.setAttribute("allNamesHTML", namesList);
		latch.countDown();
	    }
	}, new BaseHelper.onFailure() {
	    @Override
	    public void onFail(Object data) {
		System.err.println(((DatabaseError) data).toString());
		latch.countDown();
	    }
	});
	latch.await();
	dispatcherForIssuesJSP.forward(request, response);
    }

}
