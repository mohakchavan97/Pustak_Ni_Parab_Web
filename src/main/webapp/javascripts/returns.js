/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * The logic for applying the filters in this file is concpetualized according to the procedure on which api calls to server can be given. As of now, the filters are applied at client side. But if in future, the filters needs to be applied at server side, then only api calls should only be given in specific function and the logic of calling the functions should not be changed.
 */


var isSet = false, onlyDigit = /^[0-9]*$/, timeOut = 2000, totalCheckedIssues = 0;

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
	    setTimeout(callIssueAPI, timeOut);
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
    var valToCheck = issue_id.value;
    if (onlyDigit.test(valToCheck)) {
//	    document.getElementById("temp2").value = document.getElementById("temp").value;
	//call api
	filterKey = "issue_id";
	filterValue = valToCheck;
	applyFilters();
    } else {
	isSet = false;
    }
}

function personInputChanged(personName) {
    if (person_name.value.toString().trim().length) {
	book_name.disabled = "true";
	disableSelName();
	issue_id.disabled = "true";

	if (!isSet) {
	    setTimeout(callPersonAPI, timeOut);
	    isSet = true;
	}
    } else {
	enableAllFields();
    }
}

function callPersonAPI() {
    var valToCheck = person_name.value;
    filterKey = "person_name";
    filterValue = valToCheck.toString().trim().toUpperCase();
    applyFilters();
}

function bookInputChanged(bookName) {
    if (book_name.value.toString().trim().length) {
	issue_id.disabled = "true";
	disableSelName();
	person_name.disabled = "true";

	if (!isSet) {
	    setTimeout(callBookAPI, timeOut);
	    isSet = true;
	}
    } else {
	enableAllFields();
    }
}

function callBookAPI() {
    var valToCheck = book_name.value;
    filterKey = "book_name";
    filterValue = valToCheck.toString().trim().toUpperCase();
    applyFilters();
}

function nameIdChanged() {
    /*if (name_id.options[name_id.selectedIndex].value.toString() === "0_0") {
     enableAllFields();
     } else {
     book_name.disabled = "true";
     issue_id.disabled = "true";
     person_name.disabled = "true";
     }*/

    if (!isSet) {
	setTimeout(callNameIdAPI, timeOut);
	isSet = true;
    }
}

function callNameIdAPI() {
    var valToCheck = name_id.options[name_id.selectedIndex].value.toString();
    filterKey = "book_name";
    if (valToCheck === "0_0") {
	filterValue = "";
    } else {
	filterValue = valToCheck;
    }
    applyFilters();
}

function applyFilters() {
    //call api
    var i, noOfIssues = 0;
    isSet = false;
    for (i in jIssuesList) {
	if (jIssuesList[i][filterKey].includes(filterValue)) {
	    document.getElementById("issueCard_" + jIssuesList[i]["issue_id"]).hidden = false;
	    ++noOfIssues;
	} else {
	    document.getElementById("issueCard_" + jIssuesList[i]["issue_id"]).hidden = true;
	}
    }
    if (noOfIssues === 0) {
	document.getElementById("noIssuesDiv").hidden = false;
    } else {
	document.getElementById("noIssuesDiv").hidden = true;
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

function issueCheckedChange(checkedIssue) {
    if (document.getElementById("isIssueChecked_" + checkedIssue).checked) {
	var jIssue = jIssuesList.find(findIssue);
	++totalCheckedIssues;
    } else {
	--totalCheckedIssues;
    }

    var btn = document.getElementById("returnsSubmitButton");
    if (totalCheckedIssues > 0) {
	btn.value = "SUBMIT (" + totalCheckedIssues.toString() + ")";
	btn.hidden = false;
    } else {
	btn.hidden = true;
    }

    function findIssue(issue) {
	return issue["issue_id"] === checkedIssue.toString();
    }
}