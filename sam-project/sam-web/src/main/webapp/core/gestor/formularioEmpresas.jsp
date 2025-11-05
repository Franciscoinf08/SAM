
<%@taglib uri="jakarta.tags.core" prefix="c" %>
<%@taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@taglib uri="jakarta.tags.functions" prefix="fn" %>
<%@taglib uri="jakarta.tags.xml" prefix="x" %>
<%@taglib uri="jakarta.tags.sql" prefix="sql"%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cadastro de Empresa</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/EmpresaController" method="post">

        <h2>Cadastro de Empresa</h2>

        <label for="nomeEmpresa">Nome: </label>
        <input type="text" name="nomeEmpresa">
        <label for="cnpj">CNPJ: </label>
        <input type="text" name="cnpj">
        <label for="milheiroSeguranca">Milheiro de Seguranca: </label>
        <input type="number" name="milheiroSeguranca">

        <input type="submit">

    </form>
</body>
</html>
