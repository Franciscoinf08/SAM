<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="sam.model.domain.util.UsuarioTipo" %>
<%@ page import="sam.model.domain.Usuario" %>
<%@ page import="sam.model.domain.ProgramaFidelidade" %>
<%@page import="sam.model.domain.AssociacaoCliente"%>
<%@page import="sam.model.service.GestaoAssociacoesClientesService"%>
<%@page import="sam.model.service.GestaoUsuariosService"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>SAM - Clientes</title>

        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
        <link rel="icon" href="/sam/imgs/favicon.ico">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <header>
            <img id="logotipo" src="<%=request.getContextPath()%>/imgs/logotipo.png" alt="Logotipo SAM">
            <h1>Clientes</h1>
            <%@include file="/core/header.jsp" %>
        </header>

        <main class="content">
            
            <h2>Meus clientes</h2>
            <%  GestaoUsuariosService gestao = new GestaoUsuariosService();
                List<Usuario> clientes = (List<Usuario>) request.getAttribute("clientes");
                List<Usuario> meusClientes = new ArrayList<>();
                
                if(clientes == null){
                    meusClientes = gestao.getListaClientes(usuario);
                }
                else{
                    meusClientes = clientes;
                }
                
                if (ControleAutorizacao.checkPermissao("associacoes", usuario.getTipo()) && !ControleAutorizacao.checkBloqueio("associacoes", usuario.getCPF())) {
            %>
            <button><a href="/sam/associacoes?acao=ListarDisponiveis">Novo cliente</a></button>
            <%}
                if(meusClientes == null || meusClientes.isEmpty()){%>
                <p>Você ainda não tem clientes</p>
                <%} else {%>
                <table>
                <tr>
                    <th>Nome</th>
                    <th>CPF </th>
                    <th>E-mail </th>
                    <th>Ações</th>
                </tr>
                <%
                    for (Usuario c : meusClientes){
                %>
                <tr>
                    <td><%=c.getNome()%></td>
                    <td><%=c.getCPF()%></td>
                    <td><%=c.getEmail()%></td>
                    <td>
                        <a href="<%=request.getContextPath()%>/usuarioPrograma?action=programas&idUsuario=<%=c.getId()%>"><button class="btn-associar">Programas associados</button></a>
                        <%if (ControleAutorizacao.checkPermissao("associacoes", usuario.getTipo()) && !ControleAutorizacao.checkBloqueio("associacoes", usuario.getCPF())) {%>
                        <button><a href="<%=request.getContextPath()%>/associacoes?acao=Remover&idCliente=<%=c.getId()%>&idGestor=<%=usuario.getId()%>">Desassociar</a></button>
                        <%}%>
                    </td>

                </tr>
                <%}%>
                </table>
                <%}%>
            <h2>Pedidos pendentes</h2>
            <%  GestaoAssociacoesClientesService gestaoAsso = new GestaoAssociacoesClientesService();
                List<AssociacaoCliente> pedidos = gestaoAsso.listarGestor(usuario.getId());
                if(pedidos == null || pedidos.isEmpty()){%>
                    <p>Você não tem pedidos para gerencia</p>
                <%} else {%>
            
            <table>
                <tr>
                    <th>Cliente</th>
                    <th>Tipo</th>
                </tr>
                <%  for(AssociacaoCliente p : pedidos){
                        Usuario cliente = gestao.pesquisar(p.getIdCliente()); %>
                <tr>
                    <td><%=cliente.getNome()%></td>
                    <td><%=p.getTipo()%></td>
                </tr>
                <%}%>
            </table>
            <%}%>
            
        </main>
        <script src="<%=request.getContextPath()%>/js/script.js"></script>

    </body>
</html>
