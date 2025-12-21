<%@ page import="sam.model.dao.PropostaDAO" %>
<%@ page import="sam.model.domain.Proposta" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Minhas Propostas</title>

    <link rel="stylesheet" href="../../css/style.css">
    <link rel="icon" href="/sam/imgs/favicon.ico">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>

<header>
    <img id="logotipo" src="../../imgs/logotipo.png" alt="Logotipo SAM">
    <h1>Minhas Propostas</h1>
    <%@include file="/core/header.jsp" %>
</header>

<%
    PropostaDAO pDAO = new PropostaDAO();
    List<Proposta> listaPropostas = pDAO.listarPorCliente(usuario.getId());
%>
<main class="dashboard">

    <section class="card" style="flex:1 1 100%;">
        <h2>Propostas Recebidas</h2>

        <table>
            <thead>
            <tr>
                <th>Origem ⭢ Destino</th>
                <th>Status</th>
                <th>Ações</th>
            </tr>
            </thead>

            <tbody>
            <% if (listaPropostas != null && !listaPropostas.isEmpty()) { %>
            <% for (Proposta p : listaPropostas) { %>
            <tr>
                <td><%= p.getOrigem() %> ⭢ <%= p.getDestino() %></td>

                <td><%= p.getStatus() %></td>

                <td class="acoes">
                    <a href="<%=request.getContextPath()%>/proposta?action=aprovar&id=<%=p.getId()%>">
                        <button>Aprovar</button>
                    </a>

                    <a href="<%=request.getContextPath()%>/proposta?action=cancelar&id=<%=p.getId()%>">
                        <button>Cancelar</button>
                    </a>

                    <a href="<%=request.getContextPath()%>/core/geral/detalhesProposta.jsp?id=<%=p.getId()%>">
                        <button>Detalhes</button>
                    </a>
                </td>
            </tr>
            <% } %>
            <% } else { %>
            <tr>
                <td colspan="3">Nenhuma proposta encontrada.</td>
            </tr>
            <% } %>
            </tbody>
        </table>

    </section>

</main>

<script src="../../js/script.js"></script>
</body>
</html>
