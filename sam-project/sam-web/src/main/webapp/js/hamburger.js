const hamburger = document.getElementById('hamburger-btn');
const nav = document.getElementById('hamburger-dropdown');

hamburger.addEventListener('click', () => {
  nav.classList.toggle('active');
});


