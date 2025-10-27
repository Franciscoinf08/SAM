<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>Central de Suporte</title>
        <link rel="stylesheet" type="text/css" href="../css/style.css">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    </head>

    <body>
        <header>
            <a href="cliente/dashboard.jsp">
                <img id="logotipo" src="../imgs/logotipo.png" alt="Logotipo SAM">
            </a>
            <h1>Suporte</h1>
            <nav>
                <a href="cliente/dashboard.jsp">Dashboard</a>
                <a href="transacoes.jsp">Transações</a>
                <a href="gestor/empresas.jsp">Empresas</a>
                <a href="notificacoes.jsp">Notificações</a>
                <a href="suporte.jsp" class="active">Suporte</a>
            </nav>

            <div class="hamburger-menu">
                <button id="hamburger-btn">&#9776;</button>
                <div id="hamburger-dropdown" class="dropdown-content">
                    <a href="perfil.jsp">Visualizar Perfil</a>
                </div>
            </div>
        </header>

        <main class="content">
            <h2>Tickets e FAQ</h2>
            <form class="formulario">
                <label>Título:</label>
                <input type="text" placeholder="Informe o problema">
                <label>Descrição:</label>
                <textarea> placeholder="Descreva detalhadamente..."</textarea>
                <button>Enviar Ticket</button>
            </form>
        </main>
        <script src="../js/script.js"></script>
    </body>

</html>