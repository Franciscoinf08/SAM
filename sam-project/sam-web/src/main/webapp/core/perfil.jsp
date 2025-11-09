<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>SAM - Perfil</title>

        <link rel="stylesheet" type="text/css" href="../css/style.css">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>
        <header>
            <img id="logotipo" src="../imgs/logotipo.png" alt="Logotipo SAM">
            <h1>Perfil</h1>
            <nav>
                <a href="cliente/dashboard.jsp">Dashboard</a>
                <a href="transacoes.jsp" class="active">Transações</a>
                <a href="<%=request.getContextPath()%>/empresa" class="active">Empresas</a>
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

        <main>
            <div class="formulario">
                <form>
                    <label for="nome">Nome:
                        <input type="text" name="nome">
                    </label>

                    <label for="nome">CPF:
                        <input type="text" name="cpf">
                    </label>

                    <label for="email">Email:
                        <input type="email" name="email">
                    </label>

                    <label for="senha">Senha:
                        <input type="password" name="senha">
                    </label>

                    <button type="submit">Salvar Alterações</button>
                </form>

                <button id="solicitar-gestor">Solicitar conta de gestor</button>
            </div>
        </main>
        <script src="../js/script.js"></script>
        <script src="../js/botao-solicitar.js"></script>
    </body>

</html>