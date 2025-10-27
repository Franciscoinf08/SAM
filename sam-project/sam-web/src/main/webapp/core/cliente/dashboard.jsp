<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>SAM - Dashboard</title>
        <link rel="stylesheet" type="text/css" href="../../css/style.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    </head>

    <body>
        <header>
            <img id="logotipo" src="../../imgs/logotipo.png" alt="Logotipo SAM">
            <h1>Dashboard</h1>
            <nav>
                <a href="dashboard.jsp" class="active">Dashboard</a>
                <a href="../transacoes.jsp">Transações</a>
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


        <main class="dashboard">
            <section class="card">
                <h2>Saldo Total</h2>
                <p>128.500 milhas</p>
            </section>
            <section class="card">
                <h2>Últimas Transações</h2>
                <ul>
                    <li>Compra - 5.000 milhas</li>
                    <li>Venda - 3.000 milhas</li>
                    <li>Bônus - 1.000 milhas</li>
                </ul>
            </section>
            <section class="card">
                <h2>Alertas</h2>
                <p>2 programas próximos da expiração.</p>
            </section>
        </main>
        <script src="../../js/script.js"></script>
    </body>

</html>