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
    if (nome !== "") {
        strConfirmacao += "Nome ";
    } if (email !== "") {
        strConfirmacao += "Email ";
    } if (senha !== "") {
        strConfirmacao += "Senha";
    } if (strConfirmacao !== "") {
        strConfirmacao.replaceAll(" ", ", ")
    }

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
    else if (window.confirm(`Confirmar alterações?\n Itens alterados: ${strConfirmacao}`)){
        form.action ="/sam/AlteracaoPerfilController";
        form.submit();
        resultado = true;
    }

    return resultado;
}

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
