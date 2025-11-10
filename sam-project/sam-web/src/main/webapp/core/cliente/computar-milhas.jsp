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
        <title>SAM - Perfil</title>

        <link rel="stylesheet" type="text/css" href="/sam/css/style.css">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>
        <header>
            <img id="logotipo" src="/sam/imgs/logotipo.png" alt="Logotipo SAM">
            <h1>Perfil</h1>
            <nav>
                <a href="/sam/core/cliente/dashboard.jsp">Dashboard</a>
                <a href="/sam/core/transacoes.jsp">Transações</a>
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
