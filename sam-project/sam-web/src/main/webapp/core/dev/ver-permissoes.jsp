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
            <!-- BUSCAR INFORMAÇÕES DO USUÁRIO ENVIADO POR CPF -->
            <h2>Gerenciar Permissões de <!-- NOME DO USUARIO --></h2>
            <table>
                <tr>
                    <th>Permissão</th>
                    <th>Status</th>
                    <th>Ações</th>
                </tr>
                </tr>
                <tr>
                    <% if (ControleAutorizacao.checkPermissao("dashboard-cliente", cliente.getTipo())) {%>
                    <td>Dashboard</td>
                    <% if (ControleAutorizacao.checkBloqueio("dashboard-cliente", cliente.getCPF())) {%>
                    <td>BLOQUEADO</td>
                    <td>
                        <button><a href='/sam/AcessosBlockController?acao=Ativar&usuario=<%= cliente.getCPF() %>&recurso=dashboard-clientes'>Ativar</a></button>
                    </td>
                    <% } else {%>
                    <td>ATIVO</td>
                    <td>
                        <button><a
                                href='/sam/AcessosBlockController?acao=Bloquear&usuario=<%= cliente.getCPF() %>&recurso=dashboard-clientes'>Bloquear</a></button>
                    </td>
                    <%}} if (ControleAutorizacao.checkPermissao("transacoes-clientes", cliente.getTipo())) {%>
                    <td>Transações</td>
                    <% if (ControleAutorizacao.checkBloqueio("transacoes-clientes", cliente.getCPF())) {%>
                    <td>BLOQUEADO</td>
                    <td>
                        <button><a href='/sam/AcessosBlockController?acao=Ativar&usuario=<%= cliente.getCPF() %>&recurso=transacoes-clientes'>Ativar</a></button>
                    </td>
                    <% } else {%>
                    <td>ATIVO</td>
                    <td>
                        <button><a
                                href='/sam/AcessosBlockController?acao=Bloquear&usuario=<%= cliente.getCPF() %>&recurso=transacoes-clientes'>Bloquear</a></button>
                    </td>
                    <%}}if (ControleAutorizacao.checkPermissao("empresas", cliente.getTipo())) {%> 
                    <td>Empresas</td>
                    <% if (ControleAutorizacao.checkBloqueio("empresas", cliente.getCPF())) {%>
                    <td>BLOQUEADO</td>
                    <td>
                        <button><a href='/sam/AcessosBlockController?acao=Ativar&usuario=<%= cliente.getCPF() %>&recurso=empresas'>Ativar</a></button>
                    </td>
                    <% } else {%>
                    <td>ATIVO</td>
                    <td>
                        <button><a
                                href='/sam/AcessosBlockController?acao=Bloquear&usuario=<%= cliente.getCPF() %>&recurso=empresas'>Bloquear</a></button>
                    </td>
                    <%}}if (ControleAutorizacao.checkPermissao("notificacoes", cliente.getTipo())) {%>
                    <td>Notificacoes</td>
                    <% if (ControleAutorizacao.checkBloqueio("notificacoes", cliente.getCPF())) {%>
                    <td>BLOQUEADO</td>
                    <td>
                        <button><a href='/sam/AcessosBlockController?acao=Ativar&usuario=<%= cliente.getCPF() %>&recurso=notificacoes'>Ativar</a></button>
                    </td>
                    <% } else {%>
                    <td>ATIVO</td>
                    <td>
                        <button><a
                                href='/sam/AcessosBlockController?acao=Bloquear&usuario=<%= cliente.getCPF() %>&recurso=notificacoes'>Bloquear</a></button>
                    </td>
                    <%}}if (ControleAutorizacao.checkPermissao("suporte", cliente.getTipo())) {%>
                    <td>Suporte</td>
                    <% if (ControleAutorizacao.checkBloqueio("suporte", cliente.getCPF())) {%>
                    <td>BLOQUEADO</td>
                    <td>
                        <button><a href='/sam/AcessosBlockController?acao=Ativar&usuario=<%= cliente.getCPF() %>&recurso=suporte'>Ativar</a></button>
                    </td>
                    <% } else {%>
                    <td>ATIVO</td>
                    <td>
                        <button><a
                                href='/sam/AcessosBlockController?acao=Bloquear&usuario=<%= cliente.getCPF() %>&recurso=suporte'>Bloquear</a></button>
                    </td>
                    <%}}if (ControleAutorizacao.checkPermissao("perfil", cliente.getTipo())) {%>
                    <td>Perfil</td>
                    <% if (ControleAutorizacao.checkBloqueio("perfil", cliente.getCPF())) {%>
                    <td>BLOQUEADO</td>
                    <td>
                        <button><a href='/sam/AcessosBlockController?acao=Ativar&usuario=<%= cliente.getCPF() %>&recurso=perfil'>Ativar</a></button>
                    </td>
                    <% } else {%>
                    <td>ATIVO</td>
                    <td>
                        <button><a
                                href='/sam/AcessosBlockController?acao=Bloquear&usuario=<%= cliente.getCPF() %>&recurso=perfil'>Bloquear</a></button>
                    </td>
                    <%}}if (ControleAutorizacao.checkPermissao("computar-milhas", cliente.getTipo())) {%>
                    <td>Computar Milhas</td>
                    <% if (ControleAutorizacao.checkBloqueio("computar-milhas", cliente.getCPF())) {%>
                    <td>BLOQUEADO</td>
                    <td>
                        <button><a href='/sam/AcessosBlockController?acao=Ativar&usuario=<%= cliente.getCPF() %>&recurso=computar-milhas'>Ativar</a></button>
                    </td>
                    <% } else {%>
                    <td>ATIVO</td>
                    <td>
                        <button><a
                                href='/sam/AcessosBlockController?acao=Bloquear&usuario=<%= cliente.getCPF() %>&recurso=computar-milhas'>Bloquear</a></button>
                    </td>
                    <%}}if (ControleAutorizacao.checkPermissao("dashboard-gestor", cliente.getTipo())) {%>
                    <td>Dashboard</td>
                    <% if (ControleAutorizacao.checkBloqueio("dashboard-gestor", cliente.getCPF())) {%>
                    <td>BLOQUEADO</td>
                    <td>
                        <button><a href='/sam/AcessosBlockController?acao=Ativar&usuario=<%= cliente.getCPF() %>&recurso=dashboard-gestor'>Ativar</a></button>
                    </td>
                    <% } else {%>
                    <td>ATIVO</td>
                    <td>
                        <button><a
                                href='/sam/AcessosBlockController?acao=Bloquear&usuario=<%= cliente.getCPF() %>&recurso=dashboard-gestor'>Bloquear</a></button>
                    </td>
                    <%}}if (ControleAutorizacao.checkPermissao("associacoes", cliente.getTipo())) {%>
                    <td>Associar Clientes</td>
                    <% if (ControleAutorizacao.checkBloqueio("associacoes", cliente.getCPF())) {%>
                    <td>BLOQUEADO</td>
                    <td>
                        <button><a href='/sam/AcessosBlockController?acao=Ativar&usuario=<%= cliente.getCPF() %>&recurso=associacoes'>Ativar</a></button>
                    </td>
                    <% } else {%>
                    <td>ATIVO</td>
                    <td>
                        <button><a
                                href='/sam/AcessosBlockController?acao=Bloquear&usuario=<%= cliente.getCPF() %>&recurso=associacoes'>Bloquear</a></button>
                    </td>
                    <%}}if (ControleAutorizacao.checkPermissao("orcamentos", cliente.getTipo())) {%>
                    <td>Orcamentos</td>
                    <% if (ControleAutorizacao.checkBloqueio("orcamentos", cliente.getCPF())) {%>
                    <td>BLOQUEADO</td>
                    <td>
                        <button><a href='/sam/AcessosBlockController?acao=Ativar&usuario=<%= cliente.getCPF() %>&recurso=orcamentos'>Ativar</a></button>
                    </td>
                    <% } else {%>
                    <td>ATIVO</td>
                    <td>
                        <button><a
                                href='/sam/AcessosBlockController?acao=&usuario=<%= cliente.getCPF() %>&recurso=orcamentos'>Bloquear</a></button>
                    </td>
                    <%}}if (ControleAutorizacao.checkPermissao("planos", cliente.getTipo())) {%>
                    <td>Planos</td>
                    <% if (ControleAutorizacao.checkBloqueio("planos", cliente.getCPF())) {%>
                    <td>BLOQUEADO</td>
                    <td>
                        <button><a href='/sam/AcessosBlockController?acao=Ativar&usuario=<%= cliente.getCPF() %>&recurso=planos'>Ativar</a></button>
                    </td>
                    <% } else {%>
                    <td>ATIVO</td>
                    <td>
                        <button><a
                                href='/sam/AcessosBlockController?acao=Bloquear&usuario=<%= cliente.getCPF() %>&recurso=planos'>Bloquear</a></button>
                    </td>
                    <%}}if (ControleAutorizacao.checkPermissao("mensagens-avisos", cliente.getTipo())) {%>
                    <td>Enviar mensagens e avisos</td>
                    <% if (ControleAutorizacao.checkBloqueio("mensagens-avisos", cliente.getCPF())) {%>
                    <td>BLOQUEADO</td>
                    <td>
                        <button><a href='/sam/AcessosBlockController?acao=Ativar&usuario=<%= cliente.getCPF() %>&recurso=mensagens-avisos'>Ativar</a></button>
                    </td>
                    <% } else {%>
                    <td>ATIVO</td>
                    <td>
                        <button><a
                                href='/sam/AcessosBlockController?acao=Bloquear&usuario=<%= cliente.getCPF() %>&recurso=mensagens-avisos'>Bloquear</a></button>
                    </td>
                    <%}}if (ControleAutorizacao.checkPermissao("acesso-clientes", cliente.getTipo())) {%>
                    <td>Acesso aos clientes</td>
                    <% if (ControleAutorizacao.checkBloqueio("acesso-clientes", cliente.getCPF())) {%>
                    <td>BLOQUEADO</td>
                    <td>
                        <button><a href='/sam/AcessosBlockController?acao=Ativar&usuario=<%= cliente.getCPF() %>&recurso=acesso-clientes'>Ativar</a></button>
                    </td>
                    <% } else {%>
                    <td>ATIVO</td>
                    <td>
                        <button><a
                                href='/sam/AcessosBlockController?acao=Bloquear&usuario=<%= cliente.getCPF() %>&recurso=acesso-clientes'>Bloquear</a></button>
                    </td>
                    <%}}if (ControleAutorizacao.checkPermissao("programa", cliente.getTipo())) {%>
                    <td>Programas de fidelidade</td>
                    <% if (ControleAutorizacao.checkBloqueio("programa", cliente.getCPF())) {%>
                    <td>BLOQUEADO</td>
                    <td>
                        <button><a href='/sam/AcessosBlockController?acao=Ativar&usuario=<%= cliente.getCPF() %>&recurso=prorama'>Ativar</a></button>
                    </td>
                    <% } else {%>
                    <td>ATIVO</td>
                    <td>
                        <button><a
                                href='/sam/AcessosBlockController?acao=Bloquear&usuario=<%= cliente.getCPF() %>&recurso=programa'>Bloquear</a></button>
                    </td>
                    <%}}if (ControleAutorizacao.checkPermissao("avaliacoes", cliente.getTipo())) {%>
                    <td>Avaliações</td>
                    <% if (ControleAutorizacao.checkBloqueio("avaliacoes", cliente.getCPF())) {%>
                    <td>BLOQUEADO</td>
                    <td>
                        <button><a href='/sam/AcessosBlockController?acao=Ativar&usuario=<%= cliente.getCPF() %>&recurso=avaliacoes'>Ativar</a></button>
                    </td>
                    <% } else {%>
                    <td>ATIVO</td>
                    <td>
                        <button><a
                                href='/sam/AcessosBlockController?acao=Bloquear&usuario=<%= cliente.getCPF() %>&recurso=avaliacoes'>Bloquear</a></button>
                    </td>
                    <%}}if (ControleAutorizacao.checkPermissao("relatorios", cliente.getTipo())) {%>
                    <td>Relatórios</td>
                    <% if (ControleAutorizacao.checkBloqueio("relatorios", cliente.getCPF())) {%>
                    <td>BLOQUEADO</td>
                    <td>
                        <button><a href='/sam/AcessosBlockController?acao=Ativar&usuario=<%= cliente.getCPF() %>&recurso=relatorios'></a></button>
                    </td>
                    <% } else {%>
                    <td>ATIVO</td>
                    <td>
                        <button><a
                                href='/sam/AcessosBlockController?acao=Bloquear&usuario=<%= cliente.getCPF() %>&recurso=relatorios'>Bloquear</a></button>
                    </td>
                    <%}}if (ControleAutorizacao.checkPermissao("transacoes-gestor", cliente.getTipo())) {%>
                    <td>Transacoes</td>
                    <% if (ControleAutorizacao.checkBloqueio("transacoes-gestor", cliente.getCPF())) {%>
                    <td>BLOQUEADO</td>
                    <td>
                        <button><a href='/sam/AcessosBlockController?acao=Ativar&usuario=<%= cliente.getCPF() %>&recurso=transacoes-gestor'>Ativar</a></button>
                    </td>
                    <% } else {%>
                    <td>ATIVO</td>
                    <td>
                        <button><a
                                href='/sam/AcessosBlockController?acao=Bloquear&usuario=<%= cliente.getCPF() %>&recurso=transacoes-gestor'>Bloquear</a></button>
                    </td>
                    <%}}if (ControleAutorizacao.checkPermissao("campanhas", cliente.getTipo())) {%>
                    <td>Campanhas</td>
                    <% if (ControleAutorizacao.checkBloqueio("campanhas", cliente.getCPF())) {%>
                    <td>BLOQUEADO</td>
                    <td>
                        <button><a href='/sam/AcessosBlockController?acao=Ativar&usuario=<%= cliente.getCPF() %>&recurso=campanhas'>Ativar</a></button>
                    </td>
                    <% } else {%>
                    <td>ATIVO</td>
                    <td>
                        <button><a
                                href='/sam/AcessosBlockController?acao=Bloquear&usuario=<%= cliente.getCPF() %>&recurso=campanhas'>Bloquear</a></button>
                    </td>
                    <%}}%>
                </tr>
            </table>
        </main>
        <script src="../../js/script.js"></script>
    </body>

</html>