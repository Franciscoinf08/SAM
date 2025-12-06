const inputs = document.querySelectorAll(".code-input input");

inputs[0].focus();

inputs.forEach((input, index) => {
    input.addEventListener("input", () => {
        if (input.value.length === 1) {
            if (index < inputs.length - 1) {
                inputs[index + 1].focus();
            }
        }
    });

    input.addEventListener("keydown", (e) => {
        if (e.key === "Backspace" && input.value === "" && index > 0) {
            inputs[index - 1].focus();
        }
    });
});

function handleSubmit(e) {
    e.preventDefault();

    let code = "";
    inputs.forEach(i => code += i.value);

    if (code.length !== 6) {
        alert("Digite os 6 dígitos.");
        return false;
    }
    return false;
}

document.getElementById("verifyForm").addEventListener("submit", function (e) {
    let inputs = document.querySelectorAll(".code-input input");
    let codigo = "";

    inputs.forEach(i => codigo += i.value);
        document.getElementById("codigoFinal").value = codigo;
});

//Desaparecer input recuperaçao de dados

const formEmail = document.getElementById("formEmail");
const formCodigo = document.getElementById("formCodigo");

formEmail.addEventListener("submit", function (e) {
    e.preventDefault();

    const email = formEmail.querySelector("input[name='email']").value;
    if (email.trim() === "") {
        alert("Digite um e-mail válido.");
        return;
    }

    formEmail.style.display = "none";

    formCodigo.style.display = "block";

    const codeInputs = document.querySelectorAll(".code-input-recuperar input");
    if (codeInputs.length > 0) {
        codeInputs[0].focus();
    }

    formEmail.submit();
});


document.getElementById("formAlterar").addEventListener("submit", function(e) {
    const s1 = document.getElementById("senha1").value;
    const s2 = document.getElementById("senha2").value;
    const erro = document.getElementById("erroSenha");

    if (s1 !== s2) {
        e.preventDefault();
        erro.style.display = "block";
    } else {
        erro.style.display = "none";
    }
});