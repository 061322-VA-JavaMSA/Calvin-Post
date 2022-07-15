if (!principal) {
    window.location.href = "./index.html";
}
let selectedUser = null;
if (principal.role === 'MANAGER') {
    let selUserString = sessionStorage.getItem('selectedUser');
    if (selUserString) {
        selectedUser = JSON.parse(selUserString);
    }
} else if (principal.role === 'EMPLOYEE') {
    selectedUser = principal;
}
window.onbeforeunload = function () {
    sessionStorage.removeItem('selectedUser');
};
if (principal.role === 'EMPLOYEE') {
    document.getElementById('newReimbButton').style.visibility = 'visible';
    document.getElementById('newReimbButton').style.display = 'inline';
} else {
    document.getElementById('newReimbButton').style.visibility = 'hidden';
    document.getElementById('newReimbButton').style.display = 'none';
}
if (principal) {
    getReimbs();
}
let reimbs;
let ascend = true;
let lastClick;

let searchBar = document.getElementById('searchBar');
searchBar.addEventListener('input', populateReimbs);
searchBar.addEventListener('propertychange', populateReimbs);
let fStatus = document.getElementsByName('fStatus');
fStatus.forEach(rad => rad.addEventListener('change', populateReimbs));
let fType = document.getElementsByName('fType');
fType.forEach(rad => rad.addEventListener('change', populateReimbs));

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
let receiptCol = document.getElementById('receipt');
receiptCol.addEventListener('click', sortReimbs);
let sumbitRequest = document.getElementById('submitRequest');
sumbitRequest.addEventListener('click', addReimb);
let cancelRequest = document.getElementById('cancelRequest');
cancelRequest.addEventListener('click', clearRequestForm);

let reimbToView;

function matchesType(reimb) {
    switch (document.querySelector('input[name="fType"]:checked').value) {
        case '':
            return true;
            break;

        case 'food':
            if (reimb.type == 'FOOD') {
                return true;
            }
            break;

        case 'lodging':
            if (reimb.type == 'LODGING') {
                return true;
            }
            break;

        case 'travel':
            if (reimb.type == 'TRAVEL') {
                return true;
            }
            break;

        case 'other':
            if (reimb.type == 'OTHER') {
                return true;
            }
            break;

        default:
            return true;
    }
    return false;
}

function matchesStatus(reimb) {
    switch (document.querySelector('input[name="fStatus"]:checked').value) {
        case '':
            return true;
            break;

        case 'pending':
            if (reimb.status == 'PENDING') {
                return true;
            }
            break;

        case 'resolved':
            if (reimb.status == 'APPROVED' || reimb.status == 'DENIED') {
                return true;
            }
            break;

        default:
            return true;
    }
    return false;
}

function matchesSearch(reimb) {
    if (searchBar.value) {
        if (includesIgnoreCase(reimb.description, searchBar.value) || includesIgnoreCase(reimb.amount, searchBar.value) || includesIgnoreCase(reimb.submitted, searchBar.value) || includesIgnoreCase(reimb.type, searchBar.value) || includesIgnoreCase(reimb.author.fullName, searchBar.value)) {
            return true;
        }
        return false;
    }
    return true;
}

async function addReimb() {
    let reqAmount = document.getElementById('newAmount').value;
    let reqType = document.getElementById('newType').value;
    let reqDesc = document.getElementById('newDescription').value;
    let reqReceipt = document.getElementById('newType').value;

    let submitResponse = await fetch(`${apiUrl}/reimbursements`, {
        method: 'POST',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            'type': `${reqType}`,
            'amount': `${reqAmount}`,
            'description': `${reqDesc}`,
            'receipt': `${reqReceipt}`
        })
    });

    if (submitResponse.status >= 200 && submitResponse.status < 300) {
        clearRequestForm();
        getReimbs();
    } else {
        console.log('Unable to complete request.');
    }
}

function clearRequestForm() {
    document.getElementById('newRequestForm').resest();
}

