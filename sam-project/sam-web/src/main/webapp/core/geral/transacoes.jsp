<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="sam.model.domain.util.UsuarioTipo" %>
<%@ page import="sam.model.service.GestaoUsuariosService" %>
<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>SAM - Transações</title>

        <link rel="stylesheet" type="text/css" href="/sam/css/style.css">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>
        <header>
            <img id="logotipo" src="/sam/imgs/logotipo.png" alt="Logotipo SAM">
            <h1>Transações</h1>
            <%@include file="/core/header.jsp" %>
        </header>

        <main class="content">
            <h2>Registrar Transação</h2>
            <div class="formulario">
                <form name="formCadastroTransacao" onsubmit="return validarCamposCadastroTransacao(document.formCadastroTransacao)" action="/sam/CadastroTransacaoController" method="POST">
                    <% if (usuario.getTipo() == UsuarioTipo.GESTOR) { %>
                    <label for="cliente">Cliente:
                        <% GestaoUsuariosService manterUsuario = new GestaoUsuariosService(); %>
                        <select name="cliente">
                            <% for (Usuario cliente : manterUsuario.getListaClientes(usuario)) {%>
                            <option value="<%= cliente.getId() %>"><%= cliente.getNome() %></option>
                            <%}%>
                        </select>
                    </label>
                    <%} else {%>
                    <label>
                      <input name="cliente" type="hidden" value="<%= usuario.getId() %>">
                    </label>
                    <%}%>

                    <label for="tipo">Tipo:
                        <select name="tipo">
                            <option value="COMPRA">Compra</option>
                            <option value="VENDA">Venda</option>
                        </select>
                    </label>

                    <label for="quantidade">Quantidade:
                        <input name="quantidade" type="number" min="1" value="1" placeholder="1" required>
                    </label>

                    <label for="valor">Valor (R$):
                        <input name="valor" type="number" step="0.01" min="0.01" value="0.01" placeholder="0.01" required>
                    </label>

                    <label for="bonus">Bônus:
                        <input name="bonus" type="number" min="0" value="0" placeholder="0">
                    </label>

                    <button>Registrar</button>
                </form>
            </div>
        </main>

        <%@include file="/core/mensagens-erro.jsp"%>

        <script src="/sam/js/helper.js"></script>
        <script src="/sam/js/script.js"></script>
    </body>

</html>