<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>SAM - Mensagens e Avisos</title>

        <link rel="stylesheet" type="text/css" href="../../css/style.css">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>
        <header>
            <img id="logotipo" src="../../imgs/logotipo.png" alt="Logotipo SAM">
            <h1>Mensagens e Avisos</h1>
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

        <main class="dashboard">
            <section class="content" style="flex:1 1 100%;">
                <h2>Enviar Mensagem</h2>
                <form class="formulario">
                    <label for="destinatario">Destinatário:
                        <input type="text" placeholder="Nome do usuário ou cliente">
                    </label>

                    <label for="assunto">Assunto:
                        <input type="text" placeholder="Assunto da mensagem">
                    </label>

                    <label for="mensagem">Mensagem:
                        <textarea rows="4" placeholder="Escreva sua mensagem..."></textarea>
                    </label>

                    <button type="submit">Enviar Mensagem</button>
                </form>
            </section>
        </main>

        <script src="../../js/script.js"></script>
    </body>

</html>
