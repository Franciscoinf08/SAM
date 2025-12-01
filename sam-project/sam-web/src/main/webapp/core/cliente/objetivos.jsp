<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sam.model.domain.FormObjetivos" %>
<%@page import="sam.controller.LoginController"%>

<% LoginController.validarSessao(request, response); %>

<%
    FormObjetivos form = (FormObjetivos) request.getAttribute("formulario");

    String contextPath = request.getContextPath();
    String idFormulario = (form != null && form.getId() != null) ? String.valueOf(form.getId()) : "";

    String titulo = (form != null && form.getTitulo() != null) ? form.getTitulo() : "";
    String objetivosGerais = (form != null && form.getObjetivosGerais() != null) ? form.getObjetivosGerais() : "";
    String objetivosEspecificos = (form != null && form.getObjetivosEspecificos() != null) ? form.getObjetivosEspecificos() : "";
    String destPrincipal = (form != null && form.getDestPrincipal() != null) ? form.getDestPrincipal() : "";
    String prefCompanhia = (form != null && form.getPrefCompanhia() != null) ? form.getPrefCompanhia() : "";
    String nivelDetalhamento = (form != null && form.getNivelDetalhamento() != null) ? form.getNivelDetalhamento() : "Simples"; // Padrão 'Simples'
    String reqEspecificos = (form != null && form.getReqEspecificos() != null) ? form.getReqEspecificos() : "";

    String numPessoas = (form != null && form.getNumPessoas() != null) ? String.valueOf(form.getNumPessoas()) : "1";
    String orcTotal = (form != null && form.getOrcTotal() != null) ? String.valueOf(form.getOrcTotal()) : "0.00";

%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <link rel="stylesheet" type="text/css" href="<%= contextPath %>/css/objetivos.css">
    <link rel="icon" href="/sam/imgs/favicon.ico">

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulário de Definição de Perfil e Viagens</title>
</head>
<body>

<div class="container">
    <h1>Formulário de Definição de Perfil e Objetivos</h1>
    <p>Preencha e atualize este formulário para um planejamento de objetivos e viagens eficaz.</p>

    <form action="/sam/processarObjetivos" method="POST" id="formObjetivos">

        <input type="hidden" name="id" value="<%= idFormulario %>">

        <fieldset>
            <legend>Título do Formulário</legend>
            <textarea id="titulo" name="titulo" rows="1" placeholder="Ex: Viagem em família"><%= titulo %></textarea>
        </fieldset>

        <fieldset>
            <legend>1. Definição de Objetivos</legend>

            <label for="objetivos_gerais">Objetivos Gerais (Visão de Longo Prazo):</label>
            <textarea id="objetivos_gerais" name="objetivos_gerais" rows="4" placeholder="Ex: Alcançar estabilidade financeira..."><%= objetivosGerais %></textarea>

            <label for="objetivos_especificos">Objetivos Específicos (Metas Detalhadas):</label>
            <textarea id="objetivos_especificos" name="objetivos_especificos" rows="4" placeholder="Ex: Economizar R$ 8.000..."><%= objetivosEspecificos %></textarea>

        </fieldset>

        <fieldset>
            <legend>2. Dados para Orçamento de Viagens</legend>

            <label for="destino_principal">Destino Principal (Cidade/País):</label>
            <input type="text" id="destino_principal" name="destino_principal" value="<%= destPrincipal %>" placeholder="Ex: Tóquio, Japão">

            <label for="num_pessoas">Número Total de Pessoas:</label>
            <input type="number" id="num_pessoas" name="num_pessoas" min="1" value="<%= numPessoas %>">

            <label for="companhias_aereas">Preferência de Companhias Aéreas (para orçamentos):</label>
            <input type="text" id="companhias_aereas" name="companhias_aereas" value="<%= prefCompanhia %>" placeholder="Ex: Apenas LATAM/GOL, Evitar Low-Cost, Sem Preferência">

            <label for="orcamento_total">Orçamento Total Desejado para a Viagem (R$):</label>
            <input type="number" id="orcamento_total" name="orcamento_total" min="0" step="any" value="<%= orcTotal %>" placeholder="0.00">

            <br>

            <label>Nível de Detalhamento do Orçamento:</label>
            <div class="radio-group">
                <input type="radio" id="orc_simples" name="detalhamento_orcamento" value="Simples"
                    <%= "Simples".equals(nivelDetalhamento) ? "checked" : "" %>>
                <label for="orc_simples">Apenas Custo Total</label>

                <input type="radio" id="orc_detalhado" name="detalhamento_orcamento" value="Detalhado"
                    <%= "Detalhado".equals(nivelDetalhamento) ? "checked" : "" %>>
                <label for="orc_detalhado">Detalhado (Passagens, Hospedagem, Passeios)</label>
            </div>

            <br>
            <label for="outros_requisitos_viagem">Outros Requisitos Específicos (hospedagem, atividades, etc.):</label>
            <textarea id="outros_requisitos_viagem" name="outros_requisitos_viagem" rows="3" placeholder="Ex: Quarto com cozinha, atividades de aventura, carro alugado por 5 dias."><%= reqEspecificos %></textarea>
        </fieldset>
        <button type="submit" value="enviarDados">Salvar/Atualizar Informações</button>
    </form>
    <script src="../../js/form-objetivos.js"></script>
</div>
</body>