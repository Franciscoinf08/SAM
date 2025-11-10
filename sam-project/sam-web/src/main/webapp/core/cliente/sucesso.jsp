<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="sam.model.domain.Usuario"%>
<%@page import="sam.controller.LoginController"%>

<%
    LoginController.validarSessao(request, response);

    Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
%>
<html>
<head>
    <title>Sucesso</title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css">
</head>
<body id="body-sucesso">
    <header>
        <img id="logotipo" src="/sam/imgs/logotipo.png" alt="Logotipo SAM">
        <h1>Dashboard</h1>
        <nav>
            <a href="/sam/core/cliente/dashboard.jsp" class="active">Dashboard</a>
            <a href="/sam/core/transacoes.jsp">Transações</a>
            <a href="/sam/core/notificacoes.jsp">Notificações</a>
            <a href="/sam/core/suporte.jsp">Suporte</a>
        </nav>
        <h1><%=usuario.getNome()%></h1>
        <div class="hamburger-menu">

            <button id="hamburger-btn">&#9776;</button>
            <div id="hamburger-dropdown" class="dropdown-content">
                <a href="/sam/core/perfil.jsp">Visualizar Perfil</a>
            </div>
        </div>
    </header>
    <section id="secao-geral">
        <div class="div-sucesso">
            <h1>SUCESSO!!!</h1>
            <h2>Formulario registrado/atualizado com sucesso.</h2>
        </div>
        <div class="-div-retorno">
            <a href="selecao-formularios.jsp">Voltar para pagina incial ></a>
        </div>
    </section>

</body>
</html>
