/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohakchavan.pustakniparab_web.Models.DashBoard;

/**
 *
 * @author Mohak Chavan
 */
public class DashBoard {

    private boolean isImage;
    private String imageHREF;
    private TopBottomData[] topBottomData;

    public DashBoard(boolean isImage, String imageHREF) throws Exception {
	if (!isImage) {
	    throw new Exception("The value for isImage should be true if there is an image link in imageHREF.");
	} else {
	    this.isImage = isImage;
	    this.imageHREF = imageHREF;
	}
    }

    public DashBoard(boolean isImage, TopBottomData[] topBottomData) throws Exception {
	if (isImage) {
	    throw new Exception("The value of isImage should be false, if topData and bottomData are needed.");
	} else if (topBottomData.length > 2) {
	    throw new Exception("The length of topBottomData cannot be greater than 2.");
	} else {
	    this.isImage = isImage;
	    this.topBottomData = topBottomData;
	}
    }

    public boolean isIsImage() {
	return isImage;
    }

    public void setIsImage(boolean isImage) {
	this.isImage = isImage;
    }

    public String getImageHREF() {
	return imageHREF;
    }

    public void setImageHREF(String imageHREF) {
	this.imageHREF = imageHREF;
    }

}
