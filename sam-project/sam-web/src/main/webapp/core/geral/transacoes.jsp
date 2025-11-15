<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="sam.model.domain.util.UsuarioTipo" %>
<%@ page import="sam.model.service.GestaoUsuariosService" %>
<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>SAM - Transações</title>

        <link rel="stylesheet" type="text/css" href="../../css/style.css">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>
        <header>
            <img id="logotipo" src="../../imgs/logotipo.png" alt="Logotipo SAM">
            <h1>Transações</h1>
            <%@include file="/core/header.jsp" %>
        </header>

        <main class="content">
            <h2>Registrar Transação</h2>
            <form class="formulario" action="/CadastroTransacaoController" method="POST">
                <%if (usuario.getTipo() == UsuarioTipo.GESTOR) {%>
                <label>Cliente:
                    <% GestaoUsuariosService manterUsuario = new GestaoUsuariosService(); %>
                    <select>
                        <% for (Usuario cliente : manterUsuario.getListaClientes(usuario)) {%>
                        <option value="<%= cliente.getId() %>"><%= cliente.getNome() %></option>
                        <%}%>
                    </select>
                </label>
                <%}%>

                <label for="tipo">Tipo:
                    <select>
                        <option>Compra</option>
                        <option>Venda</option>
                    </select>
                </label>

                <label for="quantidade">Quantidade:
                    <input type="number" min="1" placeholder="1">
                </label>

                <label for="valor">Valor (R$):
                    <input type="number" step="0.01" min="0.01" placeholder="0,01">
                </label>

                <label for="bonus">Valor (R$):
                    <input type="number" step="0.01" min="0.00" placeholder="0,00">
                </label>

                <button>Registrar</button>
            </form>
        </main>

        <%@include file="/core/mensagens-erro.jsp"%>

        <script src="/sam/js/script.js"></script>
    </body>

</html>