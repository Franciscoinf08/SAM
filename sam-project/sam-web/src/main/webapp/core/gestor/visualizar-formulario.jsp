<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sam.model.domain.FormObjetivos"%>

<%
    FormObjetivos f = (FormObjetivos) request.getAttribute("formulario");
%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Visualizar Formulário</title>

    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/visualizar-formulario.css">
</head>

<body>

<header>
    <img id="logotipo" src="../../imgs/logotipo.png" alt="Logotipo SAM">
    <h1>Formulario</h1>
</header>

<main class="container">

    <section>
        <h2><%= f.getTitulo() %></h2>
        <p><strong>Última atualização:</strong>
            <%= f.getData() != null ? f.getData() : "--" %>
        </p>
    </section>

    <section>
        <h3>Objetivos Gerais</h3>
        <p><%= f.getObjetivosGerais() %></p>

        <h3>Objetivos Específicos</h3>
        <p><%= f.getObjetivosEspecificos() %></p>
    </section>

    <section>
        <h3>Dados da Viagem</h3>

        <p><strong>Destino:</strong> <%= f.getDestPrincipal() %></p>
        <p><strong>Número de pessoas:</strong> <%= f.getNumPessoas() %></p>
        <p><strong>Preferência de companhia:</strong> <%= f.getPrefCompanhia() %></p>
        <p><strong>Orçamento total:</strong> R$ <%= f.getOrcTotal() %></p>
        <p><strong>Nível de detalhamento:</strong> <%= f.getNivelDetalhamento() %></p>
    </section>

    <section>
        <h3>Requisitos Específicos</h3>
        <p><%= f.getReqEspecificos() %></p>
    </section>

    <a href="javascript:history.back()">
        <button>Voltar</button>
    </a>

</main>

</body>
</html>