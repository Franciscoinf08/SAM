const tipoInputEl = document.querySelector(".tipo-input");
const bonusEl = document.querySelector(".bonus");
const dataExpiracaoEl = document.querySelector(".data-expiracao");

tipoInputEl.addEventListener("change", () => {
    if (tipoInputEl.value === "COMPRA") {
        bonusEl.style.display = "block";
        dataExpiracaoEl.innerHTML = "<input name=\"dataExpiracao\" type=\"date\" value=\"<%= LocalDate.now().plusDays(1).toString() %>\" required>"
    } else {
        bonusEl.style.display = "none";
        dataExpiracaoEl.innerHTML = "";
    }
});