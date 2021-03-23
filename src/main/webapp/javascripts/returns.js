/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var isSet = false;
var onlyDigit = /^[0-9]+$/;

var issue_id, name_id, book_name, person_name, returnForm, jIssuesList, filterKey, filterValue;

function assignVar() {
    issue_id = document.getElementById("issue_id");
    name_id = document.getElementById("sel_name");
    book_name = document.getElementById("book_name");
    person_name = document.getElementById("issuer_name");
    returnForm = document.getElementById("hidData");
}

function setIssues(issuesString) {
    jIssuesList = JSON.parse(JSON.stringify(issuesString));
    assignVar();
}

function enableAllFields() {
    issue_id.removeAttribute("disabled");
    name_id.removeAttribute("disabled");
    book_name.removeAttribute("disabled");
    person_name.removeAttribute("disabled");
}

function disableSelName() {
    name_id.value = "0_0";
    name_id.disabled = "true";
}

function checkWholeValueForIssID(extra) {

    var val;
    if (isNaN(extra) || extra === undefined) {
	val = issue_id.value;
    } else {
	val = issue_id.value + extra;
    }
    if (onlyDigit.test(val)) {
	if (!isSet) {
	    setTimeout(callIssueAPI, 2000);
	    console.log("timeout set.");
	    isSet = true;
	}
    } else {
	var sliced = val.slice(0, -1);
	issue_id.value = sliced;
    }

    if (issue_id.value.toString().trim().length) {
	book_name.disabled = "true";
	disableSelName();
	person_name.disabled = "true";
    } else {
	enableAllFields();
    }
}

function callIssueAPI() {
    console.log("timeout called.");
    var valToCheck = issue_id.value;
    if (onlyDigit.test(valToCheck)) {
//	    document.getElementById("temp2").value = document.getElementById("temp").value;
	//call api
	filterKey = "issue_id";
	filterValue = valToCheck;
	callAPI();
    } else {
	isSet = false;
    }
}

function callPersonAPI(personName) {
    if (person_name.value.toString().trim().length) {
	book_name.disabled = "true";
	disableSelName();
	issue_id.disabled = "true";
    } else {
	enableAllFields();
    }
}

function callBookAPI(bookName) {
    if (book_name.value.toString().trim().length) {
	issue_id.disabled = "true";
	disableSelName();
	person_name.disabled = "true";
    } else {
	enableAllFields();
    }
}

function callNameIdAPI() {
    /*if (name_id.options[name_id.selectedIndex].value.toString() === "0_0") {
     enableAllFields();
     } else {
     book_name.disabled = "true";
     issue_id.disabled = "true";
     person_name.disabled = "true";
     }*/
}

function callAPI() {
    //call api
    console.log("API Called");
    var i;
    isSet = false;
    for (i in jIssuesList) {
	if (jIssuesList[i][filterKey].includes(filterValue)) {
	    console.log(jIssuesList[i]);
	}
    }
}

function checkPressedCharForIssID(e) {
    checkWholeValueForIssID(getStringFromEvent(e));
}

function getStringFromEvent(e) {
    var codedKey;
    if (window.event) {
	codedKey = e.keyCode;
    } else if (e.which) {
	codedKey = e.which;
    }
    return String.fromCharCode(codedKey);
}