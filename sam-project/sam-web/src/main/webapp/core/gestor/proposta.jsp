<%@ page import="java.util.List" %>
<%@ page import="sam.model.domain.Proposta" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    if (request.getAttribute("propostas") == null) {
        response.sendRedirect(request.getContextPath() + "/proposta");
        return;
    }
%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>SAM - Propostas</title>

    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
    <link rel="icon" href="/sam/imgs/favicon.ico">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap" rel="stylesheet">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>
<header>
    <img id="logotipo" src="<%= request.getContextPath() %>/imgs/logotipo.png" alt="Logotipo SAM">
    <h1>Propostas</h1>
    <%@include file="/core/header.jsp" %>
</header>

<main class="content">
    <h2>Propostas Cadastradas</h2>

    <%
        List<Proposta> propostas = (List<Proposta>) request.getAttribute("propostas");
        String erro = (String) request.getAttribute("erro");

        if (erro != null) {
    %>
    <p style="color:red;"><%= erro %></p>
    <%
        }
    %>

    <table>
        <tr>
            <th>ID</th>
            <th>Cliente</th>
            <th>Status</th>
            <th>Origem</th>
            <th>Destino</th>
            <th>Valor (R$)</th>
            <th>Milhas</th>
            <th>Taxas</th>
            <th>Ações</th>
        </tr>

        <%
            if (propostas != null && !propostas.isEmpty()) {
                for (Proposta proposta : propostas) {
        %>
        <tr>
            <td><%= proposta.getId() %></td>
            <td><%= proposta.getCliente().getNome() %></td>
            <td><%= proposta.getStatus() %></td>
            <td><%= proposta.getOrigem() %></td>
            <td><%= proposta.getDestino() %></td>
            <td>R$ <%= proposta.getValorEmDinheiro() %></td>
            <td><%= proposta.getValorEmMilhas() %></td>
            <td>R$ <%= proposta.getTaxas() %></td>

            <td>
                <a href="<%= request.getContextPath() %>/proposta?action=editar&id=<%= proposta.getId() %>">
                    <button>Editar</button>
                </a>

                <a href="<%= request.getContextPath() %>/proposta?action=excluir&id=<%= proposta.getId() %>">
                    <button>Excluir</button>
                </a>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="9">Nenhuma proposta cadastrada.</td>
        </tr>
        <%
            }
        %>
    </table>

    <a href="<%= request.getContextPath() %>/proposta?action=novo">
        <button>Adicionar Proposta</button>
    </a>
</main>

<script src="<%= request.getContextPath() %>/js/script.js"></script>
</body>
</html>
