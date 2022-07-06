// $(document).ready(function(){
// 	$('.header').height($(window).height());
// })
let apiUrl = 'http://127.0.0.1:5500/';
let loginButton = document.getElementById('login');
var ready = (callback) => {
    if (document.readyState != "loading") callback();
    else document.addEventListener("DOMContentLoaded", callback);
}
ready(() => {
    document.querySelector(".header").style.height = window.innerHeight + "px";
})

s$(document).ready(function () {
    $('#loginModal').modal('show');
    $(function () {
        $('[data-toggle="tooltip"]').tooltip()
    })
});

function validateUser() {
    var field = document.getElementById('uname').value;

    // CHeck if email
    if (/\@/.test(field)) {
        // Validate email address
    }
    else {
        // Validate username
    }
}