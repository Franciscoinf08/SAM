
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="sam.model.domain.Empresa"%>


<html>
<head>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro de Empresa</title>
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
    <div class="formulario">
        <form action="<%= request.getContextPath() %>/empresa" method="post">
            <input type="hidden" name="action" value="<%= request.getAttribute("empresa") == null ? "inserir" : "atualizar" %>">
            <input type="hidden" name="id" value="<%= request.getAttribute("empresa") != null ? ((Empresa)request.getAttribute("empresa")).getIdEmpresa() : "" %>">

            <label for="nomeEmpresa">Nome:</label>
            <input type="text" id="nomeEmpresa" name="nomeEmpresa" required
                   value="<%= request.getAttribute("empresa") != null ? ((Empresa)request.getAttribute("empresa")).getNome() : "" %>">

            <label for="cnpj">CNPJ:</label>
            <input type="text" id="cnpj" name="cnpj" required
                   value="<%= request.getAttribute("empresa") != null ? ((Empresa)request.getAttribute("empresa")).getCNPJ() : "" %>">

            <label for="milheiroSeguranca">Milheiro Segurança:</label>
            <input type="number" id="milheiroSeguranca" name="milheiroSeguranca" step="0.01" required
                   value="<%= request.getAttribute("empresa") != null ? ((Empresa)request.getAttribute("empresa")).getMilheiroSeguranca() : "" %>">

            <button type="submit">Enviar</button>
            <a href="<%=request.getContextPath()%>/empresa">Cancelar</a>
        </form>
    </div>

</main>
</body>
</html>
