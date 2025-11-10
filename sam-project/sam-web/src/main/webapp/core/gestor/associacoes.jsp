<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>SAM - Associações</title>

        <link rel="stylesheet" type="text/css" href="../../css/style.css">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>
        <header>
            <img id="logotipo" src="../../imgs/logotipo.png" alt="Logotipo SAM">
            <h1>Gestão de Associaciações</h1>
            <%@include file="/core/header.jsp" %>
        </header>

        <main class="content">
            <section class="content">
                <h2>Associar Cliente a Programa</h2>
                <form class="formulario">
                    <label for="cliente">Cliente:
                        <select>
                            <option>Maria S.</option>
                            <option>João P.</option>
                            <option>Ana Sofia</option>
                        </select>
                    </label>

                    <label for="programa">Programa de Fidelidade:
                        <select>
                            <option>Latam Pass</option>
                            <option>Smiles</option>
                            <option>Azul Fidelidade</option>
                        </select>
                    </label>

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

        <script src="../../js/script.js"></script>
    </body>

</html>
