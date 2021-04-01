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
import com.google.firebase.database.ValueEventListener;
import com.mohakchavan.pustakniparab_web.Models.Names;
import com.mohakchavan.pustakniparab_web.StaticClasses.Constants;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mohak Chavan
 */
public class NamesHelper {

    private final DatabaseReference namesReference;
    private ValueEventListener allNamesListener;

    public NamesHelper() {
	namesReference = new BaseHelper().getBaseReference().child(Constants.FIREBASE.DATABASE.NAMES);
    }

    private void setAllNamesListener(final BaseHelper.onCompleteRetrieval onCompleteRetrieval, final BaseHelper.onFailure onFail) {
	allNamesListener = new ValueEventListener() {
	    @Override
	    public void onDataChange(DataSnapshot snapshot) {
		List<Names> namesList = new ArrayList<>();
		for (DataSnapshot snap : snapshot.getChildren()) {
		    if (snap.getKey() != null && !snap.getKey().isEmpty() && !snap.getKey().contentEquals(Constants.FIREBASE.DATABASE.TOTAL_NAMES)) {
			Names name = snap.getValue(Names.class);
			namesList.add(name);
		    }
		}
		onCompleteRetrieval.onComplete(namesList);
	    }

	    @Override
	    public void onCancelled(DatabaseError error) {
		onFail.onFail(error);
	    }
	};
    }

    public void getAllNamesOnce(final BaseHelper.onCompleteRetrieval onCompleteRetrieval, final BaseHelper.onFailure onFail) {
	setAllNamesListener(onCompleteRetrieval, onFail);
	namesReference.orderByChild("ser_no").addListenerForSingleValueEvent(allNamesListener);
    }

    public void getNameDetails(String nameId, final BaseHelper.onCompleteRetrieval onCompleteRetrieval, final BaseHelper.onFailure onFail) {
	namesReference.child(nameId).addListenerForSingleValueEvent(new ValueEventListener() {
	    @Override
	    public void onDataChange(DataSnapshot snapshot) {
		if (snapshot.exists()) {
		    Names name = snapshot.getValue(Names.class);
		    onCompleteRetrieval.onComplete(name);
		} else {
		    //nameid does not exists
		    onFail.onFail(Constants.ERRORS.NO_NAME_ID);
		}
	    }

	    @Override
	    public void onCancelled(DatabaseError error) {
		onFail.onFail(error);
	    }
	});
    }

    public void addNewName(final Names newPersonDetails, final BaseHelper.onCompleteTransaction onCompleteTransaction) {
	namesReference.runTransaction(new Transaction.Handler() {
	    @Override
	    public Transaction.Result doTransaction(MutableData currentData) {
		if (currentData.getValue() != null) {
		    if (currentData.hasChild(Constants.FIREBASE.DATABASE.TOTAL_NAMES) && currentData.child(Constants.FIREBASE.DATABASE.TOTAL_NAMES).getValue() != null) {
			long currentTotalNames = currentData.child(Constants.FIREBASE.DATABASE.TOTAL_NAMES).getValue(Long.class);
			++currentTotalNames;
			if (!currentData.hasChild(String.valueOf(currentTotalNames))) {
			    newPersonDetails.setSer_no(currentTotalNames);
			    currentData.child(String.valueOf(currentTotalNames)).setValue(newPersonDetails);
			    currentData.child(Constants.FIREBASE.DATABASE.TOTAL_NAMES).setValue(currentTotalNames);
			    return Transaction.success(currentData);
			} else {
			    //Error: User id is already present. Contact developer
			    return Transaction.abort();
			}
		    } else {
			//Some error occured. Contact developer
			return Transaction.abort();
		    }
		} else {
		    //Some error occured. please try again
		    return Transaction.abort();
		}
	    }

	    @Override
	    public void onComplete(DatabaseError error, boolean committed, DataSnapshot currentData) {
		onCompleteTransaction.onComplete(committed, newPersonDetails);
	    }
	});
    }

}
