/*
* VALIDAÇÕES DE PREENCHIMENTO DE CAMPOS
*/

function validarCamposLogin(form) {
    let cpf = form.cpf.value;
    let senha = form.senha.value;
    let resultado = false;

    if (cpf === "") {
        gerarMensagemErro("Preencha o campo Usuário");
        form.cpf.focus();
    }
    else if (!validarCPF(cpf)) {
        gerarMensagemErro("CPF inválido");
        form.cpf.value = "";
        form.cpf.focus();
    }
    else if (senha === "") {
        gerarMensagemErro("Preencha o campo Senha");
        form.senha.focus();
    }
    else {
        form.action ="/sam/LoginController";
        form.submit();
        resultado = true;
    }

    return resultado
}

function validarCamposCadastro(form) {
    let nome = form.nome.value;
    let email = form.email.value;
    let cpf = form.cpf.value;
    let senha = form.senha.value;
    let senhaConfirmar = form.senhaConfirmar.value;
    let resultado = false;

    if (nome === "") {
        gerarMensagemErro("Preencha o campo Nome");
        form.nome.focus();
    }
    else if (email === "") {
        gerarMensagemErro("Preencha o campo E-mail");
        form.email.focus();
    }
    else if (!validarEmail(email)) {
        gerarMensagemErro("E-mail inválido");
        form.email.value = "";
        form.email.focus();
    }
    else if (cpf === "") {
        gerarMensagemErro("Preencha o campo CPF");
        form.cpf.focus();
    }
    else if (!validarCPF(cpf)) {
        gerarMensagemErro("CPF inválido");
        form.cpf.value = "";
        form.cpf.focus();
    }
    else if (senha === "") {
        gerarMensagemErro("Preencha o campo Senha");
        form.senha.focus();
    }
    else if (senhaConfirmar === "") {
        gerarMensagemErro("Confirme sua senha");
        form.senhaConfirmar.focus();
    }
    else if (senha !== senhaConfirmar) {
        gerarMensagemErro("Senhas diferentes");
        form.senhaConfirmar.value = "";
        form.senhaConfirmar.focus();
    }
    else {
        form.action ="/sam/CadastroController";
        form.submit();
        resultado = true;
    }

    return resultado;
}

function validarCamposAlteracaoPerfil(form) {
    let nome = form.nome.value;
    let email = form.email.value;
    let senha = form.senha.value;
    let senhaConfirmar = form.senhaConfirmar.value;
    let resultado = false;

    let strConfirmacao = "";
    if (nome !== "")
        strConfirmacao += "Nome ";
    if (email !== "")
        strConfirmacao += "Email ";
    if (senha !== "")
        strConfirmacao += "Senha";

    if (nome === "" && email === "" && senha === "") {
        gerarMensagemErro("Preencha algum dos campos");
        form.nome.focus();
    }
    else if (email !== "" && !validarEmail(email)) {
        gerarMensagemErro("E-mail inválido");
        form.email.value = "";
        form.email.focus();
    }
    else if (senha !== senhaConfirmar) {
        gerarMensagemErro("Senhas diferentes");
        form.senhaConfirmar.value = "";
        form.senhaConfirmar.focus();
    }
    else if (window.confirm(`Confirmar alterações?\n Itens alterados: ${strConfirmacao}`)) {
        form.action = "/sam/AlteracaoPerfilController";
        form.submit();
        resultado = true;
    }

    return resultado;
}

function validarCamposCadastroTransacao(form) {
    let cliente = form.cliente.value;
    let programa = form.programa.value;
    let quantidade = form.quantidade.value;
    let valor = form.valor.value;
    let bonus = form.bonus.value;
    let data = form.data.value;
    let dataExpiracao = "";
    let strConfirmacao = `Confirmar valores?\n Quantidade: ${quantidade}\n Valor: ${valor}\n Data: ${data}`;
    if (form.dataExpiracao) {
        dataExpiracao = form.dataExpiracao.value;
        strConfirmacao += `\n Data de Expiração: ${dataExpiracao}`;
    }

    let dataObj = new Date(data);
    let dataExpiracaoObj = new Date(dataExpiracao);

    if (bonus === "") {
        form.bonus.value = "0";
        bonus = form.bonus.value;
    } else if (bonus !== "0")
        strConfirmacao += `\n Bônus: ${bonus}`;

    if (cliente === "") {
        gerarMensagemErro("É necessário selecionar um cliente");
        return false;
    } else if (programa === "") {
        gerarMensagemErro("É necessário selecionar um programa");
        return false;
    } else if (dataExpiracao !== "" && dataObj.getTime() > dataExpiracaoObj.getTime()) {
        gerarMensagemErro("A data de expiração deve ser posterior à data da transação");
        return false;
    } else
        return window.confirm(strConfirmacao);
}

/*
* VALIDAÇÕES AUXILIARES
*/

function validarCPF(cpf) {
    if (cpf === "00000000000" || cpf.length !== 11 || isNaN(cpf))
        return false;

    let soma = 0;
    let resto;

    for (i = 1; i <= 9; i++)
        soma += parseInt(cpf.charAt(i - 1)) * (11 - i);
    resto = (soma * 10) % 11;
    if (resto === 10 || resto === 11)
        resto = 0;
    if (resto !== parseInt(cpf.charAt(9)))
        return false;

    soma = 0;
    for (i = 1; i <= 10; i++)
        soma += parseInt(cpf.charAt(i - 1)) * (12 - i);
    resto = (soma * 10) % 11;
    if (resto === 10 || resto === 11)
        resto = 0;
    return resto === parseInt(cpf.charAt(10));
}

function validarEmail(email) {
    let usuario = email.substring(0, email.indexOf("@"));
    let dominio = email.substring(email.indexOf("@") + 1, email.length);

    return usuario.length >= 1 &&
        dominio.length >= 1 &&
        usuario.indexOf("@") === -1 &&
        dominio.indexOf("@") === -1 &&
        usuario.indexOf(" ") === -1 &&
        dominio.indexOf(" ") === -1 &&
        dominio.indexOf(".") >= 1 &&
        dominio.lastIndexOf(".") < dominio.length - 1;
}

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

function validarSolicitarPagamento(form) {
    let arquivo = form.arquivo.value;
    let resultado = false;

    if (arquivo === "") {
        gerarMensagemErro("Adiciona um arquivo");
        form.cpf.focus();
    }
    else {
        form.action ="/sam/LoginController";
        form.submit();
        resultado = true;
    }

    return resultado
}