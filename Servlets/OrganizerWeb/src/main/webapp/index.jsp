<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>OrganizerWeb</title>
    <script type="text/javascript" src="js/index.js"></script>
</head>

<body id="body" onload="setColor('body'); setFont('body'); setSessionStarted()">
<h1> OrganizerWeb </h1>
<br>

<a href="item-list?list">Show items</a>
<br>

<h3>Add new item</h3>
<input id="newName" type="text" name="name" placeholder="Name"/>
<input id="newDescription" type="text" name="description" placeholder="Description"/>
<button onclick="addItem('newName','newDescription')">Add item</button>
<br>
<h3>Remove item</h3>
<input id="removeId" type="number" name="id" placeholder="Id"/>
<button onclick="deleteItem('removeId')">Remove item</button>
<br>

<h3>Update item</h3>
<input id="updateId" type="number" name="id" placeholder="Id"/>
<input id="updateName" type="text" name="name" placeholder="Name"/>
<input id="updateDescription" type="text" name="description" placeholder="Description"/>
<button onclick="updateItem('updateId','updateName','updateDescription')">Update item</button>
<br>

<h3>Show item</h3>
<input id="getId" type="number" name="id" placeholder="Id"/>
<button onclick="getItem('getId','showItem')">Show item</button>
<div style="display: none" id="showItem">
</div>
<br>

<h3>Clear list</h3>
<button onclick="clearList()">Clear list</button>
<br>
<br>

<div style="display: none" id="logs">
</div>
<button onclick="showLogs()">Show logs</button>
<button onclick="function hideLogs() {
    document.getElementById('logs').style.display = 'none';
}
hideLogs()">Hide logs
</button>
<br>
<br>

<h3>Change background color</h3>
<button onClick="setColor('body', 'white')">White</button>
<button onClick="setColor('body', 'red')">Red</button>
<button onClick="setColor('body', 'green')">Green</button>
<button onClick="setColor('body', 'blue')">Blue</button>

<h3>Change font style</h3>
<button onClick="setFont('body', 'serif')">Serif</button>
<button onClick="setFont('body', 'sans-serif')">Sans-serif</button>
<button onClick="setFont('body', 'monospace')">Monospace</button>
<button onClick="setFont('body', 'cursive')">Cursive</button>

<br>
<br>
<div style="display: none" id="sessionStartedDiv"></div>

</body>
</html>