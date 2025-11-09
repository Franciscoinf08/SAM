
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro de PF</title>
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
            <form action="<%= request.getContextPath() %>/programaFidelidade" method="post">

                <label>
                    <span>Nome do Programa</span>
                    <input type="text" name="nome" required>
                </label>


                <label>
                    <span>Bonus de Milhas</span>
                    <input type="number" name="bonusMilhas" required>
                </label>

                <label>
                    <span>Duração</span>
                    <input type="number" name="duracao" required>
                </label>
                <label>
                    <span> Milhas por mês</span>
                    <input type="number" name="qtdeMilhasMes" required>
                </label>

                <label>
                    <span>preco mensal</span>
                    <input type="number" name="preco" required>
                </label>

                <input type="hidden" name="idEmpresa" value="<%= request.getParameter("idEmpresa") %>">
                <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
                <button type="submit">Salvar</button>
            </form>
        </div>
</main>
</body>
</html>
