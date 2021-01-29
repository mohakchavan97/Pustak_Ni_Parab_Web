/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohakchavan.pustakniparab_web.Models;

/**
 *
 * @author Mohak Chavan
 */
public class CurrentUser {

    private static User currentUser;

    private CurrentUser() {
	if (currentUser != null) {
	    throw new RuntimeException("Use getCurrentUser() method to get the single instance of this class.");
	}
    }

    public static void setCurrentUser(String userId, String displayName, String userEmail, String photoUrl, String provider) {
	currentUser = getCurrentUser();
	currentUser.setUser(userId, displayName, userEmail, photoUrl, provider);
    }

    public static User getCurrentUser() {
	if (currentUser == null) {
	    currentUser = new User();
	}
	return currentUser;
    }

    public static String getuId() {
	return getCurrentUser().getuId();
    }

    public static String getDisplayName() {
	return getCurrentUser().getDisplayName();
    }

    public static String getEmail() {
	return getCurrentUser().getEmail();
    }

    public static String getPhotoUrl() {
	return getCurrentUser().getPhotoUrl();
    }

    public static String getProvider() {
	return getCurrentUser().getProvider();
    }

    private static class User {

	String uId, displayName, email, photoUrl, provider;

	private User() {
	}

	private void setUser(String uId, String displayName, String email, String photoUrl, String provider) {
	    this.uId = uId;
	    this.displayName = displayName;
	    this.email = email;
	    this.photoUrl = photoUrl;
	    this.provider = provider;
	}

	private String getuId() {
	    return uId;
	}

	private String getDisplayName() {
	    return displayName;
	}

	private String getEmail() {
	    return email;
	}

	private String getPhotoUrl() {
	    return photoUrl;
	}

	private String getProvider() {
	    return provider;
	}

    }

}
