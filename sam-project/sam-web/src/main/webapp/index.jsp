<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="pt-br">

    <head>
        <meta charset="UTF-8">
        <title>SAM - Login e Cadastro</title>

        <link rel="stylesheet" type="text/css" href="/sam/css/style.css">
        <link rel="stylesheet" type="text/css" href="/sam/css/login.css">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>
        <header>
            <img id="logotipo" src="/sam/imgs/logotipo.png" alt="Logotipo SAM">
            <h1>Login e Cadastro</h1>
        </header>

        <main>
            <div id="login">
                <h2>Acesse sua conta</h2>
                <div class="formulario form-autenticacao">
                    <form name="formLogin" method="POST">
                        <label>
                            <input name="cpf" type="text" placeholder="CPF (xxxxxxxxxxx)">
                        </label>
                        <label>
                            <input name="senha" type="password" placeholder="Senha">
                        </label>
                        <label>
                            <input type="button" value="Continuar" style="background-color:#e4e4e5;text-align:center" onclick="validarCamposLogin(document.formLogin)">
                        </label>
                    </form>
                </div>
            </div>
            <div id="cadastro" class="campo-inativo">
                <h2>Cadastre-se</h2>
                <div class="formulario form-autenticacao">
                    <form name="formCadastro" method="POST">
                        <label>
                            <input name="nome" type="text" placeholder="Nome">
                        </label>
                        <label>
                            <input name="email" type="text" placeholder="E-mail">
                        </label>
                        <label>
                            <input name="cpf" type="text" placeholder="CPF (xxxxxxxxxxx)">
                        </label>
                        <label>
                            <input name="senha" type="password" autocomplete="new-password" placeholder="Senha">
                        </label>
                        <label>
                            <input name="senhaConfirmar" type="password" autocomplete="new-password" placeholder="Confirmar Senha">
                        </label>
                        <label>
                            <input type="button" value="Continuar" style="background-color:#e4e4e5;text-align:center" onclick="validarCamposCadastro(document.formCadastro)">
                        </label>
                    </form>
                </div>
            </div>
        </main>
        <footer>
            <p id="alterna">Ainda não é cliente? <span id="link-alterna">Cadastre-se</span></p>
        </footer>

        <%
            String erro = (String) request.getAttribute("erro");
            if (erro != null) {%>
        <div class="mensagem-card"><%=erro%></div>
        <script>
            let mensagemEl = document.querySelector(".mensagem-card");
            setTimeout(() => {
                mensagemEl.style.top = "4em";
            }, 1);
            setTimeout(() => {
                mensagemEl.style.display = "none";
            }, 4000);
        </script>
        <%}%>
        
        <script src="/sam/js/helper.js"></script>
        <script src="/sam/js/toggle-login.js"></script>
    </body>

    </html>