<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>Computar Milhas - SAM 1.0</title>
        <link rel="stylesheet" type="text/css" href="../../css/style.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    </head>

    <body>
        <header>
                <img id="logotipo" src="../../imgs/logotipo.png" alt="Logotipo SAM">
            <h1>Dashboard</h1>
            <nav>
                <a href="dashboard.jsp" class="active">Dashboard</a>
                <a href="../transacoes.jsp">Transações</a>
                <a href="../gestor/empresas.jsp">Empresas</a>
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


        <main>
            <h2>Computar Milhas</h2>
            <p>Compare valores e descubra se a compra de milhas vale a pena.</p>

            <section class="formulario">
                <form>
                    <label for="programa">Programa de Fidelidade:</label>
                    <select id="programa">
                        <option>Latam Pass</option>
                        <option>Azul Fidelidade</option>
                        <option>Smiles</option>
                    </select>

                    <label for="quantidade">Quantidade de Milhas:</label>
                    <input type="number" id="quantidade" placeholder="Ex: 10.000">

                    <label for="valorCompra">Valor Total da Compra (R$):</label>
                    <input type="number" id="valorCompra" placeholder="Ex: 350">

                    <!-- <label for="valorMilheiro">Valor do Milheiro de Segurança (R$):</label>
                    <input type="number" id="valorMilheiro" placeholder="Ex: 28"> -->

                    <button type="button" onclick="calcularVantagem()">Calcular Vantagem</button>
                </form>
            </section>

            <section class="card" id="resultado" style="margin-top: 25px;">
                <h3>Resultado da Análise</h3>
                <p id="textoResultado">Preencha os campos acima e clique em Calcular.</p>
            </section>
        </main>
        <script src="/js/script.js"></script>
    </body>

</html>
