<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="utf-8">

        <title>SAM - Configuração de Planos</title>
        <link rel="stylesheet" type="text/css" href="../../css/style.css">
    </head>

    <body>
        <header>
            <img id="logotipo" src="../../imgs/logotipo.png" alt="Logotipo SAM">
            <h1>Planos</h1>
            <nav>
                <a href="../cliente/dashboard.jsp">Dashboard</a>
                <a href="../transacoes.jsp">Transações</a>
                <a href="empresas.jsp">Empresas</a>
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


        <main>
            <section class="dashboard">
                <div id="card-selecao" class="card" style="flex:1; min-width:360px;">
                    <h2>Planos existentes</h2>
                    <table id="plans-table">
                        <thead>
                            <tr>
                                <th>Nome</th>
                                <th>Preço</th>
                                <th>Duração</th>
                                <th>Recursos</th>
                                <th>Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Básico <strong>(padrão)</strong></td>
                                <td>R$ 0,00</td>
                                <td>1 mês</td>
                                <td>2 recursos</td>
                                <td>
                                    <button>Editar</button>
                                    <button>Aplicar a cliente</button>
                                </td>
                            </tr>
                            <tr>
                                <td>Intermediário</td>
                                <td>R$ 19,90</td>
                                <td>6 meses</td>
                                <td>4 recursos</td>
                                <td>
                                    <button>Editar</button>
                                    <button>Aplicar a cliente</button>
                                </td>
                            </tr>
                            <tr>
                                <td>Premium</td>
                                <td>R$ 49,90</td>
                                <td>12 meses</td>
                                <td>6 recursos</td>
                                <td>
                                    <button>Editar</button>
                                    <button>Aplicar a cliente</button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <div class="formulario">
                    <h2 id="form-title">Criar Plano</h2>
                    <form id="plan-form">
                        <label for="plan-name"><strong>Nome do plano</strong></label>
                        <input id="plan-name" required placeholder="Ex: Básico, Premium">

                        <label for="plan-price"><strong>Preço (R$)</strong></label>
                        <input id="plan-price" type="number" min="0" step="0.01" placeholder="0.00">

                        <label for="plan-duration"><strong>Duração (meses)</strong></label>
                        <input id="plan-duration" type="number" min="1" step="1" value="1">

                        <label><strong>Recursos</strong></label>
                        <label class="checkbox-container">
                            Acesso às transações e dashboard<input type="checkbox" name="feature" value="acesso_transacoes">
                        </label>
                        <label class="checkbox-container">
                            Importar dados externos<input type="checkbox" name="feature" value="importar_dados">
                        </label>
                        <label class="checkbox-container">
                            Criar propostas/orçamentos<input type="checkbox" name="feature" value="criar_propostas">
                        </label>
                        <label class="checkbox-container">
                            Comparar milhas entre programas<input type="checkbox" name="feature" value="comparar_programas">
                        </label>
                        <label class="checkbox-container">
                            Criar campanhas promocionais<input type="checkbox" name="feature" value="campanhas_promocionais">
                        </label>
                        <label class="checkbox-container">
                            Acesso ao sistema de tickets e FAQ<input type="checkbox" name="feature" value="suporte_ticket">
                        </label>
                        <label class="checkbox-container">
                            Receber notificações automatizadas<input type="checkbox" name="feature" value="notificacoes">
                        </label>

                        <label class="checkbox-container" style="margin-top:2em">
                            Definir como plano padrão<input type="checkbox" id="plan-default">
                        </label>

                        <div style="display:flex;gap:10px;flex-wrap:wrap;">
                            <button type="submit" id="btn-save">Salvar plano</button>
                            <button type="button" id="btn-cancel">Cancelar</button>
                            <button type="button" id="btn-delete" style="background:#b30000;">Excluir plano</button>
                        </div>
                    </form>
                </div>
            </section>
        </main>
        <script src="../../js/script.js"></script>
    </body>

</html>