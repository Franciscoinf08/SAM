<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>Empresas e Programas</title>
        <link rel="stylesheet" type="text/css" href="../../css/style.css">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    </head>

    <body>
        <header>
            <a href="../cliente/dashboard.jsp">
                <img id="logotipo" src="../../imgs/logotipo.png" alt="Logotipo SAM">
            </a>
            <h1>Empresas</h1>
            <nav>
                <a href="../cliente/dashboard.jsp">Dashboard</a>
                <a href="../transacoes.jsp">Transações</a>
                <a href="empresas.jsp" class="active">Empresas</a>
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
            <table>
                <tr>
                    <th>Nome</th>
                    <th>Programa</th>
                    <th>Ações</th>
                </tr>
                <tr>
                    <td>Latam</td>
                    <td>Latam Pass</td>
                    <td><button>Editar</button></td>
                </tr>
                <tr>
                    <td>Azul</td>
                    <td>Azul Fidelidade</td>
                    <td><button>Editar</button></td>
                </tr>
            </table>
        </main>
        <script src="../../js/script.js"></script>
    </body>

</html>