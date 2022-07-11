if (!principal) {
    window.location.href = "./index.html";
}
if (principal.role === 'MANAGER') {
    let approveB = document.getElementById('approved');
    approveB.style.visibility = 'visible';
    approveB.style.display = 'inline';
    let denyB = document.getElementById('denied');
    denyB.style.visibility = 'visible';
    denyB.style.display = 'inline';
}
window.onload = getReimbs;
let reimbs;
let ascend = true;
let lastClick;

let amount = document.getElementById('amount');
amount.addEventListener('click', sortReimbs);
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
        let rowAmount = document.createElement('td');
        rowAmount.innerHTML = r.amount ? `$ ${r.amount}` : 'undefined';
        tr.appendChild(rowAmount);
        let rowAuthor = document.createElement('td');
        rowAuthor.innerHTML = r.author ? r.author.fullName : 'undefined';
        tr.appendChild(rowAuthor);
        let rowType = document.createElement('td');
        rowType.innerHTML = r.type ? r.type : 'undefined';
        tr.appendChild(rowType);
        let rowSubmitted = document.createElement('td');
        rowSubmitted.innerHTML = r.submitted ? formatDate(r.submitted, '  ') : 'undefined';
        tr.appendChild(rowSubmitted);
        let rowStatus = document.createElement('td');
        rowStatus.innerHTML = r.status ? r.status : 'undefined';
        tr.appendChild(rowStatus);
        tr.addEventListener('click', populateViewer);
        tr.style.cursor = 'pointer';
        tr.setAttribute('data-bs-toggle', 'modal');
        tr.setAttribute('data-bs-target', '#reimbView');
        tbody.appendChild(tr);
        tr.name = r.id;
    });
}

function populateViewer(event) {
    let reimbToView = reimbs[event.target.parentNode.name];
    document.getElementById('viewAuthor').innerHTML = reimbToView.author.fullName ? reimbToView.author.fullName : '—';
    document.getElementById('viewResolver').innerHTML = reimbToView.resolver.fullName ? reimbToView.resolver.fullName : '—';
    document.getElementById('viewSubmitted').innerHTML = reimbToView.submitted ? formatDate(reimbToView.submitted, '  ') : '—';
    document.getElementById('viewResolved').innerHTML = reimbToView.resolved ? formatDate(reimbToView.resolved, '  ') : '—';
    document.getElementById('viewAmount').innerHTML = reimbToView.amount ? `$ ${reimbToView.amount}` : '—';
    document.getElementById('viewType').innerHTML = reimbToView.type ? reimbToView.type : '—';
    document.getElementById('viewStatus').innerHTML = reimbToView.status ? reimbToView.status : '—';
    document.getElementById('viewDescription').innerHTML = reimbToView.description ? reimbToView.description : '—';
}

function sortReimbs(event) {
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

        case 'amount':
            reimbs = reimbs.sort(ascend ? compareAmA : compareAmD);
            break;
        case 'author':
            reimbs = reimbs.sort(ascend ? compareAuA : compareAuD);
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

function compareAmA(a, b) {
    if (a.amount > b.amount) {
        return 1;
    }
    if (a.amount < b.amount) {
        return -1;
    }
    return 0;
}
function compareAmD(a, b) {
    if (a.amount < b.amount) {
        return 1;
    }
    if (a.amount > b.amount) {
        return -1;
    }
    return 0;
}
function compareAuA(a, b) {
    return ('' + a.author.firstName + ' ' + a.author.lastName).localeCompare(b.author.firstName + ' ' + b.author.lastName);
}
function compareAuD(a, b) {
    return ('' + b.author.firstName + ' ' + b.author.lastName).localeCompare(a.author.firstName + ' ' + a.author.lastName);
}
function compareTA(a, b) {
    return ('' + a.type).localeCompare(b.type);
}
function compareTD(a, b) {
    return ('' + b.type).localeCompare(a.type);
}
function compareSuA(a, b) {
    return ('' + a.submitted).localeCompare(b.submitted);
}
function compareSuD(a, b) {
    return ('' + b.submitted).localeCompare(a.submitted);
}
function compareStA(a, b) {
    return ('' + a.status).localeCompare(b.status);
}
function compareStD(a, b) {
    return ('' + b.status).localeCompare(a.status);
}