<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sam.model.service.GestaoSolicitacoes"%>
<%@page import="sam.model.domain.Solicitacao"%>

<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>SAM - Lista de Solicitações</title>

        <link rel="stylesheet" type="text/css" href="../../css/style.css">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>

        <header>
            <img id="logotipo" src="../../imgs/logotipo.png" alt="Logotipo SAM">
            <h1>Lista de Solicitações</h1>
            <%@include file="/core/header.jsp" %>
        </header>

        <main class="content">
            <div id="listaSolicitacoes">
                <h2>Solicitações antigas</h2>
                <table>
                    <tr>
                        <th>Forma de pagamento</th>
                        <th>Status</th>
                        <th>Ações</th>
                    </tr>
                    <%  GestaoSolicitacoes gestao = new GestaoSolicitacoesService();
                        List<Solicitacao> solicitacoes = gestao.lista(usuario.getEmail());
                        for(Solicitacao sol : solicitacoes){
                            if(sol != null){
                        %>
                    <tr>
                        <td><%=sol.getPagamento()%></td>
                        <td><%=sol.getStatus()%></td>
                        <td>
                            <button><a href="/sam/solicitarGestor?acao=Cancelar&id=<%=sol.getId%>">Cancelar</a></button>
                        </td>
                    </tr>
                    <%} else { break; }}%>
                </table>
            </div>
        </main>
        <script src="../../js/script.js"></script>
    </body>

</html>
