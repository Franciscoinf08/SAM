const searchInput = document.getElementById('searchInput');
const itemsList = document.getElementById('itemsList');
const items = itemsList.getElementsByTagName('li');

for (let i = 0; i < items.length; i++) {
    items[i].classList.add('hidden');
}

searchInput.addEventListener('keyup', function() {
    const filter = searchInput.value.toLowerCase().trim();

    if (filter === "") {
        for (let i = 0; i < items.length; i++) {
            items[i].classList.add('hidden');
        }
        return;
    }

    for (let i = 0; i < items.length; i++) {
        const item = items[i];
        const text = item.textContent || item.innerText;

        if (text.toLowerCase().indexOf(filter) > -1) {
            item.classList.remove('hidden');
        } else {
            item.classList.add('hidden');
        }
    }
});
