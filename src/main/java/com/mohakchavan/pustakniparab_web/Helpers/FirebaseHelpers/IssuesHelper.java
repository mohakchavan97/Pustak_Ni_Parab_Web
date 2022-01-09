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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Mohak Chavan
 */
public class IssuesHelper {

    private final DatabaseReference issueReference;
    private ValueEventListener allIssuesListener;
    private final BaseHelper baseHelper;

    public IssuesHelper(boolean developerMode) {
	baseHelper = new BaseHelper(developerMode);
	issueReference = baseHelper.getBaseReference().child(Constants.FIREBASE.DATABASE.ISSUES);
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
			    baseHelper.updateDataChangedTimeStampAsync();
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

    public void addReturnIssues(final JSONArray returningIssues, final String returnDate,
	    final BaseHelper.onCompleteTransaction onCompleteTransaction) {
	issueReference.runTransaction(new Transaction.Handler() {
	    @Override
	    public Transaction.Result doTransaction(MutableData currentData) {
		if (currentData.getValue() != null) {
		    for (Object issue : returningIssues) {
			JSONObject issueToReturn = (JSONObject) issue;
			if (currentData.hasChild(issueToReturn.get(Constants.IDS.ISSUE_ID).toString())
				&& currentData.child(issueToReturn.get(Constants.IDS.ISSUE_ID).toString()).getValue() != null) {
			    Issues curIssue = currentData.child(issueToReturn.get(Constants.IDS.ISSUE_ID).toString()).getValue(Issues.class);
			    if (curIssue.getIssuerId().contentEquals(issueToReturn.get(Constants.IDS.NAME_ID).toString())
				    && curIssue.getBookName().contentEquals(issueToReturn.get(Constants.IDS.BOOK_NAME).toString())
				    && curIssue.getIssueDate().contentEquals(issueToReturn.get(Constants.IDS.ISSUE_DATE).toString())) {
				curIssue.setIsReturned(Constants.YES);
				curIssue.setRetDate(returnDate);
//				currentData.child(issueToReturn.get(Constants.IDS.ISSUE_ID).toString()).setValue(curIssue);
			    } else {
				//Some details of issue are wrong. Contact developer
				return Transaction.abort();
			    }
			} else {
			    //Some error. try again or contact developer
			    return Transaction.abort();
			}
		    }
		    baseHelper.updateDataChangedTimeStampAsync();
		    return Transaction.success(currentData);
		} else {
		    //No data in cloud
		    return Transaction.abort();
		}
	    }

	    @Override
	    public void onComplete(DatabaseError error, boolean committed, DataSnapshot currentData) {
		onCompleteTransaction.onComplete(committed, currentData);
	    }
	});
    }

}
