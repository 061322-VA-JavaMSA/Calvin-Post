if (!principal) {
    window.location.href = "./index.html";
}

let sChanges = document.getElementById('saveChanges');
sChanges.addEventListener('click', saveChanges);
let cChanges = document.getElementById('cancelChanges');
cChanges.addEventListener('click', cancelChanges);

let uPass = document.getElementById('updatePass');
uPass.addEventListener('click', updatePassword);
let cPass = document.getElementById('cancelPass');
cPass.addEventListener('click', clearPass);

let myId = document.getElementById('myId');
myId.value = principal.id;
let myRole = document.getElementById('myRole');
myRole.value = principal.role;
let myUser = document.getElementById('myUser');
myUser.value = principal.username;
let myFirst = document.getElementById('myFirst');
myFirst.value = principal.firstName;
let myLast = document.getElementById('myLast');
myLast.value = principal.lastName;
let myEmail = document.getElementById('myEmail');
myEmail.value = principal.email;

async function saveChanges() {
    if (myFirst.value != principal.firstName || myLast.value != principal.lastName || myEmail.value != principal.email) {
        let profileResponse = await fetch(`${apiUrl}/users/${principal.id}`, {
            method: 'PUT',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: new URLSearchParams({
                'firstName': `${myFirst.value}`,
                'lastName': `${myLast.value}`,
                'email': `${myEmail.value}`
            })
        });

        if (profileResponse.status >= 200 && profileResponse.status < 300) {
            let profileData = await profileResponse.json();
            sessionStorage.setItem('principal', JSON.stringify(profileData));
            principal = sessionStorage.getItem('principal');
            myFirst.value = principal.firstName;
            myLast.value = principal.lastName;
            myEmail.value = principal.email;
        } else {
            console.log('Unable to update profile.');
        }
    }
}

function cancelChanges() {
    myFirst.value = principal.firstName;
    myLast.value = principal.lastName;
    myEmail.value = principal.email;
}

async function updatePassword() {
    let oldPass = document.getElementById('oldPass').value;
    let newPass = document.getElementById('newPass').value;
    let confirmPass = document.getElementById('confirmPass').value;
    let verifyPass = await fetch(`${apiUrl}/users/${principal.id}`, {
        credentials: 'include',
    });
    if (verifyPass.status >= 200 && verifyPass.status < 300) {
        let retrievedUser = await verifyPass.json();
        console.log(retrievedUser);
        if (oldPass == retrievedUser.password && newPass === confirmPass) {
            let passResponse = await fetch(`${apiUrl}/users/${principal.id}`, {
                method: 'PUT',
                credentials: 'include',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: new URLSearchParams({
                    'password': `${newPass}`
                })
            });
            if (passResponse.status >= 200 && passResponse.status < 300) {
                alert("Password was successfully updated.");
            } else {
                alert("Failed to change password.")
            }
        } else {
            alert('Failed to change password');
        }
    }
    clearPass();
}

function clearPass() {
    document.getElementById('oldPass').value = "";
    document.getElementById('newPass').value = "";
    document.getElementById('confirmPass').value = "";
}