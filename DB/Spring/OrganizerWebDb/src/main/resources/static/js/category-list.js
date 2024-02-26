function getCategories(divId) {
    let xhttp = new XMLHttpRequest();

    var div = document.getElementById(divId);
    var divRemoveCategory = document.getElementById("removeCategoryDiv");

    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {

            var categories = JSON.parse(this.responseText);

            if (categories == null || categories.length === 0) {
                div.innerHTML = "No categories found";
                divRemoveCategory.style.display = "none";
                return;
            }

            divRemoveCategory.style.display = "block";
            div.style.display = "block";
            var list = "<h2>Categories</h2>";
            list += "<ul>";
            for (category of categories) {
                list += "<li>" + category.categoryName + "</li>";
            }
            list += "</ul>";
            div.innerHTML = list;
        }
    };

    xhttp.open("GET", "/organizerwebdb/categories", true);
    xhttp.send();
}

function removeCategory(categoryId) {
    let xhttp = new XMLHttpRequest();

    id = document.getElementById(categoryId).value;

    xhttp.onload = function () {
        if (this.status !== 200) {
            alert(`Category \"${id}\" not found`)
        } else {
            location.reload();
        }

    };

    xhttp.open("DELETE", "/organizerwebdb/delete-category/" + id, true);
    xhttp.send();
}
