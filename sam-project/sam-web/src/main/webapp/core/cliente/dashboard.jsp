<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="sam.model.service.GestaoTransacoesService" %>
<%@ page import="sam.model.domain.Transacao" %>
<%@ page import="sam.model.helper.DataHelper" %>
<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.sql.SQLException" %>

<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>SAM - Dashboard</title>

        <link rel="stylesheet" type="text/css" href="/sam/css/style.css">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>
        <header>
            <img id="logotipo" src="/sam/imgs/logotipo.png" alt="Logotipo SAM">
            <h1>Dashboard</h1>
            <%@include file="/core/header.jsp" %>
        </header>


        <main>
            <%
                GestaoTransacoesService manterTransacao = new GestaoTransacoesService();
                List<Transacao> listaTransacoes = manterTransacao.listarPorCliente(usuario);

                int quantidadeTotal = 0;
                int bonusTotal = 0;
                int milhasTotal = 0;
                BigDecimal valorTotal = BigDecimal.ZERO;
                try {
                    quantidadeTotal = manterTransacao.getQuantidadeTotalCliente(usuario);
                    bonusTotal = manterTransacao.getBonusTotalCliente(usuario);
                    milhasTotal = quantidadeTotal + bonusTotal;
                    valorTotal = manterTransacao.getValorTotalCliente(usuario);
                } catch (SQLException e) {
                    e.printStackTrace();
                    request.setAttribute("erro", "Não foi possível acessar os dados do cliente");
                }
            %>
            <section class="indicadores">
                <article class="card">
                    <h2>Saldo Total</h2>
                    <ul>
                        <li>
                            <%= milhasTotal %>
                             milha<% if (milhasTotal != 1){ %>s<%}%>
                        </li>
                        <li>
                            <% if (!valorTotal.equals(valorTotal.abs())) { %>-<%}%>R$ <%= valorTotal.abs() %>
                        </li>
                    </ul>
                </article>
                <article class="card">
                    <h2>Últimas Transações</h2>
                    <%if (listaTransacoes.isEmpty()) { %>
                    Ainda não há transações
                    <%} else { %>
                    <ul>
                        <%
                            int k = Math.min(listaTransacoes.size(), 3);
                        %>
                        <% for (Transacao transacao : listaTransacoes.subList(0, k)) { %>
                        <li>
                            <%= transacao.getTipo().toString() %> - <%= transacao.getQuantidade() + transacao.getBonus() %> milha<% if (milhasTotal != 1){ %>s<%}%>
                        </li>
                        <%}%>
                    </ul>
                    <%}%>
                </article>
            </section>
            <section class="tabela-container">
                <table>
                    <tr>
                        <th>Data</th><th>Tipo</th><th>Quantidade</th><th>Valor (R$)</th><th>Bônus</th><th>Total</th><th>Ação</th>
                    </tr>
                    <%if (listaTransacoes.isEmpty()) {%>
                    <tr>
                        <td colspan="7">Ainda não há transações</td>
                    </tr>
                    <%} else { %>
                    <% for (Transacao transacao : listaTransacoes) { %>
                    <tr>
                        <td><%= DataHelper.dataFormat1(transacao.getData().toString()) %></td>
                        <td><%= transacao.getTipo().toString() %></td>
                        <td><%= transacao.getQuantidade() %></td>
                        <td><%= transacao.getValor() %></td>
                        <td>
                            <% if (transacao.getBonus() != 0) {%>
                            <%= transacao.getBonus() %>
                            <%} else {%>
                            -
                            <%}%>
                        </td>
                        <td><%= transacao.getQuantidade() + transacao.getBonus() %></td>
                        <td>
                            <form name="formRemocaoTransacao" onsubmit="return window.confirm('Deseja mesmo remover a transação?');" action="/sam/RemocaoTransacaoController" method="POST" >
                                <button value="<%= transacao.getId() %>" name="remover">Remover</button>
                            </form>
                        </td>
                    </tr>
                    <%}%>
                    <tr>
                        <td>Saldo Total</td>
                        <td>-</td>
                        <td><%= quantidadeTotal %></td>
                        <td><%= valorTotal %></td>
                        <td><%= bonusTotal %></td>
                        <td><%= milhasTotal %></td>
                        <td>-</td>
                    </tr>
                    <%}%>
                </table>
            </section>
        </main>

        <%@include file="/core/mensagens-erro.jsp"%>

        <script src="/sam/js/script.js"></script>
    </body>

</html>
