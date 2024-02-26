function addItem(newName, newDescription, newCategory) {
    let xhttp = new XMLHttpRequest();

    var name = document.getElementById(newName).value;
    var description = document.getElementById(newDescription).value;
    var category = document.getElementById(newCategory).value;

    xhttp.onload = function () {
        if (this.status !== 200) {
            alert('Error: Item not added')
        }
    }

    xhttp.open("POST", "organizerwebdb?name=" + name + "&description=" + description + "&category=" + category, true);
    xhttp.send();
}

function deleteItem(itemId) {
    let xhttp = new XMLHttpRequest();

    id = document.getElementById(itemId).value;

    xhttp.onload = function () {
        if (this.status !== 200) {
            alert(`Item with id = ${id} not found`);
        }
    }

    xhttp.open("POST", "organizerwebdb?remove=" + id, true);
    xhttp.send();
}

function updateItem(itemId, newName, newDescription, newCategory) {
    let xhttp = new XMLHttpRequest();

    var id = document.getElementById(itemId).value;
    var name = document.getElementById(newName).value;
    var description = document.getElementById(newDescription).value;
    var category = document.getElementById(newCategory).value;

    xhttp.onload = function () {
        if (this.status !== 200) {
            alert(`Item with id = ${id} not found`);
        }
    };

    xhttp.open("POST",
        "organizerwebdb?edit=" + id +
        "&updated-name=" + name +
        "&updated-description=" + description
        + "&updated-category=" + category
        , true);
    xhttp.send();
}

function getItem(itemId, result) {
    let xhttp = new XMLHttpRequest();
    var id = document.getElementById(itemId).value;
    var itemText = document.getElementById(result);

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {

            itemText.style.display = "block";
            itemText.innerHTML = this.responseText;
        } else if (this.readyState === 4 && this.status === 404) {
            alert(`Item with id = ${id} not found`);
            itemText.style.display = "none";
        }
    };

    xhttp.open("GET", "organizerwebdb?id=" + id, true);
    xhttp.send();
}

function clearList() {
    let xhttp = new XMLHttpRequest();

    xhttp.onload = function () {
        if (this.status !== 200) {
            alert('Error: Items not deleted')
        }
    }

    xhttp.open("GET", "organizerwebdb?clear", true);
    xhttp.send();
}

function hideItem(result) {
    var item = document.getElementById(result);
    item.style.display = "none";
}

function setColor(color) {
    var xhttp = new XMLHttpRequest();
    if (color == null) {
        color = "";
    }

    xhttp.onload = function () {
        if (this.status === 200) {
            newColor = xhttp.responseText;
            document.body.style.backgroundColor = newColor;
        }
    };

    xhttp.open("GET", "organizerwebdb?backgroundColor=" + color, true);
    xhttp.send();
}

function setFont(fontFamily) {
    var xhttp = new XMLHttpRequest();
    if (fontFamily == null) {
        fontFamily = "";
    }

    xhttp.onload = function () {
        if (this.status === 200) {
            newfontFamily = xhttp.responseText;
            document.body.style.fontFamily = newfontFamily;
        }
    };

    xhttp.open("GET", "organizerwebdb?fontFamily=" + fontFamily, true);
    xhttp.send();
}

function removeCategory(categoryId) {
    let xhttp = new XMLHttpRequest();

    id = document.getElementById(categoryId).value;

    xhttp.onload = function () {
        if (this.status !== 200) {
            alert(`Category \"${id}\" cannot be removed`);
        }
    }

    xhttp.open("GET", "organizerwebdb/category?remove=" + id, true);
    xhttp.send();
}