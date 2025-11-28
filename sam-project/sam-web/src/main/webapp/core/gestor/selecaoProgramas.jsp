
<%@ page import="java.util.List" %>
<%@ page import="sam.model.domain.ProgramaFidelidade" %>
<%@ page import="sam.model.service.EmpresaService" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Programas de Fidelidade</title>

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<header>
    <img id="logotipo" src="<%=request.getContextPath()%>>/imgs/logotipo.png" alt="Logotipo SAM">
    <h1>Selecionar programa</h1>
    <%@include file="/core/header.jsp" %>
</header>

<main class="content">
    <h2>Meus clientes</h2>
    ID do usuário (parameter): <%= request.getParameter("idUsuario") %><br>
    ID do usuário (attribute): <%= request.getAttribute("idUsuario") %><br>
    <%

        List<ProgramaFidelidade> programas = (List<ProgramaFidelidade>) request.getAttribute("lista");

    %>
    <table>
        <tr>
            <th>Empresa</th>
            <th>Nome</th>
            <th>Milhas p/ Mês</th>
            <th>Duracao</th>
            <th>Bonus de Milhas</th>
            <th>Preco</th>
            <th>Ações</th>
        </tr>
        <%
            EmpresaService empresaService = new EmpresaService();
            String idUsuario = (String) request.getAttribute("idUsuario");
            for (ProgramaFidelidade pf : programas){
                int idEmpresa = pf.getIdEmpresa();
                String nomeEmpresa = empresaService.buscarEmpresa(idEmpresa).getNome();
        %>
        <tr>
            <td><%=nomeEmpresa%></td>
            <td><%=pf.getNome()%></td>
            <td><%=pf.getQtdeMilhasMes()%></td>
            <td><%=pf.getDuracao()%></td>
            <td><%=pf.getBonusMilhas()%></td>
            <td><%=pf.getPrecoMensal()%></td>


            <td>
                <form action="<%=request.getContextPath()%>/usuarioPrograma" method="post">
                    <input type="hidden" name="action" value="associar">
                    <input type="hidden" name="idPrograma" value="<%= pf.getIdProgramaFidelidade() %>">
                    <input type="hidden" name="idUsuario" value="<%= idUsuario %>">
                    <button type="submit" class="btn-associar">Associar</button>
                </form>
            </td>
        </tr>
        <%
            }
        %>

    </table>
</main>
<script src="<%=request.getContextPath()%>/js/script.js"></script>

</body>
</html>

