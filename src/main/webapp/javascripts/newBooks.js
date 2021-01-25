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