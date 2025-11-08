<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <link rel="stylesheet" type="text/css" href="../../css/objetivos.css">

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulário de Definição de Perfil e Viagens</title>
</head>
<body>

<div class="container">
    <h1>Formulário de Definição de Perfil e Objetivos</h1>
    <p>Preencha e atualize este formulário para um planejamento de objetivos e viagens eficaz.</p>

    <form action="/processarObjetivos" method="POST" id="formObjetivos">

        <fieldset>
            <legend>1. Informações Básicas e de Contato</legend>

            <label for="nome">Nome Completo:</label>
            <input type="text" id="nome" name="nome" required>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>

            <div class="data-atualizacao">
                <label for="data_ultima_atualizacao">Data da Última Atualização:</label>
                <input type="date" id="data_ultima_atualizacao" name="data_ultima_atualizacao" style="width: auto; display: inline-block;">
            </div>
        </fieldset>

        <fieldset>
            <legend>2. Definição de Objetivos</legend>

            <label for="objetivos_gerais">Objetivos Gerais (Visão de Longo Prazo):</label>
            <textarea id="objetivos_gerais" name="objetivos_gerais" rows="4" placeholder="Ex: Alcançar estabilidade financeira para viajar anualmente; Definir roteiro anual de férias."></textarea>

            <label for="objetivos_especificos">Objetivos Específicos (Metas Detalhadas):</label>
            <textarea id="objetivos_especificos" name="objetivos_especificos" rows="4" placeholder="Ex: Economizar R$ 8.000 para a viagem de março; Comprar passagens até o final do mês."></textarea>

        </fieldset>

        <fieldset>
            <legend>3. Dados para Orçamento de Viagens</legend>

            <label for="destino_principal">Destino Principal (Cidade/País):</label>
            <input type="text" id="destino_principal" name="destino_principal" placeholder="Ex: Tóquio, Japão">

            <label for="periodo_viagem">Período Pretendido (Datas ou Mês/Ano):</label>
            <input type="text" id="periodo_viagem" name="periodo_viagem" placeholder="Ex: Março/2026 ou 10/03/2026 a 25/03/2026">

            <label for="num_pessoas">Número Total de Pessoas:</label>
            <input type="number" id="num_pessoas" name="num_pessoas" min="1" value="1">

            <label for="companhias_aereas">Preferência de Companhias Aéreas (para orçamentos):</label>
            <input type="text" id="companhias_aereas" name="companhias_aereas" placeholder="Ex: Apenas LATAM/GOL, Evitar Low-Cost, Sem Preferência">

            <label for="orcamento_total">Orçamento Total Desejado para a Viagem (R$):</label>
            <input type="number" id="orcamento_total" name="orcamento_total" min="0" placeholder="0.00">

            <br>

            <label>Nível de Detalhamento do Orçamento:</label>
            <div class="radio-group">
                <input type="radio" id="orc_simples" name="detalhamento_orcamento" value="Simples" checked>
                <label for="orc_simples">Apenas Custo Total</label>

                <input type="radio" id="orc_detalhado" name="detalhamento_orcamento" value="Detalhado">
                <label for="orc_detalhado">Detalhado (Passagens, Hospedagem, Passeios)</label>
            </div>

            <br>
            <label for="outros_requisitos_viagem">Outros Requisitos Específicos (hospedagem, atividades, etc.):</label>
            <textarea id="outros_requisitos_viagem" name="outros_requisitos_viagem" rows="3" placeholder="Ex: Quarto com cozinha, atividades de aventura, carro alugado por 5 dias."></textarea>

        </fieldset>
        <button type="submit" value="enviarDados">Salvar/Atualizar Informações</button>
    </form>
    <script src="../../js/form-objetivos.js"></script>
</div>
</body>