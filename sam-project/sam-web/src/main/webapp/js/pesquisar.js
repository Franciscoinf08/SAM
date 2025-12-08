const searchInput = document.getElementById('searchInput');
const itemsList = document.getElementById('itemsList');
const items = itemsList.getElementsByTagName('li');

// Opcional: Esconde todos os itens no carregamento inicial da página
for (let i = 0; i < items.length; i++) {
    items[i].classList.add('hidden');
}

searchInput.addEventListener('keyup', function() {
    const filter = searchInput.value.toLowerCase().trim(); // Use .trim() para remover espaços em branco extras

    // >>> NOVO: Verifique se o campo de pesquisa está vazio <<<
    if (filter === "") {
        // Se estiver vazio, esconda todos os itens e pare a execução da função
        for (let i = 0; i < items.length; i++) {
            items[i].classList.add('hidden');
        }
        return; // Sai da função de evento
    }
    // >>> FIM DA VERIFICAÇÃO <<<

    // 3. Itera sobre todos os itens da lista SOMENTE se houver um filtro
    for (let i = 0; i < items.length; i++) {
        const item = items[i];
        const text = item.textContent || item.innerText;

        // 4. Verifica se o texto do item inclui o texto do filtro
        if (text.toLowerCase().indexOf(filter) > -1) {
            item.classList.remove('hidden');
        } else {
            item.classList.add('hidden');
        }
    }
});
