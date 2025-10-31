<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="pt-br">

    <head>
        <meta charset="UTF-8">
        <title>SAM - Login e Cadastro</title>

        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="css/login.css">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>
        <header>
            <img id="logotipo" src="imgs/logotipo.png" alt="Logotipo SAM">
            <h1>Login e Cadastro</h1>
        </header>
        <main>
            <div id="login">
                <h2 style="margin-bottom: 1em">Acesse sua conta</h2>
                <div class="formulario">
                    <form name="formLogin" method="POST">
                        <input name="usuario" type="text" placeholder="Usuário">
                        <input name="senha" type="password" placeholder="Senha">
                        <input value="Continuar" style="background-color:#e4e4e5;text-align:center" onclick="validarCamposLogin(document.formLogin)">
                    </form>
                </div>
            </div>
            <div id="cadastro" class="campo-inativo">
                <h2 style="margin-bottom: 1em">Cadastre-se</h2>
                <div class="formulario">
                    <form name="formCadastro" method="POST">
                        <input name="nome" type="text" placeholder="Nome">
                        <input name="email" type="text" placeholder="E-mail">
                        <input name="cpf" type="text" placeholder="CPF (xxxxxxxxxxx)">
                        <input name="senha" type="password" placeholder="Senha">
                        <input name="senhaConfirmar" type="password" placeholder="Confirmar Senha">
                        <input value="Continuar" style="background-color:#e4e4e5;text-align:center" onclick="validarCamposCadastro(document.formCadastro)">
                    </form>
                </div>
            </div>
        </main>
        <footer>
            <p id="alterna">Ainda não é cliente? <span id="link-alterna">Cadastre-se</span></p>
        </footer>
        <script src="js/helper.js"></script>
        <script src="js/toggle-login.js"></script>
    </body>

    </html>