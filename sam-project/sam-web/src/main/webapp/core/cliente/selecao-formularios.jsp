<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="sam.model.domain.Usuario"%>
<%@page import="sam.controller.LoginController"%>
<%@page import="sam.model.domain.FormObjetivos"%>
<%@page import="java.util.List"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@ page import="sam.model.service.GestaoFormulariosService" %>

<%
    LoginController.validarSessao(request, response);
    Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
    String contextPath = request.getContextPath();
    List<FormObjetivos> formularios = GestaoFormulariosService.listarForms(usuario);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gerenciar Formulários</title>
    <link rel="stylesheet" type="text/css" href="<%= contextPath %>/css/style.css">
</head>
<body>

<header>
    <img id="logotipo" src="<%= contextPath %>/imgs/logotipo.png" alt="Logotipo SAM">
    <h1>Dashboard</h1>
    <nav>
        <a href="<%= contextPath %>/core/cliente/dashboard.jsp" class="active">Dashboard</a>
        <a href="<%= contextPath %>/core/transacoes.jsp">Transações</a>
        <a href="<%= contextPath %>/core/notificacoes.jsp">Notificações</a>
        <a href="<%= contextPath %>/core/suporte.jsp">Suporte</a>
    </nav>
    <h1><%=usuario.getNome()%></h1>
    <div class="hamburger-menu">

        <button id="hamburger-btn">&#9776;</button>
        <div id="hamburger-dropdown" class="dropdown-content">
            <a href="<%= contextPath %>/core/perfil.jsp">Visualizar Perfil</a>
        </div>
    </div>
</header>

<main>
    <h2>Gerenciar Formulários</h2>

    <a href="<%= contextPath %>/core/cliente/objetivos.jsp">
        <button style="margin-bottom: 20px;">+ Criar Novo Formulário</button>
    </a>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Título do Formulário</th>
            <th>Última Alteração</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>

        <%
            if (formularios != null && !formularios.isEmpty()) {
                for (FormObjetivos form : formularios) {
        %>
        <tr>
            <td><%= form.getId() %></td>
            <td><%= form.getTitulo() %></td>
            <td>
                <%= form.getData().format(formatter) %>
            </td>
            <td>
                <a href="<%= contextPath %>/processarObjetivos?id=<%= form.getId() %>">
                    <button>Editar</button>
                </a>

                    <input type="hidden" name="id" value="<%= form.getId() %>"/>

                    <a href="<%=request.getContextPath()%>/processarObjetivos?action=excluir&id=<%=form.getId()%>"><button>
                        Excluir
                    </button></a>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="4">Nenhum formulário encontrado.</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</main>
</body>
</html>