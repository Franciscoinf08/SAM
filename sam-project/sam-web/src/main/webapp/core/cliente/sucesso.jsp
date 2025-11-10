<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sucesso</title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css">
</head>
<body id="body-sucesso">
    <header>
        <img id="logotipo" src="/sam/imgs/logotipo.png" alt="Logotipo SAM">
        <h1>Dashboard</h1>
        <%@include file="/core/header.jsp" %>
    </header>
    <section id="secao-geral">
        <div class="div-sucesso">
            <h1>SUCESSO!!!</h1>
            <h2>Formulario registrado/atualizado com sucesso.</h2>
        </div>
        <div class="-div-retorno">
            <a href="selecao-formularios.jsp">Voltar para pagina incial ></a>
        </div>
    </section>

</body>
</html>
