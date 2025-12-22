function mostrarMais(id) {
    fetch(`<%=request.getContextPath()%>/notificacoes?action=detalharAjax&id=` + id)
        .then(response => response.json())
        .then(data => {
            const mensagem = document.getElementById("msg-" + id);

            mensagem.innerHTML = `
                ${data.mensagemCompleta}
                <br><br>
                <a href="#" onclick="fechar(${id}); return false;">mostrar menos</a>
            `;
        })
        .catch(e => console.error("Erro no AJAX:", e));
}

function fechar(id) {
    fetch(`<%=request.getContextPath()%>/notificacoes?action=resumoAjax&id=` + id)
        .then(response => response.json())
        .then(data => {
            const mensagem = document.getElementById("msg-" + id);

            mensagem.innerHTML = `
                ${data.resumo}
                <a href="#" onclick="mostrarMais(${id}); return false;">mostrar mais</a>
            `;
        });
}