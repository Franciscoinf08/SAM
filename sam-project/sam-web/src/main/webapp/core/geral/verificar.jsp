<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <title>Verificação de conta</title>

    <link rel="stylesheet" type="text/css" href="/sam/css/verificacao.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<main class="card" role="main">
    <h1>Verificação de conta</h1>
    <button id="enviar-codigo" type="submit">Enviar codigo</button>
    <section id="input-codigo">
        <p class="small">Insira o código de 6 dígitos enviado para o seu e-mail ou celular.</p>

        <form id="verifyForm" autocomplete="off" action="sam/UserVerify">
            <div class="code-input">
                <input maxlength="1" type="text" pattern="[0-9]*" inputmode="numeric" />
                <input maxlength="1" type="text" pattern="[0-9]*" inputmode="numeric" />
                <input maxlength="1" type="text" pattern="[0-9]*" inputmode="numeric" />
                <input maxlength="1" type="text" pattern="[0-9]*" inputmode="numeric" />
                <input maxlength="1" type="text" pattern="[0-9]*" inputmode="numeric" />
                <input maxlength="1" type="text" pattern="[0-9]*" inputmode="numeric" />
            </div>

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

<script src="/sam/js/helper.js"></script>
<script src="/sam/js/verificacao.js"></script>
</body>

</html>