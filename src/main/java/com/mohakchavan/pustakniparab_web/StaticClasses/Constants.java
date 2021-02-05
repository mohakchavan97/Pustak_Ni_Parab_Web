/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohakchavan.pustakniparab_web.StaticClasses;

/**
 *
 * @author Mohak Chavan
 */
public final class Constants {

    public static final class PATHS {

	public static final String SERVICE_ACCOUNT_PATH = "/staticContent/AccountSecrets/serviceAccount.json";
    }

    public static final class FIREBASE {

	public static final class DATABASE {

	    public static final String URL = "https://pustak-ni-parab.firebaseio.com";
	    public static final String VERIFIED_USERS = "VerifiedUsers";
	    public static final String BASEPOINT = "BasePoint";
	    public static final String ISSUES = "Issues";
	    public static final String NAMES = "Names";
	    public static final String NEW_BOOKS = "NewBooks";
	    public static final String TOTAL_RECORDS = "currentTotalIssuesRecorded";
	    public static final String TOTAL_NAMES = "currentTotalNamesRecorded";
	    public static final String TOTAL_NEW_BOOKS = "currentTotalNewBooksRecorded";
	    public static final String ACCOUNT_TYPE = "accountType";
	}

	public static final class AUTHORIZATION {

	    public static final String UID = "uid";
	}
    }

    public static final class SESSION_KEY_NAMES {

	public static final String IS_CURRENT_USER_VERIFIED = "isCuurentUserVerified";
    }

    public static final class ERRORS {

	public static final String SOME_ERROR = "Some Error Occurred. Please try again.";
    }

    public static final class USER_KEY_NAMES {

	public static final String UID = "uId";
	public static final String DISPLAY_NAME = "displayName";
	public static final String EMAIL = "email";
	public static final String PHOTO_URL = "photoUrl";
	public static final String PROVIDER = "provider";
    }

    public static final String GOOGLE_CLIENT_ID = "172709641516-rdv19n8cbqpb1u4p3d0tfhp3fqht9dbk.apps.googleusercontent.com";
    public static final String GOOGLE_ISSUER_1 = "accounts.google.com";
    public static final String GOOGLE_ISSUER_2 = "https://accounts.google.com";

}
