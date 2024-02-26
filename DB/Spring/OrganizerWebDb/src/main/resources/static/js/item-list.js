function getItems(divId) {
    let xhttp = new XMLHttpRequest();

    var div = document.getElementById(divId);

    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {

            var items = JSON.parse(this.responseText);

            if (items == null || items.length === 0) {
                div.innerHTML = "No items found";
                return;
            }

            div.style.display = "block";
            var table = "<h2>Items</h2>";
            table += "<table border='1'><tr><th>ID</th><th>Name</th><th>Description</th><th>Category</th></tr>";
            for (item of items) {
                table += "<tr><td>" + item.id + "</td><td>" + item.name + "</td><td>"
                    + item.description + "</td><td>"
                    + (item.category == null ? "-" : item.category.categoryName)
                    + "</td></tr>";
            }
            table += "</table>";
            div.innerHTML = table;
        }
    };

    xhttp.open("GET", "/organizerwebdb/items", true);
    xhttp.send();
}
