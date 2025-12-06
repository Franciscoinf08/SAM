document.addEventListener("DOMContentLoaded", function (e) {
    e.preventDefault();

    const links = document.querySelectorAll(".cancelarLink");

    links.forEach(link => {
        link.addEventListener("click", function (event) {
            event.preventDefault(); // Impede que o <a> abra imediatamente
            
            if (confirm("Tem certeza que deseja cancelar?")) {
                window.location.href = this.href; // Agora sim redireciona
            }
        });
    });

});

function emailEnviado(sucesso){
    if (status === "ok") {
        alert("Operação realizada com sucesso!");
    }
    if (status === "erro") {
        alert("Erro ao realizar a operação.");
    }
}