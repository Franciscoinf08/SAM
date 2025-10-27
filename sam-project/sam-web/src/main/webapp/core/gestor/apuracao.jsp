<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>Apuração de Resultados</title>
        <link rel="stylesheet" type="text/css" href="../../css/style.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    </head>

    <body>
        <header>

            <h1>Apuração</h1>
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
            <section class="card">
                <h2>Filtros</h2>
                <form class="formulario">
                    <label for="cliente">Cliente:</label>
                    <select id="cliente">
                        <option>Todos</option>
                        <option>Maria S.</option>
                        <option>João P.</option>
                        <option>Ana Sofia</option>
                    </select>

                    <label for="programa">Programa de Fidelidade:</label>
                    <select id="programa">
                        <option>Todos</option>
                        <option>Latam Pass</option>
                        <option>Smiles</option>
                        <option>Azul Fidelidade</option>
                    </select>

                    <label for="periodo">Período:</label>
                    <input type="month" id="periodo">

                    <button type="button">Filtrar</button>
                    <button type="button">Gerar Relatório</button>
                </form>
            </section>

            <section class="content">
                <h2>Resumo de Resultados</h2>
                <table>
                    <thead>
                        <tr>
                            <th>Cliente</th>
                            <th>Programa</th>
                            <th>Milhas Compradas</th>
                            <th>Milhas Vendidas</th>
                            <th>Bônus</th>
                            <th>Total Milhas</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Maria S.</td>
                            <td>Latam Pass</td>
                            <td>5.000</td>
                            <td>2.000</td>
                            <td>500</td>
                            <td>3.500</td>
                        </tr>
                        <tr>
                            <td>João P.</td>
                            <td>Smiles</td>
                            <td>3.000</td>
                            <td>1.000</td>
                            <td>200</td>
                            <td>2.200</td>
                        </tr>
                        <tr>
                            <td>Ana Sofia</td>
                            <td>Azul Fidelidade</td>
                            <td>4.000</td>
                            <td>1.500</td>
                            <td>300</td>
                            <td>2.800</td>
                        </tr>
                    </tbody>
                </table>
            </section>

            <section class="card" style="flex:1 1 100%;">
                <h2>Gráficos de Performance</h2>
                <p>Aqui poderiam ser inseridos gráficos.</p>
            </section>
        </main>

        <script src="../../js/script.js"></script>
    </body>

</html>
