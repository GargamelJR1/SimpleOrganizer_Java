<html>
<head>
    <title>OrganizerWebDb</title>
    <script type="text/javascript" src="js/organizerwebdb.js"></script>
</head>

<body onload="setColor(); setFont()">
<h1> OrganizerWeb </h1>
<br/>

<a href="organizerwebdb?list">Show items</a>
<br>

<a href="organizerwebdb/category?list">Show categories</a>
<br>

<h3>Add new item</h3>
<input id="newName" type="text" name="name" placeholder="Name"/>
<input id="newDescription" type="text" name="description" placeholder="Description"/>
<input id="newCategory" type="text" name="category" placeholder="Category"/>
<br>
<button onclick="addItem('newName','newDescription','newCategory')">Add item</button>
<br>

<h3>Remove item</h3>
<input id="removeId" type="text" name="id" placeholder="Id"/>
<br>
<button onclick="deleteItem('removeId')">Remove item</button>
<br>

<h3>Update item</h3>
<input id="updateId" type="text" name="id" placeholder="Id"/>
<input id="updateName" type="text" name="name" placeholder="Name"/>
<input id="updateDescription" type="text" name="description" placeholder="Description"/>
<input id="updateCategory" type="text" name="category" placeholder="Category">
<br>
<button onclick="updateItem('updateId','updateName','updateDescription','updateCategory')">Update item</button>
<br>

<h3>Show item</h3>
<input id="getId" type="text" name="id" placeholder="Id"/>
<br>
<button onclick="getItem('getId','showItem')">Show item</button>
<button onclick="hideItem('showItem')">Hide</button>
<div style="display: none" id="showItem">
</div>
<br>

<h3>Clear list</h3>
<button onclick="clearList()">Clear list</button>

<br>
<br>

<div id="removeCategoryDiv">
    <br>
    Remove Category: <input type="text" id="removeCategory" name="removeCategory" placeholder="category">
    <button onclick="removeCategory('removeCategory')">Remove</button>
</div>

<br>
<br>
All data above is read from the database. No data is hard-coded in the HTML or saved in cookies.
<br>
Data below is read from cookies.

<br>
<br>

<h3>Change background color</h3>
<button onClick="setColor('white')">White</button>
<button onClick="setColor('red')">Red</button>
<button onClick="setColor('green')">Green</button>
<button onClick="setColor('blue')">Blue</button>

<h3>Change font style</h3>
<button onClick="setFont('serif')">Serif</button>
<button onClick="setFont('sans-serif')">Sans-serif</button>
<button onClick="setFont('monospace')">Monospace</button>
<button onClick="setFont('cursive')">Cursive</button>

</body>
</html>