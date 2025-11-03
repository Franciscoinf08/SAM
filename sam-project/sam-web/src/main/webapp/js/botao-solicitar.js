const botaoSolicitacaoEl = document.querySelector("#solicitar-gestor");

botaoSolicitacaoEl.addEventListener("click", () => {
    if (botaoSolicitacaoEl.innerHTML == "Solicitar conta de gestor") {
        if (window.confirm("Tem certeza que deseja solicitar uma conta de gestor?")) {
            alert("Solicitação feita!");
            botaoSolicitacaoEl.innerHTML = "Cancelar solicitação"
        } else {
            alert("Solicitação cancelada.");
        }
    } else {
        if (window.confirm("Tem certeza que deseja cancelar sua solicitação para conta de gestor?")) {
            alert("Solicitação cancelada!");
            botaoSolicitacaoEl.innerHTML = "Solicitar conta de gestor"
        } else {
            alert("Solicitação mantida.");
        }
    }
});


