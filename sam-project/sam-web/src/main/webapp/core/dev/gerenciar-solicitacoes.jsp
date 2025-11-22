<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sam.model.dao.UsuarioDAO"%>
<%@page import="java.util.List"%>

<% UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();%>

<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>SAM - Gerenciar Solicitações </title>

        <link rel="stylesheet" type="text/css" href="../../css/style.css">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>

        <header>
            <img id="logotipo" src="../../imgs/logotipo.png" alt="Logotipo SAM">
            <h1>Gerenciar Solicitações</h1>
            <%@include file="/core/header.jsp" %>
        </header>

        <main class="content">
            <h2>Solicitações para conta gestor</h2>
            <table>
                <tr>
                    <th>Nome</th>
                    <th>E-mail</th>
                    <th>Forma de Pagamento</th>
                    <th>Status</th>
                    <th>Ações</th><!-- Inicial: Solicitar pagamento / Final: Aprovar e Recusar -->
                </tr>
                
                <%  GestaoSolicitacoes gestao = new GestaoSolicitacoesService();
                    List<Solicitacao> solicitacoes = gestao.lista(usuario.getEmail());
                    for(Solicitacao sol : solicitacoes){
                    %>
                    <tr>
                        <td><%=sol.getNome()%></td>
                        <td><%=sol.getEmail()%></td>
                        <td><%=sol.getPagamento()%></td>
                        <td><%=sol.getStatus()%></td>
                        <%  switch(sol.getStatus()){
                                case PENDENTE:
                        %>
                        <td>
                            <button><a href="/sam/solicitarGestor?acao=Pagamento&id=<%=sol.getId%>">Solicitar Pagamento</a></button>
                        </td>
                        <%      break;
                                case AGUARDANDO:
                        %>
                        <td>
                            <button><a href="/sam/solicitarGestor?acao=Aprovar&id=<%=sol.getId%>">Aprovar</a></button>
                            <button><a href="/sam/solicitarGestor?acao=Recusar&id=<%=sol.getId%>">Recusar</a></button>
                        </td>
                        <%      break;
                                default:
                        %>
                        <td>
                            <p>Sem ações disponíveis</p>
                        </td>
                        <%      break;}%>
                    </tr>
                    <%}%>
            </table>
        </main>
        <script src="../../js/script.js"></script>
    </body>

</html>
