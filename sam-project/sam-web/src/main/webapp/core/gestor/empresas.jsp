<%@ page import="java.util.List" %>
<%@ page import="sam.model.domain.Empresa" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    if (request.getAttribute("empresas") == null) {
        response.sendRedirect(request.getContextPath() + "/empresa");
        return;
    }
%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>SAM - Empresas</title>




    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap" rel="stylesheet">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>
<header>
    <img id="logotipo" src="<%= request.getContextPath() %>/imgs/logotipo.png" alt="Logotipo SAM">
    <h1>Empresas</h1>
    <nav>
        <a href="../transacoes.jsp">Transações</a>
        <a href="<%=request.getContextPath()%>/empresa" class="active">Empresas</a>
        <a href="../notificacoes.jsp">Notificações</a>
        <a href="../suporte.jsp">Suporte</a>
    </nav>

    <div class="hamburger-menu">
        <button id="hamburger-btn">&#9776;</button>
        <div id="hamburger-dropdown" class="dropdown-content">
            <a href="../perfil.jsp">Visualizar Perfil</a>
        </div>
    </div>
</header>

<main class="content">
    <h2>Gerenciar Empresas</h2>

    <%
        List<Empresa> empresas = (List<Empresa>) request.getAttribute("empresas");
        String erro = (String) request.getAttribute("erro");

        if (erro != null) {
    %>
    <p style="color:red;"><%= erro %></p>
    <%
        }
    %>

    <table>
        <%
            if (empresas != null && !empresas.isEmpty()) {
                for (Empresa empresa : empresas) {
        %>
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>CNPJ</th>
            <th>Milheiro Segurança</th>
        </tr>

        <tr>
            <td><%= empresa.getIdEmpresa() %></td>
            <td><%= empresa.getNome() %></td>
            <td><%= empresa.getCNPJ() %></td>
            <td><%= empresa.getMilheiroSeguranca() %></td>
            <td><a href="<%=request.getContextPath()%>/empresa?action=editar&id=<%= empresa.getIdEmpresa()%>"><button>Editar</button></a></td>
            <td><a href="<%= request.getContextPath() %>/programaFidelidade?action=listar&idEmpresa=<%=empresa.getIdEmpresa()%>"><button>Programas de Fidelidade</button></a></td>
            <td><a href="<%=request.getContextPath()%>/empresa?action=excluir&id=<%=empresa.getIdEmpresa()%>"><button>Excluir</button></a></td>

        </tr>
        <%
            }
        } else {
        %>
        <tr><td >Nenhuma empresa cadastrada.</td></tr>
        <%
            }
        %>
    </table>
    <a href="<%= request.getContextPath() %>/empresa?action=novo"><button>Adicionar Empresa</button></a>
</main>

<script src="<%= request.getContextPath() %>/js/script.js"></script>
</body>
</html>
