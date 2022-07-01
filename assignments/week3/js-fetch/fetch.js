// document.getElementById('getData').onclick = getData;
document.getElementById('getData').addEventListener("click", getData);

/*
    - When button is clicked, send http request to API for a specific id

    - get the id value from input box
    - send request to PokeAPI
        - Method: GET
        - Headers: None
        - Body: None
        - url: https://pokeapi.co/api/v2/pokemon/ + id from input box
    - might have to convert JSON to JS object

    - populate the data in Section
*/
let baseApiURL = 'https://pokeapi.co/api/v2/pokemon';

async function getData() {
    console.log('Button was clicked!');
    let id = document.getElementById('dataInput').value;
    console.log(`id = ${id}`);

    let httpResponse = await fetch(`${baseApiURL}/${id}`);

    if (httpResponse.status >= 200 && httpResponse.status < 300) {
        let data = await httpResponse.json();

        populateData(data);

    } else {
        console.log('Invalid request.');
    }
}

function populateData(response) {
    console.log(response);
    let dis = document.getElementById('data');
    dis.innerHTML = '';
    var h1 = document.createElement('h1');
    h1.innerHTML = response.name;
    dis.appendChild(h1);
    var img = document.createElement('img');
    img.src = response.sprites.front_default;
    dis.appendChild(img);
    var table = document.createElement('table');

    var tr = document.createElement('tr');
    tr.innerHTML = `<th>Move</th><th>Level</th><th>Method</th>`;
    table.appendChild(tr);
    var moves = response.moves;
    for (i = 0; i < moves.length; i++) {
        var tr = document.createElement('tr');
        var td1 = document.createElement('td');
        td1.innerHTML = moves[i].move.name;
        tr.appendChild(td1);
        var td2 = document.createElement('td');
        td2.innerHTML = moves[i].version_group_details[0].level_learned_at;
        tr.appendChild(td2);
        var td3 = document.createElement('td');
        td3.innerHTML = moves[i].version_group_details[0].move_learn_method.name;
        tr.appendChild(td3);
        table.appendChild(tr);

    }
    dis.appendChild(table);
}

function printValues(obj) {
    for (var k in obj) {
        if (obj[k] instanceof Object) {
            printValues(obj[k]);
        } else {
            document.write(obj[k] + "<br>");
        };
    }
}

function createTable() {
    createTableBtn.removeEventListener('click', createTable);

    var table = document.createElement('table');
    var rows = (Math.floor(Math.random() * 9)) + 1;
    var cols = (Math.floor(Math.random() * 9)) + 1;
    for (var i = 0; i < rows; i++) {
        var tr;
        tr = document.createElement('tr');
        for (var j = 0; j < cols; j++) {
            var td;
            if (i === 0) td = document.createElement('th');
            else td = document.createElement('td');
            td.innerHTML = j;
            tr.appendChild(td);
        }

        table.appendChild(tr);
    }
    tableDiv.appendChild(table);

    createTableBtn.innerHTML = 'Remove Table';
    createTableBtn.onclick = removeTable;
}