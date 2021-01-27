/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohakchavan.pustakniparab_web;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.ExportedUserRecord;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.SessionCookieOptions;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONObject;

/**
 *
 * @author Mohak Chavan
 */
public class UserSignedIn extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
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
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserSignedIn</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserSignedIn at " + request.getContextPath() + "</h1>");

            String idToken = request.getParameter("idToken").trim();
            String accessToken = request.getParameter("accessToken").trim();
//            JSONObject json = new JSONObject(userToken);

            final GoogleIdToken.Payload payload = GoogleTokenParser.verifyUserAndGetPayload(idToken);
            out.println("<h1>Hello " + payload.get("name").toString()
                    + "<br/>With EmailId: " + payload.getEmail()
                    + "<br/></h1><img src=\"" + payload.get("picture") + "\"/>");
            System.out.println("Entering Firebase Operations");
            FirebaseOptions.Builder options = FirebaseOptions.builder();
//            options.setCredentials(GoogleCredentials.create(new AccessToken(accessToken, new Date(payload.getExpirationTimeSeconds()))));
//            options.setCredentials(GoogleCredentials.getApplicationDefault());
            options.setCredentials(GoogleCredentials.fromStream(
                    new FileInputStream("F:/NetBeans Projects/Pustak_Ni_Parab_Web/AccountSecrets/serviceAccount.json")));
//            options.setProjectId("pustak-ni-parab");
            options.setDatabaseAuthVariableOverride((Map<String, Object>) new HashMap<String, Object>().put("uid", payload.getSubject()));
            options.setDatabaseUrl("https://pustak-ni-parab.firebaseio.com");
            FirebaseApp app = FirebaseApp.initializeApp(options.build());
            FirebaseDatabase database = FirebaseDatabase.getInstance(app);
            FirebaseAuth auth = FirebaseAuth.getInstance(app);
            String token = auth.createCustomToken(payload.getSubject());

//            UserRecord.UpdateRequest ur=new UserRecord.UpdateRequest(auth.getUserByEmail(payload.getEmail()).getUid());
//            UserRecord.CreateRequest cr = new UserRecord.CreateRequest();
//            cr.setDisplayName(payload.get("name").toString());
//            cr.setEmail(payload.getEmail());
//            cr.setEmailVerified(payload.getEmailVerified());
//            cr.setPhotoUrl(payload.get("picture").toString());
//            cr.setUid(payload.getSubject());
//            auth.createUser(cr);
//            final FirebaseToken token = auth.verifyIdToken(idToken, true);
            final CountDownLatch latch = new CountDownLatch(1);
            database.getReference("/").child("BasePoint/Issues/3301")
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            Issues i = snapshot.getValue(Issues.class);
                            System.out.println(i.getBookName() + " "
                            //                                    + token.getUid()
                            );
                            request.setAttribute("data", i.getBookName() + "<br/>"
                            //                                    + token.getUid()
                            );
                            try {
                                request.getRequestDispatcher("./resultIssue.jsp").forward(request, response);
                            } catch (ServletException ex) {
                                Logger.getLogger(UserSignedIn.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(UserSignedIn.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            latch.countDown();
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            System.out.println("Error thrown: " + error.getMessage());
                            latch.countDown();
//                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });
//                        FirebaseAuth auth= FirebaseAuth.getInstance(app);
//                        auth.
            try {
                latch.await();
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
            System.out.println("Exit Firebase");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            throw new RuntimeException(e);
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
