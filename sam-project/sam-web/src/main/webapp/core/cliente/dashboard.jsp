<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="sam.model.service.GestaoTransacoesService" %>
<%@ page import="sam.model.domain.Transacao" %>
<%@ page import="java.util.List" %>
<%@ page import="sam.model.helper.DataHelper" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="sam.model.domain.util.TransacaoTipo" %>

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


        <main class="dashboard">
            <section class="card">
                <h2>Saldo Total</h2>
                <p>128.500 milhas</p>
            </section>
            <section class="card">
                <details>
                    <%
                        GestaoTransacoesService manterTransacao = new GestaoTransacoesService();
                        List<Transacao> listaTransacoes = manterTransacao.listarPorCliente(usuario);
                    %>
                    <summary>
                        <h2>Últimas Transações</h2>
                        <ul>
                            <% for (int i = 0; i < 3; i++) { Transacao transacao = listaTransacoes.get(i); %>
                            <li><%= transacao.getTipo().toString() %> - <%= transacao.getQuantidade() + transacao.getBonus() %> milhas</li>
                            <%}%>
                        </ul>
                    </summary>

                    <table>
                        <tr>
                            <th>Data</th><th>Tipo</th><th>Quantidade</th><th>Valor (R$)</th><th>Bônus</th><th>Total</th>
                        </tr>
                        <%
                            BigDecimal valorTotal = BigDecimal.ZERO;
                            int quantidadeTotal = 0;
                            int bonusTotal = 0;
                        %>
                        <%
                            for (Transacao transacao : listaTransacoes) {
                                if (transacao.getTipo() == TransacaoTipo.COMPRA) {
                                    valorTotal = valorTotal.subtract(transacao.getValor());
                                    quantidadeTotal += transacao.getQuantidade();
                                    bonusTotal += transacao.getBonus();
                                } else {
                                    valorTotal = valorTotal.add(transacao.getValor());
                                    quantidadeTotal -= transacao.getQuantidade();
                                    bonusTotal -= transacao.getBonus();
                                }
                        %>
                        <tr>
                            <td>
                                <%= DataHelper.dataFormat1(transacao.getData().toString()) %>
                            </td>
                            <td>
                                <%= transacao.getTipo().toString() %>
                            </td>
                            <td>
                                <%= transacao.getQuantidade() %>
                            </td>
                            <td>
                                <%= transacao.getValor() %>
                            </td>
                            <td>
                                <%= transacao.getBonus() %>
                            </td>
                            <td>
                                <%= transacao.getQuantidade() + transacao.getBonus() %>
                            </td>
                        </tr>
                        <%}%>

                        <tr>
                            <td>
                                Total
                            </td>
                            <td>
                                -
                            </td>
                            <td>
                                <%= quantidadeTotal %>
                            </td>
                            <td>
                                <%= valorTotal %>
                            </td>
                            <td>
                                <%= bonusTotal %>
                            </td>
                            <td>
                                <%= quantidadeTotal + bonusTotal %>
                            </td>
                        </tr>
                    </table>
                </details>
            </section>
        </main>
        
        <script src="/sam/js/script.js"></script>
    </body>

</html>
