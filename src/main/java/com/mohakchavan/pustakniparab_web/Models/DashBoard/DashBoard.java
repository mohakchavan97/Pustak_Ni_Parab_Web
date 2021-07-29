/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohakchavan.pustakniparab_web.Models.DashBoard;

import java.util.Arrays;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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

    public DashBoard(String dashDataString) throws ParseException, Exception {
	JSONObject json = (JSONObject) new JSONParser().parse(dashDataString);
	isImage = (boolean) json.get("isImage");
	if (isImage) {
	    imageHREF = (String) json.get("imageHREF");
	} else {
	    json = (JSONObject) new JSONParser().parse((String) json.get("topBottomData"));
	    if (json.size() > 2) {
		throw new Exception("The length of topBottomData cannot be greater than 2.");
	    } else {
		topBottomData = new TopBottomData[json.size()];
		topBottomData[0] = new TopBottomData(json.get("0").toString());
		topBottomData[1] = new TopBottomData(json.get("1").toString());
	    }
	}
    }

    public boolean isImage() {
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

    public List<TopBottomData> getTopBottomData() {
	return Arrays.asList(topBottomData);
    }

    @Override
    public String toString() {
	JSONObject toReturn = new JSONObject();
	toReturn.put("isImage", isImage);
	if (isImage) {
	    toReturn.put("imageHREF", imageHREF);
	} else {
	    JSONObject json = new JSONObject();
	    for (int i = 0; i < topBottomData.length; i++) {
		json.put(i, topBottomData[i].toString());
	    }
	    toReturn.put("topBottomData", json.toString());
	}
	return toReturn.toString();
    }

}
