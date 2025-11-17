const tipoInputEl = document.querySelector(".tipo-input");
const bonusInputEl = document.querySelector(".bonus-input");

tipoInputEl.addEventListener("change", () => {
    if (tipoInputEl.value === "COMPRA")
        bonusInputEl.style.display = "block";
    else
        bonusInputEl.style.display = "none";
});