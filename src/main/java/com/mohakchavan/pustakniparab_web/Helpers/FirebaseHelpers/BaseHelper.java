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
import com.mohakchavan.pustakniparab_web.Models.VerifiedUsers;
import com.mohakchavan.pustakniparab_web.StaticClasses.Constants;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mohak Chavan
 */
public class BaseHelper {

    private final FirebaseDatabase firebaseDatabase;
    private DatabaseReference rootReference;

    public BaseHelper() {
	firebaseDatabase = FirebaseDatabase.getInstance(BaseAuthenticator.getFirebaseApp());
	setRootReference();
    }

    public DatabaseReference getRootReference() {
	return rootReference;
    }

    private void setRootReference() {
	rootReference = firebaseDatabase.getReference("/");
    }

    public void getAllVerifiedUsers(final onCompleteRetrieval onCompleteRetrieval, final onFailure onFailure) {
	rootReference.child(Constants.FIREBASE.DATABASE.VERIFIED_USERS)
		.addListenerForSingleValueEvent(new ValueEventListener() {
		    @Override
		    public void onDataChange(DataSnapshot snapshot) {
			if (snapshot.exists() && snapshot.hasChildren()) {
			    List<VerifiedUsers> verifiedUsers = new ArrayList<VerifiedUsers>();
			    for (DataSnapshot snap : snapshot.getChildren()) {
				if (snap.getKey() != null && !snap.getKey().isEmpty() && !snap.hasChild("accountType")) {
				    VerifiedUsers user = snap.getValue(VerifiedUsers.class);
				    verifiedUsers.add(user);
				}
			    }
			    onCompleteRetrieval.onComplete(verifiedUsers);
			} else {
			    onFailure.onFail(Constants.ERRORS.SOME_ERROR);
			}
		    }

		    @Override
		    public void onCancelled(DatabaseError error) {
			onFailure.onFail(error);
		    }
		});
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
