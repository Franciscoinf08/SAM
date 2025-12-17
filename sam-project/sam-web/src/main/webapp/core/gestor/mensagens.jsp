<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>SAM - Mensagens e Avisos</title>

        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
        <link rel="icon" href="<%=request.getContextPath()%>/imgs/favicon.ico">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>
        <header>
            <img id="logotipo" src="<%=request.getContextPath()%>/imgs/logotipo.png" alt="Logotipo SAM">
            <h1>Mensagens e Avisos</h1>
            <%@include file="/core/header.jsp" %>
        </header>

        <main>
            <% List<Usuario> clientes = (List<Usuario>) request.getAttribute("clientes");%>
            <form action="notificacoes" method="POST" class="formulario">
                <label for="titulo">
                    <input type="text" name="titulo" placeholder="Digite o assunto...">
                </label>
                <label for="mensagem">
                    <textarea name="mensagem" placeholder="mensagem"></textarea>
                </label>

                <label for="cliente">
                    <select name="cliente">
                        <%for(Usuario c : clientes) { %>
                        <option value="<%=c.getId()%>"><%=c.getNome()%></option>
                        <%}%>
                    </select>
                </label>

                <button type="submit">Enviar</button>
            </form>
        </main>

        <script src="<%=request.getContextPath()%>/js/script.js"></script>
    </body>

</html>
