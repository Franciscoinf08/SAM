function validarCNPJ(cnpj) {
    if (!cnpj) return false;

    cnpj = cnpj.replace(/\D/g, '');

    if (cnpj.length !== 14) return false;

    if (/^(\d)\1{13}$/.test(cnpj)) return false;


    let soma = 0;
    let peso = 5;

    for (let i = 0; i < 12; i++) {
        soma += parseInt(cnpj.charAt(i)) * peso;
        peso--;
        if (peso < 2) peso = 9;
    }

    let dig1 = soma % 11;
    dig1 = dig1 < 2 ? 0 : 11 - dig1;

    soma = 0;
    peso = 6;

    for (let i = 0; i < 13; i++) {
        soma += parseInt(cnpj.charAt(i)) * peso;
        peso--;
        if (peso < 2) peso = 9;
    }

    let dig2 = soma % 11;
    dig2 = dig2 < 2 ? 0 : 11 - dig2;


    return dig1 === parseInt(cnpj.charAt(12)) &&
        dig2 === parseInt(cnpj.charAt(13));
}

