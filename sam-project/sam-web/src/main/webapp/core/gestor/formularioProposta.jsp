<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="sam.model.domain.Proposta" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>SAM - Cadastro de Proposta</title>

    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
    <link rel="icon" href="/sam/imgs/favicon.ico">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap" rel="stylesheet">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>
<header>
    <img id="logotipo" src="<%= request.getContextPath() %>/imgs/logotipo.png" alt="Logotipo SAM">
    <h1>Propostas</h1>
    <%@include file="/core/header.jsp" %>
</header>

<main class="content">
    <div class="formulario">
        <form action="<%= request.getContextPath() %>/proposta" method="post">

            <input type="hidden" name="action"
                   value="<%= request.getAttribute("proposta") == null ? "inserir" : "atualizar" %>">

            <input type="hidden" name="id"
                   value="<%= request.getAttribute("proposta") != null ? ((Proposta)request.getAttribute("proposta")).getId() : "" %>">

            <label for="idCliente">ID do Cliente:</label>
            <input type="number" id="idCliente" name="idCliente" required>

            <label for="status">Status:</label>
            <select id="status" name="status" required>
                <option value="CRIADA">Criada</option>
                <option value="ENVIADA">Enviada</option>
            </select>

            <label for="valorEmDinheiro">Valor em Dinheiro (R$):</label>
            <input type="number" id="valorEmDinheiro" name="valorEmDinheiro" step="0.01" required>

            <label for="valorEmMilhas">Valor em Milhas:</label>
            <input type="number" id="valorEmMilhas" name="valorEmMilhas" required>

            <label for="taxas">Taxas (R$):</label>
            <input type="number" id="taxas" name="taxas" step="0.01" required>

            <label for="origem">Origem:</label>
            <input type="text" id="origem" name="origem" required>

            <label for="destino">Destino:</label>
            <input type="text" id="destino" name="destino" required>

            <label for="dataIda">Data de Ida:</label>
            <input type="date" id="dataIda" name="dataIda" required>

            <label for="dataVolta">Data de Volta:</label>
            <input type="date" id="dataVolta" name="dataVolta" required>

            <label for="observacoes">Observações:</label>
            <textarea id="observacoes" name="observacoes" rows="4"></textarea>

            <button type="submit">Salvar Proposta</button>
            <a href="<%= request.getContextPath() %>/proposta">Cancelar</a>
        </form>
    </div>
</main>

<script src="<%= request.getContextPath() %>/js/script.js"></script>
</body>
</html>
