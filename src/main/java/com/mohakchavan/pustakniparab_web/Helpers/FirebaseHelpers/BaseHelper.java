/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohakchavan.pustakniparab_web.Helpers.FirebaseHelpers;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mohakchavan.pustakniparab_web.Models.BaseData;
import com.mohakchavan.pustakniparab_web.Models.Issues;
import com.mohakchavan.pustakniparab_web.Models.Names;
import com.mohakchavan.pustakniparab_web.Models.NewBooks;
import com.mohakchavan.pustakniparab_web.Models.VerifiedUsers;
import com.mohakchavan.pustakniparab_web.StaticClasses.Constants;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Mohak Chavan
 */
public class BaseHelper {

    private final FirebaseDatabase firebaseDatabase;
    private DatabaseReference rootReference;
    private DatabaseReference baseReference;

    public BaseHelper() {
	firebaseDatabase = FirebaseDatabase.getInstance(BaseAuthenticator.getFirebaseApp());
	setRootReference();
	setBaseReference();
    }

    private DatabaseReference getRootReference() {
	return rootReference;
    }

    private void setRootReference() {
	rootReference = firebaseDatabase.getReference("/");
    }

    public DatabaseReference getBaseReference() {
	return baseReference;
    }

    private void setBaseReference() {
//	baseReference = getRootReference().child(Constants.FIREBASE.DATABASE.BASEPOINT);
	baseReference = getRootReference().child(Constants.FIREBASE.DATABASE.TESTDATA);
    }

    public void getAllVerifiedUsers(final onCompleteRetrieval onCompleteRetrieval, final onFailure onFailure) {
	rootReference.child(Constants.FIREBASE.DATABASE.VERIFIED_USERS)
		.addListenerForSingleValueEvent(new ValueEventListener() {
		    @Override
		    public void onDataChange(DataSnapshot snapshot) {
			if (snapshot.exists() && snapshot.hasChildren()) {
			    List<VerifiedUsers> verifiedUsers = new ArrayList<>();
			    for (DataSnapshot snap : snapshot.getChildren()) {
				if (snap.getKey() != null && !snap.getKey().isEmpty() && !snap.hasChild(Constants.FIREBASE.DATABASE.ACCOUNT_TYPE)) {
				    VerifiedUsers user = snap.getValue(VerifiedUsers.class);
				    verifiedUsers.add(user);
				}
			    }
			    onCompleteRetrieval.onComplete(verifiedUsers);
			} else {
			    onFailure.onFail(Constants.ERRORS.SOME_ERROR_FULL);
			}
		    }

		    @Override
		    public void onCancelled(DatabaseError error) {
			onFailure.onFail(error);
		    }
		});
    }

    public void getAllBaseData(final onCompleteRetrieval onCompleteRetrieval, final onFailure onFailure) {
	baseReference.addListenerForSingleValueEvent(new ValueEventListener() {
	    @Override
	    public void onDataChange(DataSnapshot snapshot) {
		BaseData baseData = new BaseData();
		for (DataSnapshot s : snapshot.getChildren()) {
		    switch (s.getKey()) {
			case Constants.FIREBASE.DATABASE.ISSUES:
			    for (DataSnapshot snap : s.getChildren()) {
				if (snap.getKey() != null && !snap.getKey().isEmpty() && !snap.getKey().contentEquals(Constants.FIREBASE.DATABASE.TOTAL_RECORDS)) {
				    Issues issue = snap.getValue(Issues.class);
				    baseData.addIssue(issue);
				}
			    }
			    break;
			case Constants.FIREBASE.DATABASE.NAMES:
			    for (DataSnapshot snap : s.getChildren()) {
				if (snap.getKey() != null && !snap.getKey().isEmpty() && !snap.getKey().contentEquals(Constants.FIREBASE.DATABASE.TOTAL_NAMES)) {
				    Names name = snap.getValue(Names.class);
				    baseData.addName(name);
				}
			    }
			    break;
			case Constants.FIREBASE.DATABASE.NEW_BOOKS:
			    for (DataSnapshot snap : s.getChildren()) {
				if (snap.getKey() != null && !snap.getKey().isEmpty() && !snap.getKey().contentEquals(Constants.FIREBASE.DATABASE.TOTAL_NEW_BOOKS)) {
				    NewBooks book = snap.getValue(NewBooks.class);
				    baseData.addBook(book);
				}
			    }
			    break;
		    }
		}
		onCompleteRetrieval.onComplete(baseData);
	    }

	    @Override
	    public void onCancelled(DatabaseError error) {
		onFailure.onFail(error);
	    }
	});
    }

    public void updateDataChangedTimeStampAsync() {
	baseReference.child(Constants.FIREBASE.DATABASE.TIMESTAMPS)
		.child(Constants.FIREBASE.DATABASE.DATA_CHANGED_TIMESTAMP)
		.setValueAsync(new Date().getTime());
    }

    public void updateImagesCreatedTimeStampAsync() {
	baseReference.child(Constants.FIREBASE.DATABASE.TIMESTAMPS)
		.child(Constants.FIREBASE.DATABASE.IMAGES_CREATED_TIMESTAMP)
		.setValueAsync(new Date().getTime());
    }

    public interface onCompleteTransaction {

	void onComplete(boolean committed, Object data);
    }

    public interface onCompleteRetrieval {

	void onComplete(Object data);
    }

    public interface onFailure {

	void onFail(Object data);
    }

    public interface onDeletion {

	void onDelete(boolean deleted);
    }

}
