/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohakchavan.pustakniparab_web.StaticClasses;

import com.mohakchavan.pustakniparab_web.Models.SessionUser;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mohak Chavan
 */
public class SessionHelper {

    public SessionHelper() {
    }

    public static Map checkSessionAndGetCurrentUser(HttpServletRequest request) {
	Map userData = new HashMap();
	HttpSession session = request.getSession(false);
	if (session == null) {
	    userData.put(Constants.ATTRIBUTE_KEY_NAMES.IS_SESSION_VALID, Boolean.FALSE);
	} else {
	    SessionUser user = (SessionUser) session.getAttribute(Constants.ATTRIBUTE_KEY_NAMES.USER_SESSION_DATA);
	    if (user == null) {
		userData.put(Constants.ATTRIBUTE_KEY_NAMES.IS_SESSION_VALID, Boolean.FALSE);
	    } else {
		userData.put(Constants.ATTRIBUTE_KEY_NAMES.IS_SESSION_VALID, Boolean.TRUE);
		userData.put(Constants.ATTRIBUTE_KEY_NAMES.CURRENT_USER, user);
	    }
	}
	return userData;
    }
}
