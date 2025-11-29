<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sam.model.service.GestaoSolicitacoesService"%>
<%@page import="sam.model.domain.Solicitacao"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>SAM - Lista de Solicitações</title>

        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>

        <header>
            <img id="logotipo" src="<%= request.getContextPath() %>/imgs/logotipo.png" alt="Logotipo SAM">
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
                    <%  GestaoSolicitacoesService gestao = new GestaoSolicitacoesService();
                        List<Solicitacao> solicitacoes = gestao.listarEmail(usuario.getEmail());
                        for(Solicitacao sol : solicitacoes){
                            if(sol != null){
                        %>
                    <tr>
                        <td><%=sol.getPagamento()%></td>
                        <td><%=sol.getStatus()%></td>
                        <%  switch(sol.getStatus()){
                                case PENDENTE:
                                case AGUARDANDO:
                        %>
                        <td>
                            <a href="/sam/solicitarGestor?acao=Cancelar&id=<%=String.valueOf(sol.getId())%>"><button>Cancelar</button></a>
                        </td>
                        <%      break;
                                default:
                        %>
                        <td>
                            <p>Sem ações disponíveis</p>
                        </td>
                        <%      break;}%>
                    </tr>
                    <%} else { break; }}%>
                </table>
            </div>
        </main>
        <script src="../../js/script.js"></script>
    </body>

</html>
