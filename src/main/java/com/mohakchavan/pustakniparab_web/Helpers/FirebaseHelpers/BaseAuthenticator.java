/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohakchavan.pustakniparab_web.Helpers.FirebaseHelpers;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.mohakchavan.pustakniparab_web.StaticClasses.Constants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
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

    private FirebaseApp firebaseApp;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseOptions.Builder optionsBuilder;
    private ServletContext context;

    public BaseAuthenticator() {
	
    }

    public BaseAuthenticator(ServletContext context) {
	this.context = context;
    }

    private void buildFirebaseOptions() {
	try {
	    File file = new File(context.getRealPath(Constants.PATHS.SERVICE_ACCOUNT_PATH));
	    JSONObject json = (JSONObject) new JSONParser().parse(new InputStreamReader(new FileInputStream(file)));
	    optionsBuilder = FirebaseOptions.builder();
	    optionsBuilder.setCredentials(GoogleCredentials.fromStream(new FileInputStream(file)));
	    Map<String, Object> auth = new HashMap<String, Object>();
	    auth.put(Constants.FIREBASE.AUTHORIZATION.UID, json.get("client_id"));
	    optionsBuilder.setDatabaseAuthVariableOverride(auth);
	    optionsBuilder.setDatabaseUrl(Constants.FIREBASE.DATABASE.URL);
	} catch (FileNotFoundException ex) {
	    Logger.getLogger(BaseAuthenticator.class.getName()).log(Level.SEVERE, null, ex);
	} catch (IOException ex) {
	    Logger.getLogger(BaseAuthenticator.class.getName()).log(Level.SEVERE, null, ex);
	} catch (ParseException ex) {
	    Logger.getLogger(BaseAuthenticator.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

}
