<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sam.model.domain.Usuario"%>
<%@page import="sam.controller.LoginController"%>

<%
    LoginController.validarSessao(request, response);

    Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
%>

<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>SAM - Perfil</title>

        <link rel="stylesheet" type="text/css" href="/sam/css/style.css">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>
        <header>
            <img id="logotipo" src="/sam/imgs/logotipo.png" alt="Logotipo SAM">
            <h1>Perfil</h1>
            <nav>
                <a href="/sam/core/cliente/dashboard.jsp">Dashboard</a>
                <a href="/sam/core/transacoes.jsp">Transações</a>
                <a href="/sam/core/gestor/empresas.jsp">Empresas</a>
                <a href="/sam/core/notificacoes.jsp">Notificações</a>
                <a href="/sam/core/suporte.jsp">Suporte</a>
            </nav>

            <div class="hamburger-menu">
                <h1><%=usuario.getNome()%></h1>
                <button id="hamburger-btn">&#9776;</button>
                <div id="hamburger-dropdown" class="dropdown-content">
                    <a href="/sam/core/perfil.jsp">Visualizar Perfil</a>
                </div>
            </div>
        </header>

        <main>
            <div class="formulario">
                <label>CPF: <%=usuario.getCPF()%></label>
                <label>Tipo do Usuário: <%=usuario.getTipo()%></label>
                <form name="formAlteracao" method="POST">
                    <label for="nome">Nome:
                        <input type="text" name="nome" placeholder="<%=usuario.getNome()%>">
                    </label>

                    <label for="email">Email:
                        <input type="email" name="email" placeholder="<%=usuario.getEmail()%>">
                    </label>

                    <label for="senha">Senha:
                        <input type="password" autocomplete="new-password" name="senha">
                    </label>

                    <label for="senha-confirmar">Confirmar Senha:
                        <input type="password" autocomplete="new-password" name="senhaConfirmar">
                    </label>

                    <button onclick="validarCamposAlteracaoPerfil(document.formAlteracao)">Salvar Alterações</button>
                </form>

                <button id="solicitar-gestor">Solicitar conta de gestor</button>
            </div>
        </main>

        <%
            String erro = (String) request.getAttribute("erro");
            if (erro != null) {%>
        <div class="mensagem-card"><%=erro%></div>
        <script>
            let mensagemEl = document.querySelector(".mensagem-card");
            setTimeout(() => { mensagemEl.style.top = "4em"; }, 1);
            setTimeout(() => { mensagemEl.style.display = "none"; }, 4000);
        </script>
        <%}%>

        <script src="/sam/js/helper.js"></script>
        <script src="/sam/js/script.js"></script>
        <script src="/sam/js/botao-solicitar.js"></script>
    </body>

</html>