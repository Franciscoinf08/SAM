<%@ page import="java.util.List" %>
<%@ page import="sam.model.domain.Notificacao" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
<head>
    <meta charset="UTF-8">
    <title>SAM - Notificações</title>

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/notificacao.css">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<header>
    <img id="logotipo" src="<%=request.getContextPath()%>/imgs/logotipo.png" alt="Logotipo SAM">
    <h1>Notificações</h1>
    <%@include file="/core/header.jsp" %>
</header>

<main>
    <%
        List<Notificacao> notificacoes = (List<Notificacao>) request.getAttribute("listaNotificacoes");

    %>
    <h2>Notificações</h2>

    <ul class="notificacoes">
    <%
        if(!notificacoes.isEmpty()){
            for (Notificacao n : notificacoes) {
                if (!n.isLida()){
    %>

        <li class="notificacao nao-lida">
            <div class="notificacao-topo">
                <h3 class="titulo"><%=n.getTitulo()%></h3>
                <span class="data"><%=n.getData()%></span>
            </div>

            <p class="mensagem">
                <%=n.getResumo()%>
                <a href="<%=request.getContextPath()%>/notificacoes?action=detalhar" class="mais">mostrar mais</a>
            </p>

            <a href="<%=request.getContextPath()%>/notificacoes?action=marcarComoLida&idNotificacao=<%=n.getId()%>"><button class="btn-lida">Marcar como lida</button></a>
        </li>
    <%
                }
            }
        } else {
    %>
        </ul>


        <p>voce nao tem notificacoes no momento</p>
    <%}%>
</main>
<script src="<%= request.getContextPath() %>/js/script.js"></script>
</body>
</html>
