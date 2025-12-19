<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="sam.model.dao.UsuarioDAO" %>
<%@ page import="sam.model.common.Conexao" %>
<%@ page import="sam.model.domain.Usuario" %>
<%@ page import="java.util.List" %>

<%

    UsuarioDAO udao = UsuarioDAO.getInstance();
    List<Usuario> usuarios = udao.listarTodos();


%>

<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <title>SAM - Avaliações</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
</head>

<body>
<header>
    <img id="logotipo" src="<%= request.getContextPath() %>/imgs/logotipo.png" alt="Logotipo SAM">
    <h1>Avaliações</h1>
    <%@include file="/core/header.jsp" %>
</header>

<main class="dashboard">

    <!-- ============================ FEEDBACK =============================== -->
    <section class="card" style="flex:1 1 100%;">
        <h2>Adicionar Avaliação</h2>

        <form class="formulario" id="formAvaliacao" action="<%= request.getContextPath() %>/feedback" method="POST">
            <input type="hidden" name="idAutor" value="<%=usuario.getId()%>">
            <label>Usuário avaliado:
                <select name="idAvaliado" required>
                    <option value="">Selecione...</option>
                    <% for (Usuario u : usuarios) { %>
                        <option value="<%=u.getId()%>"><%=u.getNome()%></option>
                    <% } %>
                </select>
            </label>

            <label>Nota:
                <select name="nota" required>
                    <option value="5">5 - Excelente</option>
                    <option value="4">4 - Bom</option>
                    <option value="3">3 - Regular</option>
                    <option value="2">2 - Ruim</option>
                    <option value="1">1 - Péssimo</option>
                </select>
            </label>

            <label>Comentário:
                <textarea name="comentario" rows="3" required></textarea>
            </label>

            <button type="submit">Enviar Avaliação</button>
        </form>
    </section>

    <!-- ============================ DENÚNCIA =============================== -->
    <section class="card" style="flex:1 1 100%;">
        <h2>Denunciar Usuário</h2>

        <form class="formulario" action="<%= request.getContextPath() %>/denuncia" method="POST">

            <input type="hidden" name="idDenunciante" value="<%=usuario.getId()%>">

            <label>Usuário denunciado:
                <select name="idDenunciado" required>
                    <option value="">Selecione...</option>
                    <% for (Usuario u : usuarios) { %>
                        <option value="<%=u.getId()%>"><%=u.getNome()%></option>
                    <% } %>
                </select>
            </label>

            <label>Motivo:
                <select name="motivo" required>
                    <option value="Comportamento inadequado">Comportamento inadequado</option>
                    <option value="Fraude ou má conduta">Fraude ou má conduta</option>
                    <option value="Spam ou propaganda">Spam ou propaganda</option>
                    <option value="Outro">Outro</option>
                </select>
            </label>

            <label>Detalhes:
                <textarea name="detalhes" rows="3" required></textarea>
            </label>

            <button type="submit">Enviar Denúncia</button>
        </form>

    </section>

</main>
<script src="<%= request.getContextPath() %>/js/script.js"></script>

</body>
</html>
