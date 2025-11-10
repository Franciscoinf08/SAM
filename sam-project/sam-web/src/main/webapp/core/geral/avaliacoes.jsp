<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>SAM - Avaliações</title>

        <link rel="stylesheet" type="text/css" href="../../css/style.css">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>
        <header>
            <img id="logotipo" src="../../imgs/logotipo.png" alt="Logotipo SAM">
            <h1>Avaliações</h1>
            <%@include file="/core/header.jsp" %>
        </header>

        <main class="dashboard">

            <section class="card" style="flex:1 1 100%;">
                <h2>Adicionar Avaliação</h2>
                <form class="formulario" id="formAvaliacao">
                    <label for="usuario">Usuário avaliado:
                        <input type="text" placeholder="Nome do usuário">
                    </label>

                    <label for="nota">Nota:
                        <select>
                            <option value="5">⭐️⭐️⭐️⭐️⭐️ - Excelente</option>
                            <option value="4">⭐️⭐️⭐️⭐️ - Bom</option>
                            <option value="3">⭐️⭐️⭐️ - Regular</option>
                            <option value="2">⭐️⭐️ - Ruim</option>
                            <option value="1">⭐️ - Péssimo</option>
                        </select>
                    </label>

                    <label for="comentario">Comentário:
                        <textarea rows="3" placeholder="Escreva seu comentário..."></textarea>
                    </label>

                    <button type="button" onclick="adicionarAvaliacao()">Enviar Avaliação</button>
                </form>
            </section>

            <section class="card" style="flex:1 1 100%;">
                <h2>Avaliações Recentes</h2>
                <ul class="notificacoes">
                    <li><strong>Maria S.</strong> — ⭐️⭐️⭐️⭐️⭐️<br>Excelente atendimento e suporte rápido.</li>
                    <li><strong>João P.</strong> — ⭐️⭐️⭐️<br>Poderia responder mensagens com mais agilidade.</li>
                </ul>
            </section>

            <section class="card" style="flex:1 1 100%;">
                <h2>Denunciar Usuário</h2>
                <form class="formulario">
                    <label for="denunciado">Usuário denunciado:
                        <input type="text" placeholder="Nome do usuário">
                    </label>

                    <label for="motivo">Motivo:
                        <select>
                            <option>Comportamento inadequado</option>
                            <option>Fraude ou má conduta</option>
                            <option>Spam ou propaganda</option>
                            <option>Outro</option>
                        </select>
                    </label>

                    <label for="detalhes">Detalhes:
                        <textarea rows="3" placeholder="Descreva o ocorrido..."></textarea>
                    </label>

                    <button type="button" onclick="enviarDenuncia()">Enviar Denúncia</button>
                </form>
            </section>

        </main>
        <script src="/sam/js/script.js"></script>
    </body>

</html>