<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>Gestão de Programas de Fidelidade</title>
        <link rel="stylesheet" type="text/css" href="/css/style.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    </head>

    <body>
        <header>
            <a href="../tela-cliente/dashboard.jsp">
                <img id="logotipo" src="/imgs/logotipo.png" alt="Logotipo SAM"> 
            </a>
            <h1>Programas</h1>
            <nav>
                <a href="../tela-cliente/dashboard.jsp">Dashboard</a>
                <a href="../tela-clienteGestor/transacoes.jsp">Transações</a>
                <a href="empresas.jsp">Empresas</a>
                <a href="../tela-clienteGestor/notificacoes.jsp">Notificações</a>
                <a href="../tela-clienteGestor/suporte.jsp">Suporte</a>
            </nav>
            <div class="login-menu">
                <a href="../login.jsp">Login/Cadastrar-se</a>
            </div>
            <div class="hamburger-menu">
                <button id="hamburger-btn">&#9776;</button>
                <div id="hamburger-dropdown" class="dropdown-content">
                    <a href="../tela-clienteGestor/perfil.jsp">Visualizar Perfil</a>
                </div>
            </div>
        </header>

        <main class="content">
            <section class="content">
                <h2>Adicionar Programa de Fidelidade</h2>
                <form class="formulario">
                    <label for="nomePrograma">Nome do Programa:</label>
                    <input type="text" id="nomePrograma" placeholder="Ex: Latam Pass">

                    <label for="empresaAssociada">Empresa Associada:</label>
                    <input type="text" id="empresaAssociada" placeholder="Ex: LATAM Airlines">

                    <label for="regrasPrograma">Regras do Programa:</label>
                    <textarea id="regrasPrograma" rows="3" placeholder="Descreva as regras do programa"></textarea>

                    <button type="button">Adicionar Programa</button>
                </form>
            </section>

            <section class="content">
                <h2>Programas Cadastrados</h2>
                <table>
                    <thead>
                        <tr>
                            <th>Programa</th>
                            <th>Empresa</th>
                            <th>Regras</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Latam Pass</td>
                            <td>LATAM Airlines</td>
                            <td>Acúmulo de milhas em voos nacionais</td>
                            <td>
                                <button>Editar</button>
                                <button>Excluir</button>
                            </td>
                        </tr>
                        <tr>
                            <td>Smiles</td>
                            <td>Gol Linhas Aéreas</td>
                            <td>Acúmulo de milhas em voos e parceiros</td>
                            <td>
                                <button>Editar</button>
                                <button>Excluir</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </section>
        </main>

        <script src="/js/script.js"></script>
    </body>

</html>
