/*
* GERADOR DAS MENSAGENS DE ERRO
*/

function gerarMensagemErro(erro) {
    let body = document.body;
    let mensagemErroEl = document.createElement("div");
    let conteudo = document.createTextNode(erro);

    mensagemErroEl.appendChild(conteudo);
    mensagemErroEl.classList.add("mensagem-card");
    body.appendChild(mensagemErroEl);

    setTimeout(() => { mensagemErroEl.style.top = "4em"; }, 1);
    setTimeout(() => { mensagemErroEl.style.display = "none"; }, 4000);
}