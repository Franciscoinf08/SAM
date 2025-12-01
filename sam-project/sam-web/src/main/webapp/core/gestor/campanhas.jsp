<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>SAM - Gestão de Propostas e Campanhas</title>

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
            <h1>Campanhas Promocionais</h1>
            <%@include file="/core/header.jsp" %>
        </header>

        <main class="dashboard">
            <section class="card">
                <h2>Criar Campanha Promocional</h2>
                <form class="formulario">
                    <label for="tituloCampanha">Título da Campanha:</label>
                    <input type="text" id="tituloCampanha" placeholder="Ex: Promoção Clientes Ouro">

                    <label for="publicoAlvo">Público Alvo:</label>
                    <select id="publicoAlvo">
                        <option value="todos">Todos os clientes</option>
                        <option value="ouro">Clientes categoria Ouro</option>
                        <option value="prata">Clientes categoria Prata</option>
                        <option value="novos">Novos clientes</option>
                    </select>

                    <label for="objetivo">Objetivo da Campanha:
                        <textarea rows="3" placeholder="Defina o objetivo da campanha (ex: aumentar engajamento, vendas etc.)"></textarea>
                    </label>

                    <label for="mensagemCampanha">Mensagem Promocional:
                        <textarea rows="4" placeholder="Escreva a mensagem que será enviada aos clientes..."></textarea>
                    </label>

                    <button type="button">Enviar Campanha</button>
                </form>
            </section>
            <section class="content">
                <h2>Histórico de Campanhas</h2>
                <table>
                    <thead>
                        <tr>
                            <th>Título / Cliente</th>
                            <th>Tipo</th>
                            <th>Data</th>
                            <th>Status</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Campanha: Fim de Ano</td>
                            <td>Campanha Promocional</td>
                            <td>10/10/2025</td>
                            <td>Enviada</td>
                            <td><button>Ver Detalhes</button></td>
                        </tr>
                    </tbody>
                </table>
            </section>

        </main>

        <script src="../../js/script.js"></script>
    </body>

</html>
