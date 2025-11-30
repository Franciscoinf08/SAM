<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sam.model.service.GestaoSolicitacoesService"%>
<%@page import="sam.model.domain.Solicitacao"%>

<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>SAM - Gerenciar Solicitações </title>

        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>

        <header>
            <img id="logotipo" src="<%= request.getContextPath() %>/imgs/logotipo.png" alt="Logotipo SAM">
            <h1>Enviar Solicitação de Pagamento</h1>
            <%@include file="/core/header.jsp" %>
        </header>

        <main class="content">
            <div class="formulario email">
                <h2>Solicitação de pagamento</h2>
                <%String id = (String) request.getAttribute("id");
                GestaoSolicitacoesService gestao = new GestaoSolicitacoesService();
                Solicitacao sol = gestao.pesquisar(id);%>
                <form action="/sam/solicitarGestor" method="POST">
                    <input type="hidden" name="acao" value="Pagamento">
                    <input type="hidden" name="id" value="<%=id%>">
                    
                    <label for="nome">Nome do usuário:
                        <input type="text" value="<%=sol.getNome()%>" name="nome" readonly>
                    </label>
                    <label for="email">Email:
                        <input type="text" value="<%=sol.getEmail()%>" name="email" readonly>
                    </label>
                    <label for="formaPagamento">Forma de pagamento escolhida:
                        <input type="text" value="<%=sol.getPagamento()%>" name="formaPagamento" readonly>
                    </label>
                    <label for="arquivo">Arquivo a anexar:
                        <input type="text" name="arquivo" placeholder="Cole o caminho do arquivo com as informações">
                    </label>
                    <input type="submit" value="Confirmar solicitação de pagamento">
                </form>
            </div>
        </main>
        <script src="/sam/js/script.js"></script>
    </body>

</html>
