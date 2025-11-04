<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sam.model.domain.Usuario"%>
<%@page import="sam.controller.LoginController"%>

<%
    LoginController.validarSessao(request, response);

    Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
%>

<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>SAM - Programas de Fidelidade</title>

        <link rel="stylesheet" type="text/css" href="/sam/css/style.css">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>
        <header>
            <img id="logotipo" src="/sam/imgs/logotipo.png" alt="Logotipo SAM">
            <h1>Programas de Fidelidade</h1>
            <nav>
                <a href="/sam/core/transacoes.jsp">Transações</a>
                <a href="/sam/core/gestor/empresas.jsp">Empresas</a>
                <a href="/sam/core/notificacoes.jsp">Notificações</a>
                <a href="/sam/core/suporte.jsp">Suporte</a>
            </nav>

            <div class="hamburger-menu">
                <h1><%=usuario.getNome()%></h1>
                <button id="hamburger-btn">&#9776;</button>
                <div id="hamburger-dropdown" class="dropdown-content">
                    <a href="/sam/core/perfil.jsp">Visualizar Perfil</a>
                </div>
            </div>
        </header>

        <main class="content">
            <section class="content">
                <h2>Adicionar Programa de Fidelidade</h2>
                <form class="formulario">
                    <label for="nomePrograma">Nome do Programa:
                        <input type="text" placeholder="Ex: Latam Pass">
                    </label>

                    <label for="empresaAssociada">Empresa Associada:
                        <input type="text" placeholder="Ex: LATAM Airlines">
                    </label>

                    <label for="regrasPrograma">Regras do Programa:
                        <textarea rows="3" placeholder="Descreva as regras do programa"></textarea>
                    </label>

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

        <script src="/sam/js/script.js"></script>
    </body>

</html>
