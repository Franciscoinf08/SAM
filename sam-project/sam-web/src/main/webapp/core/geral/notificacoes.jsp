<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>SAM - Notificações</title>

        <link rel="stylesheet" type="text/css" href="../../css/style.css">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>
        <header>
            <img id="logotipo" src="../../imgs/logotipo.png" alt="Logotipo SAM">
            <h1>Notificações</h1>
            <%@include file="/core/header.jsp" %>
        </header>

        <main>
            <h2>Últimas notificações</h2>
            <ul class="notificacoes">
                <li>Milhas da conta Azul expiram em 7 dias.</li>
                <li>Nova campanha promocional disponível.</li>
            </ul>
            <h2>Propostas e orçamentos</h2>
            <table>
                <thead>
                    <tr>
                        <th>Opção</th>
                        <th>Milhas</th>
                        <th>Dinheiro (R$)</th>
                        <th>Milhas + Dinheiro</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Latam Pass - Direto</td>
                        <td>50.000</td>
                        <td>1.200</td>
                        <td>25.000 + 600</td>
                        <td><button class="acao-proposta">Aceitar</button>
                            <button>Recusar</button></td>
                    </tr>
                    <tr>
                        <td>Smiles - 1 Conexão</td>
                        <td>45.000</td>
                        <td>1.400</td>
                        <td>20.000 + 700</td>
                        <td><button>Aceitar</button>
                            <button>Recusar</button></td>
                    </tr>
                    <tr>
                        <td>Azul Fidelidade - Direto</td>
                        <td>55.000</td>
                        <td>1.100</td>
                        <td>30.000 + 500</td>
                        <td><button>Aceitar</button>
                            <button>Recusar</button></td>
                    </tr>
                </tbody>
            </table>
        </main>
        <script src="../js/script.js"></script>
    </body>

</html>