<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>SAM - Relatórios</title>

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
            <h1>Relatórios</h1>
            <%@include file="/core/header.jsp" %>
        </header>

        <main class="dashboard">
            <section class="card">
                <h2>Buscar Usuário</h2>
                <form class="formulario">
                    <label for="tipoUsuario">Tipo de Usuário:
                        <select>
                            <option>Cliente</option>
                            <option>Gestor</option>
                        </select>
                    </label>

                    <label for="nomeUsuario">Nome do Usuário:
                        <input type="text" placeholder="Digite o nome para pesquisar">
                    </label>
                    <button type="button">Pesquisar</button>
                </form>
            </section>

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
                    <tbody>
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

            <section class="card" style="display:none;">
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

        <script src="/sam/js/script.js"></script>
    </body>

</html>
