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
public class VerifiedUsers {

    private String userEmail, userName, userPhoto, userUid;

    public VerifiedUsers() {
    }

    public VerifiedUsers(String userEmail, String userName, String userPhoto, String userUid) {
	this.userEmail = userEmail;
	this.userName = userName;
	this.userPhoto = userPhoto;
	this.userUid = userUid;
    }

    public String getUserEmail() {
	return userEmail;
    }

    public void setUserEmail(String userEmail) {
	this.userEmail = userEmail;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getUserPhoto() {
	return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
	this.userPhoto = userPhoto;
    }

    public String getUserUid() {
	return userUid;
    }

    public void setUserUid(String userUid) {
	this.userUid = userUid;
    }

}
