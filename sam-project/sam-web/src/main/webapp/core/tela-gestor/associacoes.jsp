<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>Gestão de Associações</title>
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
            <h1>Associaciações</h1>
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
                <h2>Associar Cliente a Programa</h2>
                <form class="formulario">
                    <label for="cliente">Cliente:</label>
                    <select id="cliente">
                        <option>Maria S.</option>
                        <option>João P.</option>
                        <option>Ana Sofia</option>
                    </select>

                    <label for="programa">Programa de Fidelidade:</label>
                    <select id="programa">
                        <option>Latam Pass</option>
                        <option>Smiles</option>
                        <option>Azul Fidelidade</option>
                    </select>

                    <button type="button">Associar</button>
                </form>
            </section>

            <section class="content">
                <h2>Associações Existentes</h2>
                <table>
                    <thead>
                        <tr>
                            <th>Cliente</th>
                            <th>Programa</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Maria S.</td>
                            <td>Latam Pass</td>
                            <td>
                                <button>Remover</button>
                            </td>
                        </tr>
                        <tr>
                            <td>João P.</td>
                            <td>Smiles</td>
                            <td>
                                <button>Remover</button>
                            </td>
                        </tr>
                        <tr>
                            <td>Ana Sofia</td>
                            <td>Azul Fidelidade</td>
                            <td>
                                <button>Remover</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </section>
        </main>

        <script src="/js/script.js"></script>
    </body>

</html>
