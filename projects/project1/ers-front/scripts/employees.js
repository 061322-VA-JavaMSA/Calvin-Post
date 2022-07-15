// if no users are logged in/ user logged in is not admin, redirects to homepage 
if(!principal || principal.role !== 'MANAGER'){
    window.location.href="./index.html";
}else{
    getUsers();
}
let users;
let ascend = true;
let lastClick;

let uId = document.getElementById('uId');
uId.addEventListener('click', sortUsers);
let uRole = document.getElementById('uRole');
uRole.addEventListener('click', sortUsers);
let uUname = document.getElementById('uUname');
uUname.addEventListener('click', sortUsers);
let uFirstName = document.getElementById('uFirstName');
uFirstName.addEventListener('click', sortUsers);
let uLastName = document.getElementById('uLastName');
uLastName.addEventListener('click', sortUsers);
let uEmail = document.getElementById('uEmail');
uEmail.addEventListener('click', sortUsers);

async function getUsers(){

    let response = await fetch(`${apiUrl}/users`, {
        credentials: 'include'
    });

    if(response.status == 200){
        users = await response.json();

        populateUsers();
    } else{
        console.log('Unable to retrieve users.')
    }
}

function populateUsers(){
    let tableBody = document.getElementById('users-tbody');
    while (tableBody.firstChild) {
        tableBody.removeChild(tableBody.firstChild);
    }
    let rowNum = 0;
    users.forEach(user => {
        let tr = document.createElement('tr');
        let tdId = document.createElement('td');
        let tdRole = document.createElement('td');
        let tdUsername = document.createElement('td');
        let tdFirstName = document.createElement('td');
        let tdLastName = document.createElement('td');
        let tdEmail = document.createElement('td');

        tdId.innerHTML = user.id;
        tdRole.innerHTML = user.role;
        tdUsername.innerHTML = user.username;
        tdFirstName.innerHTML = user.firstName;
        tdLastName.innerHTML = user.lastName;
        tdEmail.innerHTML = user.email;

        tr.append(tdId);
        tr.append(tdRole);
        tr.append(tdUsername);
        tr.append(tdFirstName);
        tr.append(tdLastName);
        tr.append(tdEmail);

        tr.id = rowNum;
        rowNum ++;
        tr.addEventListener('click', setSelectedUser);
        tr.style.cursor = 'pointer';
        tableBody.append(tr);
    });
}

function setSelectedUser(event) {
    sessionStorage.setItem('selectedUser', JSON.stringify(users[event.target.parentNode.id]));
    window.location.href = "./reimbursements.html";
}

function sortUsers(event) {
    if (lastClick) {
        lastClick.innerHTML = lastClick.innerHTML.slice(0, -2);
    }
    let thisClick = event.target;
    if (thisClick == lastClick) {
        ascend = !ascend;
    } else {
        ascend = true;
    }
    thisClick.innerHTML += ascend ? ' ▲' : ' ▼';
    lastClick = thisClick;
    switch (thisClick.id) {

        case 'uId':
            users = users.sort(ascend ? compareIA : compareID);
            break;
        case 'uRole':
            users = users.sort(ascend ? compareRA : compareRD);
            break;

        case 'uUname':
            users = users.sort(ascend ? compareUA : compareUD);
            break;

        case 'uFirstName':
            users = users.sort(ascend ? compareFNA : compareFND);
            break;

        case 'uLastName':
            users = users.sort(ascend ? compareLNA : compareLND);
            break;

        case 'uEmail':
            users = users.sort(ascend ? compareEA : compareED);
            break;

        default:
    }
    populateUsers();
}

function compareIA(a, b) {
    if (a.id > b.id) {
        return 1;
    }
    if (a.id < b.id) {
        return -1;
    }
    return 0;
}
function compareID(a, b) {
    if (a.id < b.id) {
        return 1;
    }
    if (a.id > b.id) {
        return -1;
    }
    return 0;
}
function compareRA(a, b) {
    return ('' + a.role).localeCompare(b.role);
}
function compareRD(a, b) {
    return ('' + b.role).localeCompare(a.role);
}
function compareUA(a, b) {
    return ('' + a.username).localeCompare(b.username);
}
function compareUD(a, b) {
    return ('' + b.username).localeCompare(a.username);
}
function compareFNA(a, b) {
    return ('' + a.firstName).localeCompare(b.firstName);
}
function compareFND(a, b) {
    return ('' + b.firstName).localeCompare(a.firstName);
}
function compareLNA(a, b) {
    return ('' + a.lastName).localeCompare(b.lastName);
}
function compareLND(a, b) {
    return ('' + b.lastName).localeCompare(a.lastName);
}
function compareEA(a, b) {
    return ('' + a.email).localeCompare(b.email);
}
function compareED(a, b) {
    return ('' + b.email).localeCompare(a.email);
}