let apiUrl = 'http://localhost:8080/ers';

let principalString = sessionStorage.getItem('principal');
let principal = null;

let nav_right = document.getElementById("nav-right");
let nav_left = document.getElementById("nav-left");

if (principalString) {
    principal = JSON.parse(principalString);

    if (principal.role === 'MANAGER') {
        createNavElement('Employees', nav_right, './employees.html', null, null);
    }

    createNavElement('Reimbursements', nav_right, './reimbursements.html', null, null);
    createNavElement('Profile', nav_right, './profile.html', null, null);
    createNavElement('Sign Out', nav_right, null, logout, null);
} else {
    createNavElement('Sign In / Create User', nav_right, null, null, {
        'toggle': 'modal',
        'target': '#loginModal'
    });
}

async function logout() {

    let response = await fetch(`${apiUrl}/auth`, {
        method: 'DELETE',
        credentials: 'include'
    });

    if (response.status == 200) {
        sessionStorage.clear();
        principal = null;
        window.location.href="./index.html";
    } else {
        console.log('Unable to logout.')
    }
}

function createNavElement(innerHTML, parentElement, link, callback, options) {
    let li = document.createElement('li');
    li.setAttribute('class', 'nav-item');

    let a = document.createElement('a');
    a.setAttribute('class', 'nav-link');

    if (link) {
        a.setAttribute('href', link);
    } else {
        a.setAttribute('href', '#');
    }

    if (callback) {
        a.addEventListener('click', callback);
    }

    if (options) {
        a.setAttribute('data-bs-toggle', options.toggle);
        a.setAttribute('data-bs-target', options.target);
        // a.dataset.toggle = options.toggle;
        // a.dataset.target = options.target;
    }

    a.innerHTML = innerHTML;

    li.appendChild(a);

    parentElement.appendChild(li);
}

function formatDate(timestamp, split) {
    let date = new Date(timestamp);
    let month = date.getMonth() + 1;
    let day = date.getDate();
    let hour = date.getHours();
    let min = date.getMinutes();
    let sec = date.getSeconds();
    let amPm = hour < 12 ? "AM" : "PM";
    if (hour > 12) {
        hour -= 12;
    }
    month = (month < 10 ? "0" : "") + month;
    day = (day < 10 ? "0" : "") + day;
    hour = (hour < 10 ? "0" : "") + hour;
    min = (min < 10 ? "0" : "") + min;
    sec = (sec < 10 ? "0" : "") + sec;

    let str = month + "-" + day + "-" + date.getFullYear() + split +  hour + ":" + min + ":" + sec + " " + amPm;

    return str;
}

function pad(str, length, align) {
    let newStr = str.toString();
    while (newStr.length < length) {
        newStr = align == 'left' ? newStr + ' ' : ' ' + newStr;
    }
    return newStr;
}