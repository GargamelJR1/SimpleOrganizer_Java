function addItem(newName, newDescription, newCategory) {
    let xhttp = new XMLHttpRequest();

    var name = document.getElementById(newName).value;
    var description = document.getElementById(newDescription).value;
    var category = document.getElementById(newCategory).value;

    xhttp.onload = function () {
        if (this.status !== 201) {
            alert('Error: Item not added')
        }
    }

    xhttp.open("POST", "/organizerwebdb/add-item", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("name=" + name + "&description=" + description + "&category=" + category);
}

function deleteItem(itemId) {
    let xhttp = new XMLHttpRequest();

    id = document.getElementById(itemId).value;

    xhttp.onload = function () {
        if (this.status !== 200) {
            alert(`Item with id = ${id} not found`);
        }
    }

    xhttp.open("DELETE", "/organizerwebdb/delete-item/" + id, true);
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

    xhttp.open("PUT", "/organizerwebdb/update-item/" + id, true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("name=" + name + "&description=" + description + "&category=" + category);
}

function getItem(itemId, result) {
    let xhttp = new XMLHttpRequest();
    var id = document.getElementById(itemId).value;
    var itemText = document.getElementById(result);

    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status === 200) {

            var item = JSON.parse(this.responseText);

            if (item == null) {
                alert(`Item with id = ${id} not found`);
                itemText.style.display = "none";
                return;
            }

            itemText.style.display = "block";
            itemText.innerHTML =
                "Name: " + "<br>" + item.name + "<br>" +
                "Description: " + "<br>" + item.description + "<br>" +
                "Category: " + "<br>" + (item.category == null ? "-" : item.category.categoryName);
        } else if (this.readyState == 4 && this.status === 404) {
            alert(`Item with id = ${id} not found`);
            itemText.style.display = "none";
        }
    };

    xhttp.open("GET", "/organizerwebdb/item/" + id, true);
    xhttp.send();
}

function clearList() {
    let xhttp = new XMLHttpRequest();

    xhttp.onload = function () {
        if (this.status !== 200) {
            alert('Error: Items not deleted')
        }
    }

    xhttp.open("DELETE", "/organizerwebdb/delete-all-items", true);
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

    xhttp.open("POST", "/organizerwebdb/background-color", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("color=" + color);
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

    xhttp.open("POST", "/organizerwebdb/font-family", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("font=" + fontFamily);
}