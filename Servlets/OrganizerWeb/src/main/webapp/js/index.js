function addItem(newName, newDescription) {
    var xhttp = new XMLHttpRequest();

    var name = document.getElementById(newName).value;
    var description = document.getElementById(newDescription).value;

    xhttp.open("POST", "item-list?name=" + name + "&description=" + description, true);
    xhttp.send();
}

function deleteItem(removeItemId) {
    var xhttp = new XMLHttpRequest();

    var id = document.getElementById(removeItemId).value;

    xhttp.onload = function () {
        if (this.status === 404) {
            alert("Item not found")
        } else if (this.status === 200) {
            alert(`Item with id = ${id} deleted`);
        }
    };

    xhttp.open("POST", "item-list?remove=" + id, true);
    xhttp.send();
}

function updateItem(updateItemId, newName, newDescription) {
    var xhttp = new XMLHttpRequest();

    var id = document.getElementById(updateItemId).value;
    var name = document.getElementById(newName).value;
    var description = document.getElementById(newDescription).value;

    xhttp.onload = function () {
        if (this.status === 404) {
            alert("Item not found")
        } else if (this.status === 200) {
            alert(`Item with id = ${id} updated`);
        }
    };

    xhttp.open("POST", "item-list?edit=" + id + "&updated-name=" + name + "&updated-description=" + description, true);
    xhttp.send();
}

function getItem(getItemId, result) {
    var xhttp = new XMLHttpRequest();

    var id = document.getElementById(getItemId).value;
    var itemText = document.getElementById(result);

    xhttp.onload = function () {
        if (this.status === 404) {
            alert(`Item with id = ${id} not found`);
        } else if (this.status === 200) {
            itemText.style.display = "block";
            itemText.innerHTML = this.responseText;
        }
    };

    xhttp.open("GET", "item-list?id=" + id, true);
    xhttp.send();
}

function clearList() {
    var xhttp = new XMLHttpRequest();

    xhttp.open("POST", "item-list?clear", true);
    xhttp.send();
}

function showLogs() {
    var xhttp = new XMLHttpRequest();

    var logs = document.getElementById("logs");
    logs.style.display = "block";

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            if (this.responseText != null) {
                logs.innerHTML = this.responseText;
            } else {
                logs.innerHTML = "There are no logs";
            }
        }
    };

    xhttp.open("GET", "item-list-logs", true);
    xhttp.send();
}

function setColor(id, color) {
    var xhttp = new XMLHttpRequest();
    if (color == null) {
        color = "";
    }

    xhttp.onload = function () {
        if (this.status === 200) {
            newColor = xhttp.responseText;
            element = document.getElementById(id);
            element.style.backgroundColor = newColor;
        }
    };

    xhttp.open("GET", "item-list?background-color=" + color, true);
    xhttp.send();
}

function setFont(id, fontFamily) {
    var xhttp = new XMLHttpRequest();
    if (fontFamily == null) {
        fontFamily = "";
    }

    xhttp.onload = function () {
        if (this.status === 200) {
            newfontFamily = xhttp.responseText;
            element = document.getElementById(id);
            element.style.fontFamily = newfontFamily;
        }
    };

    xhttp.open("GET", "item-list?font-family=" + fontFamily, true);
    xhttp.send();
}

function setSessionStarted() {
    var xhttp = new XMLHttpRequest();

    xhttp.onload = function () {
        if (this.status === 200) {
            sessionStartedDateTime = xhttp.responseText;
            element = document.getElementById("sessionStartedDiv");
            element.style.display = "block";
            element.innerHTML = "Session started: " + sessionStartedDateTime;
        }
    };

    xhttp.open("GET", "item-list?session-started=)", true);
    xhttp.send();
}