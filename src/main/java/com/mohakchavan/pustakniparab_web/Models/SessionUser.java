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

    private final String uId, displayName, email, photoUrl, provider;

    public SessionUser(String userId, String displayName, String userEmail, String photoUrl, String provider) {
	this.uId = userId;
	this.displayName = displayName;
	this.email = userEmail;
	this.photoUrl = photoUrl;
	this.provider = provider;
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
}
