/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohakchavan.pustakniparab_web.Helpers.FirebaseHelpers;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.mohakchavan.pustakniparab_web.Models.NewBooks;
import com.mohakchavan.pustakniparab_web.StaticClasses.Constants;

/**
 *
 * @author Mohak Chavan
 */
public class NewBooksHelper {

    private final DatabaseReference newBooksReference;
    private final BaseHelper baseHelper;

    public NewBooksHelper(boolean developerMode) {
	baseHelper = new BaseHelper(developerMode);
	newBooksReference = baseHelper.getBaseReference().child(Constants.FIREBASE.DATABASE.NEW_BOOKS);
    }

    public void addNewRecord(final NewBooks newBooks, final BaseHelper.onCompleteTransaction onCompleteTransaction) {
	newBooksReference.runTransaction(new Transaction.Handler() {
	    @Override
	    public Transaction.Result doTransaction(MutableData currentData) {
		if (currentData.getValue() != null) {
		    if (currentData.hasChild(Constants.FIREBASE.DATABASE.TOTAL_NEW_BOOKS) && currentData.child(Constants.FIREBASE.DATABASE.TOTAL_NEW_BOOKS).getValue() != null) {
			long currentTotal = currentData.child(Constants.FIREBASE.DATABASE.TOTAL_NEW_BOOKS).getValue(Long.class);
			++currentTotal;
			if (!currentData.hasChild(String.valueOf(currentTotal))) {
			    newBooks.setNewBookId(currentTotal);
//                                currentData.child(String.valueOf(currentTotal)).setValue(newBooks);
//                                currentData.child(Constants.FIREBASE.DATABASE.TOTAL_NEW_BOOKS).setValue(currentTotal);
			    baseHelper.updateDataChangedTimeStampAsync();
			    return Transaction.success(currentData);
			} else {
			    // Error "The record is already present. Please try again."
			    return Transaction.abort();
			}
		    } else {
			// Error "Some Error Occurred. Please Contact Developer."
			return Transaction.abort();
		    }
		} else {
		    // Error "Some Error Occurred. Please try again."
		    return Transaction.abort();
		}
	    }

	    @Override
	    public void onComplete(DatabaseError error, boolean committed, DataSnapshot currentData) {
		onCompleteTransaction.onComplete(committed, newBooks);
	    }
	});
    }

}
