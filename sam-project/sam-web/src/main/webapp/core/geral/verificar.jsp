<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <title>Verificação de conta</title>

    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/verificacao.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

<%
    Boolean sucesso = (Boolean) request.getAttribute("verificacaoSucesso");
    if (sucesso != null && sucesso) {
%>
<form id="cadastroForm" action="/sam/CadastroController" method="post">
    <input type="hidden" name="nome" value="<%= session.getAttribute("nome") %>">
    <input type="hidden" name="email" value="<%= session.getAttribute("email") %>">
    <input type="hidden" name="cpf" value="<%= session.getAttribute("cpf") %>">
    <input type="hidden" name="senha" value="<%= session.getAttribute("senha") %>">
</form>
<script>
    document.getElementById("cadastroForm").submit();
</script>
<%
    }
%>

<main>

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
    <section class="card">
        <h1>Verificação de conta</h1>
        <p class="small">Insira o código de 6 dígitos enviado para o seu e-mail.</p>

        <form id="verifyForm" action="/sam/VerificarCodigoController" method="POST" autocomplete="off">
            <div class="code-input">
                <input name="d1" maxlength="1" type="text" pattern="[0-9]*" inputmode="numeric" />
                <input name="d2" maxlength="1" type="text" pattern="[0-9]*" inputmode="numeric" />
                <input name="d3" maxlength="1" type="text" pattern="[0-9]*" inputmode="numeric" />
                <input name="d4" maxlength="1" type="text" pattern="[0-9]*" inputmode="numeric" />
                <input name="d5" maxlength="1" type="text" pattern="[0-9]*" inputmode="numeric" />
                <input name="d6" maxlength="1" type="text" pattern="[0-9]*" inputmode="numeric" />

                <input type="hidden" name="nome" value="<%= session.getAttribute("nome") %>">
                <input type="hidden" name="email" value="<%= session.getAttribute("email") %>">
                <input type="hidden" name="cpf" value="<%= session.getAttribute("cpf") %>">
                <input type="hidden" name="senha" value="<%= session.getAttribute("senha") %>">
            </div>

            <input type="hidden" name="destinoSucesso" value="CadastroController">
            <input type="hidden" name="destinoFalha" value="/core/geral/verificar.jsp">

            <button class="verify" type="submit">Verificar</button>
        </form>

        <div class="info">
            Não recebeu o código? Verifique a caixa de spam ou solicite um novo código após 60 segundos.
        </div>
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

<script src="<%= request.getContextPath() %>/js/helper.js"></script>
<script src="<%= request.getContextPath() %>/js/verificacao.js"></script>
</body>

</html>