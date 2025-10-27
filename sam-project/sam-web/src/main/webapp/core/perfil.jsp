<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>Perfil do Usuário</title>
        <link rel="stylesheet" type="text/css" href="../css/style.css">
        
    </head>

    <body>
        <header>
            <a href="../tela-cliente/dashboard.jsp">
                <img id="logotipo" src="/imgs/logotipo.png" alt="Logotipo SAM"> 
            </a>
            <h1>Transações</h1>
            <nav>
                <a href="../tela-cliente/dashboard.jsp">Dashboard</a>
                <a href="transacoes.jsp" class="active">Transações</a>
                <a href="tela-gestor/empresas.jsp">Empresas</a>
                <a href="notificacoes.jsp">Notificações</a>
                <a href="suporte.jsp">Suporte</a>
            </nav>
            <div class="login-menu"><a href="../index.html">Login/Cadastrar-se</a></div>
            <div class="hamburger-menu">
                <button id="hamburger-btn">&#9776;</button>
                <div id="hamburger-dropdown" class="dropdown-content">
                    <a href="perfil.jsp">Visualizar Perfil</a>
                </div>
            </div>
        </header>

        <main>
            <div class="formulario">
                <form id="perfilForm">
                    <label for="nome">Nome:</label>
                    <input type="text" id="nome" name="nome" value="Ana Sofia">

                    <label for="nome">CPF:</label>
                    <input type="text" id="cpf" name="cpf" value="111.222.333-44">

                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" value="ana@email.com">

                    <label for="senha">Senha:</label>
                    <input type="password" id="senha" name="senha" value="123456">

                    <button type="submit">Salvar Alterações</button>
                </form>

                <button id="solicitar-gestor">Solicitar conta de gestor</button>
            </div>
        </main>
        <script src="/js/script.js"></script>
        <script src="/js/botao-solicitar.js"></script>
    </body>

</html>