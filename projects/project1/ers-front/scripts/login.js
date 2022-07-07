// Checks if a user is already logged in, if yes redirect to homepage
// if (principal) {
//     window.location.href = "./index.html";
// }

// Setting up event listener for login button
let loginButton = document.getElementById('login');
loginButton.addEventListener('click', login);

async function login() {

    let username = document.getElementById('username').value;
    let password = document.getElementById('password').value;

    let response = await fetch(`${apiUrl}/auth`, {
        method: 'POST',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            'username': `${username}`,
            'password': `${password}`
        })
    });

    if (response.status == 200) {
        let data = await response.json();

        sessionStorage.setItem('principal', JSON.stringify(data));
        window.location.href = "./index.html";
    } else {
        console.log('Unable to login.')
    }
}