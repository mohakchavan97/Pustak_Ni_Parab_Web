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
import com.mohakchavan.pustakniparab_web.Helpers.SessionHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
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

	SessionHelper sessionHelper = new SessionHelper(request);
	Map sessionMap = sessionHelper.checkSessionAndGetCurrentUser();
	if (!sessionMap.containsKey(Constants.ATTRIBUTE_KEY_NAMES.IS_SESSION_VALID) || !((Boolean) sessionMap.get(Constants.ATTRIBUTE_KEY_NAMES.IS_SESSION_VALID))) {
	    request.getRequestDispatcher(Constants.PATHS.JSP.LOGIN).forward(request, response);
	}

	boolean isForAdd = false;
	Enumeration params = request.getParameterNames();
	while (params.hasMoreElements()) {
	    if (params.nextElement().toString().contentEquals(Constants.ATTRIBUTE_KEY_NAMES.IS_REQUEST_FOR_ADD_ISSUE)) {
		isForAdd = true;
		break;
	    } else {
		isForAdd = false;
	    }
	}
	NamesHelper namesHelper = new NamesHelper(sessionHelper.isDeveloperMode());

	if (!isForAdd) {
	    //Enable this code if request is from homepage. (i.e.: By default, enable this code.)
	    try {

		redirectToIssuesJSP(request, response, namesHelper, dispatchToIssuesJSP);

	    } catch (IOException | InterruptedException | ServletException ex) {
		Logger.getLogger(AddIssue.class.getName()).log(Level.SEVERE, null, ex);
	    }

	} else if (isForAdd && request.getParameter(Constants.ATTRIBUTE_KEY_NAMES.IS_REQUEST_FOR_ADD_ISSUE).contentEquals(Constants.YES)) {
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
		verifyNameAndAddIssue(request, response, namesHelper, newIssue, dispatchToIssuesJSP);

	    } catch (InterruptedException ex) {
		Logger.getLogger(AddIssue.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}

	out.println("</body>");
	out.println("</html>");
    }

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
	dispatcherForIssuesJSP.forward(request, response);
    }

    private void verifyNameAndAddIssue(final HttpServletRequest request, final HttpServletResponse response, NamesHelper namesHelper,
	    final Issues newIssue, RequestDispatcher dispatchToIssuesJSP) throws InterruptedException, ServletException, IOException {
	final CountDownLatch latch = new CountDownLatch(1);
	final SessionHelper sessionHelper = new SessionHelper(request);
	//For Verifying the name from cloud
	namesHelper.getNameDetails(newIssue.getIssuerId(), new BaseHelper.onCompleteRetrieval() {
	    @Override
	    public void onComplete(Object data) {
		if (data != null) {
		    Names name = (Names) data;
		    if (newIssue.getIssuerName().contentEquals(name.returnFullName().toUpperCase())
			    && newIssue.getIssuerAddr().contentEquals(name.returnFullAddress().toUpperCase())
			    && newIssue.getIssuerCont().contentEquals(name.getContact().toUpperCase())) {

			IssuesHelper issuesHelper = new IssuesHelper(sessionHelper.isDeveloperMode());
			issuesHelper.addNewIssue(newIssue, new BaseHelper.onCompleteTransaction() {
			    @Override
			    public void onComplete(boolean committed, Object data) {
				if (committed) {
				    request.setAttribute(Constants.ATTRIBUTE_KEY_NAMES.IS_TRANSACTION_SUCCESS, data);
				    latch.countDown();

				} else {
				    //Some error
				    request.setAttribute(Constants.ATTRIBUTE_KEY_NAMES.HAS_TRNSCTN_ERROR_WITH_DATA, Constants.ERRORS.ISSUE_NOT_ADDED);
				    latch.countDown();
				}
			    }
			});
		    } else {
			//name is not verified
			request.setAttribute(Constants.ATTRIBUTE_KEY_NAMES.HAS_ERROR_WITH_DATA, Constants.ERRORS.SOME_ERROR_FULL);
			latch.countDown();
		    }
		}
	    }
	}, new BaseHelper.onFailure() {
	    @Override
	    public void onFail(Object data) {
		request.setAttribute(Constants.ATTRIBUTE_KEY_NAMES.HAS_ERROR_WITH_DATA, Constants.ERRORS.SOME_ERROR_FULL);
		latch.countDown();
	    }
	});

	latch.await();
	redirectToIssuesJSP(request, response, namesHelper, dispatchToIssuesJSP);
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
