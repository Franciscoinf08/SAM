<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Alterar Senha</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>

<div class="container" style="max-width:400px; margin:auto; padding:20px;">
    <h2>Alterar Senha</h2>

    <form id="formAlterar" method="POST" action="<%= request.getContextPath() %>/AlterarSenhaController">
        <label>Nova senha</label>
        <input type="password" name="senha1" required>

        <label>Confirmar senha</label>
        <input type="password" name="senha2" required>

        <button type="submit">Alterar senha</button>

        <p id="erroSenha" style="color:red; display:none; margin-top:10px;">
            As senhas não correspondem.
        </p>

        <button type="submit" style="margin-top:15px;">Salvar</button>
    </form>
</div>

<script src="${pageContext.request.contextPath}/js/verificacao.js"></script>

</body>
</html>
