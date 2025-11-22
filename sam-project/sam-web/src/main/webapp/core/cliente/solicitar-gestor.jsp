<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>SAM - Solicitar Conta Gestor</title>

        <link rel="stylesheet" type="text/css" href="../../css/style.css">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>

        <header>
            <img id="logotipo" src="../../imgs/logotipo.png" alt="Logotipo SAM">
            <h1>Solicitar Conta Gestor</h1>
            <%@include file="/core/header.jsp" %>
        </header>

        <main class="content">
            <div id="novaSolicitacao">
                <h2>Realizar nova solicitação</h2>
                <a href="/sam/core/cliente/lista-solicitacoes.jsp" id="solicitar-gestor" class="button-a">Listar solicitação</a>
                <form action="/solicitarGestor?acao=Pedir&nome=<%=usuario.getNome()%>&email=<%=usuario.getEmail()%>" method="POST" name="formSolicitarGestor">
                    <input type="text" placeholder="Forma de pagamento" list="pagamento" name="forma-pagamento">
                    <datalist id="pagamento">
                        <option value="boleto">Boleto</option>
                        <option value="cartão">Cartão</option>
                        <option value="pix">Pix</option>
                    </datalist>
                    <input type="submit" value="Confirmar solicitação">
                </form>
            </div>
        </main>
        <script src="../../js/script.js"></script>
    </body>

</html>
