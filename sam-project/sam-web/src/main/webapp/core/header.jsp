<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sam.model.domain.Usuario"%>
<%@page import="sam.autorizacao.ControleAutorizacao"%>
<%@page import="sam.controller.LoginController"%>

<%
    LoginController.validarSessao(request, response);

    Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
%>

<nav>
    <% if (ControleAutorizacao.checkPermissao("dashboard-cliente", usuario.getTipo()) && !ControleAutorizacao.checkBloqueio("dashboard-cliente", usuario.getCPF())) {%>
    <a href="../cliente/dashboard.jsp">Dashboard</a>
    <%} if (ControleAutorizacao.checkPermissao("transacoes-clientes", usuario.getTipo()) && !ControleAutorizacao.checkBloqueio("transacoes-clientes", usuario.getCPF())) {%>
    <a href="../geral/transacoes.jsp">Transações Clientes</a>
    <%} if (ControleAutorizacao.checkPermissao("empresas", usuario.getTipo()) && !ControleAutorizacao.checkBloqueio("empresas", usuario.getCPF())) {%>
    <a href="../gestor/empresas.jsp">Empresas</a>
    <%} if (ControleAutorizacao.checkPermissao("notificacoes", usuario.getTipo()) && !ControleAutorizacao.checkBloqueio("notificacoes", usuario.getCPF())) {%>
    <a href="../geral/notificacoes.jsp">Notificações</a>
    <%} if (ControleAutorizacao.checkPermissao("suporte", usuario.getTipo()) && !ControleAutorizacao.checkBloqueio("suporte", usuario.getCPF())) {%>
    <a href="../geral/suporte.jsp">Suporte</a>
</nav>

<div class="hamburger-menu">
    <button id="hamburger-btn">&#9776;</button>
    <div id="hamburger-dropdown" class="dropdown-content">
        <%} if (ControleAutorizacao.checkPermissao("perfil", usuario.getTipo()) && !ControleAutorizacao.checkBloqueio("perfil", usuario.getCPF())) {%>
        <a href="../geral/perfil.jsp">Visualizar Perfil</a>
        <%} if (ControleAutorizacao.checkPermissao("computar-milhas", usuario.getTipo()) && !ControleAutorizacao.checkBloqueio("computar-milhas", usuario.getCPF())) {%>
        <a href="../gestor/computar-milhas.jsp">Computar Milhas</a>
        <%} if (ControleAutorizacao.checkPermissao("dashboard-gestor", usuario.getTipo()) && !ControleAutorizacao.checkBloqueio("dashboard-gestor", usuario.getCPF())) {%>
        <a href="../gestor/apuracao.jsp">Apuração(Dashboard Gestor)</a>
        <%} if (ControleAutorizacao.checkPermissao("associacoes", usuario.getTipo()) && !ControleAutorizacao.checkBloqueio("associacoes", usuario.getCPF())) {%>
        <a href="../gestor/associacoes.jsp">Associações</a>
        <%} if (ControleAutorizacao.checkPermissao("orcamentos", usuario.getTipo()) && !ControleAutorizacao.checkBloqueio("orcamentos", usuario.getCPF())) {%>
        <a href="../gestor/orcamentos.jsp">Orçamentos</a>
        <%} if (ControleAutorizacao.checkPermissao("planos", usuario.getTipo()) && !ControleAutorizacao.checkBloqueio("planos", usuario.getCPF())) {%>
        <a href="../gestor/planos.jsp">Planos</a>
        <%} if (ControleAutorizacao.checkPermissao("mensagens-avisos", usuario.getTipo()) && !ControleAutorizacao.checkBloqueio("mensagens-avisos", usuario.getCPF())) {%>
        <a href="../gestor/mensagens.jsp">Mensagens e avisos</a>
        <%} if (ControleAutorizacao.checkPermissao("acesso-clientes", usuario.getTipo()) && !ControleAutorizacao.checkBloqueio("acesso-clientes", usuario.getCPF())) {%>
        <a href="../gestor/meus-cientes.jsp">Meus clientes</a>
        <%} if (ControleAutorizacao.checkPermissao("programa", usuario.getTipo()) && !ControleAutorizacao.checkBloqueio("programa", usuario.getCPF())) {%>
        <a href="../gestor/programas.jsp">Programas de fidelidade</a>
        <%} if (ControleAutorizacao.checkPermissao("avaliacoes", usuario.getTipo()) && !ControleAutorizacao.checkBloqueio("avaliacoes", usuario.getCPF())) {%>
        <a href="../geral/avaliacoes.jsp">Avaliações</a>
        <%} if (ControleAutorizacao.checkPermissao("relatorios", usuario.getTipo()) && !ControleAutorizacao.checkBloqueio("relatorios", usuario.getCPF())) {%>
        <a href="../geral/avaliacoes.jsp">Relatórios</a>
        <%} if (ControleAutorizacao.checkPermissao("transacoes-gestor", usuario.getTipo()) && !ControleAutorizacao.checkBloqueio("transacoes-gestor", usuario.getCPF())) {%>
        <a href="../geral/transacoes.jsp">Transações Gestor</a>
        <%} if (ControleAutorizacao.checkPermissao("campanhas", usuario.getTipo()) && !ControleAutorizacao.checkBloqueio("campanhas", usuario.getCPF())) {%>
        <a href="../gestor/planos.jsp">Campanhas</a>
        <%} if (ControleAutorizacao.checkPermissao("ver-usuarios", usuario.getTipo()) && !ControleAutorizacao.checkBloqueio("ver-usuarios", usuario.getCPF())) {%>
        <a href="../dev/visualizar-usuarios.jsp">Ver Usuários</a>
        <%}%>
    </div>
</div>
    
    <script src="../js/hamburger.js"></script>
