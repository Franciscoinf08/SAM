<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>

<head>
    <meta charset="UTF-8">
    <title>SAM - FAQ</title>

    <link rel="stylesheet" type="text/css" href="/sam/css/style.css">
    <link rel="stylesheet" type="text/css" href="/sam/css/login.css">
    <link rel="icon" href="/sam/imgs/favicon.ico">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>
    <header>
        <img id="logotipo" src="/sam/imgs/logotipo.png" alt="Logotipo SAM">
        <h1>FAQ</h1>
        <% if (request.getSession().getAttribute("usuario") != null) {%>
        <%@include file="/core/header.jsp" %>
        <%}%>
    </header>
</body>
</html>
