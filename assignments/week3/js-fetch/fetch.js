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

async function populateData(response) {
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
    tr.innerHTML = `<th>Move</th><th>Type</th><th>Power</th><th>PP</th><th>Level</th><th>Method</th>`;
    table.appendChild(tr);
    var moves = response.moves;
    for (i = 0; i < moves.length; i++) {
        let move = await (await fetch(moves[i].move.url)).json();
        var tr = document.createElement('tr');
        var moveName = document.createElement('td');
        moveName.innerHTML = moves[i].move.name;
        tr.appendChild(moveName);
        var moveType = document.createElement('td');
        moveType.innerHTML = move.type.name;
        tr.appendChild(moveType);
        var movePower = document.createElement('td');
        movePower.innerHTML = move.power == null ? '—' : move.power;
        movePower.style.textAlign = 'center';
        tr.appendChild(movePower);
        var movePP = document.createElement('td');
        movePP.innerHTML = move.pp;
        movePP.style.textAlign = 'center';
        tr.appendChild(movePP);
        var moveLevel = document.createElement('td');
        let level = moves[i].version_group_details[0].level_learned_at
        moveLevel.innerHTML = level == '0' ? '—' : level;
        moveLevel.style.textAlign = 'right';
        tr.appendChild(moveLevel);
        var moveMethod = document.createElement('td');
        moveMethod.innerHTML = moves[i].version_group_details[0].move_learn_method.name;
        tr.appendChild(moveMethod);
        table.appendChild(tr);

    }
    dis.appendChild(table);
}

async function getMove(url) {
    let httpRes = await fetch('url');
    let moveObj = await httpRes.json();
    console.log("Move Object");
    console.log(moveObj);
    return moveObj;
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