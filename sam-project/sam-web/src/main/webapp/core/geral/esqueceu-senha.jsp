<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <title>Esqueci minha senha</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/verificacao.css">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>

<header>
    <img id="logotipo" alt="Logo do Sistema" src="${pageContext.request.contextPath}/imgs/logotipo.png">
    <h1>Notificações</h1>
</header>

<main>
    <div>
        <h2>Recuperação de Senha</h2>
        <div class="formulario form-autenticacao">
            <form id="formEmail" name="formRecuperacao" method="POST" action="/sam/RecuperarSenhaController">
                <label>
                    <p>Insira seu email para recuperação</p>
                    <input name="email" type="text" placeholder="seuemail@hotmail.com">
                </label>
                <button class="verify" type="submit">Verificar</button>
            </form>
        </div>
    </div>
    <div class="verificacao">
        <p class="small">Insira o código de 6 dígitos enviado para o seu e-mail.</p>

        <form id="formCodigo" action="/sam/core/geral/verificar.jsp" method="post" autocomplete="off" style="display:none;">
            <div class="code-input">
                <input name="d1" maxlength="1" type="text" pattern="[0-9]*" inputmode="numeric" />
                <input name="d2" maxlength="1" type="text" pattern="[0-9]*" inputmode="numeric" />
                <input name="d3" maxlength="1" type="text" pattern="[0-9]*" inputmode="numeric" />
                <input name="d4" maxlength="1" type="text" pattern="[0-9]*" inputmode="numeric" />
                <input name="d5" maxlength="1" type="text" pattern="[0-9]*" inputmode="numeric" />
                <input name="d6" maxlength="1" type="text" pattern="[0-9]*" inputmode="numeric" />
            </div>

            <input type="hidden" id="codigoFinal" name="codigoFinal">
            <button class="verify" type="submit">Verificar</button>
        </form>

        <div class="info">
            Não recebeu o código? Verifique a caixa de spam ou solicite um novo código após 60 segundos.
        </div>
    </div>


</main>

<%
    String erro = (String) request.getAttribute("erro");
    if (erro != null) {%>
<div class="mensagem-card"><%=erro%></div>
<script>
    let mensagemEl = document.querySelector(".mensagem-card");
    setTimeout(() => { mensagemEl.style.top = "4em"; }, 1);
    setTimeout(() => { mensagemEl.style.display = "none"; }, 4000);
</script>
<%}%>

<%
    Boolean emailEnviado = (Boolean) request.getAttribute("emailEnviado");
%>

<script>
    window.addEventListener("DOMContentLoaded", () => {
        const formEmail = document.getElementById("formEmail");
        const formCodigo = document.getElementById("formCodigo");

        <% if (emailEnviado != null && emailEnviado) { %>
        formEmail.style.display = "none";
        formCodigo.style.display = "block";

        // Foco no primeiro input do código
        const inputs = document.querySelectorAll(".code-input input");
        if (inputs.length > 0) inputs[0].focus();
        <% } %>
    });
</script>

<script src="${pageContext.request.contextPath}/js/helper.js"></script>
<script src="${pageContext.request.contextPath}/js/verificacao.js"></script>


</body>
</html>
