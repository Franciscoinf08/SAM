<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sam.model.dao.UsuarioDAO"%>

<% UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();%>

<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>SAM - Visulizar Usuários</title>

        <link rel="stylesheet" type="text/css" href="../../css/style.css">
    <link rel="icon" href="/sam/imgs/favicon.ico">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>

        <header>
            <img id="logotipo" src="../../imgs/logotipo.png" alt="Logotipo SAM">
            <h1>Gerenciar Usuários</h1>
            <%@include file="/core/header.jsp" %>
        </header>

        <main class="content">
            <h2>Gerenciar Usuarios</h2>
            <table>
                <tr>
                    <th>Nome</th>
                    <th>Tipo</th>
                    <th>Ações</th>
                </tr>
                <% long cont = 1;
                    while (true) {
                        Usuario cliente = usuarioDAO.pesquisar(cont);
                        if (cliente != null) {%>
                <tr>
                    <td><%=cliente.getNome()%></td>
                    <td><%=cliente.getTipo()%></td>
                    <td>
                        <button>Relatórios</button>
                        <button><a href="ver-permissoes.jsp?id=<%=cont%>">Permissões</a></button>
                    </td>
                </tr>
                <%} else {
                            break;
                        }
                        cont++;
                    }

                %>
            </table>
        </main>
        <script src="../../js/script.js"></script>
    </body>

</html>
