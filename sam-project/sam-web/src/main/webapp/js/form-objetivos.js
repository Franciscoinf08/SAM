document.addEventListener('DOMContentLoaded', function() {

    const form = document.getElementById('formObjetivos');

    if (form) {

        form.addEventListener('submit', function(event) {

            event.preventDefault();

            const campos = form.querySelectorAll('input:not([type="submit"]):not([type="button"]):not([type="radio"]):not([type="checkbox"]), textarea');
            let formValido = true;
            let camposVaziosCount = 0;

            campos.forEach(campo => {
                if (campo.offsetParent !== null) {
                    const valor = campo.value.trim();

                    if (valor === '') {
                        formValido = false;
                        camposVaziosCount++;
                        campo.classList.add('campo-invalido');
                    } else {
                        campo.classList.remove('campo-invalido');
                    }
                }
            });

            if (formValido) {
                form.submit();
            } else {
                alert(`${camposVaziosCount} campos estão vazios, preencha os campos vazios antes de continuar.`);
            }
        });
    }
});