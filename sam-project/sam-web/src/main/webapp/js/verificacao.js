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

//Desaparecer input recuperaçao de dados

document.getElementById("verifyForm").addEventListener("submit", function (e) {
    let inputs = document.querySelectorAll(".code-input input");
    let codigo = "";

    inputs.forEach(i => codigo += i.value);
        document.getElementById("codigoFinal").value = codigo;
});

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
