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
import com.mohakchavan.pustakniparab_web.Models.Issues;
import com.mohakchavan.pustakniparab_web.StaticClasses.Constants;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mohak Chavan
 */
public class IssuesHelper {

    private final DatabaseReference issueReference;
    private ValueEventListener allIssuesListener;

    public IssuesHelper() {
	issueReference = new BaseHelper().getBaseReference().child(Constants.FIREBASE.DATABASE.ISSUES);
    }

    public void addNewIssue(final Issues newIssueDetails, final BaseHelper.onCompleteTransaction onCompleteTransaction) {
	issueReference.runTransaction(new Transaction.Handler() {
	    @Override
	    public Transaction.Result doTransaction(MutableData currentData) {
		if (currentData.getValue() != null) {
		    if (currentData.hasChild(Constants.FIREBASE.DATABASE.TOTAL_RECORDS)
			    && currentData.child(Constants.FIREBASE.DATABASE.TOTAL_RECORDS).getValue() != null) {
			long currentTotal = currentData.child(Constants.FIREBASE.DATABASE.TOTAL_RECORDS).getValue(Long.class);
			++currentTotal;
			if (!currentData.hasChild(String.valueOf(currentTotal))) {
			    newIssueDetails.setIssueNo(currentTotal);
//			    currentData.child(String.valueOf(currentTotal)).setValue(newIssueDetails);
//			    currentData.child(Constants.FIREBASE.DATABASE.TOTAL_RECORDS).setValue(currentTotal);
			    return Transaction.success(currentData);
			} else {
			    //Error for issue already present
			    return Transaction.abort();
			}
		    } else {
			//Error for some error. contact developer
			return Transaction.abort();
		    }
		} else {
		    //Display SOme Error
		    return Transaction.abort();
		}
	    }

	    @Override
	    public void onComplete(DatabaseError error, boolean committed, DataSnapshot currentData) {
		onCompleteTransaction.onComplete(committed, newIssueDetails);
	    }
	});
    }

    public void getAllIssuesOnce(BaseHelper.onCompleteRetrieval onCompleteRetrieval, BaseHelper.onFailure onFail) {
	setAllIssuesListener(onCompleteRetrieval, onFail);
	issueReference.orderByChild("issueNo").addListenerForSingleValueEvent(allIssuesListener);
    }

    private void setAllIssuesListener(final BaseHelper.onCompleteRetrieval onCompleteRetrieval, final BaseHelper.onFailure onFail) {
	allIssuesListener = new ValueEventListener() {
	    @Override
	    public void onDataChange(DataSnapshot snapshot) {
		List<Issues> issues = new ArrayList<>();
		for (DataSnapshot snap : snapshot.getChildren()) {
		    if (snap.getKey() != null && !snap.getKey().isEmpty() && !snap.getKey().contentEquals(Constants.FIREBASE.DATABASE.TOTAL_RECORDS)) {
			Issues issue = snap.getValue(Issues.class);
			issues.add(issue);
		    }
		}
		onCompleteRetrieval.onComplete(issues);
	    }

	    @Override
	    public void onCancelled(DatabaseError error) {
		onFail.onFail(error);
	    }
	};
    }

}
