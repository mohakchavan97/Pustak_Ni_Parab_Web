/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function validation() {
    var mob = document.getElementById('cont_no');
    var filter_mob = /^[6-9]+\d{9}/;

    if ((mob.value.length) > 0) {
        if (!filter_mob.test(mob.value.toString()))
        {
            alert('Enter 10 digit Mobile Number');
            mob.focus();
            return false;
        }
    }
    return true;
}

function viewall(){
    location.href="./Names_Backend_2";
}