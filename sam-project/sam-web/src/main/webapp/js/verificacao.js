//VISUAL---VISUAL---VISUAL

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

//VISUAL---VISUAL---VISUAL