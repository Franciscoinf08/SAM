<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <title>SAM - Login/Cadastro</title>
    </head>

    <body>
        <header>
            <a href="../Tela-cliente/index.html"><img id="logotipo" src="../logotipo.png" alt="Logotipo SAM"></a>
            <h1>Login e Cadastro</h1>
            <nav>
                <a href="../Tela-cliente/index.html">Dashboard</a>
                <a href="transacoes.html">Transações</a>
                <a href="../Tela-gestor/empresas.html">Empresas</a>
                <a href="notificacoes.html">Notificações</a>
                <a href="suporte.html">Suporte</a>
            </nav>
            <div class="login-menu"><a href="login.html" class="active">Login/Cadastrar-se</a></div>
            <div class="hamburger-menu">
                <button id="hamburger-btn">&#9776;</button>
                <div id="hamburger-dropdown" class="dropdown-content">
                    <a href="perfil.html">Visualizar Perfil</a>
                </div>
            </div>
        </header>
        <main>
            <div id="login">
                <h2>Acesse sua conta</h2>
                <form>
                    <div class="input-identificacao">
                        <input name="cpf" type="text" placeholder="CPF">
                    </div>
                    <div class="input-identificacao">
                        <input name="cpf" type="text" placeholder="Senha">
                    </div>
                    <div class="input-identificacao enviar-identificacao">
                        <input name="continuar" type="submit" value="Continuar">
                    </div>
                </form>
            </div>
            <div id="cadastro" class="campo-inativo">
                <h2>Cadastre-se</h2>
                <form>
                    <div class="input-identificacao">
                        <input name="nome" type="text" placeholder="Nome">
                    </div>
                    <div class="input-identificacao">
                        <input name="e-mail" type="text" placeholder="E-mail">
                    </div>
                    <div class="input-identificacao">
                        <input name="cpf" type="text" placeholder="CPF">
                    </div>
                    <div class="input-identificacao">
                        <input name="senha" type="text" placeholder="Senha">
                    </div>
                    <div class="input-identificacao">
                        <input name="senha-confirmar" type="text" placeholder="Confirmar Senha">
                    </div>
                    <div class="input-identificacao enviar-identificacao">
                        <input name="continuar" type="submit" value="Continuar">
                    </div>
                </form>
            </div>
        </main>
        <footer>
            <p id="alterna">Ainda não é cliente? <span id="link-alterna">Cadastre-se</span></p>
        </footer>
        <script src="/js/script.js"></script>
        <script src="/js/login.js"></script>
    </body>

</html>
