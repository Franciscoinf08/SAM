<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="sam.model.domain.Notificacao"%>

<html>
<head>
    <meta charset="UTF-8">
    <title>SAM - Notificações</title>

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<header>
    <img id="logotipo" src="../../imgs/logotipo.png" alt="Logotipo SAM">
    <h1>Notificações</h1>
    <%@include file="/core/header.jsp" %>
</header>

<main>
    <h2>Notificações</h2>

    <table border="1">
        <tr>
            <th>Título</th>
            <th>Mensagem</th>
            <th>Data</th>
            <th>Status</th>
            <th>Ação</th>
        </tr>

        <%
            List<Notificacao> lista = (List<Notificacao>) request.getAttribute("lista");

            if (lista != null && !lista.isEmpty()) {
                for (Notificacao n : lista) {
        %>
        <tr>
            <td><%= n.getTitulo() %></td>
            <td><%= n.getMensagem() %></td>
            <td><%= n.getDataDoEnvio() %></td>
            <td><%= n.isLida() ? "Lida" : "Nova" %></td>
            <td>
                <% if (!n.isLida()) { %>
                <form method="post" action="<%= request.getContextPath() %>/notificacoes">
                    <input type="hidden" name="acao" value="lida">
                    <input type="hidden" name="id" value="<%= n.getId() %>">
                    <button type="submit">Marcar como lida</button>
                </form>
                <% } %>
            </td>
        </tr>

        <%
            }
        } else {
        %>

        <tr>
            <td colspan="5">Nenhuma notificação encontrada.</td>
        </tr>

        <%
            }
        %>

    </table>
</main>
</body>
</html>
