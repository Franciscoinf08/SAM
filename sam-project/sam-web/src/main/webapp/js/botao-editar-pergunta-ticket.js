document.querySelectorAll(".botao-editar").forEach(botao => {
    botao.addEventListener("click", () => {
        let id = botao.dataset.id;
        let usuario = botao.dataset.usuario;
        let titulo = botao.dataset.titulo;
        let descricao = botao.dataset.descricao;

        abrirPopupEditarPerguntaTicket(id, usuario, titulo, descricao);
    });
});