async function getReimbs() {
    let requestUrl = selectedUser ? `${apiUrl}/reimbursements/${selectedUser.id}` : `${apiUrl}/reimbursements`;
    let reimbRes = await fetch(requestUrl, {
        credentials: 'include'
    });

    if (reimbRes.status >= 200 && reimbRes.status < 300) {
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
    let id = 0;
    let filteredReimbs = [];
    reimbs.forEach(function (r) {
        if (matchesSearch(r) && matchesStatus(r) && matchesType(r)) {
            filteredReimbs.push(r);
        }
    });
    document.getElementById('showForUser').innerHTML = selectedUser ? `Requests from ${selectedUser.fullName}` : `Requests from all employees`;
    document.getElementById('showCount').innerHTML = `Showing ${filteredReimbs.length} results`;
    filteredReimbs.forEach(function (r) {
        let tr = document.createElement('tr');
        let rowAuthor = document.createElement('td');
        rowAuthor.innerHTML = r.author ? r.author.fullName : 'undefined';
        tr.appendChild(rowAuthor);
        let rowAmount = document.createElement('td');
        rowAmount.innerHTML = r.amount ? `$ ${r.amount.toFixed(2)}` : 'undefined';
        tr.appendChild(rowAmount);
        let rowType = document.createElement('td');
        rowType.innerHTML = r.type ? r.type : 'undefined';
        tr.appendChild(rowType);
        let rowSubmitted = document.createElement('td');
        rowSubmitted.innerHTML = r.submitted ? formatDate(r.submitted, '  ') : 'undefined';
        tr.appendChild(rowSubmitted);
        let rowStatus = document.createElement('td');
        rowStatus.innerHTML = r.status ? r.status : 'undefined';
        tr.appendChild(rowStatus);
        let rowReceipt = document.createElement('td');
        rowReceipt.innerHTML = r.receipt ? '✓' : '✗';
        tr.appendChild(rowReceipt);
        tr.addEventListener('click', populateViewer);
        tr.style.cursor = 'pointer';
        tr.setAttribute('data-bs-toggle', 'modal');
        tr.setAttribute('data-bs-target', '#reimbView');
        tbody.appendChild(tr);
        tr.id = id;
        id++;
    });
}

async function resolveReimb(event) {
    let resolvedId = reimbToView.id;
    let resolvedStatus = event.target.value;
    let resolveResponse = await fetch(`${apiUrl}/reimbursements`, {
        method: 'PUT',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            'id': `${resolvedId}`,
            'status': `${resolvedStatus}`
        })
    });

    if (resolveResponse.status >= 200 && resolveResponse.status < 300) {
        getReimbs();
    } else {
        console.log('Unable to complete request.');
    }
}

function populateViewer(event) {
    reimbToView = reimbs[event.target.parentNode.id];
    console.log(reimbToView);
    document.getElementById('viewAuthor').innerHTML = reimbToView.author.fullName ? reimbToView.author.fullName : '—';
    document.getElementById('viewResolver').innerHTML = reimbToView.resolver.fullName ? reimbToView.resolver.fullName : '—';
    document.getElementById('viewSubmitted').innerHTML = reimbToView.submitted ? formatDate(reimbToView.submitted, '  ') : '—';
    document.getElementById('viewResolved').innerHTML = reimbToView.resolved ? formatDate(reimbToView.resolved, '  ') : '—';
    document.getElementById('viewAmount').innerHTML = reimbToView.amount ? `$ ${reimbToView.amount.toFixed(2)}` : '—';
    document.getElementById('viewType').innerHTML = reimbToView.type ? reimbToView.type : '—';
    document.getElementById('viewStatus').innerHTML = reimbToView.status ? reimbToView.status : '—';
    document.getElementById('viewDescription').innerHTML = reimbToView.description ? reimbToView.description : '—';
    let recCont = document.getElementById('viewReceiptContainer');
    if (reimbToView.receipt) {
        document.getElementById('viewReceipt').src = "data:image/*,.pdf;base64," + reimbToView.receipt;
        recCont.style.visibility = 'visible';
        recCont.style.display = 'inline';
    } else {
        recCont.style.visibility = 'hidden';
        recCont.style.display = 'none';
    }

    let approveB = document.getElementById('approved');
    let denyB = document.getElementById('denied');
    if (principal.role === 'MANAGER' && reimbToView.status === 'PENDING') {
        approveB.style.visibility = 'visible';
        approveB.style.display = 'inline';
        approveB.addEventListener('click', resolveReimb);
        denyB.style.visibility = 'visible';
        denyB.style.display = 'inline';
        denyB.addEventListener('click', resolveReimb);
    } else {
        approveB.style.visibility = 'hidden';
        approveB.style.display = 'none';
        denyB.style.visibility = 'hidden';
        denyB.style.display = 'none';
    }
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

        case 'receipt':
            reimbs = reimbs.sort(ascend ? compareRA : compareRD);
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
function compareRA(a, b) {
    if (a.receipt && !b.receipt) {
        return 1;
    }
    if (!a.receipt && b.receipt) {
        return -1;
    }
    return 0;
}
function compareRD(a, b) {
    if (a.receipt && !b.receipt) {
        return -1;
    }
    if (!a.receipt && b.receipt) {
        return 1;
    }
    return 0;
}