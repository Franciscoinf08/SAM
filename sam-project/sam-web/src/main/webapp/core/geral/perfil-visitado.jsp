<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sam.model.dao.UsuarioDAO"%>
<%@page import="sam.controller.AssociacoesClientesController"%>
<%@page import="sam.model.domain.util.UsuarioTipo"%>

<% UsuarioDAO usuarioDAO = UsuarioDAO.getInstance(); %>

<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>SAM - Visitar Perfil</title>

        <link rel="stylesheet" type="text/css" href="/sam/css/style.css">
        <link rel="stylesheet" type="text/css" href="/sam/css/visitado.css">
        <link rel="icon" href="/sam/imgs/favicon.ico">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>
        <header>
            <img id="logotipo" src="/sam/imgs/logotipo.png" alt="Logotipo SAM">
            <h1>Visitar Perfil</h1>
            <%@include file="/core/header.jsp" %>
        </header>

        <main class="content">
            <% String idParam = request.getParameter("id");
               long id = Long.parseLong(idParam);
               Usuario visitado = usuarioDAO.pesquisar(id); %>
            
            <!-- SE "bloqueado" MOSTRA APENAS NOME E UM "Usuário bloqueado" 
            SE "desbloqueado" MOSTRA TUDO -->
            
            <div id="visitado">
                <img id="user-icon" src="/sam/imgs/user-icon.png" alt="Icone perfil">
                <div id="visitado-texto">
                    <h2><%=visitado.getNome()%></h2>
                    <p><%=visitado.getTipo()%></p>
                    <p><%=visitado.getEmail()%></p>
                </div>
                <div id="acoes-visitado">
                    <button>Avaliações</button>
                    <button>Relatórios</button>
                    <!-- SE "bloqueado" ACAO "desbloquear" 
                         SE "desbloqueado" ACAO "bloquear"-->
                    <button><a href="/sam/userBlock?acao=Bloquear&id=<%=visitado.getId()%>">Bloquear</a></button>
                    <% if(usuario.getTipo() == UsuarioTipo.GESTOR && visitado.getTipo() == UsuarioTipo.CLIENTE){%>
                    <button><a href="/sam/associacoes?acao=Adicionar&idCliente=<%=visitado.getId()%>&idGestor=<%=usuario.getId()%>">Adicionar cliente</a></button>
                    <%}%>
                </div>
            </div>
            
        </main>
        <script src="/sam/js/script.js"></script>
    </body>

</html>