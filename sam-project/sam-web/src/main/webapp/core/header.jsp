<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sam.controller.LoginController"%> <!-- MUDAR PARA O CONTROLLER EQUIVALENTE NO PROJETO -->
<%@page import="sam.model.domain.Pessoa"%> <!-- MUDAR PARA A CLASS EQUIVALENTE NO PROJETO -->
<%@page import="sam.autorizacao.ControleAutorizacao"%>

<%
    LoginController.validarSessao(request, response);

    Pessoa usuario = (Pessoa) request.getSession().getAttribute("usuario");
%>

<nav>
    <% if (ControleAutorizacao.checkPermissao("dashboard-cliente", usuario.getUsuarioTipo()) && !ControleAutorizacao.checkBloqueio("dashboard-cliente", usuario.getCPF())) {%>
    <a href="../cliente/dashboard.jsp">Dashboard</a>
    <% if (ControleAutorizacao.checkPermissao("transacoes-clientes", usuario.getUsuarioTipo()) && !ControleAutorizacao.checkBloqueio("transacoes-clientes", usuario.getCPF())) {%>
    <a href="../geral/transacoes.jsp">Transações Clientes</a>
    <% if (ControleAutorizacao.checkPermissao("empresas", usuario.getUsuarioTipo()) && !ControleAutorizacao.checkBloqueio("empresas", usuario.getCPF())) {%>
    <a href="../gestor/empresas.jsp">Empresas</a>
    <% if (ControleAutorizacao.checkPermissao("notificacoes", usuario.getUsuarioTipo()) && !ControleAutorizacao.checkBloqueio("notificacoes", usuario.getCPF())) {%>
    <a href="../geral/notificacoes.jsp">Notificações</a>
    <% if (ControleAutorizacao.checkPermissao("suporte", usuario.getUsuarioTipo()) && !ControleAutorizacao.checkBloqueio("suporte", usuario.getCPF())) {%>
    <a href="../geral/suporte.jsp">Suporte</a>
</nav>

<div class="hamburger-menu">
    <button id="hamburger-btn">&#9776;</button>
    <div id="hamburger-dropdown" class="dropdown-content">
        <% if (ControleAutorizacao.checkPermissao("perfil", usuario.getUsuarioTipo()) && !ControleAutorizacao.checkBloqueio("perfil", usuario.getCPF())) {%>
        <a href="../geral/perfil.jsp">Visualizar Perfil</a>
        <% if (ControleAutorizacao.checkPermissao("computar-milhas", usuario.getUsuarioTipo()) && !ControleAutorizacao.checkBloqueio("computar-milhas", usuario.getCPF())) {%>
        <a href="../gestor/computar-milhas.jsp">Computar Milhas</a>
        <% if (ControleAutorizacao.checkPermissao("dashboard-gestor", usuario.getUsuarioTipo()) && !ControleAutorizacao.checkBloqueio("dashboard-gestor", usuario.getCPF())) {%>
        <a href="../gestor/apuracao.jsp">Apuração (Dashboard Gestor)</a>
        <% if (ControleAutorizacao.checkPermissao("associacoes", usuario.getUsuarioTipo()) && !ControleAutorizacao.checkBloqueio("associacoes", usuario.getCPF())) {%>
        <a href="../gestor/associacoes.jsp">Associações</a>
        <% if (ControleAutorizacao.checkPermissao("orcamentos", usuario.getUsuarioTipo()) && !ControleAutorizacao.checkBloqueio("orcamentos", usuario.getCPF())) {%>
        <a href="../gestor/orcamentos.jsp">Orçamentos</a>
        <% if (ControleAutorizacao.checkPermissao("planos", usuario.getUsuarioTipo()) && !ControleAutorizacao.checkBloqueio("planos", usuario.getCPF())) {%>
        <a href="../gestor/planos.jsp">Planos</a>
        <% if (ControleAutorizacao.checkPermissao("mensagens-avisos", usuario.getUsuarioTipo()) && !ControleAutorizacao.checkBloqueio("mensagens-avisos", usuario.getCPF())) {%>
        <a href="../gestor/mensagens.jsp">Mensagens e avisos</a>
        <% if (ControleAutorizacao.checkPermissao("acesso-clientes", usuario.getUsuarioTipo()) && !ControleAutorizacao.checkBloqueio("acesso-clientes", usuario.getCPF())) {%>
        <a href="../gestor/meus-cientes.jsp">Meus clientes</a>
        <% if (ControleAutorizacao.checkPermissao("programa", usuario.getUsuarioTipo()) && !ControleAutorizacao.checkBloqueio("programa", usuario.getCPF())) {%>
        <a href="../gestor/programas.jsp">Programas de fidelidade</a>
        <% if (ControleAutorizacao.checkPermissao("avaliacoes", usuario.getUsuarioTipo()) && !ControleAutorizacao.checkBloqueio("avaliacoes", usuario.getCPF())) {%>
        <a href="../geral/avaliacoes.jsp">Avaliações</a>
        <% if (ControleAutorizacao.checkPermissao("relatorios", usuario.getUsuarioTipo()) && !ControleAutorizacao.checkBloqueio("relatorios", usuario.getCPF())) {%>
        <a href="../geral/avaliacoes.jsp">Relatórios</a>
        <% if (ControleAutorizacao.checkPermissao("transacoes-gestor", usuario.getUsuarioTipo()) && !ControleAutorizacao.checkBloqueio("transacoes-gestor", usuario.getCPF())) {%>
        <a href="../geral/transacoes.jsp">Transações Gestor</a>
        <% if (ControleAutorizacao.checkPermissao("campanhas", usuario.getUsuarioTipo()) && !ControleAutorizacao.checkBloqueio("campanhas", usuario.getCPF())) {%>
        <a href="../gestor/planos.jsp">Campanhas</a>
        <% if (ControleAutorizacao.checkPermissao("ver-usuarios", usuario.getUsuarioTipo()) && !ControleAutorizacao.checkBloqueio("campanhas", usuario.getCPF())) {%>
        <a href="../gestor/planos.jsp">Campanhas</a>
    </div>
</div>
