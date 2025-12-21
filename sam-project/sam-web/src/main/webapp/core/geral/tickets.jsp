<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="sam.model.domain.PerguntaTicket" %>
<%@ page import="sam.model.service.GestaoPerguntasTicketService" %>
<%@ page import="sam.model.service.GestaoRespostasTicketService" %>
<%@ page import="sam.model.domain.RespostaTicket" %>
<%@ page import="java.util.List" %>
<%@ page import="sam.model.common.exception.PersistenciaException" %>
<%@ page import="sam.model.service.GestaoUsuariosService" %>
<%@ page import="sam.model.domain.util.UsuarioTipo" %>
<%@ page import="java.util.Objects" %>
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

        <section class="pergunta-card" <% if (usuario.getTipo() != UsuarioTipo.DESENVOLVEDOR) { %>style="margin-bottom:5vw"<%}%>>
            <h2>
                <%= manterUsuario.pesquisar(pergunta.getIdUsuario()).getNome() %>:
            </h2>
            <h1><%= pergunta.getTitulo() %></h1>
            <p style="white-space:pre-line;"><%= pergunta.getDescricao() %></p>
        </section>

        <% if (usuario.getTipo() == UsuarioTipo.DESENVOLVEDOR) { %>
        <section class="formulario" style="max-width:none; margin-bottom:5vw">
            <h2>Responder</h2>
            <form action="/sam/CadastroRespostaTicketController" method="POST">
                <label><input type="hidden" name="usuario" value="<%= usuario.getId() %>"></label>
                <label><input type="hidden" name="pergunta" value="<%= pergunta.getId() %>"></label>
                <label><textarea placeholder="Insira a resposta" name="descricao"></textarea></label>
                <button>Enviar</button>
            </form>
        </section>
        <%}%>

        <section class="respostas">
            <% for (RespostaTicket resposta : listaRespostas) { %>
            <article>
                <h2>
                    <%= manterUsuario.pesquisar(resposta.getIdUsuario()).getNome() %>:
                </h2>
                <p style="white-space:pre-line;">
                    <%= resposta.getDescricao() %>
                </p>
                <% if (Objects.equals(usuario.getId(), resposta.getIdUsuario())) { %>
                <div class="acoes">
                    <button onclick="removerResposta(<%= resposta.getId() %>, <%= pergunta.getId() %>)">
                        <img src="/sam/imgs/remover.png">
                    </button>
                    <button class="botao-editar"
                            data-id="<%= resposta.getId() %>"
                            data-usuario="<%= usuario.getId() %>"
                            data-pergunta="<%= pergunta.getId() %>"
                            data-descricao="<%= resposta.getDescricao() %>">
                        <img src="/sam/imgs/editar.png">
                    </button>
                </div>
                <%}%>
            </article>
            <%}%>
        </section>
    </main>

    <script src="/sam/js/botao-editar-resposta-ticket.js"></script>
    <script src="/sam/js/mensagens-erro.js"></script>
    <script src="/sam/js/send-form.js"></script>
    <script src="/sam/js/popup.js"></script>
    <script src="/sam/js/script.js"></script>
</body>
</html>
