const tipoInputEl = document.querySelector(".tipo-input");
const bonusEl = document.querySelector(".bonus");
const dataExpiracaoEl = document.querySelector(".data-expiracao");

tipoInputEl.addEventListener("change", () => {
    if (tipoInputEl.value === "COMPRA") {
        bonusEl.style.display = "block";
        let amanha = new Date(new Date().setDate(new Date().getDate() + 1)).toISOString().split('T')[0]
        dataExpiracaoEl.innerHTML = `Data de Expiração:<input name=\"dataExpiracao\" type=\"date\" value=\"${amanha}\" required>`;
    } else {
        bonusEl.style.display = "none";
        dataExpiracaoEl.innerHTML = "";
    }
});