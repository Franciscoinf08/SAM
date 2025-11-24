
<%@ page import="java.util.List" %>
<%@ page import="sam.model.domain.util.UsuarioTipo" %>
<%@ page import="sam.model.domain.Usuario" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>SAM - Clientes</title>

        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <header>
            <img id="logotipo" src="<%=request.getContextPath()%>>/imgs/logotipo.png" alt="Logotipo SAM">
            <h1>Clientes</h1>
            <%@include file="/core/header.jsp" %>
        </header>

        <main class="content">
            <h2>Meus clientes</h2>
            <%
                List<Usuario> clientes = (List<Usuario>) request.getAttribute("clientes");

            %>
            <table>
                <tr>
                    <th>Nome</th>
                    <th>CPF </th>
                    <th>E-mail </th>
                    <th>Ações</th>
                </tr>
                <%
                    for (Usuario c : clientes){
                %>
                <tr>
                    <td><%=c.getNome()%></td>
                    <td><%=c.getCPF()%></td>
                    <td><%=c.getEmail()%></td>
                    <td>
                        <div class="dropdown">
                            <button class="btn-associar" onclick="toggleDropdown(this)">Associar</button>

                            <div class="dropdown-content">
                                <a href="#" onclick="associarPrograma(10, 'Programa A')">Programa Smiles</a>
                                <a href="#" onclick="associarPrograma(10, 'Programa B')">Programa Multiplus</a>
                                <a href="#" onclick="associarPrograma(10, 'Programa C')">Programa Latam Pass</a>
                            </div>
                        </div>
                    </td>

                </tr>
                <%
                    }
                %>

            </table>
        </main>
        <script src="../../js/script.js"></script>

    </body>
</html>
