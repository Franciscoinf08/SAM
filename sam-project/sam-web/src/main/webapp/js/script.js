const btn = document.getElementById("hamburger-btn");
const dropdown = document.getElementById("hamburger-dropdown");

btn.addEventListener("click", () => {
    dropdown.classList.toggle("show");
});

// Função chamada ao clicar no botão "Associar"
function toggleDropdown(buttonElement) {
    // 1. Encontra o elemento pai '.dropdown'
    const dropdownContainer = buttonElement.closest('.dropdown');

    // 2. Encontra o menu '.dropdown-content' dentro desse container
    const dropdownMenu = dropdownContainer.querySelector('.dropdown-content');

    // 3. Alterna a classe 'show' (que torna o menu visível)
    dropdownMenu.classList.toggle("show");
}

// Opcional: Fechar o dropdown se o usuário clicar fora dele
window.onclick = function(event) {
    // Se o clique NÃO foi no botão
    if (!event.target.matches('.btn-associar')) {
        // Fecha todos os menus abertos
        const dropdowns = document.getElementsByClassName("dropdown-content");
        for (let i = 0; i < dropdowns.length; i++) {
            const openDropdown = dropdowns[i];
            if (openDropdown.classList.contains('show')) {
                openDropdown.classList.remove('show');
            }
        }
    }
}

// Função de exemplo para a ação real
function associarPrograma(idCliente, nomePrograma) {
    console.log(`Cliente ${idCliente} associado ao ${nomePrograma}.`);
    alert(`Associando cliente ao ${nomePrograma}...`);
    // Aqui você faria a chamada AJAX/Fetch para o seu backend

    // Fecha o dropdown após a seleção
    window.onclick({ target: document.body });
}