<%@page import="java.util.ArrayList"%>
<%@page import="sam.model.service.UsuariosBlockService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sam.model.service.GestaoUsuariosService"%>
<%@page import="sam.model.domain.util.UsuarioTipo"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <title>SAM - Novo Cliente</title>

        <link rel="stylesheet" type="text/css" href="/sam/css/style.css">
        <link rel="icon" href="/sam/imgs/favicon.ico">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>
        <header>
            <img id="logotipo" src="/sam/imgs/logotipo.png" alt="Logotipo SAM">
            <h1>Novo Cliente</h1>
            <%@include file="/core/header.jsp" %>
        </header>

        <main class="content">
            <h2>Clientes Disponíveis</h2>
            <%  GestaoUsuariosService gestao = new GestaoUsuariosService();
                List<Usuario> clientes = gestao.listarPorTipo("CLIENTE");
                List<Usuario> disponiveis = new ArrayList<>();
                UsuariosBlockService gestaoBlock = new UsuariosBlockService();
                
                for(Usuario c : clientes){
                    if(c.getIdGestor() == null && !gestaoBlock.check(usuario.getId(), c.getId()) && !gestaoBlock.check(c.getId(), usuario.getId())){
                        disponiveis.add(c);
                    }
                }
                
                if(disponiveis == null || disponiveis.isEmpty()){
            %>
            <p>Nenhum cliente disponível</p>
            <%} else {%>
            <table>
                <tr>
                    <th>Nome</th>
                    <th>E-mail </th>
                    <th>Ações</th>
                </tr>
            <%  for(Usuario c : disponiveis){%>
                <tr>
                    <td><%=c.getNome()%></td>
                    <td><%=c.getEmail()%></td>
                    <td><button><a href="/sam/associacoes?acao=Adicionar&idCliente=<%=c.getId()%>&idGestor=<%=usuario.getId()%>">Adicionar cliente</a></button></td>
                </tr>
            <%}%>
            </table>
            <%}%>
        </main>
        <script src="/sam/js/script.js"></script>
    </body>

</html>