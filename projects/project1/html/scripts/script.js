

async function getUsers() {
    let input = document.getElementById('userId').value;
    let httpResponse = await fetch(`http://localhost:8080/project1/users`, {mode:'cors'});
    let user = await httpResponse.json();
    console.log(user);

    populateUser(user);
}

function populateUser(response) {
    let sec = document.getElementById('users');
    let table = document.createElement('table');
    let th = document.createElement('tr');
    th.innerHTML = "<th>ID</th><th>Username</th><th>First Name</th><th>Last Name</th><th>Email</th><th>Role</th>";
    table.appendChild(th);
    
    response.forEach(u => {
        let tr = document.createElement('tr');
        
        let id = document.createElement('td');
        id.innerHTML = u.id;
        tr.appendChild(id);
        
        let un = document.createElement('td');
        un.innerHTML = u.username;
        tr.appendChild(un);
        
        let fn = document.createElement('td');
        fn.innerHTML = u.firstName;
        tr.appendChild(fn);
        
        let ln = document.createElement('td');
        ln.innerHTML = u.lastName;
        tr.appendChild(ln);
        
        let email = document.createElement('td');
        email.innerHTML = u.email;
        tr.appendChild(email);

        let role = document.createElement('td');
        role.innerHTML = u.role;
        tr.appendChild(role);

        table.appendChild(tr);
    }); 

    sec.appendChild(table);
}