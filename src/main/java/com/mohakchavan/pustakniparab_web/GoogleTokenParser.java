/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohakchavan.pustakniparab_web;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.util.Collections;

/**
 *
 * @author Mohak Chavan
 */
public class GoogleTokenParser {

//    private static final String GOOGLE_CLIENT_ID = "172709641516-h64u23dq9sqmjtnue6stlgibir2i19h6.apps.googleusercontent.com";
    private static final String GOOGLE_CLIENT_ID = "172709641516-rdv19n8cbqpb1u4p3d0tfhp3fqht9dbk.apps.googleusercontent.com";

    static GoogleIdToken.Payload verifyUserAndGetPayload(String userToken) throws Exception {
        JacksonFactory jacksonFactory = new JacksonFactory();
        GoogleIdTokenVerifier tokenVerifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), jacksonFactory)
                .setAudience(Collections.singletonList(GOOGLE_CLIENT_ID)).build();
        GoogleIdToken idToken = tokenVerifier.verify(userToken);

        if (idToken != null) {
            return idToken.getPayload();
        } else {
            throw new IllegalArgumentException("User Token is invalid.");
        }
        
    }
}
