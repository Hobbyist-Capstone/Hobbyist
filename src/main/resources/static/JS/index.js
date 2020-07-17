$(document).ready(function(){
    $('.sidenav').sidenav();
});

function openLoginForm() {
    document.getElementById("small-login-form").style.display = "block";
    closeJoinForm()
}

function closeLoginForm() {
    document.getElementById("small-login-form").style.display = "none";
}


// Large

function openLargeLoginForm() {
    document.getElementById("large-login-form").style.display = "block";
    closeLargeJoinForm()
}

function closeLargeLoginForm() {
    document.getElementById("large-login-form").style.display = "none";
}






// document.addEventListener('DOMContentLoaded', function() {
//     console.log(M);
//     var elems = document.querySelectorAll('.dropdown-trigger');
//     var instances = M.Dropdown.init(elems, {
//         hover: true
//     })
//     // var instance = M.Dropdown.getInstance(elem);

// });

// M.AutoInit();

