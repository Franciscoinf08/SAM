const textoLinkEl = document.querySelector("#alterna");
const toggleLinkEl = document.querySelector("#link-alterna");
const loginEl = document.querySelector("#login");
const cadastroEl = document.querySelector("#cadastro");

toggleLinkEl.addEventListener("click", () => {
    if (loginEl.classList.contains("campo-inativo")) {
        // troca para login
        loginEl.classList.remove("campo-inativo");
        cadastroEl.classList.add("campo-inativo");
        textoLinkEl.innerHTML = "Ainda não é cliente? ";
        toggleLinkEl.innerHTML = "Cadastre-se";
        textoLinkEl.appendChild(toggleLinkEl);
    } else {
        // troca para cadastro
        loginEl.classList.add("campo-inativo");
        cadastroEl.classList.remove("campo-inativo");
        textoLinkEl.innerHTML = "Já é cliente? ";
        toggleLinkEl.innerHTML = "Entre";
        textoLinkEl.appendChild(toggleLinkEl);
    }

});

