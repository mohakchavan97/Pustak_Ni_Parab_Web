/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohakchavan.pustakniparab_web.Models.DashBoard;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Mohak Chavan
 */
public class TopBottomData {

    private long topData;
    private String bottomData;

    public TopBottomData(long topData, String bottomData) {
	this.topData = topData;
	this.bottomData = bottomData;
    }

    public TopBottomData(String topBottomDataString) throws ParseException {
	JSONObject json = (JSONObject) new JSONParser().parse(topBottomDataString);
	topData = (long) json.get("topData");
	bottomData = (String) json.get("bottomData");
    }

    public long getTopData() {
	return topData;
    }

    public void setTopData(long topData) {
	this.topData = topData;
    }

    public String getBottomData() {
	return bottomData;
    }

    public void setBottomData(String bottomData) {
	this.bottomData = bottomData;
    }

    @Override
    public String toString() {
	JSONObject json = new JSONObject();
	json.put("topData", topData);
	json.put("bottomData", bottomData);
	return json.toString();
    }

}
