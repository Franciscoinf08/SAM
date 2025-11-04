<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sam.model.domain.Usuario"%>
<%@page import="sam.controller.LoginController"%>

<%
    LoginController.validarSessao(request, response);

    Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
%>

<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>SAM - Empresas</title>

        <link rel="stylesheet" type="text/css" href="/sam/css/style.css">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>
        <header>
            <img id="logotipo" src="/sam/imgs/logotipo.png" alt="Logotipo SAM">
            <h1>Empresas</h1>
            <nav>
                <a href="/sam/core/transacoes.jsp">Transações</a>
                <a href="/sam/core/gestor/empresas.jsp" class="active">Empresas</a>
                <a href="/sam/core/notificacoes.jsp">Notificações</a>
                <a href="/sam/core/suporte.jsp">Suporte</a>
            </nav>

            <div class="hamburger-menu">
                <h1><%=usuario.getNome()%></h1>
                <button id="hamburger-btn">&#9776;</button>
                <div id="hamburger-dropdown" class="dropdown-content">
                    <a href="/sam/core/perfil.jsp">Visualizar Perfil</a>
                </div>
            </div>
        </header>

        <main class="content">
            <h2>Gerenciar Empresas</h2>
            <table>
                <tr>
                    <th>Nome</th>
                    <th>Programa</th>
                    <th>Ações</th>
                </tr>
                <tr>
                    <td>Latam</td>
                    <td>Latam Pass</td>
                    <td><button>Editar</button></td>
                </tr>
                <tr>
                    <td>Azul</td>
                    <td>Azul Fidelidade</td>
                    <td><button>Editar</button></td>
                </tr>
            </table>
        </main>
        
        <script src="/sam/js/script.js"></script>
    </body>

</html>