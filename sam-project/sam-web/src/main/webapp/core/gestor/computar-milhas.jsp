<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>SAM - Computar Milhas</title>

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
            <h1>Computar Milhas</h1>
            <%@include file="/core/header.jsp" %>
        </header>


        <main>
            <h2>Computar Milhas</h2>
            <p>Compare valores e descubra se a compra de milhas vale a pena.</p>

            <section class="formulario">
                <form>
                    <label for="programa">Programa de Fidelidade:
                        <select>
                            <option>Latam Pass</option>
                            <option>Azul Fidelidade</option>
                            <option>Smiles</option>
                        </select>
                    </label>

                    <label for="quantidade">Quantidade de Milhas:
                        <input type="number" placeholder="Ex: 10.000">
                    </label>

                    <label for="valorCompra">Valor Total da Compra (R$):
                        <input type="number" placeholder="Ex: 350">
                    </label>

                    <button type="button" onclick="calcularVantagem()">Calcular Vantagem</button>
                </form>
            </section>

            <section class="card" style="margin-top: 25px;">
                <h3>Resultado da Análise</h3>
                <p id="textoResultado">Preencha os campos acima e clique em Calcular.</p>
            </section>
        </main>
        <script src="/sam/js/script.js"></script>
    </body>

</html>
