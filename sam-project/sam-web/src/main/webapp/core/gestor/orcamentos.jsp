<%@ page import="sam.model.dao.UsuarioDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="sam.model.dao.PropostaDAO" %>
<%@ page import="sam.model.domain.Proposta" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <title>SAM - Orçamentos</title>

    <link rel="stylesheet" type="text/css" href="../../css/style.css">
    <link rel="icon" href="/sam/imgs/favicon.ico">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>
<header>
    <img id="logotipo" src="../../imgs/logotipo.png" alt="Logotipo SAM">
    <h1>Orçamentos</h1>
    <%@include file="/core/header.jsp" %>
</header>

<%

    UsuarioDAO udao = UsuarioDAO.getInstance();
    List<Usuario> usuarios = udao.getListaClientes(usuario);


%>

<main class="dashboard">
    <section class="content" style="flex:1 1 100%;">
        <h2>Gerar Orçamento</h2>

        <form class="formulario" method="POST" action="<%=request.getContextPath()%>/proposta">
            <input type="hidden" name="idGestor" value="<%=usuario.getId()%>">
            <label>Cliente:
                <select name="idCliente" required>
                    <option value="">Selecione...</option>
                    <% for (Usuario u : usuarios) { %>
                    <option value="<%=u.getId()%>"><%=u.getNome()%></option>
                    <% } %>
                </select>
            </label>

            <label for="origem">Origem:
                <input type="text" name="origem" id="origem" placeholder="Cidade ou país" required>
            </label>

            <label for="destino">Destino:
                <input type="text" name="destino" id="destino" placeholder="Cidade ou país" required>
            </label>

            <label for="dataIda">Data de Ida:
                <input type="date" name="dataIda" id="dataIda" required>
            </label>

            <label for="dataVolta">Data de Volta:
                <input type="date" name="dataVolta" id="dataVolta" required>
            </label>

            <label for="numAdultos">Quantidade de Adultos:
                <input type="number" name="numAdultos" id="numAdultos" min="1" value="1" required>
            </label>

            <label for="numCriancas">Quantidade de Crianças:
                <input type="number" name="numCriancas" id="numCriancas" min="0" value="0">
            </label>

            <label for="valorEmDinheiro">Valor em Dinheiro (R$):
                <input type="number" step="0.01" name="valorEmDinheiro" id="valorEmDinheiro">
            </label>

            <label for="valorEmMilhas">Valor em Milhas:
                <input type="number" name="valorEmMilhas" id="valorEmMilhas">
            </label>

            <label for="taxas">Taxas (R$):
                <input type="number" step="0.01" name="taxas" id="taxas">
            </label>

            <label for="observacoes">Observações:
                <textarea name="observacoes" id="observacoes" rows="3"></textarea>
            </label>

            <button type="submit">Salvar Proposta</button>
        </form>

    </section>
    <%
        PropostaDAO pDAO = new PropostaDAO();
        List<Proposta> listaPropostas = pDAO.listarPorGestor(usuario.getId());

    %>

    <section class="card" style="flex:1 1 100%;">
        <h2>Comparação de Opções</h2>
        <table>
            <thead>
            <tr>
                <th>Origem ⭢ Destino</th>
                <th>Cliente</th>
                <th>Status</th>
                <th>Ações</th>
            </tr>
            </thead>
            <tbody>
            <% if (listaPropostas != null && !listaPropostas.isEmpty()) { %>
            <% for (Proposta p : listaPropostas) { %>
            <tr>
                <td>
                    <%= p.getOrigem() %> ⭢ <%= p.getDestino() %>
                </td>

                <td>
                    <%= p.getCliente().getNome() %>
                </td>

                <td>
                    <%= p.getStatus() %>
                </td>
                <td class="acoes">
                    <a href="<%=request.getContextPath()%>/core/geral/detalhesProposta.jsp?id=<%=p.getId()%>">
                        <button>Detalhes</button>
                    </a>

                    <a href="<%=request.getContextPath()%>/proposta?action=excluir&id=<%=p.getId()%>"
                       onclick="return confirm('Deseja realmente excluir esta proposta?')">
                        <button>Excluir</button>
                    </a>
                </td>
            </tr>
            <% } %>
            <% } else { %>
            <tr>
                <td colspan="4">Nenhuma proposta encontrada.</td>
            </tr>
            <% } %>
            </tbody>

        </table>
    </section>
</main>

<script src="../../js/script.js"></script>
</body>

</html>
