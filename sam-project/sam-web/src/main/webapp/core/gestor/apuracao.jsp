<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="sam.model.service.GestaoUsuariosService" %>
<%@ page import="sam.model.service.GestaoTransacoesService" %>
<%@ page import="sam.model.domain.Usuario" %>
<%@ page import="sam.model.domain.Transacao" %>
<%@ page import="sam.model.helper.DataHelper" %>
<%@ page import="sam.model.helper.SaldoHelper" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.sql.SQLException" %>

<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>SAM - Apuração de Resultados</title>

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
            <h1>Apuração de Resultados</h1>
            <%@include file="/core/header.jsp" %>
        </header>

        <%
            GestaoUsuariosService manterUsuario = new GestaoUsuariosService();
            GestaoTransacoesService manterTransacao = new GestaoTransacoesService();
            List<Usuario> listaClientes = manterUsuario.getListaClientes(usuario);

            String idClienteUnparsed = request.getParameter("cliente");
            Long idCliente;

            try {
                idCliente = Long.parseLong(idClienteUnparsed);
            } catch (RuntimeException e) {
                idCliente = null;
            }
        %>

        <main>
            <section class="card formulario">
                <h2>Filtros</h2>
                <form method="POST">
                    <label for="cliente">Cliente:
                        <select name="cliente">
                            <option value="todos" <%if (idClienteUnparsed == "todos") { %>selected<% } %>>
                                Todos
                            </option>
                            <% for (Usuario cliente : listaClientes) {%>
                            <option value="<%= cliente.getId() %>" <%if (idCliente == cliente.getId()) {%>selected<%}%>>
                                <%= cliente.getNome() %>
                            </option>
                            <%}%>
                        </select>
                    </label>

                    <button>Filtrar</button>
                </form>
            </section>

            <section class="tabela-container">
                <h2>Resultados</h2>
                <table>
                    <% if (listaClientes.isEmpty()) { %>
                    <th colspan="7">Ainda não há clientes</th>

                    <% } else if (idCliente == null) { %>
                    <tr>
                        <th>Cliente</th>
                        <th>CPF</th>
                        <th>Milhas Compradas</th>
                        <th>Milhas Vendidas</th>
                        <th>Bônus</th>
                        <th>Saldo de Milhas</th>
                        <th>Saldo de Dinheiro (R$)</th>
                    </tr>
                    <% for (Usuario cliente : listaClientes) { %>
                    <tr>
                        <td><%= cliente.getNome() %></td>
                        <td><%= cliente.getCPF() %></td>
                        <td><%= SaldoHelper.getMilhasCompradasCliente(cliente) %></td>
                        <td><%= SaldoHelper.getMilhasVendidasCliente(cliente) %></td>
                        <td><%= SaldoHelper.getBonusCliente(cliente) %></td>
                        <td><%= SaldoHelper.getSaldoMilhasCliente(cliente) %></td>
                        <td><%= SaldoHelper.getSaldoDinheiroCliente(cliente) %></td>
                    </tr>

                    <%
                        }} else {
                            Usuario cliente = null;
                            List<Transacao> listaTransacoes = null;

                            int quantidadeTotal = 0;
                            int bonusTotal = 0;
                            int milhasTotal = 0;
                            BigDecimal valorTotal = BigDecimal.ZERO;
                            try {
                                cliente = manterUsuario.pesquisar(idCliente);
                                listaTransacoes = manterTransacao.listarAtivasPorCliente(cliente);

                                quantidadeTotal = SaldoHelper.getSaldoQuantidadeCliente(cliente);
                                bonusTotal = SaldoHelper.getBonusCliente(cliente);
                                milhasTotal = quantidadeTotal + bonusTotal;
                                valorTotal = SaldoHelper.getSaldoDinheiroCliente(cliente);
                            } catch (SQLException e) {
                                e.printStackTrace();
                                request.setAttribute("erro", "Não foi possível acessar os dados do cliente");
                            } catch (RuntimeException e) {
                                e.printStackTrace();
                                request.setAttribute("erro", "Dados inválidos");
                            }
                    %>
                    <tr>
                        <th>Data</th>
                        <th>Data de Expiração</th>
                        <th>Programa de Fidelidade</th>
                        <th>Tipo</th>
                        <th>Quantidade</th>
                        <th>Valor (R$)</th>
                        <th>Bônus</th>
                        <th>Total</th>
                        <th>Ação</th>
                    </tr>
                    <%if (listaTransacoes == null || listaTransacoes.isEmpty()) {%>
                    <tr>
                        <td colspan="9">Ainda não há transações</td>
                    </tr>
                    <%} else { %>
                    <% for (Transacao transacao : listaTransacoes) { %>
                    <tr>
                        <td><%= DataHelper.dataFormat1(transacao.getData().toString()) %></td>
                        <td>
                            <%
                                LocalDate dataExpiracao = LocalDate.parse(transacao.getDataExpiracao().toString());
                                boolean expirando = DataHelper.verificarProximidadeAgora(dataExpiracao, 1);
                                if (expirando) {
                            %><span style="color:red;font-weight:bold;"><%}%>
                            <%= DataHelper.dataFormat1(transacao.getDataExpiracao().toString()) %>
                            <% if (expirando) {%></span><%}%>
                        </td>
                        <td><%= transacao.getIdProgramaFidelidade() %></td>
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
                    <td>-</td>
                    <td>-</td>
                    <td><%= quantidadeTotal %></td>
                    <td><%= valorTotal %></td>
                    <td><%= bonusTotal %></td>
                    <td><%= milhasTotal %></td>
                    <td>-</td>
                </tr>
                <%}}%>

                </table>
            </section>
        </main>

        <script src="/sam/js/script.js"></script>
    </body>

</html>
