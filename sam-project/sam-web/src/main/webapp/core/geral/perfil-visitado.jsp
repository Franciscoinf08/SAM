<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sam.model.dao.UsuarioDAO"%>
<%@page import="sam.model.domain.util.UsuarioTipo"%>
<%@page import="sam.model.service.UsuariosBlockService"%>

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
            <%  Long id = (Long) request.getAttribute("id");

                if (id == null) {
                    id = Long.parseLong(request.getParameter("id"));
                }
                Usuario visitado = usuarioDAO.pesquisar(id); 

                UsuariosBlockService gestaoBlock = new UsuariosBlockService();
                // TRUE SE O USUARIO ATUAL BLOQUEOU ESSE PERFIL
                if(gestaoBlock.check(usuario.getId(), visitado.getId())){
            %>
                <div id="visitado">
                    <img class="user-icon" src="/sam/imgs/user-block.png" alt="Icone perfil bloqueado">
                    <div class="direita">
                        <div class="visitado-texto">
                            <h2><%=visitado.getNome()%></h2>
                            <p>Você bloqueou esse perfil</p>
                        </div>
                        <div class="acoes-visitado">
                            <button><a href="/sam/userBlock?acao=Desbloquear&idVisitado=<%=visitado.getId()%>&idUsuario=<%=usuario.getId()%>">Desbloquear</a></button>
                        </div>
                    </div>
                </div>
            <%} 
                // TRUE SE O PERFIL ATUAL BLOQUEOU ESSE USUARIO
            else if(gestaoBlock.check(visitado.getId(), usuario.getId())){%>
                <div id="visitado">
                    <img class="user-icon" src="/sam/imgs/user-block.png" alt="Icone perfil bloqueado">
                    <div class="direita">
                        <div class="visitado-texto">
                            <h2><%=visitado.getNome()%></h2>
                            <p>Você foi bloqueado por esse perfil</p>
                        </div>
                    </div>
                </div>
            <%} else {%>
                <div id="visitado">
                    <img class="user-icon" src="/sam/imgs/user-icon.png" alt="Icone perfil">
                    <div class="direita">
                        <div class="visitado-texto">
                            <h2><%=visitado.getNome()%></h2>
                            <p><%=visitado.getTipo()%></p>
                            <p><%=visitado.getEmail()%></p>
                        </div>
                        <div class="acoes-visitado">
                            <button>Avaliações</button>
                            <button>Relatórios</button>
                            <button><a href="/sam/userBlock?acao=Bloquear&idVisitado=<%=visitado.getId()%>&idUsuario=<%=usuario.getId()%>">Bloquear</a></button>
                            <% if(usuario.getTipo() == UsuarioTipo.GESTOR && visitado.getTipo() == UsuarioTipo.CLIENTE && visitado.getIdGestor() == null){%>
                            <button><a href="/sam/associacoes?acao=Adicionar&idCliente=<%=visitado.getId()%>&idGestor=<%=usuario.getId()%>">Adicionar cliente</a></button>
                            <%}%>
                        </div>
                    </div>
                </div>
            <%}%>
        </main>
        <script src="/sam/js/script.js"></script>
    </body>

</html>