/*
* Funções para enviar formulário sem o uso de tags <form>
*/

function removerFaq(id) {
    if (!window.confirm("Deseja mesmo remover essa entrada do FAQ?"))
        return;
    let form = document.createElement("form");
    form.method = "POST";
    form.action = "/sam/RemocaoFaqController";

    let input = document.createElement("input");
    input.type = "hidden";
    input.name = "remover";
    input.value = id;

    form.appendChild(input);
    document.body.appendChild(form);
    form.submit();
}

function removerPergunta(id) {
    if (!window.confirm("Deseja mesmo remover essa pergunta?"))
        return;
    let form = document.createElement("form");
    form.method = "POST";
    form.action = "/sam/RemocaoTicketController";

    let input = document.createElement("input");
    input.type = "hidden";
    input.name = "remover";
    input.value = id;

    form.appendChild(input);
    document.body.appendChild(form);
    form.submit();
}

function removerResposta(id, pergunta) {
    if (!window.confirm("Deseja mesmo remover essa resposta?"))
        return;
    let form = document.createElement("form");
    form.method = "POST";
    form.action = "/sam/RemocaoRespostaTicketController";

    let inputId = document.createElement("input");
    inputId.type = "hidden";
    inputId.name = "remover";
    inputId.value = id;

    let inputPergunta = document.createElement("input");
    inputPergunta.type = "hidden";
    inputPergunta.name = "pergunta";
    inputPergunta.value = pergunta;

    form.appendChild(inputId);
    form.appendChild(inputPergunta);
    document.body.appendChild(form);
    form.submit();
}