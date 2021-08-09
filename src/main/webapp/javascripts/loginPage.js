/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function signInClicked() {
    document.getElementById("loader").style.display = "block";
}

function onSignIn(googleUser) {
    document.getElementById("idToken").value = googleUser.getAuthResponse().id_token;
    document.getElementById("accessToken").value = googleUser.getAuthResponse(true).access_token;

    // We need to register an Observer on Firebase Auth to make sure auth is initialized.
    var unsubscribe = firebase.auth().onAuthStateChanged((firebaseUser) => {

	// Check if we are already signed-in Firebase with the correct user.
	if (!isUserEqual(googleUser, firebaseUser)) {
	    // Build Firebase credential with the Google ID token.
	    var googleCredential = firebase.auth.GoogleAuthProvider.credential(
		    googleUser.getAuthResponse().id_token);

	    // Sign in with credential from the Google user.
	    firebase.auth().signInWithCredential(googleCredential).catch((error) => {
		// Handle Errors here.
		var errorCode = error.code;
		var errorMessage = error.message;
		// The email of the user's account used.
		var email = error.email;
		// The firebase.auth.AuthCredential type that was used.
		var credential = error.credential;
		// ...
	    });
	} else {
	    unsubscribe();
	    document.getElementById("firebaseAuthId").value = firebaseUser.uid;
//	firebase.auth().signOut().then(() => {
//	    console.log("Sign-out successful.");
//	}).catch((error) => {
//	    console.log(error);
//	});
	    googleUser.disconnect();
	    document.getElementById("userForm").submit();
	}
    });
}

function isUserEqual(googleUser, firebaseUser) {
    if (firebaseUser) {
	var providerData = firebaseUser.providerData;
	for (var i = 0; i < providerData.length; i++) {
	    if (providerData[i].providerId === firebase.auth.GoogleAuthProvider.PROVIDER_ID &&
		    providerData[i].uid === googleUser.getBasicProfile().getId()) {
		// We don't need to reauth the Firebase connection.
		return true;
	    }
	}
    }
    return false;
}

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
var firebaseConfig = {
    apiKey: "AIzaSyAW4PSGz4Slo2ihuYsn5t7Rselk0631pic",
    authDomain: "pustak-ni-parab.firebaseapp.com",
    databaseURL: "https://pustak-ni-parab.firebaseio.com",
    projectId: "pustak-ni-parab",
    storageBucket: "pustak-ni-parab.appspot.com",
    messagingSenderId: "172709641516",
    appId: "1:172709641516:web:e0b082c93bf2cd8d9ec089",
    measurementId: "G-MF9K8JKM3V"
};

function loaded() {
    // Initialize Firebase
    firebase.initializeApp(firebaseConfig);
    document.getElementById("loader").style.display = "none";
}


