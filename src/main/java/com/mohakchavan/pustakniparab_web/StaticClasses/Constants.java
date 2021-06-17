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
	public static final String DASHBOARD_DATA_PATH = "/restrictedContent/dashboardData";

	public static final class JSP {

	    public static final String LOGIN = "login";
	    public static final String ADD_NAME = "addName";
	    public static final String NEW_BOOKS = "newBooks";
//	    public static final String  = "";
	}

	public static final class SERVLET {

	    public static final String HOME = "Home";
	    public static final String ADD_ISSUE = "AddIssue";
	    public static final String RETURN_ISSUE = "ReturnIssue";
	    public static final String ALL_NAMES = "AllNames";
	    public static final String LOGOUT = "Logout";
//	    public static final String  = "";
	}
    }

    public static final class FIREBASE {

	public static final class DATABASE {

	    public static final String URL = "https://pustak-ni-parab.firebaseio.com";
	    public static final String VERIFIED_USERS = "VerifiedUsers";
	    public static final String BASEPOINT = "BasePoint";
	    public static final String TESTDATA = "TestData";
	    public static final String ISSUES = "Issues";
	    public static final String NAMES = "Names";
	    public static final String NEW_BOOKS = "NewBooks";
	    public static final String TOTAL_RECORDS = "currentTotalIssuesRecorded";
	    public static final String TOTAL_NAMES = "currentTotalNamesRecorded";
	    public static final String TOTAL_NEW_BOOKS = "currentTotalNewBooksRecorded";
	    public static final String ACCOUNT_TYPE = "accountType";
	    public static final String TIMESTAMPS = "TimeStamps";
	    public static final String IMAGES_CREATED_TIMESTAMP = "imagesCreatedTimeStamp";
	    public static final String DATA_CHANGED_TIMESTAMP = "dataChangedTimeStamp";
	}

	public static final class AUTHORIZATION {

	    public static final String UID = "uid";
	}
    }

    public static final class ATTRIBUTE_KEY_NAMES {

	public static final String USER_SESSION_DATA = "userDataStoredInSession";
	public static final String IS_CURRENT_USER_VERIFIED = "isCurrentUserVerified";
	public static final String HAS_ERROR_WITH_DATA = "hasErrorWithData";
	public static final String HAS_TRNSCTN_ERROR_WITH_DATA = "hasTrnsctnErrorWithData";
	public static final String ALL_NAMES_FOR_HTML = "allNamesHTML";
	public static final String ALL_ISSUES_FOR_HTML = "allIssuesHTML";
	public static final String IS_REQUEST_FOR_ADD_ISSUE = "isForAdd";
	public static final String IS_REQUEST_TO_RETURN_ISSUE = "isForReturn";
	public static final String IS_TRANSACTION_SUCCESS = "isSuccessfull";

	public static final String IS_SESSION_VALID = "isSessionValidWithUserData";
	public static final String CURRENT_USER = "currentUser";
    }

    public static final class ERRORS {

	public static final String SOME_ERROR = "Some Error Occurred.";
	public static final String TRY_AGAIN = "Please try again.";
	public static final String NO_ISSUES = "No Issues Found.";
	public static final String NO_NAMES = "No Names Found.";
	public static final String ISSUE_NOT_ADDED = "Issue not added." + SPACE + Constants.ERRORS.SOME_ERROR_FULL;
	public static final String ISSUE_NOT_RETURNED = "Issues are not returned." + SPACE + Constants.ERRORS.SOME_ERROR_FULL;
	public static final String NO_NAME_ID = "The selected ID of the name does not exists. " + TRY_AGAIN;
	public static final String SOME_ERROR_FULL = SOME_ERROR + SPACE + TRY_AGAIN;
	public static final String NAME_NOT_ADDED = "The details of the person were not added." + SPACE + Constants.ERRORS.SOME_ERROR_FULL;
	public static final String BOOKS_NOT_ADDED = "The details of the new books were not added." + SPACE + Constants.ERRORS.SOME_ERROR_FULL;
    }

    public static final class IDS {

	public static final String BOOK_NAME = "book_name";
	public static final String PRICE = "price";
	public static final String AUTHOR_PUBLISHER = "author_publisher";
	public static final String SEL_NAME = "sel_name";
	public static final String ISSUER_CONTACT = "issuer_contact";
	public static final String ISSUER_NAME = "issuer_name";
	public static final String ISSUER_ADDRESS = "issuer_address";
	public static final String ISSUE_DATE = "issue_date";
	public static final String RETURN_DATE = "return_date";
	public static final String ISSUE_ID = "issue_id";
	public static final String NAME_ID = "name_id";
	public static final String NEW_BOOK_ID = "new_book_id";
	public static final String RESULT_TYPE = "result_type";
	public static final String RETURNED_ISSUES = "returned_issues";
	public static final String ISSUER_FNAME = "issuer_first_name";
	public static final String ISSUER_LNAME = "issuer_last_name";
	public static final String BLK_FLT = "issuer_block_flat";
	public static final String STRT_NAME = "issuer_street_name";
	public static final String LOCAL_AREA = "issuer_locality_area";
	public static final String NEW_BOOK_DATE = "new_book_date";
	public static final String DONOR_NAME = "donor_name";
	public static final String TOTAL_BOOKS = "total_books";
	public static final String BOOKS_LANGUAGE = "books_language";
	public static final String SEL_LANG = "sel_lang";
//	public static final String  = "";

    }

    public static enum VALUES {
	issue, returns, name, newBooks;

//	public static final String ISSUE = "issue";
//	public static final String RETURNS = "returns";
//	public static final String NAME = "name";	
//	public static final String  = "";
    }

    public static final class USER_KEY_NAMES {

	public static final String UID = "uId";
	public static final String DISPLAY_NAME = "displayName";
	public static final String EMAIL = "email";
	public static final String PHOTO_URL = "photoUrl";
	public static final String PROVIDER = "provider";
    }

    public static final class ARRAYS {

	public static final String MONTHS[] = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
    }

    public static final String TIME_ZONE = "Asia/Kolkata";
    public static final String INDIAN_STANDARD_TIME = "IST";
    public static final String DATE_FORMAT = "dd MMM yyyy";
    public static final String YES = "YES";
    public static final String NO = "NO";
    public static final String GOOGLE_CLIENT_ID = "172709641516-rdv19n8cbqpb1u4p3d0tfhp3fqht9dbk.apps.googleusercontent.com";
    public static final String GOOGLE_ISSUER_1 = "accounts.google.com";
    public static final String GOOGLE_ISSUER_2 = "https://accounts.google.com";

    public static final String SPACE = " ";

    public static final long BASEDATA_PERIOD = 30000; // in milliseconds

}
