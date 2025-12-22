function toggleNotificacao(link) {
    const mensagem = link.closest(".mensagem");
    const curta = mensagem.querySelector(".curta");
    const completa = mensagem.querySelector(".completa");

    const expandida = completa.style.display === "inline";

    if (expandida) {
        completa.style.display = "none";
        curta.style.display = "inline";
        link.textContent = "mostrar mais";
    } else {
        completa.style.display = "inline";
        curta.style.display = "none";
        link.textContent = "mostrar menos";
    }
}
