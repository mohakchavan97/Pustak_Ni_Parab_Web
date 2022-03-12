/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohakchavan.pustakniparab_web.MainServlets;

import com.mohakchavan.pustakniparab_web.StaticClasses.Constants;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mohak Chavan
 */
public class Logout extends HttpServlet {

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
	    out.println("<title>Logout | Pustak Ni Parab</title>");
	    out.println("<script src=\"https://www.gstatic.com/firebasejs/8.8.1/firebase-app.js\"></script>");
	    out.println("<script src=\"https://www.gstatic.com/firebasejs/8.8.1/firebase-auth.js\"></script>");
	    out.println("</head>");
	    out.println("<body onload=\"loaded();\">");
//	    out.println("<h1>Servlet Logout at " + request.getContextPath() + "</h1>");

	    out.println("<div>");
	    request.getRequestDispatcher("./genericContent/loader.jsp").include(request, response);
	    out.println("</div>");

	    HttpSession session = request.getSession(false);
	    if (session != null) {
		session.invalidate();
	    }
//	    request.getRequestDispatcher(Constants.PATHS.JSP.LOGIN).forward(request, response);

	    out.println("<script>\n"
		    + "var firebaseConfig = {\n"
		    + "    apiKey: \"AIzaSyAW4PSGz4Slo2ihuYsn5t7Rselk0631pic\",\n"
		    + "    authDomain: \"pustak-ni-parab.firebaseapp.com\",\n"
		    + "    databaseURL: \"https://pustak-ni-parab.firebaseio.com\",\n"
		    + "    projectId: \"pustak-ni-parab\",\n"
		    + "    storageBucket: \"pustak-ni-parab.appspot.com\",\n"
		    + "    messagingSenderId: \"172709641516\",\n"
		    + "    appId: \"1:172709641516:web:e0b082c93bf2cd8d9ec089\",\n"
		    + "    measurementId: \"G-MF9K8JKM3V\"\n"
		    + "};\n"
		    + "function loaded(){\n"
		    + "    firebase.initializeApp(firebaseConfig);\n"
		    + "    var unsubscribe = firebase.auth().onAuthStateChanged((firebaseUser) => {\n"
		    + "    if(firebaseUser){\n"
		    + "        firebase.auth().signOut().then(() => {\n"
		    + "            console.log(\"Sign-out successful.\");\n"
		    + "        }).catch((error) => {\n"
		    + "            console.log(error);\n"
		    + "        });\n"
		    + "    }\n"
		    + "    unsubscribe();\n"
		    + "    document.location.href=\"login\";\n"
		    + "    });\n"
		    + "}\n"
		    + "</script>");

	    out.println("</body>");
	    out.println("</html>");
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
