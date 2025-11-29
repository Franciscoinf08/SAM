<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
            <%@include file="/core/header.jsp" %>
        </header>

        <main class="main-perfil">
            <div class="formulario form-autenticacao">
                <h1>Alterar dados de Usuario</h1>
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

                    <label>
                        <button type="button" onclick="validarCamposAlteracaoPerfil(document.formAlteracao)">Salvar Alterações</button>
                    </label>
                </form>
                <%if (ControleAutorizacao.checkPermissao("solicitar-gestor", usuario.getTipo()) && !ControleAutorizacao.checkBloqueio("solicitar-gestor", usuario.getCPF())){%>
                <h1>Solicitar conta gestor</h1>
                <h2>Realizar nova solicitação</h2>
                <a href="/sam/core/cliente/lista-solicitacoes.jsp"><button>Listar solicitação</button></a>
                <form action="/sam/solicitarGestor" method="POST" id="formSolicitarGestor">

                    <input type="hidden" name="acao" value="Pedir">
                    <input type="hidden" name="nome" value="<%=usuario.getNome()%>">
                    <input type="hidden" name="email" value="<%=usuario.getEmail()%>">
                    <input type="hidden" name="idUsuario" value="<%=String.valueOf(usuario.getId())%>">

                    <select name="formaPagamento" required >
                        <option value="">Selecione...</option>
                        <option value="boleto">Boleto</option>
                        <option value="cartao">Cartão</option>
                        <option value="pix">Pix</option>
                    </select>

                    <input type="submit" value="Confirmar solicitação">
                </form>
                <%}%>
            </div>
            <div class="formulario">
                <form action="/sam/core/cliente/selecao-formularios.jsp">
                    <h1>Formularios de definição de objetivos</h1>
                    <button>Acessar meus formularios</button>
                </form>
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