<%@ page import="sam.model.dao.PropostaDAO" %>
<%@ page import="sam.model.domain.Proposta" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="sam.model.domain.util.UsuarioTipo" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String idParam = request.getParameter("id");
    Proposta proposta = null;

    if (idParam != null) {
        Long idProposta = Long.parseLong(idParam);
        PropostaDAO pDAO = new PropostaDAO();
        proposta = pDAO.buscarPorId(idProposta);
    }

    if (proposta == null) {
        response.sendRedirect("orcamentos.jsp");
        return;
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Detalhes da Proposta</title>

    <link rel="stylesheet" href="../../css/style.css">
    <link rel="icon" href="/sam/imgs/favicon.ico">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;600&display=swap" rel="stylesheet">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <style>
        .proposta-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 30px;
        }

        .status-pill {
            padding: 6px 14px;
            border-radius: 20px;
            font-size: 0.9rem;
            font-weight: 600;
            background-color: #013b6d;
            color: white;
        }

        .proposta-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
            gap: 20px;
        }

        .bloco {
            background-color: #f5f6f8;
            border-radius: 12px;
            padding: 20px;
        }

        .bloco h3 {
            margin-bottom: 15px;
            font-size: 1rem;
            color: #013b6d;
            border-bottom: 1px solid #ddd;
            padding-bottom: 5px;
        }

        .linha-info {
            display: flex;
            justify-content: space-between;
            margin-bottom: 10px;
            font-size: 0.95rem;
        }

        .linha-info span {
            font-weight: 500;
            color: #555;
        }

        .linha-info p {
            font-weight: 600;
            color: #222;
        }

        .observacoes {
            margin-top: 25px;
            background-color: #f5f6f8;
            padding: 20px;
            border-radius: 12px;
        }

        .observacoes h3 {
            margin-bottom: 10px;
            color: #013b6d;
        }

        .acoes {
            margin-top: 30px;
            text-align: center;
        }
    </style>
</head>

<body>

<header>
    <img id="logotipo" src="../../imgs/logotipo.png" alt="Logotipo SAM">
    <h1>Detalhes da Proposta</h1>
    <%@include file="/core/header.jsp" %>
</header>

<main class="dashboard">

    <section class="card" style="flex:1 1 100%;">

        <div class="proposta-header">
            <h2><%=proposta.getOrigem()%>⭢<%=proposta.getDestino()%></h2>
            <div class="status-pill"><%= proposta.getStatus() %></div>
        </div>

        <div class="proposta-grid">

            <div class="bloco">
                <h3>Viagem</h3>

                <div class="linha-info">
                    <span>Origem</span>
                    <p><%= proposta.getOrigem() %></p>
                </div>

                <div class="linha-info">
                    <span>Destino</span>
                    <p><%= proposta.getDestino() %></p>
                </div>

                <div class="linha-info">
                    <span>Data de Ida</span>
                    <p><%= proposta.getDataIda().format(formatter) %></p>
                </div>

                <div class="linha-info">
                    <span>Data de Volta</span>
                    <p><%= proposta.getDataVolta().format(formatter) %></p>
                </div>
            </div>

            <div class="bloco">
                <h3>Passageiros</h3>

                <div class="linha-info">
                    <span>Adultos</span>
                    <p><%= proposta.getNumAdultos() %></p>
                </div>

                <div class="linha-info">
                    <span>Crianças</span>
                    <p><%= proposta.getNumCriancas() %></p>
                </div>
            </div>

            <div class="bloco">
                <h3>Valores</h3>

                <div class="linha-info">
                    <span>Dinheiro</span>
                    <p>R$ <%= proposta.getValorEmDinheiro() %></p>
                </div>

                <div class="linha-info">
                    <span>Milhas</span>
                    <p><%= proposta.getValorEmMilhas() %></p>
                </div>

                <div class="linha-info">
                    <span>Taxas</span>
                    <p>R$ <%= proposta.getTaxas() %></p>
                </div>
            </div>

            <div class="bloco">
                <h3>Cliente</h3>

                <div class="linha-info">
                    <span>Nome</span>
                    <p><%= proposta.getCliente().getNome() %></p>
                </div>

                <div class="linha-info">
                    <span>Email</span>
                    <p><%= proposta.getCliente().getEmail() %></p>
                </div>

                <div class="linha-info">
                    <span>CPF</span>
                    <p><%= proposta.getCliente().getCPF() %></p>
                </div>
            </div>

        </div>

        <div class="observacoes">
            <h3>Observações</h3>
            <p>
                <%= (proposta.getObservacoes() != null && !proposta.getObservacoes().isEmpty())
                        ? proposta.getObservacoes()
                        : "Nenhuma observação informada." %>
            </p>
        </div>
        <%
            if(usuario.getTipo()== UsuarioTipo.GESTOR){%>
        <a href="../gestor/orcamentos.jsp">
            <button>Voltar</button>
        </a><%}else if(usuario.getTipo() == UsuarioTipo.CLIENTE){%>
        <a href="../cliente/visualizacaoPropostas.jsp"><button>Voltar</button></a>

        <%}%>

    </section>

</main>

<script src="../../js/script.js"></script>
</body>
</html>
