<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="sam.model.domain.PerguntaTicket" %>
<%@ page import="sam.model.service.GestaoPerguntasTicketService" %>
<%@ page import="java.util.List" %>
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

    <main class="content">
        <% if (usuario.getTipo() != UsuarioTipo.DESENVOLVEDOR) { %>
        <section class="formulario" style="max-width:none; margin-bottom:5vw">
            <h2>Registrar Ticket</h2>
            <form onsubmit="return window.confirm('Deseja mesmo registrar essa pergunta?')"
                  action="/sam/CadastroTicketController"
                  method="POST">
                <label>
                    <input type="hidden" name="usuario" value="<%= usuario.getId() %>">
                </label>
                <label>Título:
                    <input type="text" placeholder="Informe o problema" name="titulo" required>
                </label>
                <label>Descrição:
                   <textarea placeholder="Descreva detalhadamente" name="descricao"></textarea>
               </label>
               <button>Enviar Ticket</button>
            </form>
        </section>

        <section>
            <h2>Seus Tickets</h2>
            <div class="perguntas">
                <%
                    GestaoPerguntasTicketService manterPergunta = new GestaoPerguntasTicketService();
                    List<PerguntaTicket> listaPerguntas;

                    listaPerguntas = manterPergunta.listarPorUsuario(usuario);

                    if (listaPerguntas.isEmpty()) { %>
                        Ainda não há tickets
                    <% } for (PerguntaTicket pergunta : listaPerguntas) {
                %>
                <article class="pergunta-entry">
                    <h1 onclick="window.location.href = '/sam/core/geral/tickets.jsp?pergunta=<%= pergunta.getId() %>'" class="titulo-ticket">
                        <%= pergunta.getTitulo() %>
                    </h1>
                    <p style="white-space:pre-line;">
                        <%= pergunta.getDescricao() %>
                    </p>
                    <div class="acoes">
                        <button onclick="removerPergunta(<%= pergunta.getId() %>)">
                            <img src="/sam/imgs/remover.png">
                        </button>
                        <button class="botao-editar"
                                data-id="<%= pergunta.getId() %>"
                                data-usuario="<%= usuario.getId() %>"
                                data-titulo="<%= pergunta.getTitulo() %>"
                                data-descricao="<%= pergunta.getDescricao() %>">
                            <img src="/sam/imgs/editar.png">
                        </button>
                    </div>
                </article>
                <%}%>
            </div>
        </section>
        <%}%>
    </main>

    <%@include file="/core/mensagens-erro.jsp"%>

    <script src="/sam/js/botao-editar-pergunta-ticket.js"></script>
    <script src="/sam/js/send-form.js"></script>
    <script src="/sam/js/popup.js"></script>
    <script src="/sam/js/script.js"></script>
</body>
</html>
