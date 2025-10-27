<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>Gestão de Propostas e Campanhas</title>
        <link rel="stylesheet" type="text/css" href="../../css/style.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    </head>

    <body>
        <header>
            <a href="../tela-cliente/dashboard.jsp">
                <img id="logotipo" src="/imgs/logotipo.png" alt="Logotipo SAM"> 
            </a>
            <h1>Campanhas Promocionais</h1>
            <nav>
                <a href="../tela-cliente/dashboard.jsp">Dashboard</a>
                <a href="../transacoes.jsp">Transações</a>
                <a href="empresas.jsp">Empresas</a>
                <a href="../notificacoes.jsp">Notificações</a>
                <a href="../suporte.jsp">Suporte</a>
            </nav>
            <div class="login-menu">
                <a href="../login.jsp">Login/Cadastrar-se</a>
            </div>
            <div class="hamburger-menu">
                <button id="hamburger-btn">&#9776;</button>
                <div id="hamburger-dropdown" class="dropdown-content">
                    <a href="../perfil.jsp">Visualizar Perfil</a>
                </div>
            </div>
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

                    <label for="objetivo">Objetivo da Campanha:</label>
                    <textarea id="objetivo" rows="3" placeholder="Defina o objetivo da campanha (ex: aumentar engajamento, vendas etc.)"></textarea>

                    <label for="mensagemCampanha">Mensagem Promocional:</label>
                    <textarea id="mensagemCampanha" rows="4" placeholder="Escreva a mensagem que será enviada aos clientes..."></textarea>

                    <button type="button" id="btnEnviarCampanha">Enviar Campanha</button>
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
                    <tbody id="tabelaHistorico">
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

        <script src="/js/script.js"></script>
    </body>

</html>
