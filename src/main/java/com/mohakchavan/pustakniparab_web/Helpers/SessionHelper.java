/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohakchavan.pustakniparab_web.Helpers;

import com.mohakchavan.pustakniparab_web.Models.SessionUser;
import com.mohakchavan.pustakniparab_web.StaticClasses.Constants;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mohak Chavan
 */
public class SessionHelper {

    private HttpSession session;

    public SessionHelper(HttpServletRequest request) {
	session = request.getSession(false);
    }

    public Map checkSessionAndGetCurrentUser() {
	Map userData = new HashMap();
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

    public SessionUser getSessionUser() {
	SessionUser user = null;
	if (session != null) {
	    user = (SessionUser) session.getAttribute(Constants.ATTRIBUTE_KEY_NAMES.USER_SESSION_DATA);
	}
	return user;
    }

    public boolean isDeveloperMode() {
	boolean isDeveloperMode = false;
	if (session != null && session.getAttribute(Constants.ATTRIBUTE_KEY_NAMES.IS_DEVELOPER_MODE) != null) {
	    isDeveloperMode = (boolean) session.getAttribute(Constants.ATTRIBUTE_KEY_NAMES.IS_DEVELOPER_MODE);
	}
	return isDeveloperMode;
    }

    public void setDeveloperMode(boolean developerMode) {
	if (session != null) {
	    session.setAttribute(Constants.ATTRIBUTE_KEY_NAMES.IS_DEVELOPER_MODE, developerMode);
	}
    }
}
