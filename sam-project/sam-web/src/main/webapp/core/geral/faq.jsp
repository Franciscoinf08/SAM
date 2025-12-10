<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="sam.model.domain.util.UsuarioTipo" %>
<%@ page import="sam.model.service.GestaoFaqService" %>
<%@ page import="sam.model.domain.FaqEntry" %>
<%@ page import="java.util.List" %>
<html>

<head>
    <meta charset="UTF-8">
    <title>SAM - FAQ</title>

    <link rel="stylesheet" type="text/css" href="/sam/css/style.css">
    <link rel="stylesheet" type="text/css" href="/sam/css/faq.css">
    <link rel="icon" href="/sam/imgs/favicon.ico">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>
    <% boolean logado = request.getSession().getAttribute("usuario") != null; %>
    <header>
        <img id="logotipo" src="/sam/imgs/logotipo.png" alt="Logotipo SAM">
        <h1>FAQ</h1>
        <% if (logado) { %>
            <%@include file="/core/header.jsp" %>
        <%}%>
    </header>

    <main>
        <%
            Usuario user = (Usuario) request.getSession().getAttribute("usuario");
            if (logado && user.getTipo() == UsuarioTipo.DESENVOLVEDOR) {
        %>
        <section class="formulario" style="max-width:none; margin-bottom:5vw">
            <h2>Registrar FAQ</h2>
            <form name="formCadastroFaq"
                  onsubmit="return window.confirm('Deseja mesmo registrar essa pergunta?')"
                  action="/sam/CadastroFaqController"
                  method="POST">

                <label for="titulo">
                    <input name="titulo" type="text" placeholder="Insira um título" required>
                </label>

                <label for="pergunta">
                    <textarea name="pergunta" placeholder="Detalhe a pergunta" value=""></textarea>
                </label>

                <label for="resposta">
                    <textarea name="resposta" placeholder="Insira a resposta" required></textarea>
                </label>

                <button>Enviar FAQ</button>
            </form>
        </section>
        <%} else if (!logado) {%>
        <h1 onclick="window.location.href = '/sam'" id="voltar">Voltar ao Login e Cadastro</h1>
        <%}%>

        <section class="faq">
            <%
                GestaoFaqService manterFaq = new GestaoFaqService();
                List<FaqEntry> listaFaq = manterFaq.listarFaq();
                for (FaqEntry faq : listaFaq) {
            %>
            <details class="faq-entry">
                <summary>
                    <% if (logado && user.getTipo() == UsuarioTipo.DESENVOLVEDOR) { %>
                    <div class="acoes">
                        <button onclick="removerFaq(<%= faq.getId() %>)"><img src="/sam/imgs/remover.png"></button>
                        <button onclick="abrirPopupEditarFaq(<%= faq.getId() %>, '<%= faq.getTitulo() %>', '<%= faq.getPergunta() %>', '<%= faq.getResposta() %>')">
                            <img src="/sam/imgs/editar.png">
                        </button>
                    </div>
                    <%}%>
                    <h1><%= faq.getTitulo() %></h1>
                </summary>
                <p><%= faq.getPergunta() %></p>
                <h2>Solução:</h2>
                <p><%= faq.getResposta() %></p>
            </details>
            <%}%>
        </section>
    </main>

    <%@include file="/core/mensagens-erro.jsp"%>

    <script src="/sam/js/popup.js"></script>
    <script src="/sam/js/send-form.js"></script>
    <script src="/sam/js/script.js"></script>
</body>
</html>
