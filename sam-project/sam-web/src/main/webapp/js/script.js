const btn = document.getElementById("hamburger-btn");
const dropdown = document.getElementById("hamburger-dropdown");

btn.addEventListener("click", () => {
    dropdown.classList.toggle("show");
});

window.addEventListener("click", function (event) {
    if (!event.target.matches('#hamburger-btn')) {
        if (dropdown.classList.contains('show')) {
            dropdown.classList.remove('show');
        }
    }
});
