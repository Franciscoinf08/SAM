<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <title>Esqueci minha senha</title>

    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/verificacao.css">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>

<%
    String codigoDigitado = request.getParameter("codigoFinal");
    String codigoSessao = (String) session.getAttribute("codigoVerificacao");

    if (codigoDigitado != null) {
        if (codigoDigitado.equals(codigoSessao)) {
            request.getRequestDispatcher("core/geral/alterar-senha.jsp").forward(request, response);
        } else {
            request.setAttribute("erro", "Código incorreto. Tente novamente.");
            request.getRequestDispatcher("core/mensagens-erro.jsp").forward(request, response);
        }
    }
%>

<%
    String mensagem = (String) request.getAttribute("mensagem");
    if (mensagem != null) {
%>
<script>
    alert("<%= mensagem %>");
</script>
<%
    }
%>
<main>
    <section id="formEmail">
        <div class="formulario form-autenticacao">
            <h1>Recuperar Senha</h1>
            <form id="verifyForm" name="formRecuperacao" method="POST" action="/sam/RecuperarSenhaController">
                <label>
                    <p>Insira seu email para recuperação</p>
                    <input name="email" type="text" placeholder="seuemail@hotmail.com">
                </label>
                <button class="verify" type="submit">Verificar</button>
            </form>
        </div>
    </section>
    <section id="formCodigo" class="card" role="main">
        <h1>Recuperar Senha</h1>
        <section id="input-codigo">
            <p class="small">Insira o código de 6 dígitos enviado para o seu e-mail ou celular.</p>

            <form autocomplete="off" action="/sam/VerificarCodigoController" method="POST">
                <div class="code-input">
                    <input maxlength="1" type="text" pattern="[0-9]*" inputmode="numeric" />
                    <input maxlength="1" type="text" pattern="[0-9]*" inputmode="numeric" />
                    <input maxlength="1" type="text" pattern="[0-9]*" inputmode="numeric" />
                    <input maxlength="1" type="text" pattern="[0-9]*" inputmode="numeric" />
                    <input maxlength="1" type="text" pattern="[0-9]*" inputmode="numeric" />
                    <input maxlength="1" type="text" pattern="[0-9]*" inputmode="numeric" />
                </div>

                <input type="hidden" name="destinoSucesso" value="AlterarSenhaController">
                <input type="hidden" name="destinoFalha" value="index.jsp">

                <button class="verify" type="submit">Verificar</button>
            </form>

            <div class="info">
                Não recebeu o código? Verifique a caixa de spam ou solicite um novo código após 60 segundos.
            </div>
        </section>
    </section>


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

        const inputs = document.querySelectorAll(".code-input input");
        if (inputs.length > 0) inputs[0].focus();
        <% } %>
    });
</script>


<script src="<%= request.getContextPath() %>/js/verificacao.js"></script>

</body>
</html>
