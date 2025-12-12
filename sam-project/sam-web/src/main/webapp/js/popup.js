/*
* Funções para abrir popups
*/

function abrirPopupEditarFaq(id, titulo, pergunta, resposta) {

    if (document.querySelectorAll(".popup").length === 1)
        return;
    let popup = document.createElement("div");
    popup.classList.add("popup");

    let botaoFechar = document.createElement("button");
    botaoFechar.innerHTML = "&#10006"
    botaoFechar.addEventListener("click", () => fecharPopup(popup));

    let form = document.createElement("form");
    form.method = "POST";
    form.action = "/sam/EdicaoFaqController";

    let inputId = document.createElement("input");
    inputId.type = "hidden";
    inputId.name = "id";
    inputId.value = id;

    let labelId = document.createElement("label");
    labelId.appendChild(inputId);

    let inputTitulo = document.createElement("input");
    inputTitulo.type = "text";
    inputTitulo.name = "titulo";
    inputTitulo.placeholder = titulo;
    inputTitulo.value = titulo;
    inputTitulo.required = true;

    let labelTitulo = document.createElement("label");
    labelTitulo.innerHTML = "Título:"
    labelTitulo.appendChild(inputTitulo);

    let inputPergunta = document.createElement("textarea");
    inputPergunta.type = "text";
    inputPergunta.name = "pergunta";
    inputPergunta.placeholder = pergunta;
    inputPergunta.value = pergunta;

    let labelPergunta = document.createElement("label");
    labelPergunta.innerHTML = "Detalhamento da Pergunta:"
    labelPergunta.appendChild(inputPergunta);

    let inputResposta = document.createElement("textarea");
    inputResposta.type = "text";
    inputResposta.name = "resposta";
    inputResposta.placeholder = resposta;
    inputResposta.value = resposta;
    inputResposta.required = true;

    let labelResposta = document.createElement("label");
    labelResposta.innerHTML = "Resposta:"
    labelResposta.appendChild(inputResposta);

    let submit = document.createElement("button");
    submit.innerHTML = "Enviar";
    submit.type = "submit";

    form.appendChild(labelId);
    form.appendChild(labelTitulo);
    form.appendChild(labelPergunta);
    form.appendChild(labelResposta);
    form.appendChild(submit);
    popup.appendChild(botaoFechar);
    popup.appendChild(form);
    document.body.appendChild(popup);
}

function abrirPopupEditarPerguntaTicket(id, titulo, descricao) {

    if (document.querySelectorAll(".popup").length === 1)
        return;
    let popup = document.createElement("div");
    popup.classList.add("popup");

    let botaoFechar = document.createElement("button");
    botaoFechar.innerHTML = "&#10006"
    botaoFechar.addEventListener("click", () => fecharPopup(popup));

    let form = document.createElement("form");
    form.method = "POST";
    form.action = "/sam/EdicaoPerguntaTicketController";

    let inputId = document.createElement("input");
    inputId.type = "hidden";
    inputId.name = "id";
    inputId.value = id;

    let labelId = document.createElement("label");
    labelId.appendChild(inputId);

    let inputTitulo = document.createElement("input");
    inputTitulo.type = "text";
    inputTitulo.name = "titulo";
    inputTitulo.placeholder = titulo;
    inputTitulo.value = titulo;
    inputTitulo.required = true;

    let labelTitulo = document.createElement("label");
    labelTitulo.innerHTML = "Título:"
    labelTitulo.appendChild(inputTitulo);

    let inputDescricao = document.createElement("textarea");
    inputDescricao.type = "text";
    inputDescricao.name = "descricao";
    inputDescricao.placeholder = descricao;
    inputDescricao.value = descricao;

    let labelDescricao = document.createElement("label");
    labelDescricao.innerHTML = "Detalhamento da Descricao:"
    labelDescricao.appendChild(inputDescricao);

    let submit = document.createElement("button");
    submit.innerHTML = "Enviar";
    submit.type = "submit";

    form.appendChild(labelId);
    form.appendChild(labelTitulo);
    form.appendChild(labelDescricao);
    form.appendChild(submit);
    popup.appendChild(botaoFechar);
    popup.appendChild(form);
    document.body.appendChild(popup);
}

function abrirPopupEditarRespostaTicket(id, descricao) {

    if (document.querySelectorAll(".popup").length === 1)
        return;
    let popup = document.createElement("div");
    popup.classList.add("popup");

    let botaoFechar = document.createElement("button");
    botaoFechar.innerHTML = "&#10006"
    botaoFechar.addEventListener("click", () => fecharPopup(popup));

    let form = document.createElement("form");
    form.method = "POST";
    form.action = "/sam/EdicaoRespostaTicketController";

    let inputId = document.createElement("input");
    inputId.type = "hidden";
    inputId.name = "id";
    inputId.value = id;

    let labelId = document.createElement("label");
    labelId.appendChild(inputId);

    let inputDescricao = document.createElement("textarea");
    inputDescricao.type = "text";
    inputDescricao.name = "descricao";
    inputDescricao.placeholder = descricao;
    inputDescricao.value = descricao;

    let labelDescricao = document.createElement("label");
    labelDescricao.innerHTML = "Detalhamento da Descricao:"
    labelDescricao.appendChild(inputDescricao);

    let submit = document.createElement("button");
    submit.innerHTML = "Enviar";
    submit.type = "submit";

    form.appendChild(labelId);
    form.appendChild(labelDescricao);
    form.appendChild(submit);
    popup.appendChild(botaoFechar);
    popup.appendChild(form);
    document.body.appendChild(popup);

}

/*
* Função para fechar os popups
*/

function fecharPopup(popup) {
    document.body.removeChild(popup);
}