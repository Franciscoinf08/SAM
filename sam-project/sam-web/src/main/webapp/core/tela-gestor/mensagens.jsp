<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>Mensagens e Avisos</title>
        <link rel="stylesheet" type="text/css" href="/css/style.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    </head>

    <body>
        <header>
            <a href="../tela-cliente/dashboard.jsp">
                <img id="logotipo" src="/imgs/logotipo.png" alt="Logotipo SAM"> 
            </a>
            <h1>Mensagens e Avisos</h1>
            <nav>
                <a href="../tela-cliente/dashboard.jsp">Dashboard</a>
                <a href="../tela-clienteGestor/transacoes.jsp">Transações</a>
                <a href="empresas.jsp">Empresas</a>
                <a href="../tela-clienteGestor/notificacoes.jsp">Notificações</a>
                <a href="../tela-clienteGestor/suporte.jsp">Suporte</a>
            </nav>
            <div class="login-menu">
                <a href="../login.jsp">Login/Cadastrar-se</a>
            </div>
            <div class="hamburger-menu">
                <button id="hamburger-btn">&#9776;</button>
                <div id="hamburger-dropdown" class="dropdown-content">
                    <a href="../tela-clienteGestor/perfil.jsp">Visualizar Perfil</a>
                </div>
            </div>
        </header>

        <main class="dashboard">
            <section class="content" style="flex:1 1 100%;">
                <h2>Enviar Mensagem</h2>
                <form class="formulario">
                    <label for="destinatario">Destinatário:</label>
                    <input type="text" id="destinatario" placeholder="Nome do usuário ou cliente">

                    <label for="assunto">Assunto:</label>
                    <input type="text" id="assunto" placeholder="Assunto da mensagem">

                    <label for="mensagem">Mensagem:</label>
                    <textarea id="mensagem" rows="4" placeholder="Escreva sua mensagem..."></textarea>

                    <button type="button">Enviar Mensagem</button>
                </form>
            </section>
        </main>

        <script src="/js/script.js"></script>
    </body>

</html>
