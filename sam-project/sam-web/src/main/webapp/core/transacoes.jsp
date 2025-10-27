<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>Transações</title>
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
            <h1>Transações</h1>
            <nav>
                <a href="cliente/dashboard.jsp">Dashboard</a>
                <a href="transacoes.jsp" class="active">Transações</a>
                <a href="gestor/empresas.jsp">Empresas</a>
                <a href="notificacoes.jsp">Notificações</a>
                <a href="suporte.jsp">Suporte</a>
            </nav>

            <div class="hamburger-menu">
                <button id="hamburger-btn">&#9776;</button>
                <div id="hamburger-dropdown" class="dropdown-content">
                    <a href="perfil.jsp">Visualizar Perfil</a>
                </div>
            </div>
        </header>

        <main class="content">
            <h2>Registrar Transação</h2>
            <form class="formulario">
                <label>Tipo:</label>
                <select>
                    <option>Compra</option>
                    <option>Venda</option>
                    <option>Bônus</option>
                </select>

                <label>Quantidade:</label>
                <input type="number" placeholder="Digite a quantidade">

                <label>Valor (R$):</label>
                <input type="text" placeholder="0,00">

                <button>Registrar</button>
            </form>
        </main>
        <script src="../js/script.js"></script>
    </body>

</html>