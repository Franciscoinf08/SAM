<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="sam.controller.AcessosBlockController" %>


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
                    <% if (ControleAutorizacao.checkPermissao("dashboard-cliente", usuario.getTipo())) {%>
                    <td>Dashboard</td>
                    <% if (ControleAutorizacao.checkBloqueio("dashboard-cliente", usuario.getCPF())) {%>
                    <td>BLOQUEADO</td>
                    <td>
                        <button><a href='/sam/AcessosBlockController?acao=Ativar&usuario=<%= usuario.getCPF() %>&recurso=dashboard-clientes'>Ativar</a></button>
                    </td>
                    <% } else {%>
                    <td>ATIVO</td>
                    <td>
                        <button><a
                                href='/sam/AcessosBlockController?acao=Bloquear&usuario=<%= usuario.getCPF() %>&recurso=dashboard-clientes'>Bloquear</a></button>
                    </td>
                    <%}} if (ControleAutorizacao.checkPermissao("transacoes-clientes", usuario.getTipo())) {%>
                    <td>Transações</td>
                    <% if (ControleAutorizacao.checkBloqueio("transacoes-clientes", usuario.getCPF())) {%>
                    <td>BLOQUEADO</td>
                    <td>
                        <button><a href='/sam/AcessosBlockController?acao=Ativar&usuario=<%= usuario.getCPF() %>&recurso=transacoes-clientes'>Ativar</a></button>
                    </td>
                    <% } else {%>
                    <td>ATIVO</td>
                    <td>
                        <button><a
                                href='/sam/AcessosBlockController?acao=Bloquear&usuario=<%= usuario.getCPF() %>&recurso=transacoes-clientes'>Bloquear</a></button>
                    </td>
                    <%}}if (ControleAutorizacao.checkPermissao("empresas", usuario.getTipo())) {%> 
                    <td>Empresas</td>
                    <% if (ControleAutorizacao.checkBloqueio("empresas", usuario.getCPF())) {%>
                    <td>BLOQUEADO</td>
                    <td>
                        <button><a href='/sam/AcessosBlockController?acao=Ativar&usuario=<%= usuario.getCPF() %>&recurso=empresas'>Ativar</a></button>
                    </td>
                    <% } else {%>
                    <td>ATIVO</td>
                    <td>
                        <button><a
                                href='/sam/AcessosBlockController?acao=Bloquear&usuario=<%= usuario.getCPF() %>&recurso=empresas'>Bloquear</a></button>
                    </td>
                    <%}}if (ControleAutorizacao.checkPermissao("notificacoes", usuario.getTipo())) {%>
                    <td>Notificacoes</td>
                    <% if (ControleAutorizacao.checkBloqueio("notificacoes", usuario.getCPF())) {%>
                    <td>BLOQUEADO</td>
                    <td>
                        <button><a href='/sam/AcessosBlockController?acao=Ativar&usuario=<%= usuario.getCPF() %>&recurso=notificacoes'>Ativar</a></button>
                    </td>
                    <% } else {%>
                    <td>ATIVO</td>
                    <td>
                        <button><a
                                href='/sam/AcessosBlockController?acao=Bloquear&usuario=<%= usuario.getCPF() %>&recurso=notificacoes'>Bloquear</a></button>
                    </td>
                    <%}}if (ControleAutorizacao.checkPermissao("suporte", usuario.getTipo())) {%>
                    <td>Suporte</td>
                    <% if (ControleAutorizacao.checkBloqueio("suporte", usuario.getCPF())) {%>
                    <td>BLOQUEADO</td>
                    <td>
                        <button><a href='/sam/AcessosBlockController?acao=Ativar&usuario=<%= usuario.getCPF() %>&recurso=suporte'>Ativar</a></button>
                    </td>
                    <% } else {%>
                    <td>ATIVO</td>
                    <td>
                        <button><a
                                href='/sam/AcessosBlockController?acao=Bloquear&usuario=<%= usuario.getCPF() %>&recurso=suporte'>Bloquear</a></button>
                    </td>
                    <%}}if (ControleAutorizacao.checkPermissao("perfil", usuario.getTipo())) {%>
                    <td>Perfil</td>
                    <% if (ControleAutorizacao.checkBloqueio("perfil", usuario.getCPF())) {%>
                    <td>BLOQUEADO</td>
                    <td>
                        <button><a href='/sam/AcessosBlockController?acao=Ativar&usuario=<%= usuario.getCPF() %>&recurso=perfil'>Ativar</a></button>
                    </td>
                    <% } else {%>
                    <td>ATIVO</td>
                    <td>
                        <button><a
                                href='/sam/AcessosBlockController?acao=Bloquear&usuario=<%= usuario.getCPF() %>&recurso=perfil'>Bloquear</a></button>
                    </td>
                    <%}}if (ControleAutorizacao.checkPermissao("computar-milhas", usuario.getTipo())) {%>
                    <td>Computar Milhas</td>
                    <% if (ControleAutorizacao.checkBloqueio("computar-milhas", usuario.getCPF())) {%>
                    <td>BLOQUEADO</td>
                    <td>
                        <button><a href='/sam/AcessosBlockController?acao=Ativar&usuario=<%= usuario.getCPF() %>&recurso=computar-milhas'>Ativar</a></button>
                    </td>
                    <% } else {%>
                    <td>ATIVO</td>
                    <td>
                        <button><a
                                href='/sam/AcessosBlockController?acao=Bloquear&usuario=<%= usuario.getCPF() %>&recurso=computar-milhas'>Bloquear</a></button>
                    </td>
                    <%}}if (ControleAutorizacao.checkPermissao("dashboard-gestor", usuario.getTipo())) {%>
                    <td>Dashboard</td>
                    <% if (ControleAutorizacao.checkBloqueio("dashboard-gestor", usuario.getCPF())) {%>
                    <td>BLOQUEADO</td>
                    <td>
                        <button><a href='/sam/AcessosBlockController?acao=Ativar&usuario=<%= usuario.getCPF() %>&recurso=dashboard-gestor'>Ativar</a></button>
                    </td>
                    <% } else {%>
                    <td>ATIVO</td>
                    <td>
                        <button><a
                                href='/sam/AcessosBlockController?acao=Bloquear&usuario=<%= usuario.getCPF() %>&recurso=dashboard-gestor'>Bloquear</a></button>
                    </td>
                    <%}}if (ControleAutorizacao.checkPermissao("associacoes", usuario.getTipo())) {%>
                    <td>Associar Clientes</td>
                    <% if (ControleAutorizacao.checkBloqueio("associacoes", usuario.getCPF())) {%>
                    <td>BLOQUEADO</td>
                    <td>
                        <button><a href='/sam/AcessosBlockController?acao=Ativar&usuario=<%= usuario.getCPF() %>&recurso=associacoes'>Ativar</a></button>
                    </td>
                    <% } else {%>
                    <td>ATIVO</td>
                    <td>
                        <button><a
                                href='/sam/AcessosBlockController?acao=Bloquear&usuario=<%= usuario.getCPF() %>&recurso=associacoes'>Bloquear</a></button>
                    </td>
                    <%}}if (ControleAutorizacao.checkPermissao("orcamentos", usuario.getTipo())) {%>
                    <td>Orcamentos</td>
                    <% if (ControleAutorizacao.checkBloqueio("orcamentos", usuario.getCPF())) {%>
                    <td>BLOQUEADO</td>
                    <td>
                        <button><a href='/sam/AcessosBlockController?acao=Ativar&usuario=<%= usuario.getCPF() %>&recurso=orcamentos'>Ativar</a></button>
                    </td>
                    <% } else {%>
                    <td>ATIVO</td>
                    <td>
                        <button><a
                                href='/sam/AcessosBlockController?acao=&usuario=<%= usuario.getCPF() %>&recurso=orcamentos'>Bloquear</a></button>
                    </td>
                    <%}}if (ControleAutorizacao.checkPermissao("planos", usuario.getTipo())) {%>
                    <td>Planos</td>
                    <% if (ControleAutorizacao.checkBloqueio("planos", usuario.getCPF())) {%>
                    <td>BLOQUEADO</td>
                    <td>
                        <button><a href='/sam/AcessosBlockController?acao=Ativar&usuario=<%= usuario.getCPF() %>&recurso=planos'>Ativar</a></button>
                    </td>
                    <% } else {%>
                    <td>ATIVO</td>
                    <td>
                        <button><a
                                href='/sam/AcessosBlockController?acao=Bloquear&usuario=<%= usuario.getCPF() %>&recurso=planos'>Bloquear</a></button>
                    </td>
                    <%}}if (ControleAutorizacao.checkPermissao("mensagens-avisos", usuario.getTipo())) {%>
                    <td>Enviar mensagens e avisos</td>
                    <% if (ControleAutorizacao.checkBloqueio("mensagens-avisos", usuario.getCPF())) {%>
                    <td>BLOQUEADO</td>
                    <td>
                        <button><a href='/sam/AcessosBlockController?acao=Ativar&usuario=<%= usuario.getCPF() %>&recurso=mensagens-avisos'>Ativar</a></button>
                    </td>
                    <% } else {%>
                    <td>ATIVO</td>
                    <td>
                        <button><a
                                href='/sam/AcessosBlockController?acao=Bloquear&usuario=<%= usuario.getCPF() %>&recurso=mensagens-avisos'>Bloquear</a></button>
                    </td>
                    <%}}if (ControleAutorizacao.checkPermissao("acesso-clientes", usuario.getTipo())) {%>
                    <td>Acesso aos clientes</td>
                    <% if (ControleAutorizacao.checkBloqueio("acesso-clientes", usuario.getCPF())) {%>
                    <td>BLOQUEADO</td>
                    <td>
                        <button><a href='/sam/AcessosBlockController?acao=Ativar&usuario=<%= usuario.getCPF() %>&recurso=acesso-clientes'>Ativar</a></button>
                    </td>
                    <% } else {%>
                    <td>ATIVO</td>
                    <td>
                        <button><a
                                href='/sam/AcessosBlockController?acao=Bloquear&usuario=<%= usuario.getCPF() %>&recurso=acesso-clientes'>Bloquear</a></button>
                    </td>
                    <%}}if (ControleAutorizacao.checkPermissao("programa", usuario.getTipo())) {%>
                    <td>Programas de fidelidade</td>
                    <% if (ControleAutorizacao.checkBloqueio("programa", usuario.getCPF())) {%>
                    <td>BLOQUEADO</td>
                    <td>
                        <button><a href='/sam/AcessosBlockController?acao=Ativar&usuario=<%= usuario.getCPF() %>&recurso=prorama'>Ativar</a></button>
                    </td>
                    <% } else {%>
                    <td>ATIVO</td>
                    <td>
                        <button><a
                                href='/sam/AcessosBlockController?acao=Bloquear&usuario=<%= usuario.getCPF() %>&recurso=programa'>Bloquear</a></button>
                    </td>
                    <%}}if (ControleAutorizacao.checkPermissao("avaliacoes", usuario.getTipo())) {%>
                    <td>Avaliações</td>
                    <% if (ControleAutorizacao.checkBloqueio("avaliacoes", usuario.getCPF())) {%>
                    <td>BLOQUEADO</td>
                    <td>
                        <button><a href='/sam/AcessosBlockController?acao=Ativar&usuario=<%= usuario.getCPF() %>&recurso=avaliacoes'>Ativar</a></button>
                    </td>
                    <% } else {%>
                    <td>ATIVO</td>
                    <td>
                        <button><a
                                href='/sam/AcessosBlockController?acao=Bloquear&usuario=<%= usuario.getCPF() %>&recurso=avaliacoes'>Bloquear</a></button>
                    </td>
                    <%}}if (ControleAutorizacao.checkPermissao("relatorios", usuario.getTipo())) {%>
                    <td>Relatórios</td>
                    <% if (ControleAutorizacao.checkBloqueio("relatorios", usuario.getCPF())) {%>
                    <td>BLOQUEADO</td>
                    <td>
                        <button><a href='/sam/AcessosBlockController?acao=Ativar&usuario=<%= usuario.getCPF() %>&recurso=relatorios'></a></button>
                    </td>
                    <% } else {%>
                    <td>ATIVO</td>
                    <td>
                        <button><a
                                href='/sam/AcessosBlockController?acao=Bloquear&usuario=<%= usuario.getCPF() %>&recurso=relatorios'>Bloquear</a></button>
                    </td>
                    <%}}if (ControleAutorizacao.checkPermissao("transacoes-gestor", usuario.getTipo())) {%>
                    <td>Transacoes</td>
                    <% if (ControleAutorizacao.checkBloqueio("transacoes-gestor", usuario.getCPF())) {%>
                    <td>BLOQUEADO</td>
                    <td>
                        <button><a href='/sam/AcessosBlockController?acao=Ativar&usuario=<%= usuario.getCPF() %>&recurso=transacoes-gestor'>Ativar</a></button>
                    </td>
                    <% } else {%>
                    <td>ATIVO</td>
                    <td>
                        <button><a
                                href='/sam/AcessosBlockController?acao=Bloquear&usuario=<%= usuario.getCPF() %>&recurso=transacoes-gestor'>Bloquear</a></button>
                    </td>
                    <%}}if (ControleAutorizacao.checkPermissao("campanhas", usuario.getTipo())) {%>
                    <td>Campanhas</td>
                    <% if (ControleAutorizacao.checkBloqueio("campanhas", usuario.getCPF())) {%>
                    <td>BLOQUEADO</td>
                    <td>
                        <button><a href='/sam/AcessosBlockController?acao=Ativar&usuario=<%= usuario.getCPF() %>&recurso=campanhas'>Ativar</a></button>
                    </td>
                    <% } else {%>
                    <td>ATIVO</td>
                    <td>
                        <button><a
                                href='/sam/AcessosBlockController?acao=Bloquear&usuario=<%= usuario.getCPF() %>&recurso=campanhas'>Bloquear</a></button>
                    </td>
                    <%}}%>
                </tr>
            </table>
        </main>
        <script src="../../js/script.js"></script>
    </body>

</html>