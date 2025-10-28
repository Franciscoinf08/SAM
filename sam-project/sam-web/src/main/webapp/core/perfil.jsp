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
            <img id="logotipo" src="../imgs/logotipo.png" alt="Logotipo SAM">
            <h1>Perfil</h1>
            <nav>
                <a href="cliente/dashboard.jsp">Dashboard</a>
                <a href="transacoes.jsp" class="active">Transações</a>
                <a href="gestor/empresas.jsp">Empresas</a>
                <a href="notificacoes.jsp">Notificações</a>
                <a href="suporte.jsp">Suporte</a>
            </nav>

            <div class="hamburger-menu">
                <button id="hamburger-btn">&#9776;</button>
                <div id="hamburger-dropdown" class="dropdown-content">
                    <a href="perfil.jsp">Visualizar Perfil</a>
                </div>
            </div>
        </header>

        <main>
            <div class="formulario">
                <form>
                    <label for="nome">Nome:
                        <input type="text" name="nome" value="Ana Sofia">
                    </label>

                    <label for="nome">CPF:
                        <input type="text" name="cpf" value="111.222.333-44">
                    </label>

                    <label for="email">Email:
                        <input type="email" name="email" value="ana@email.com">
                    </label>

                    <label for="senha">Senha:
                        <input type="password" name="senha" value="123456">
                    </label>

                    <button type="submit">Salvar Alterações</button>
                </form>

                <button id="solicitar-gestor">Solicitar conta de gestor</button>
            </div>
        </main>
        <script src="../js/script.js"></script>
        <script src="../js/botao-solicitar.js"></script>
    </body>

</html>