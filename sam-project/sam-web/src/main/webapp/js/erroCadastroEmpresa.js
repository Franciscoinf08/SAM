let cnpj = document.getElementById("cpnj");

function validarCnpj(cnpj) {
    if (!cnpj) return false;

    cnpj = cnpj.replace(/\D/g, '');


    if (cnpj.length !== 14) return false;

    if (/^(\d)\1{13}$/.test(cnpj)) return false;

    let tamanho = cnpj.length - 2;
    let numeros = cnpj.substring(0, tamanho);
    let digitos = cnpj.substring(tamanho);
    let soma = 0;
    let pos = tamanho - 7;

    for (let i = tamanho; i >= 1; i--) {
        soma += numeros.charAt(tamanho - i) * pos--;
        if (pos < 2) pos = 9;
    }

    let resultado = soma % 11 < 2 ? 0 : 11 - (soma % 11);
    if (resultado !== digitos.charAt(0)) return false;

    tamanho = tamanho + 1;
    numeros = cnpj.substring(0, tamanho);
    soma = 0;
    pos = tamanho - 7;
    for (let i = tamanho; i >= 1; i--) {
        soma += numeros.charAt(tamanho - i) * pos--;
        if (pos < 2) pos = 9;
    }

    resultado = soma % 11 < 2 ? 0 : 11 - (soma % 11);
    return resultado === digitos.charAt(1);
}


document.addEventListener("DOMContentLoaded", () => {
    const form = document.querySelector("form");
    const inputCnpj = document.querySelector("input[name='cnpj']");

    form.addEventListener("submit", (e) => {
        const cnpj = inputCnpj.value;
        if (!validarCnpj(cnpj)) {
            e.preventDefault(); // impede envio
            alert("CNPJ inválido. Verifique e tente novamente.");
            inputCnpj.focus();
        }
    });
});
document.getElementById("cnpj").addEventListener("input", function(e) {
    let v = e.target.value.replace(/\D/g, "");
    v = v.replace(/^(\d{2})(\d)/, "$1.$2");
    v = v.replace(/^(\d{2})\.(\d{3})(\d)/, "$1.$2.$3");
    v = v.replace(/\.(\d{3})(\d)/, ".$1/$2");
    v = v.replace(/(\d{4})(\d)/, "$1-$2");
    e.target.value = v;
});