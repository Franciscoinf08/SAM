<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>SAM - Orçamentos</title>

        <link rel="stylesheet" type="text/css" href="../../css/style.css">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>
        <header>
            <img id="logotipo" src="../../imgs/logotipo.png" alt="Logotipo SAM">
            <h1>Orçamentos</h1>
            <nav>
                <a href="../transacoes.jsp">Transações</a>
                <a href="empresas.jsp">Empresas</a>
                <a href="../notificacoes.jsp">Notificações</a>
                <a href="../suporte.jsp">Suporte</a>
            </nav>

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
                    <label for="destino">Destino:
                        <input type="text" placeholder="Cidade ou país">
                    </label>

                    <label for="dataIda">Data de Ida:
                        <input type="date">
                    </label>

                    <label for="dataVolta">Data de Volta:
                        <input type="date">
                    </label>

                    <label for="adultos">Quantidade de Adultos:
                        <input type="number" min="1" value="1">
                    </label>

                    <label for="criancas">Quantidade de Crianças:
                        <input type="number" min="0" value="0">
                    </label>

                    <label for="conexoes">Conexões:
                        <select>
                            <option>Direto</option>
                            <option>1 Conexão</option>
                            <option>2 Conexões</option>
                        </select>
                    </label>

                    <label for="companhia">Companhia Aérea:
                        <select>
                            <option>Indiferente</option>
                            <option>Latam</option>
                            <option>Azul</option>
                            <option>Gol</option>
                        </select>
                    </label>

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

        <script src="../../js/script.js"></script>
    </body>

</html>
