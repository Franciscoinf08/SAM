<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="sam.model.domain.ProgramaFidelidade" %>
<%@ page import="java.util.List" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro de Empresa</title>
</head>
<body>
<header>
    <img id="logotipo" src="<%= request.getContextPath() %>/imgs/logotipo.png" alt="Logotipo SAM">
    <h1>Empresas</h1>
    <nav>
        <a href="../transacoes.jsp">Transações</a>
        <a href="empresas.jsp" class="active">Empresas</a>
        <a href="../notificacoes.jsp">Notificações</a>
        <a href="../suporte.jsp">Suporte</a>
    </nav>
    <div class="hamburger-menu">
        <button id="hamburger-btn">&#9776;</button>
        <div id="hamburger-dropdown" class="dropdown-content">
            <a href="../perfil.jsp">Visualizar Perfil</a>
        </div>
    </div>
</header>

<main class="content">
    <div>
        <h2>Programas de fidelidade</h2>
        <table>
            <%
                List<ProgramaFidelidade> programas = (List<ProgramaFidelidade>) request.getAttribute("programas");
                if (programas != null && !programas.isEmpty()){
            %>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Bônus de Milhas</th>
                <th>Milhas por Mês</th>
                <th>Duração (meses)</th>
                <th>Preço Mensal (R$)</th>
                <th>Ações</th>
            </tr>

            <%
                for (ProgramaFidelidade p : programas){
            %>
            <tr>
                <td><%= p.getIdProgramaFidelidade() %></td>
                <td><%= p.getNome() %></td>
                <td><%= p.getBonusMilhas() %></td>
                <td><%= p.getQtdeMilhasMes() %></td>
                <td><%= p.getDuracao() %></td>
                <td><%= p.getPrecoMensal() %></td>
                <td>
                    <a href="<%= request.getContextPath() %>/programaFidelidade?action=editar&id=<%= p.getIdProgramaFidelidade()%>&idEmpresa=<%=request.getAttribute("idEmpresa")%>">
                        <button>Editar</button>
                    </a>
                    <a href="<%= request.getContextPath() %>/programaFidelidade?action=excluir&id=<%=p.getIdProgramaFidelidade()%>&idEmpresa=<%=request.getAttribute("idEmpresa")%>">
                        <button>Excluir</button>
                    </a>
                </td>
            </tr>
            <%
                }
            } else {
            %>
            <tr><td colspan="7">Nenhum programa de fidelidade cadastrado.</td></tr>
            <%
                }
            %>
        </table>

        <a href="<%= request.getContextPath() %>/programaFidelidade?action=novo&idEmpresa=<%= request.getParameter("idEmpresa") %>"><button>Adicionar</button></a>
        <a href="<%= request.getContextPath() %>/empresa"><button>Voltar</button></a>
    </div>
</main>
</body>
</html>
