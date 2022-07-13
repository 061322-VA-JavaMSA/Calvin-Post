let submit = document.getElementById('submit');
let h = document.getElementById('response');
submit.addEventListener('click', checkAnswer);
function checkAnswer() {
    let answer = document.querySelector('input[name="ttaal"]:checked').value;
    h.style.visibility = "visible";
    if (answer) {
        h.textContent = "Correct answer!";
        h.style.color = "green";
    } else {
        h.textContent = "Incorrect answer!";
        h.style.color = "red";
    }
}