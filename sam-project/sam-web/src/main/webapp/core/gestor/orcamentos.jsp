<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>Orçamentos de Viagem</title>
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
            <h1>Orçamentos</h1>
            <nav>
                <a href="../tela-cliente/dashboard.jsp">Dashboard</a>
                <a href="../transacoes.jsp">Transações</a>
                <a href="empresas.jsp">Empresas</a>
                <a href="../tela-clienteGestor/notificacoes.v">Notificações</a>
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
            <section class="content" style="flex:1 1 100%;">
                <h2>Gerar Orçamento</h2>
                <form class="formulario">
                    <label for="destino">Destino:</label>
                    <input type="text" id="destino" placeholder="Cidade ou país">

                    <label for="dataIda">Data de Ida:</label>
                    <input type="date" id="dataIda">

                    <label for="dataVolta">Data de Volta:</label>
                    <input type="date" id="dataVolta">

                    <label for="adultos">Quantidade de Adultos:</label>
                    <input type="number" id="adultos" min="1" value="1">

                    <label for="criancas">Quantidade de Crianças:</label>
                    <input type="number" id="criancas" min="0" value="0">

                    <label for="conexoes">Conexões:</label>
                    <select id="conexoes">
                        <option>Direto</option>
                        <option>1 Conexão</option>
                        <option>2 Conexões</option>
                    </select>

                    <label for="companhia">Companhia Aérea:</label>
                    <select id="companhia">
                        <option>Indiferente</option>
                        <option>Latam</option>
                        <option>Azul</option>
                        <option>Gol</option>
                    </select>

                    <button type="button">Gerar Orçamento</button>
                </form>
            </section>

            <section class="card" style="flex:1 1 100%;">
                <h2>Comparação de Opções</h2>
                <table>
                    <thead>
                        <tr>
                            <th>Opção</th>
                            <th>Milhas</th>
                            <th>Dinheiro (R$)</th>
                            <th>Milhas + Dinheiro</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Latam Pass - Direto</td>
                            <td>50.000</td>
                            <td>1.200</td>
                            <td>25.000 + 600</td>
                            <td><button>Salvar</button>
                                <button>Propor a um Cliente</button>
                                <button>Excluir</button></td>
                        </tr>
                        <tr>
                            <td>Smiles - 1 Conexão</td>
                            <td>45.000</td>
                            <td>1.400</td>
                            <td>20.000 + 700</td>
                            <td><button>Salvar</button>
                                <button>Propor a um Cliente</button>
                                <button>Excluir</button></td>
                        </tr>
                        <tr>
                            <td>Azul Fidelidade - Direto</td>
                            <td>55.000</td>
                            <td>1.100</td>
                            <td>30.000 + 500</td>
                            <td><button>Salvar</button>
                                <button>Propor a um Cliente</button>
                                <button>Excluir</button></td>
                        </tr>
                    </tbody>
                </table>
            </section>
        </main>

        <script src="/js/script.js"></script>
    </body>

</html>
