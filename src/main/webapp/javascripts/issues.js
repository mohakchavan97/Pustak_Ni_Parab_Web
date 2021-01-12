/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function get2d(num) {
    return (num.toString().length < 2 ? "0" + num : num).toString();
}

function ins_date() {
    n = new Date();
    y = n.getFullYear().toString();
    m = (n.getMonth() + 1).toString();
    d = n.getDate().toString();
    str = y + "-" + get2d(m) + "-" + get2d(d);
//    document.getElementById("issue_date").value = get2d(d) + "-" + get2d(m) + "-" + y;
    document.getElementById("issue_date").value = str;
}

function validation() {
    var mob = document.getElementById('issuer_cont');
    var id = document.getElementById('sel_name');
    var filter_mob = /^[6-9]+\d{9}/;

    if ((mob.value.length) > 0) {
        if (!filter_mob.test(mob.value.toString()))
        {
            alert('Enter 10 digit Mobile Number');
            mob.focus();
            return false;
        }

    }

    if ((id.options[id.selectedIndex].value) == "0_0") {
        alert('Enter the Issuer ID or Select Other');
        id.focus();
        return false;
    }

    return true;
}