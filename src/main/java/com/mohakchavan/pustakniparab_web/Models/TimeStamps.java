/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohakchavan.pustakniparab_web.Models;

import java.util.Date;

/**
 *
 * @author Mohak Chavan
 */
public class TimeStamps {

    private long imagesCreatedTimeStamp;
    private long dataChangedTimeStamp;

    public TimeStamps() {
    }

    public TimeStamps(long imagesCreatedTimeStamp, long dataChangedTimeStamp) {
	this.imagesCreatedTimeStamp = imagesCreatedTimeStamp;
	this.dataChangedTimeStamp = dataChangedTimeStamp;
    }

    public long getImagesCreatedTimeStamp() {
	return imagesCreatedTimeStamp;
    }

    public void setImagesCreatedTimeStamp(long imagesCreatedTimeStamp) {
	this.imagesCreatedTimeStamp = imagesCreatedTimeStamp;
    }

    public long getDataChangedTimeStamp() {
	return dataChangedTimeStamp;
    }

    public void setDataChangedTimeStamp(long dataChangedTimeStamp) {
	this.dataChangedTimeStamp = dataChangedTimeStamp;
    }

    public boolean areImagesUpdated() {
	return new Date(imagesCreatedTimeStamp)
		.compareTo(new Date(dataChangedTimeStamp)) > 0;
    }

}
