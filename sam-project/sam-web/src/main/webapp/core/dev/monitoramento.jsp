<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="sam.model.domain.Atividade" %>
<%@ page import="sam.model.domain.AtividadeReferencia" %>
<%@ page import="sam.model.domain.util.TipoAtividades" %>
<%@ page import="sam.model.domain.util.TipoEntidades" %>

<%
    List<Atividade> atividades =
            (List<Atividade>) request.getAttribute("atividades");

    Atividade atividade =
            (Atividade) request.getAttribute("atividade");

    List<AtividadeReferencia> referencias =
            (List<AtividadeReferencia>) request.getAttribute("referencias");
%>

<html>
<head>
    <meta charset="UTF-8">
    <title>SAM - Monitoramento</title>

    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <link rel="icon" href="<%= request.getContextPath() %>/imgs/favicon.ico">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap" rel="stylesheet">
</head>

<body>

<header>
    <img id="logotipo" src="<%= request.getContextPath() %>/imgs/logotipo.png" alt="Logotipo SAM">
    <h1>Monitoramento de Atividades</h1>
    <%@ include file="/core/header.jsp" %>
</header>

<main>

    <%
        // ======================
        // LISTAGEM DE ATIVIDADES
        // ======================
        if (atividade == null) {
    %>

    <h2>Atividades do Sistema</h2>

    <div class="tabela-container">
        <table>
            <tr>
                <th>Data/Hora</th>
                <th>Tipo</th>
                <th>Executor</th>
                <th>Descrição</th>
                <th>Ações</th>
            </tr>

            <%
                if (atividades != null && !atividades.isEmpty()) {
                    for (Atividade a : atividades) {

                        String descricaoTipo;
                        try {
                            descricaoTipo =
                                    TipoAtividades.valueOf(a.getTipo()).getDescricao();
                        } catch (Exception e) {
                            descricaoTipo = a.getTipo();
                        }
            %>
            <tr>
                <td><%= a.getDataHora() %></td>
                <td><%= descricaoTipo %></td>
                <td>ID <%= a.getUsuarioExecutorId() %></td>
                <td><%= a.getResumo() %></td>
                <td>
                    <a href="<%= request.getContextPath() %>/atividades?action=detalhes&id=<%= a.getId() %>">
                        <button>Detalhes</button>
                    </a>
                </td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="5">Nenhuma atividade registrada</td>
            </tr>
            <%
                }
            %>
        </table>
    </div>

    <%
        // ======================
        // DETALHES DA ATIVIDADE
        // ======================
    } else {

        String descricaoTipo;
        try {
            descricaoTipo =
                    TipoAtividades.valueOf(atividade.getTipo()).getDescricao();
        } catch (Exception e) {
            descricaoTipo = atividade.getTipo();
        }
    %>

    <div class="card" style="width: 100%;">
        <h2>Detalhes da Atividade</h2>

        <p><strong>Tipo:</strong> <%= descricaoTipo %></p>
        <p><strong>Data/Hora:</strong> <%= atividade.getDataHora() %></p>
        <p><strong>Executor:</strong> ID <%= atividade.getUsuarioExecutorId() %></p>
        <p><strong>Descrição:</strong></p> <%= atividade.getDescricao() %>
        <hr>

        <h2>Entidades Envolvidas</h2>

        <table>
            <tr>
                <th>Tipo de Entidade</th>
                <th>ID</th>
            </tr>

            <%
                if (referencias != null && !referencias.isEmpty()) {
                    for (AtividadeReferencia ref : referencias) {
                        String tipoEntidade;
                        try {
                            tipoEntidade =
                                    TipoEntidades.valueOf(ref.getTipoEntidade()).getDescricao();
                        } catch (Exception e) {
                            tipoEntidade = ref.getTipoEntidade();
                        }
            %>
            <tr>
                <td><%= tipoEntidade%></td>
                <td><%= ref.getEntidadeId() %></td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="2">Nenhuma entidade associada</td>
            </tr>
            <%
                }
            %>
        </table>

        <br>

        <a href="<%= request.getContextPath() %>/atividades">
            <button>Voltar</button>
        </a>
    </div>

    <%
        }
    %>

</main>

</body>
</html>
