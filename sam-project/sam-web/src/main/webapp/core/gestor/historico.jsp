<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="sam.model.domain.FormObjetivos" %>
<%@ page import="sam.model.domain.Feedback" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>SAM - Historico</title>

    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/historico.css">
    <link rel="icon" href="/sam/imgs/favicon.ico">
</head>

<body>
<header>
    <img id="logotipo" src="<%= request.getContextPath() %>/imgs/logotipo.png" alt="Logotipo SAM">
    <h1>Historico de Usuario</h1>
    <%@include file="/core/header.jsp" %>
</header>

<main>

    <section id="hist-formulario">
        <h2>Historico de Formulários</h2>

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
                List<FormObjetivos> formularios = (List<FormObjetivos>) request.getAttribute("formularios");

                if (formularios != null && !formularios.isEmpty()) {
                    for (FormObjetivos f : formularios) {
            %>
            <tr>
                <td><%= f.getId() %></td>
                <td><%= f.getTitulo() %></td>
                <td><%= (f.getData() != null) ? f.getData() : "--" %></td>
                <td>
                    <a href="<%= request.getContextPath() %>/VisualizarFormularioController?id=<%= f.getId() %>">
                        <button>Visualizar</button>
                    </a>
                </td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="4">Nenhum formulário encontrado.</td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </section>

    <section id="hist-avaliacao">
        <h2>Avaliações do Cliente</h2>

        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Usuário</th>
                <th>Comentário</th>
                <th>⭐</th>
            </tr>
            </thead>

            <tbody>
            <%
                List<Feedback> feedbacks = (List<Feedback>) request.getAttribute("feedbacks");

                if (feedbacks != null && !feedbacks.isEmpty()) {
                    for (Feedback fb : feedbacks) {
            %>
            <tr>
                <td><%= fb.getId() %></td>
                <td><%= fb.getAutor().getNome() %></td>
                <td>
                    <div><%= fb.getComentario() %></div>
                </td>
                <td><%= fb.getNota() %></td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="4">Nenhum feedback encontrado.</td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </section>

</main>

<script src="<%= request.getContextPath() %>/js/script.js"></script>
</body>
</html>