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
    form.action = "/sam/RemocaoPerguntaTicketController";

    let input = document.createElement("input");
    input.type = "hidden";
    input.name = "remover";
    input.value = id;

    form.appendChild(input);
    document.body.appendChild(form);
    form.submit();
}

function removerResposta(id) {
    if (!window.confirm("Deseja mesmo remover essa resposta?"))
        return;
    let form = document.createElement("form");
    form.method = "POST";
    form.action = "/sam/RemocaoRespostaTicketController";

    let input = document.createElement("input");
    input.type = "hidden";
    input.name = "remover";
    input.value = id;

    form.appendChild(input);
    document.body.appendChild(form);
    form.submit();
}