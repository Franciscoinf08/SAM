document.querySelectorAll(".botao-editar").forEach(botao => {
    botao.addEventListener("click", () => {
        let id = botao.dataset.id;
        let usuario = botao.dataset.usuario;
        let pergunta = botao.dataset.pergunta;
        let descricao = botao.dataset.descricao;

        abrirPopupEditarRespostaTicket(id, usuario, pergunta, descricao);
    });
});