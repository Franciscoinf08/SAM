<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="sam.model.domain.util.UsuarioTipo" %>
<%@ page import="sam.model.service.GestaoUsuariosService" %>
<%@ page import="sam.model.service.ProgramaFidelidadeService" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Date" %>

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
                    <%
                        GestaoUsuariosService manterUsuario = new GestaoUsuariosService();
                        if (usuario.getTipo() == UsuarioTipo.GESTOR) {
                    %>
                    <label for="cliente">Cliente:
                        <select name="cliente" id="cliente">
                            <% for (Usuario cliente : manterUsuario.getListaClientes(usuario)) {%>
                            <option value="<%= cliente.getId() %>"><%= cliente.getNome() %></option>
                            <%}%>
                        </select>
                    </label>
                    <%} else {%>
                    <label>
                        <input name="cliente" id="cliente" type="hidden" value="<%= usuario.getId() %>">
                    </label>
                    <%}%>

                    <label for="programa">Programa de Fidelidade:
                        <select name="programa" id="programa">
                            <option></option>
                        </select>
                    </label>

                    <label for="tipo">Tipo:
                        <select name="tipo" class="tipo-input">
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

                    <label for="bonus" class="bonus-input">Bônus:
                        <input name="bonus" type="number" min="0" value="0" placeholder="0">
                    </label>

                    <label for="data">Data:
                        <input name="data" type="date" value="<%= new Date(System.currentTimeMillis()).toString() %>" required>
                    </label>

                    <button>Registrar</button>
                </form>
            </div>
        </main>

        <%@include file="/core/mensagens-erro.jsp"%>
        <script src="/sam/js/ajax.js"></script>
        <script>
            ajaxProgramaFidelidade();
            document.querySelector("#cliente").addEventListener("change", () => { console.log("a"); ajaxProgramaFidelidade(); });
        </script>
        <script src="/sam/js/toggle-bonus.js"></script>
        <script src="/sam/js/helper.js"></script>
        <script src="/sam/js/script.js"></script>
    </body>

</html>