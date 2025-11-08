<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="sam.controller.AcessosBlockController" %>
<%@page import="sam.model.dao.UsuarioDAO"%>

<% UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();%>

<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>SAM - Visulizar Usuários</title>

        <link rel="stylesheet" type="text/css" href="../../css/style.css">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link
            href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap"
            rel="stylesheet">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>
        <header>
            <img id="logotipo" src="../../imgs/logotipo.png" alt="Logotipo SAM">
            <h1>Permissões</h1>
            <%@include file="/core/header.jsp" %>
        </header>

        <main class="content">
            <% String id = request.getParameter("id");
            Usuario cliente = usuarioDAO.pesquisar(id);
            %>
            <h2>Gerenciar Permissões de <%=cliente.getNome()%></h2>
            <table>
                <tr>
                    <th>Permissão</th>
                    <th>Status</th>
                    <th>Ações</th>
                </tr>
                <%
                String[][] permissoes = {
                    {"dashboard-cliente", "Dashboard"},
                    {"transacoes-clientes", "Transações"},
                    {"empresas", "Empresas"},
                    {"notificacoes", "Notificações"},
                    {"suporte", "Suporte"},
                    {"perfil", "Perfil"},
                    {"computar-milhas", "Computar Milhas"},
                    {"dashboard-gestor", "Apuração(Dashboard Gestor)"},
                    {"associacoes", "Associações"},
                    {"orcamentos", "Orçamentos"},
                    {"planos", "Planos"},
                    {"mensagens-avisos", "Mensagens e Avisos"},
                    {"acesso-clientes", "Acesso aos Clientes"},
                    {"programa", "Programas de Fidelidade"},
                    {"avaliacoes", "Avaliações"},
                    {"relatorios", "Relatórios"},
                    {"transacoes-gestor", "Transações Gestor"},
                    {"campanhas", "Campanhas"}
                };

                for (String[] p : permissoes) {
                    String recurso = p[0];
                    String nome = p[1];

                    if (ControleAutorizacao.checkPermissao(recurso, cliente.getTipo())) {
                        boolean bloqueado = ControleAutorizacao.checkBloqueio(recurso, cliente.getCPF());
            %>
                        <tr>
                            <td><%= nome %></td>
                            <td><%= bloqueado ? "BLOQUEADO" : "ATIVO" %></td>
                            <td>
                                <% if (bloqueado) { %>
                                    <a class="button-a" href="/sam/AcessosBlockController?acao=Ativar&usuario=<%= cliente.getCPF() %>&recurso=<%= recurso %>">Ativar</a>
                                <% } else { %>
                                    <a class="button-a" href="/sam/AcessosBlockController?acao=Bloquear&usuario=<%= cliente.getCPF() %>&recurso=<%= recurso %>">Bloquear</a>
                                <% } %>
                            </td>
                        </tr>
            <%
                    }
                }
            %>
            </table>
        </main>
        <script src="../../js/script.js"></script>
    </body>

</html>