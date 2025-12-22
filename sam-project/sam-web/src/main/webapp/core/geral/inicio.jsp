<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sam.model.service.GestaoAssociacoesClientesService"%>
<%@page import="sam.model.service.GestaoUsuariosService"%>
<%@page import="sam.model.domain.util.UsuarioTipo"%>
<%@page import="sam.model.domain.util.AssociacaoClienteTipo"%>
<%@page import="sam.model.domain.AssociacaoCliente"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>SAM - Início</title>

        <link rel="stylesheet" type="text/css" href="/sam/css/style.css">
        <link rel="stylesheet" type="text/css" href="/sam/css/pesquisar.css">
        <link rel="icon" href="/sam/imgs/favicon.ico">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>
        <header>
            <img id="logotipo" src="/sam/imgs/logotipo.png" alt="Logotipo SAM">
            <h1>Início</h1>
            <%@include file="/core/header.jsp" %>
        </header>

        <main class="main-inicio">
            
            <!-- SE CLIENTE, APARECE TODAS AS SOLICITAÇÕES DE ASSOCIAÇÃO PARA APROVAÇÃO
            
                 QUANDO APROVADO OU RECUSADO, SOME A SOLICITAÇÃO (SOME TODAS SE APROVADO) -->
            
            <div id="corpo-inicio">
                <input type="text" id="searchInput" placeholder="Pesquisar...">
                <ul id="itemsList">
                    <%  GestaoUsuariosService gestao = new GestaoUsuariosService();
                        List<Usuario> visitados = gestao.listarTodos();
                        for(Usuario v : visitados){
                        if(!v.equals(usuario) && v.getTipo() != UsuarioTipo.DESENVOLVEDOR){%>
                    <li><a href="/sam/core/geral/perfil-visitado.jsp?id=<%=v.getId()%>"><%=v.getNome()%></a></li>
                    <%}}%>
                </ul>
            </div>
                
            <%  if(usuario.getTipo() == UsuarioTipo.CLIENTE){%>
            
            <div id="pedidos-gestao">
                <h2>Pedidos de Gerencia</h2>
                    <% GestaoAssociacoesClientesService gestaoAsso = new GestaoAssociacoesClientesService();
                    List<AssociacaoCliente> pedidos = gestaoAsso.listarCliente(usuario.getId());
                    if(pedidos == null || pedidos.isEmpty()){%>
                        <p>Você não tem pedidos para gerencia</p>
                    <%} else {%>
                <table>
                    <tr>
                        <th>Gestor</th>
                        <th>Tipo</th>
                        <th>Ações</th>
                    </tr>
                <%  for(AssociacaoCliente p : pedidos){
                        Usuario gerente = gestao.pesquisar(p.getIdGestor()); %>
                    <tr>
                        <td><%=gerente.getNome()%></td>
                        <td><%=p.getTipo()%></td>
                        <td>
                            <% if(p.getTipo() == AssociacaoClienteTipo.ASSOCIAR){%>
                            <button><a href="<%=request.getContextPath()%>/associacoes?acao=Aprovar&idCliente=<%=usuario.getId()%>&idGestor=<%=gerente.getId()%>&tipo=ASSOCIAR">Aprovar</a></button>
                            <%} else {%>
                            <button><a href="<%=request.getContextPath()%>/associacoes?acao=Aprovar&idCliente=<%=usuario.getId()%>&idGestor=<%=gerente.getId()%>&tipo=DESASSOCIAR">Aprovar</a></button>
                            <%}%>
                            <button><a href="<%=request.getContextPath()%>/associacoes?acao=Recusar&idPedido=<%=p.getId()%>">Recusar</a></button>
                        </td>
                    </tr>
                <%}%>
            <%}%>
                </div>   
            <%}%>

        </main>

        <%@include file="/core/mensagens-erro.jsp"%>

        <script src="/sam/js/script.js"></script>
        <script src="/sam/js/pesquisar.js"></script>
    </body>

</html>