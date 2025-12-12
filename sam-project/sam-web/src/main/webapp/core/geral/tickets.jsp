<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="sam.model.domain.PerguntaTicket" %>
<%@ page import="sam.model.service.GestaoPerguntasTicketService" %>
<%@ page import="sam.model.service.GestaoRespostasTicketService" %>
<%@ page import="sam.model.domain.RespostaTicket" %>
<%@ page import="java.util.List" %>
<%@ page import="sam.model.common.exception.PersistenciaException" %>
<%@ page import="sam.model.service.GestaoUsuariosService" %>
<%@ page import="sam.model.domain.util.UsuarioTipo" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>SAM - Tickets de Suporte</title>

    <link rel="stylesheet" type="text/css" href="/sam/css/style.css">
    <link rel="stylesheet" type="text/css" href="/sam/css/suporte.css">
    <link rel="icon" href="/sam/imgs/favicon.ico">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    <header>
        <img id="logotipo" src="/sam/imgs/logotipo.png" alt="Logotipo SAM">
        <h1>Tickets de Suporte</h1>
        <%@include file="/core/header.jsp" %>
    </header>

    <%
        Long idPergunta = null;
        try {
            idPergunta = Long.parseLong(request.getParameter("pergunta"));
        } catch (RuntimeException e) {
            String erro = "Dados inválidos";
            request.setAttribute("erro", erro);

            RequestDispatcher rd = request.getRequestDispatcher("/core/geral/suporte.jsp");
            rd.forward(request, response);
        }

        GestaoPerguntasTicketService manterPergunta = new GestaoPerguntasTicketService();
        GestaoRespostasTicketService manterResposta = new GestaoRespostasTicketService();
        GestaoUsuariosService manterUsuario = new GestaoUsuariosService();

        PerguntaTicket pergunta = null;
        List<RespostaTicket> listaRespostas = null;
        try {
            pergunta = manterPergunta.pesquisar(idPergunta);
            listaRespostas = manterResposta.listarPorPergunta(pergunta);
        }  catch (PersistenciaException e) {
            request.setAttribute("erro", e.getLocalizedMessage());

            RequestDispatcher rd = request.getRequestDispatcher("/core/geral/suporte.jsp");
            rd.forward(request, response);
        } catch (NullPointerException e) {
            request.setAttribute("erro", "A pergunta não existe");

            RequestDispatcher rd = request.getRequestDispatcher("/core/geral/suporte.jsp");
            rd.forward(request, response);
        }
    %>
    <main>
        <h1 class="voltar" onclick="window.location.href = '/sam/core/geral/suporte.jsp'">
            Voltar para o suporte
        </h1>

        <section class="respostas">
            <article class="pergunta-card">
                <h1><%= pergunta.getTitulo() %></h1>
                <p><%= pergunta.getDescricao() %></p>
            </article>

            <% for (RespostaTicket resposta : listaRespostas) { %>
            <article>
                <h2><%= manterUsuario.pesquisar(resposta.getIdUsuario()).getNome() %>:</h2>
                <p><%= resposta.getDescricao() %></p>

                <% if (usuario.getTipo() == UsuarioTipo.DESENVOLVEDOR) { %>
                <div class="acoes">
                    <button onclick="removerResposta(<%= resposta.getId() %>)"><img src="/sam/imgs/remover.png"></button>
                    <button onclick="abrirPopupEditarRespostaTicket(<%= resposta.getId() %>, '<%= resposta.getDescricao() %>')">
                        <img src="/sam/imgs/editar.png">
                    </button>
                </div>
                <%}%>
            </article>
            <%}%>
        </section>
    </main>

    <script src="/sam/js/mensagens-erro.js"></script>
    <script src="/sam/js/send-form.js"></script>
    <script src="/sam/js/popup.js"></script>
    <script src="/sam/js/script.js"></script>
</body>
</html>
