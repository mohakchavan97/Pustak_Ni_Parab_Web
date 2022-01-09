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
public class SessionUser {

    private String uId, displayName, email, photoUrl, provider, authUID;
    private boolean isDeveloper;

    public SessionUser(String userId, String displayName, String userEmail, String photoUrl, String provider) {
	this.uId = userId;
	this.displayName = displayName;
	this.email = userEmail;
	this.photoUrl = photoUrl;
	this.provider = provider;
	this.authUID = null;
	this.isDeveloper = false;
    }

    public String getuId() {
	return uId;
    }

    public String getDisplayName() {
	return displayName;
    }

    public String getEmail() {
	return email;
    }

    public String getPhotoUrl() {
	return photoUrl;
    }

    public String getProvider() {
	return provider;
    }

    public String getAuthUID() {
	return authUID;
    }

    public void setAuthUID(String authUID) {
	this.authUID = authUID;
    }

    public boolean isDeveloper() {
	return isDeveloper;
    }

    public void setIsDeveloper(boolean isDeveloper) {
	this.isDeveloper = isDeveloper;
    }
}
