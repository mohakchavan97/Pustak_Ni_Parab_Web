/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function get2d(num) {
    return (num.toString().length < 2 ? "0" + num : num).toString();
}

function setDate() {
//    var months = ["JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"];
//    n = new Date();
//    y = n.getFullYear().toString();
//    m = (n.getMonth() + 1).toString();
//    d = n.getDate().toString();
//    str = y + "-" + get2d(m) + "-" + get2d(d);
//    str = get2d(n.getDate().toString()) + " " + months[n.getMonth()] + " " + n.getFullYear().toString();
//    document.getElementById("issue_date").value = get2d(d) + "-" + get2d(m) + "-" + y;

    var date = document.getElementById("issue_day");
    var month = document.getElementById("issue_month");
    var year = document.getElementById("issue_year");
    document.getElementById("issue_date").value = date.options[date.selectedIndex].value + " "
	    + month.options[month.selectedIndex].value + " " + year.options[year.selectedIndex].value;
}

function validation() {
    var mob = document.getElementById('issuer_contact');
    var id = document.getElementById('sel_name');
    var filter_mob = /^[6-9]+\d{9}/;
    var dt = document.getElementById("issue_date");

    if ((mob.value.length) > 0) {
	if (!filter_mob.test(mob.value.toString()))
	{
	    alert('Enter 10 digit Mobile Number');
	    mob.focus();
	    return false;
	}

    }

    if ((id.options[id.selectedIndex].value.toString()) === "0_0") {
	alert('Select the Issuer ID');
	id.focus();
	return false;
    }

    var date = new Date(dt.value);
    var day = document.getElementById("issue_day");
    if (dt.value.toString().includes("0_0") || isNaN(date) || get2d(date.getDate()) !== day.options[day.selectedIndex].value) {
	alert('Select Proper Date');
	return false;
    }

    var isForAdd = document.createElement("input");
    isForAdd.setAttribute("hidden", "true");
    isForAdd.setAttribute("name", "isForAdd");
    isForAdd.setAttribute("id", "isForAdd");
    isForAdd.setAttribute("value", "YES");
    document.getElementById("issue").appendChild(isForAdd);
    return true;
}

function getname(allNames) {
    var issr_name = document.getElementById("issuer_name");
    var issr_add = document.getElementById("issuer_address");
    var issr_cont = document.getElementById("issuer_contact");
    var sel_name = document.getElementById("sel_name");
    if (sel_name.options[sel_name.selectedIndex].value.toString() === "0_0") {
	issr_name.value = "";
	issr_add.value = "";
	issr_cont.value = "";
    } else {
	for (var i = 0; i < allNames.length; i++) {
	    if (allNames[i][0].toString() === sel_name.options[sel_name.selectedIndex].value.toString()) {
		issr_name.value = (allNames[i][1].toString()
			+ (allNames[i][2].toString().length ? " " + allNames[i][2].toString() : "")).trim();
		var add = "" + allNames[i][3].toString();
		add += add.length ? ", " + allNames[i][4].toString() : allNames[i][4].toString();
		add += add.length ? ", " + allNames[i][5].toString() : allNames[i][5].toString();
		issr_add.value = add.trim();
		issr_cont.value = (allNames[i][6].toString()).trim();
		break;
	    }
	}
    }
}

}