<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sam.controller.BloqueiosController"%>
<%@page import="sam.controller.AutorizacoesController"%>


<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>SAM - Visulizar Usuários</title>

        <link rel="stylesheet" type="text/css" href="../../css/style.css">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>
        <header>
            <img id="logotipo" src="../../imgs/logotipo.png" alt="Logotipo SAM">
            <h1>Permissões</h1>
            <%@include file="/core/header.jsp" %>
        </header>

        <main class="content">
            <!-- BUSCAR INFORMAÇÕES DO USUÁRIO ENVIADO POR CPF -->
            <h2>Gerenciar Permissões de <!-- NOME DO USUARIO --></h2>
            <table>
                <tr>
                    <th>Permissão</th>
                    <th>Status</th>
                    <th>Ações</th>
                </tr>
                </tr>
                <tr><!-- ADICIONAR for LISTA DE FUNCIONALIDADES DO USUARIO -->
                    <td><!-- NOME DA PERMISSÃO --></td>
                    <% if (ControleAutorizacao.checkBloqueio(/*NOME DA PERMISSÃO EM STRING*/, usuario.getCPF())) {%>
                    <td>BLOQUEADO</td>
                    <td>
                        <button><a href='/sam/BloqueiosController?acao=Ativar'>Ativar</a></button>
                    </td>
                    <% } else{%>
                    <td>ATIVO</td>
                    <td>
                        <button><a href='/sam/BloqueiosController?acao=Bloquear'>Bloquear</a></button>
                    </td>
                    <%}%>

                </tr>
            </table>
        </main>
        <script src="../../js/script.js"></script>
    </body>

</html>
