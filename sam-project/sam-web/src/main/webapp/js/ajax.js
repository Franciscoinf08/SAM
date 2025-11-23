function ajaxProgramaFidelidade() {
    let idCliente = document.querySelector("#cliente").value;
    let xhr;
    if (window.XMLHttpRequest)
        xhr = new XMLHttpRequest();
    else
        xhr = new ActiveXObject("Microsoft.XMLHTTP");

    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            let data = JSON.parse(xhr.responseText);
            console.log(data);
            console.log(xhr.responseText);
            let select = document.formCadastroTransacao.programa;

            select.innerHTML = "";
            data.forEach(function (programa) {
                let opcao = document.createElement("option");
                opcao.value = programa.id;
                opcao.textContent = programa.nome;
                select.appendChild(opcao);
            });
        }
        console.log(this.readyState);
        console.log(this.status);
    };

    let parameters = "idCliente=" + encodeURIComponent(idCliente);
    xhr.open("POST", "/sam/PesquisarProgramasAjax", true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.send(parameters);
}