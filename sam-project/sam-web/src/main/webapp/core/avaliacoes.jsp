<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>SAM - Avaliações e Denúncias</title>
        <link rel="stylesheet" type="text/css" href="../css/style.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    </head>

    <body>
        <header>
            <a href="../cliente/dashboard.jsp">
                <img id="logotipo" src="../imgs/logotipo.png" alt="Logotipo SAM">
            </a>
            <h1>Avaliações</h1>
            <nav>
                <a href="../tela-cliente/dashboard.jsp">Dashboard</a>
                <a href="transacoes.jsp">Transações</a>
                <a href="tela-gestor/empresas.jsp">Empresas</a>
                <a href="notificacoes.jsp">Notificações</a>
                <a href="suporte.jsp">Suporte</a>
            </nav>
            <div class="login-menu"><a href="../login.jsp">Login/Cadastrar-se</a></div>
            <div class="hamburger-menu">
                <button id="hamburger-btn">&#9776;</button>
                <div id="hamburger-dropdown" class="dropdown-content">
                    <a href="perfil.jsp">Visualizar Perfil</a>
                </div>
            </div>
        </header>

        <main class="dashboard">

            <section class="card" style="flex:1 1 100%;">
                <h2>Adicionar Avaliação</h2>
                <form class="formulario" id="formAvaliacao">
                    <label for="usuario">Usuário avaliado:</label>
                    <input type="text" id="usuario" placeholder="Nome do usuário">

                    <label for="nota">Nota:</label>
                    <select id="nota">
                        <option value="5">⭐️⭐️⭐️⭐️⭐️ - Excelente</option>
                        <option value="4">⭐️⭐️⭐️⭐️ - Bom</option>
                        <option value="3">⭐️⭐️⭐️ - Regular</option>
                        <option value="2">⭐️⭐️ - Ruim</option>
                        <option value="1">⭐️ - Péssimo</option>
                    </select>

                    <label for="comentario">Comentário:</label>
                    <textarea id="comentario" rows="3" placeholder="Escreva seu comentário..."></textarea>

                    <button type="button" onclick="adicionarAvaliacao()">Enviar Avaliação</button>
                </form>
            </section>

            <section class="card" style="flex:1 1 100%;">
                <h2>Avaliações Recentes</h2>
                <ul id="listaAvaliacoes" class="notificacoes">
                    <li><strong>Maria S.</strong> — ⭐️⭐️⭐️⭐️⭐️<br>Excelente atendimento e suporte rápido.</li>
                    <li><strong>João P.</strong> — ⭐️⭐️⭐️<br>Poderia responder mensagens com mais agilidade.</li>
                </ul>
            </section>

            <section class="card" style="flex:1 1 100%;">
                <h2>Denunciar Usuário</h2>
                <form class="formulario" id="formDenuncia">
                    <label for="denunciado">Usuário denunciado:</label>
                    <input type="text" id="denunciado" placeholder="Nome do usuário">

                    <label for="motivo">Motivo:</label>
                    <select id="motivo">
                        <option>Comportamento inadequado</option>
                        <option>Fraude ou má conduta</option>
                        <option>Spam ou propaganda</option>
                        <option>Outro</option>
                    </select>

                    <label for="detalhes">Detalhes:</label>
                    <textarea id="detalhes" rows="3" placeholder="Descreva o ocorrido..."></textarea>

                    <button type="button" onclick="enviarDenuncia()">Enviar Denúncia</button>
                </form>
            </section>

        </main>
        <script src="/js/script.js"></script>
    </body>

</html>