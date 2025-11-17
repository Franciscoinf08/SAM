/*
* BOTÃO HAMBÚRGUER
*/

const btn = document.getElementById("hamburger-btn");
const dropdown = document.getElementById("hamburger-dropdown");

btn.addEventListener("click", () => {
    dropdown.classList.toggle("show");
});