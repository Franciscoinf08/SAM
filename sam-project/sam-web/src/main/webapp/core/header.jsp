<%@page pageEncoding="UTF-8"%>
<%@page import="sam.model.domain.Usuario"%>
<%@page import="sam.autorizacao.ControleAutorizacao"%>
<%@page import="sam.controller.LoginController"%>
<%@page import="sam.model.domain.util.UsuarioTipo"%>

<%
    LoginController.validarSessao(request, response);

    Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
%>

<nav>
    <a href="<%=request.getContextPath()%>/core/geral/inicio.jsp">Início</a>

    <%if (ControleAutorizacao.checkPermissao("notificacoes", usuario.getTipo()) && !ControleAutorizacao.checkBloqueio("notificacoes", usuario.getCPF())) {%>
    <a href="<%=request.getContextPath()%>/notificacoes?action=listar">Notificações</a>

    <%} if (ControleAutorizacao.checkPermissao("suporte", usuario.getTipo()) && !ControleAutorizacao.checkBloqueio("suporte", usuario.getCPF())) {%>
    <a href="<%=request.getContextPath()%>/core/geral/suporte.jsp">Suporte</a>

    <%} if (ControleAutorizacao.checkPermissao("faq", usuario.getTipo()) && !ControleAutorizacao.checkBloqueio("faq", usuario.getCPF())) {%>
    <a href="<%=request.getContextPath()%>/core/geral/faq.jsp">FAQ</a>

    <%} if (ControleAutorizacao.checkPermissao("monitoramento", usuario.getTipo()) && !ControleAutorizacao.checkBloqueio("suporte", usuario.getCPF())) {%>
    <a href="<%=request.getContextPath()%>/atividades">Monitoramento</a>
    <%}%>
</nav>

<div class="hamburger-menu">
    <h1><%=usuario.getNome()%></h1>
    <button id="hamburger-btn">&#9776;</button>
    <div id="hamburger-dropdown" class="dropdown-content">
        <% if (ControleAutorizacao.checkPermissao("perfil", usuario.getTipo()) &&
                !ControleAutorizacao.checkBloqueio("perfil", usuario.getCPF())) {%>
        <a href="<%=request.getContextPath()%>/core/geral/perfil.jsp">Visualizar Perfil</a>

        <%} if (ControleAutorizacao.checkPermissao("transacoes", usuario.getTipo()) &&
                !ControleAutorizacao.checkBloqueio("transacoes", usuario.getCPF()) &&
                (usuario.getTipo() != UsuarioTipo.CLIENTE || usuario.getIdGestor() != null)) { %>
        <a href="<%=request.getContextPath()%>/core/geral/transacoes.jsp">Transações</a>

        <%} if (ControleAutorizacao.checkPermissao("dashboard-cliente", usuario.getTipo()) &&
                !ControleAutorizacao.checkBloqueio("dashboard-cliente", usuario.getCPF())) {%>
        <a href="<%=request.getContextPath()%>/core/cliente/dashboard.jsp">Dashboard</a>

        <%} if (ControleAutorizacao.checkPermissao("dashboard-gestor", usuario.getTipo()) &&
                !ControleAutorizacao.checkBloqueio("dashboard-gestor", usuario.getCPF())) {%>
        <a href="<%=request.getContextPath()%>/core/gestor/apuracao.jsp">Apuração</a>

        <%} if (ControleAutorizacao.checkPermissao("empresas", usuario.getTipo()) &&
                !ControleAutorizacao.checkBloqueio("empresas", usuario.getCPF())) {%>
        <a href="<%=request.getContextPath()%>/core/gestor/empresas.jsp">Empresas</a>

        <%} if (ControleAutorizacao.checkPermissao("associacoes", usuario.getTipo()) &&
                !ControleAutorizacao.checkBloqueio("associacoes", usuario.getCPF())) {%>
        <a href="<%=request.getContextPath()%>/core/gestor/associacoes.jsp">Associações</a>

        <%} if (ControleAutorizacao.checkPermissao("mensagens-avisos", usuario.getTipo()) &&
                !ControleAutorizacao.checkBloqueio("mensagens-avisos", usuario.getCPF())) {%>
        <a href="<%=request.getContextPath()%>/notificacoes?action=formulario">Enviar mensagens</a>


        <%} if (ControleAutorizacao.checkPermissao("acesso-clientes", usuario.getTipo()) &&
                !ControleAutorizacao.checkBloqueio("acesso-clientes", usuario.getCPF())) {%>
        <a href="<%=request.getContextPath()%>/usuarioPrograma?action=listar">Meus clientes</a>

        <%} if (ControleAutorizacao.checkPermissao("avaliacoes", usuario.getTipo()) &&
                !ControleAutorizacao.checkBloqueio("avaliacoes", usuario.getCPF())) {%>
        <a href="<%=request.getContextPath()%>/core/geral/avaliacoes.jsp">Avaliações</a>

        <%} if (ControleAutorizacao.checkPermissao("relatorios", usuario.getTipo()) &&
                !ControleAutorizacao.checkBloqueio("relatorios", usuario.getCPF())) {%>
        <a href="<%=request.getContextPath()%>/core/geral/relatorios.jsp">Relatórios</a>

        <%} if (ControleAutorizacao.checkPermissao("campanhas", usuario.getTipo()) &&
                !ControleAutorizacao.checkBloqueio("campanhas", usuario.getCPF())) {%>
        <a href="<%=request.getContextPath()%>/core/gestor/planos.jsp">Campanhas</a>

        <%} if (ControleAutorizacao.checkPermissao("ver-usuarios", usuario.getTipo()) &&
                !ControleAutorizacao.checkBloqueio("ver-usuarios", usuario.getCPF())) {%>
        <a href="<%=request.getContextPath()%>/core/dev/visualizar-usuarios.jsp">Ver Usuários</a>

        <%} if (ControleAutorizacao.checkPermissao("ver-solicitacoes", usuario.getTipo()) &&
                !ControleAutorizacao.checkBloqueio("ver-solicitacoes", usuario.getCPF())) {%>
        <a href="<%=request.getContextPath()%>/core/dev/gerenciar-solicitacoes.jsp">Solicitações para Conta Gestor</a>

        <%} if (ControleAutorizacao.checkPermissao("ver-propostas", usuario.getTipo()) &&
                !ControleAutorizacao.checkBloqueio("ver-propostas", usuario.getCPF())) {%>
        <a href="<%=request.getContextPath()%>/core/cliente/visualizacaoPropostas.jsp">Visualizar Propostas</a>

        <%} if (ControleAutorizacao.checkPermissao("orcamentos", usuario.getTipo()) &&
                !ControleAutorizacao.checkBloqueio("orcamentos", usuario.getCPF())) {%>
        <a href="/sam/core/gestor/orcamentos.jsp">Orçamentos</a>

        <%} if (ControleAutorizacao.checkPermissao("visualizar-historico", usuario.getTipo()) &&
                !ControleAutorizacao.checkBloqueio("historico", usuario.getCPF())) {%>
        <a href="/sam/ListarUsuariosController">Histórico de Usuário</a>
        <%}%>

    </div>
</div>