<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>

<head>
    <meta charset="UTF-8">
    <title>SAM - Tickets de Suporte</title>

    <link rel="stylesheet" type="text/css" href="/sam/css/style.css">
    <link rel="icon" href="/sam/imgs/favicon.ico">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>
    <header>
        <img id="logotipo" src="/sam/imgs/logotipo.png" alt="Logotipo SAM">
        <h1>Tickets de Suporte</h1>
        <%@include file="/core/header.jsp" %>
    </header>

    <main class="content">
        <h2>Tickets</h2>
        <form class="formulario">
            <label>Título:
                <input type="text" placeholder="Informe o problema">
            </label>
            <label>Descrição:
                <textarea placeholder="Descreva detalhadamente"></textarea>
            </label>
            <button>Enviar Ticket</button>
        </form>
    </main>
    <script src="/sam/js/script.js"></script>
</body>
</html>
