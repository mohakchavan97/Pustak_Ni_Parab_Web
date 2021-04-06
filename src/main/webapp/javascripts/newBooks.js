/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function pageLoaded() {
    document.getElementById("loader").style.display = "none";
    setDate();
    langChanged();
}

function validation() {
    var lang = document.getElementById("sel_lang");
    var dt = document.getElementById("new_book_date");
    var totBooks = document.getElementById("total_books");

    if (isNaN(parseInt(totBooks.value)) || (parseInt(totBooks.value)) < 1) {
	alert('Enter Proper Number of Books');
	totBooks.focus();
	return false;
    }

    if ((lang.options[lang.selectedIndex].value.toString()) === "0_0") {
	alert('Select Proper Book Language');
	lang.focus();
	return false;
    }

    var date = new Date(dt.value);
    var day = document.getElementById("newBook_day");
    if (dt.value.toString().includes("0_0") || isNaN(date) || get2d(date.getDate()) !== day.options[day.selectedIndex].value) {
	alert('Select Proper Date');
	return false;
    }

    return true;
}

function get2d(num) {
    return (num.toString().length < 2 ? "0" + num : num).toString();
}

function setDate() {
    var date = document.getElementById("newBook_day");
    var month = document.getElementById("newBook_month");
    var year = document.getElementById("newBook_year");
    document.getElementById("new_book_date").value = date.options[date.selectedIndex].value + " "
	    + month.options[month.selectedIndex].value + " " + year.options[year.selectedIndex].value;
}

function langChanged() {
    var lang = document.getElementById("sel_lang");
    var specLang = document.getElementById("specLang");
    var bookLang = document.getElementById("books_language");
    if ((lang.options[lang.selectedIndex].value.toString()) === "-2") {
	specLang.hidden = false;
	bookLang.value = "";
    } else {
	specLang.hidden = true;
	bookLang.value = lang.options[lang.selectedIndex].value.toString();
    }
}