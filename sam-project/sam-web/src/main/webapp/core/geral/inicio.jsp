<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>SAM - Início</title>

        <link rel="stylesheet" type="text/css" href="/sam/css/style.css">
        <link rel="stylesheet" type="text/css" href="/sam/css/pesquisar.css">
        <link rel="icon" href="/sam/imgs/favicon.ico">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>
        <header>
            <img id="logotipo" src="/sam/imgs/logotipo.png" alt="Logotipo SAM">
            <h1>Início</h1>
            <%@include file="/core/header.jsp" %>
        </header>

        <main class="main-inicio">
            <div id="imagem-inicial">

            </div>
            <div id="corpo-inicio">
                <input type="text" id="searchInput" placeholder="Pesquisar...">
                <ul id="itemsList">
                    <li>Maçã</li>
                    <li>Banana</li>
                    <li>Laranja</li>
                    <li>Abacate</li>
                    <li>Uva</li>
                </ul>
            </div>

        </main>

        <%@include file="/core/mensagens-erro.jsp"%>

        <script src="/sam/js/script.js"></script>
        <script src="/sam/js/pesquisar.js"></script>
    </body>

</html>