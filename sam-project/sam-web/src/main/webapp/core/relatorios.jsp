<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>Histórico de Usuários</title>
        <link rel="stylesheet" type="text/css" href="../css/style.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    </head>

    <body>
        <header>
            <a href="../tela-cliente/dashboard.jsp">
                <img id="logotipo" src="/imgs/logotipo.png" alt="Logotipo SAM"> 
            </a>
            <h1>Suporte</h1>
            <nav>
                <a href="../tela-cliente/dashboard.jsp">Dashboard</a>
                <a href="transacoes.jsp">Transações</a>
                <a href="tela-gestor/empresas.jsp">Empresas</a>
                <a href="notificacoes.jsp">Notificações</a>
                <a href="suporte.jsp">Suporte</a>
            </nav>
            <div class="login-menu"><a href="../login.jsp">Login/Cadastrar-se</a></div>
            <div class="hamburger-menu">
                <button id="hamburger-btn">&#9776;</button>
                <div id="hamburger-dropdown" class="dropdown-content">
                    <a href="perfil.jsp">Visualizar Perfil</a>
                </div>
            </div>
        </header>

        <main class="dashboard">
            <!-- Filtro de busca -->
            <section class="card">
                <h2>Buscar Usuário</h2>
                <form class="formulario">
                    <label for="tipoUsuario">Tipo de Usuário:</label>
                    <select id="tipoUsuario">
                        <option>Cliente</option>
                        <option>Gestor</option>
                    </select>

                    <label for="nomeUsuario">Nome do Usuário:</label>
                    <input type="text" id="nomeUsuario" placeholder="Digite o nome para pesquisar">

                    <button type="button" id="btnBuscar">Pesquisar</button>
                </form>
            </section>

            <!-- Resultados da busca -->
            <section class="content">
                <h2>Resultados</h2>
                <table>
                    <thead>
                        <tr>
                            <th>Usuário</th>
                            <th>Tipo</th>
                            <th>Programas Associados</th>
                            <th>Empresas Parceiras</th>
                            <th>Avaliação Média</th>
                            <th>Desempenho</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody id="resultadoTabela">
                        <tr>
                            <td>Maria S.</td>
                            <td>Cliente</td>
                            <td>Latam Pass, Smiles</td>
                            <td>LATAM, Gol</td>
                            <td>4.7 / 5</td>
                            <td>Alta fidelização e bom histórico de compras</td>
                            <td><button>Ver Detalhes</button></td>
                        </tr>
                        <tr>
                            <td>João P.</td>
                            <td>Gestor</td>
                            <td>Azul Fidelidade</td>
                            <td>Azul Linhas Aéreas</td>
                            <td>4.3 / 5</td>
                            <td>Bom desempenho com clientes recorrentes</td>
                            <td><button>Ver Detalhes</button></td>
                        </tr>
                    </tbody>
                </table>
            </section>

            <!-- Card de detalhes -->
            <section class="card" id="detalhesUsuario" style="display:none;">
                <h2>Detalhes do Usuário</h2>
                <p><strong>Nome:</strong> <span id="detNome">—</span></p>
                <p><strong>Tipo:</strong> <span id="detTipo">—</span></p>
                <p><strong>Programas Associados:</strong> <span id="detProgramas">—</span></p>
                <p><strong>Empresas Parceiras:</strong> <span id="detEmpresas">—</span></p>
                <p><strong>Avaliações Recebidas:</strong></p>
                <ul id="detAvaliacoes">
                    <li>—</li>
                </ul>
            </section>
        </main>

        <script src="/js/script.js"></script>
    </body>

</html>
