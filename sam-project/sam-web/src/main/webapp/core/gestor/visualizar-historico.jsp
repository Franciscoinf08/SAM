<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="sam.model.domain.Usuario" %>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <title>SAM - Visualizar Historico</title>

    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
    <link rel="icon" href="/sam/imgs/favicon.ico">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>
<header>
    <img id="logotipo" src="../../imgs/logotipo.png" alt="Logotipo SAM">
    <h1>Historicos</h1>
    <%@include file="/core/header.jsp" %>
</header>

<main>
    <h2>Clientes Cadastrados</h2>

    <div class="tabela-container">
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Email</th>
                <th>Ações</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");

                if (usuarios != null && !usuarios.isEmpty()) {
                    for (Usuario u : usuarios) {
            %>
            <tr>
                <td><%= u.getId() %></td>
                <td><%= u.getNome() %></td>
                <td><%= u.getEmail() %></td>
                <td>
                    <form action="<%= request.getContextPath() %>/HistoricoController" method="post">
                        <input type="hidden" name="idUsuario" value="<%= u.getId() %>">
                        <button type="submit">Ver Histórico</button>
                    </form>
                </td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="4">Nenhum usuário encontrado.</td>
            </tr>
            <%
                }
            %>
            </tbody>

        </table>
    </div>
</main>


</body>
</html>