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
import java.util.ArrayList;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Mohak Chavan
 */
public class ReturnIssue extends HttpServlet {

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
	RequestDispatcher dispatchToReturnsJSP = request.getRequestDispatcher("returns");

	boolean isForReturn = false;
	Enumeration params = request.getParameterNames();
	while (params.hasMoreElements()) {
	    if (params.nextElement().toString().contentEquals(Constants.ATTRIBUTE_KEY_NAMES.IS_REQUEST_TO_RETURN_ISSUE)) {
		isForReturn = true;
		break;
	    } else {
		isForReturn = false;
	    }
	}
	IssuesHelper issuesHelper = new IssuesHelper();

	if (!isForReturn) {
	    //Enable this code if request is from homepage.
	    try {

		redirectToReturnsJSP(request, response, issuesHelper, dispatchToReturnsJSP);

	    } catch (IOException | ServletException | InterruptedException ex) {
		Logger.getLogger(AddIssue.class.getName()).log(Level.SEVERE, null, ex);
	    }

	} else if (isForReturn && request.getParameter(Constants.ATTRIBUTE_KEY_NAMES.IS_REQUEST_TO_RETURN_ISSUE).contentEquals(Constants.YES)) {
	    //enable this code when request is for returing the issue
	    JSONParser parser = new JSONParser();
	    try {
		JSONArray returningIssues = (JSONArray) parser.parse(request.getParameter("issuesToReturn"));
		String returnDate = request.getParameter(Constants.IDS.RETURN_DATE).trim().toUpperCase();
		String returnedIssues = "";
		for (Object issue : returningIssues) {
		    returnedIssues += ((JSONObject) issue).get(Constants.IDS.ISSUE_ID).toString().trim() + ", ";
		}
		returnedIssues = returnedIssues.substring(0, returnedIssues.length() - 2);
		returnedIssues += ";" + returnDate;
		final String stringToReturn = returnedIssues;
		final CountDownLatch latch = new CountDownLatch(1);
		issuesHelper.addReturnIssues(returningIssues, returnDate,
			new BaseHelper.onCompleteTransaction() {
		    @Override
		    public void onComplete(boolean committed, Object data) {
			if (committed) {
			    request.setAttribute(Constants.ATTRIBUTE_KEY_NAMES.IS_TRANSACTION_SUCCESS, stringToReturn);
			    latch.countDown();

			} else {
			    //Some error
			    request.setAttribute(Constants.ATTRIBUTE_KEY_NAMES.HAS_TRNSCTN_ERROR_WITH_DATA, Constants.ERRORS.ISSUE_NOT_ADDED);
			    latch.countDown();
			}
		    }
		});
		latch.await();
		redirectToReturnsJSP(request, response, issuesHelper, dispatchToReturnsJSP);
	    } catch (ParseException | InterruptedException ex) {
		Logger.getLogger(ReturnIssue.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
	try {
	    /* TODO output your page here. You may use following sample code. */
	    out.println("<!DOCTYPE html>");
	    out.println("<html>");
	    out.println("<head>");
	    out.println("<title>Servlet ReturnIssue</title>");
	    out.println("</head>");
	    out.println("<body>");
//	    out.println("<h1>Servlet ReturnIssue at " + request.getContextPath() + "</h1>");
	    out.println("</body>");
	    out.println("</html>");
	} catch (Exception ex) {
	}
    }

    private void redirectToReturnsJSP(final HttpServletRequest request, HttpServletResponse response, IssuesHelper issuesHelper,
	    RequestDispatcher dispatchToReturnsJSP) throws ServletException, IOException, InterruptedException {
	final CountDownLatch latch2 = new CountDownLatch(2);

	issuesHelper.getAllIssuesOnce(new BaseHelper.onCompleteRetrieval() {
	    @Override
	    public void onComplete(Object data) {
		if (data != null) {
		    List<Issues> issuesList = new ArrayList<>();
		    for (Issues issue : (List<Issues>) data) {
			if (issue.getIsReturned().contentEquals(Constants.NO)) {
			    issuesList.add(issue);
			}
		    }
		    request.setAttribute(Constants.ATTRIBUTE_KEY_NAMES.ALL_ISSUES_FOR_HTML, issuesList);
		}
		latch2.countDown();
	    }
	}, new BaseHelper.onFailure() {
	    @Override
	    public void onFail(Object data) {
		System.err.println(((DatabaseError) data).toString());
		request.setAttribute(Constants.ATTRIBUTE_KEY_NAMES.HAS_ERROR_WITH_DATA, Constants.ERRORS.SOME_ERROR_FULL);
		latch2.countDown();
	    }
	});

	NamesHelper namesHelper = new NamesHelper();
	namesHelper.getAllNamesOnce(new BaseHelper.onCompleteRetrieval() {
	    @Override
	    public void onComplete(Object data) {
		List<Names> namesList = null;
		if (data != null) {
		    namesList = (List<Names>) data;
		}
//			String allNamesHTML = "";
//			for (Names name : namesList) {
//			    allNamesHTML += "<option value=\"" + name.getSer_no() + "\">" + name.getSer_no() + "</option>";
//			}
		request.setAttribute(Constants.ATTRIBUTE_KEY_NAMES.ALL_NAMES_FOR_HTML, namesList);
		latch2.countDown();
	    }
	}, new BaseHelper.onFailure() {
	    @Override
	    public void onFail(Object data) {
		System.err.println(((DatabaseError) data).toString());
		request.setAttribute(Constants.ATTRIBUTE_KEY_NAMES.HAS_ERROR_WITH_DATA, Constants.ERRORS.SOME_ERROR_FULL);
		latch2.countDown();
	    }
	});

	latch2.await();
	dispatchToReturnsJSP.forward(request, response);
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
