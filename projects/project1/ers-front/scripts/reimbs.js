// if (!principal) {
//     window.location.href = "./index.html";
// }
window.onload = getReimbs;
let reimbs;
let ascend = true;
let lastId;

let infoButton = document.getElementById('info');
infoButton.addEventListener('click', getReimbs);
let author = document.getElementById('author');
author.addEventListener('click', sortReimbs);
let type = document.getElementById('type');
type.addEventListener('click', sortReimbs);
let submitted = document.getElementById('submitted');
submitted.addEventListener('click', sortReimbs);
let statusCol = document.getElementById('status');
statusCol.addEventListener('click', sortReimbs);

async function getReimbs() {
    let reimbRes = await fetch(`${apiUrl}/reimbursements`, {
        method: 'GET'
    });

    if (reimbRes.status == 200) {
        reimbs = await reimbRes.json();
        console.log(reimbs);
        populateReimbs();
    } else {
        console.log('Unable to fetch data.');
    }
}

function populateReimbs() {
    let tbody = document.getElementById('reimb-tbody');
    while (tbody.firstChild) {
        tbody.removeChild(tbody.firstChild);
    }

    reimbs.forEach(function (r) {

        let tr = document.createElement('tr');
        tr.id = r.id;
        let auth = document.createElement('td');
        auth.innerHTML = r.author ? (r.author.firstName + ' ' + r.author.lastName) : 'undefined';
        tr.appendChild(auth);
        let type = document.createElement('td');
        type.innerHTML = r.reimbType.type;
        tr.appendChild(type);
        let sub = document.createElement('td');
        sub.innerHTML = formatDate(r.submitted);
        tr.appendChild(sub);
        let stat = document.createElement('td');
        stat.innerHTML = r.reimbStatus.status;
        tr.appendChild(stat);
        tbody.appendChild(tr);
    });
}

function sortReimbs() {
    let clickedId = event.target.id;
    if (clickedId == lastId) {
        ascend = !ascend;
    } else {
        ascend = true;
    }
    lastId = clickedId;
    switch (clickedId) {
        case 'author':
            reimbs = reimbs.sort(ascend ? compareAA : compareAD);
            break;

        case 'type':
            reimbs = reimbs.sort(ascend ? compareTA : compareTD);
            break;

        case 'submitted':
            reimbs = reimbs.sort(ascend ? compareSuA : compareSuD);
            break;

        case 'status':
            reimbs = reimbs.sort(ascend ? compareStA : compareStD);
            break;

        default:
    }
    populateReimbs();
}

function compareAA(a, b) {
    return ('' + a.author.firstName + ' ' + a.author.lastName).localeCompare(b.author.firstName + ' ' + b.author.lastName);
}
function compareAD(a, b) {
    return ('' + b.author.firstName + ' ' + b.author.lastName).localeCompare(a.author.firstName + ' ' + a.author.lastName);
}
function compareTA(a, b) {
    return ('' + a.reimbType.type).localeCompare(b.reimbType.type);
}
function compareTD(a, b) {
    return ('' + b.reimbType.type).localeCompare(a.reimbType.type);
}
function compareSuA(a, b) {
    return ('' + a.submitted).localeCompare(b.submitted);
}
function compareSuD(a, b) {
    return ('' + b.submitted).localeCompare(a.submitted);
}
function compareStA(a, b) {
    return ('' + a.reimbStatus.status).localeCompare(b.reimbStatus.status);
}
function compareStD(a, b) {
    return ('' + b.reimbStatus.status).localeCompare(a.reimbStatus.status);
}

function formatDate(timestamp) {
    let date = new Date(timestamp);
    let month = date.getMonth() + 1;
    let day = date.getDate();
    let hour = date.getHours();
    let min = date.getMinutes();
    let sec = date.getSeconds();

    month = (month < 10 ? "0" : "") + month;
    day = (day < 10 ? "0" : "") + day;
    hour = (hour < 10 ? "0" : "") + hour;
    min = (min < 10 ? "0" : "") + min;
    sec = (sec < 10 ? "0" : "") + sec;

    let str = date.getFullYear() + "-" + month + "-" + day + " | " +  hour + ":" + min + ":" + sec;

    /*alert(str);*/

    return str;
}