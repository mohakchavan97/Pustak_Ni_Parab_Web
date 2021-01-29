/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohakchavan.pustakniparab_web.Helpers;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.mohakchavan.pustakniparab_web.Models.CurrentUser;
import com.mohakchavan.pustakniparab_web.StaticClasses.Constants;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mohak Chavan
 */
public class GoogleUserVerifier {

    public GoogleUserVerifier() {
    }

    public void verifyUser(String idToken) {
	try {
	    final GoogleIdToken.Payload payload = verifyUserAndGetPayload(idToken);

	    CurrentUser.setCurrentUser(
		    payload.getSubject(),
		    payload.get("name").toString(),
		    payload.getEmail(),
		    payload.get("picture").toString(),
		    payload.getIssuer());
	} catch (Exception ex) {
	    Logger.getLogger(GoogleUserVerifier.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    private GoogleIdToken.Payload verifyUserAndGetPayload(String userIdToken) throws Exception {
	JacksonFactory jacksonFactory = new JacksonFactory();
	GoogleIdTokenVerifier tokenVerifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), jacksonFactory)
		.setAudience(Collections.singletonList(Constants.GOOGLE_CLIENT_ID)).build();
	GoogleIdToken googleIdToken = tokenVerifier.verify(userIdToken);

	if (googleIdToken != null) {
	    return googleIdToken.getPayload();
	} else {
	    throw new IllegalArgumentException("User Token is invalid.");
	}

    }

}
