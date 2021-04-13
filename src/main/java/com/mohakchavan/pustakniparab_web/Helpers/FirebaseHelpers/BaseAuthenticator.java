/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohakchavan.pustakniparab_web.Helpers.FirebaseHelpers;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.mohakchavan.pustakniparab_web.Models.SessionUser;
import com.mohakchavan.pustakniparab_web.StaticClasses.Constants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Mohak Chavan
 */
public class BaseAuthenticator {

    private static FirebaseApp firebaseApp = null;
    private FirebaseOptions.Builder optionsBuilder;
    private ServletContext context;

    public BaseAuthenticator() {
    }

    public BaseAuthenticator(ServletContext context) {
	this.context = context;
    }

    public static FirebaseApp getFirebaseApp() {
	return firebaseApp;
    }

    public SessionUser authenticateUserAndInitializeFirebase(String idToken) {
	SessionUser user = authenticateUser(idToken);
	if (user != null) {
	    initializeFirebaseApp();
	}
	return user;
    }

    private SessionUser authenticateUser(String idToken) {
	try {
	    final GoogleIdToken.Payload payload = getUserPayload(idToken);

	    if (!Constants.GOOGLE_CLIENT_ID.equals(payload.getAudience())) {
		throw new IllegalArgumentException("Audience mismatch");
	    } else if (!Constants.GOOGLE_CLIENT_ID.equals(payload.getAuthorizedParty())) {
		throw new IllegalArgumentException("Client ID mismatch");
	    } else if (!Constants.GOOGLE_ISSUER_1.equals(payload.getIssuer()) && !Constants.GOOGLE_ISSUER_2.equals(payload.getIssuer())) {
		throw new IllegalArgumentException("Issuer mismatch");
	    }

	    return new SessionUser(
		    payload.getSubject(),
		    payload.get("name").toString(),
		    payload.getEmail(),
		    payload.get("picture").toString(),
		    payload.getIssuer());

	} catch (Exception ex) {
	    Logger.getLogger(BaseAuthenticator.class.getName()).log(Level.SEVERE, null, ex);
	    System.out.println(ex.toString());
	}
	return null;
    }

    private GoogleIdToken.Payload getUserPayload(String userIdToken) throws Exception {
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

    private void initializeFirebaseApp() {
	buildFirebaseOptions();
	if (FirebaseApp.getApps().isEmpty()) {
	    firebaseApp = FirebaseApp.initializeApp(optionsBuilder.build());
	} else {
	    FirebaseApp.getInstance().delete();
	    if (firebaseApp != null) {
		firebaseApp.delete();
	    }
	    firebaseApp = FirebaseApp.initializeApp(optionsBuilder.build());
	}
    }

    private void buildFirebaseOptions() {
	try {
	    File file = new File(context.getRealPath(Constants.PATHS.SERVICE_ACCOUNT_PATH));
	    JSONObject json = (JSONObject) new JSONParser().parse(new InputStreamReader(new FileInputStream(file)));
	    optionsBuilder = FirebaseOptions.builder();
	    optionsBuilder.setCredentials(GoogleCredentials.fromStream(new FileInputStream(file)));
	    Map<String, Object> auth = new HashMap<>();
	    auth.put(Constants.FIREBASE.AUTHORIZATION.UID, json.get("client_id"));
	    optionsBuilder.setDatabaseAuthVariableOverride(auth);
	    optionsBuilder.setDatabaseUrl(Constants.FIREBASE.DATABASE.URL);
	} catch (FileNotFoundException ex) {
	    Logger.getLogger(BaseAuthenticator.class.getName()).log(Level.SEVERE, null, ex);
	} catch (IOException | ParseException ex) {
	    Logger.getLogger(BaseAuthenticator.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

}
