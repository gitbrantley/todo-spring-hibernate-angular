<!DOCTYPE html>
<html ng-app="todoApp">
<head>
	<title>Todos in Angular.js</title>
	
	<!-- <link rel="stylesheet" type="text/css" href="resources/lib/bootstrap/css/bootstrap.min.css"> -->
	<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="resources/css/style.css">
	<script type="text/javascript" src="resources/lib/jquery-2.1.0.js"></script>
	<!-- <script type="text/javascript" src="resources/lib/bootstrap/js/bootstrap.min.js"></script> -->
	<script type="text/javascript" src="resources/lib/angular.js"></script>
	<script type="text/javascript" src="resources/lib/ui-bootstrap-tpls-0.12.0.js"></script>
	<script type="text/javascript" src="resources/js/todo-biz.js"></script>
	<script type="text/javascript" src="resources/js/todo-ds.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h1>Todos</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12" ng-controller="TodoControl">
				<button ng-click="saveOrLogin()" class="save">{{slbText}}</button>
				<ul class="todos">
					<li ng-repeat="todo in todos" class="todo-container">
						<input type="text" ng-model="todo.name" class="todo-name"/>
						<ol ng-if="todo.items.length > 0" class="todo-inner-list">
							<li ng-repeat="item in todo.items">
								<input type="text" ng-model="item.value" class="item"/>
								<button ng-click="deleteItem(todo, item)" class="delete-item">X</button>
							</li>
						</ol>
						<button ng-click="addTodo(todo)" class="add-item">Add Item</button>
					</li>
				</ul>
				<button ng-click="newTodo()" class="wide-button new-todo">New Todo</button>
				<button ng-click="save()" class="wide-button save">{{slbText}}</button>
				
				<button ng-click="logout()" class="wide-button logout" ng-display="showLogout">Logout</button>
			</div>
		</div>
	</div>
	    <script type="text/ng-template" id="login.html">
	        <div class="modal-header">
	            <h3 class="modal-title">Please Login</h3>
	        </div>
	        <div class="modal-body">
				<table>
	           	<tr>
					<td>Username:</td>
					<td><input type="text" ng-bind="username"/></td>
				</tr><tr>
					<td>Password:</td>
					<td><input type="password" ng-bind="password"/></td>
				</tr>
				</table>
	        </div>
	        <div class="modal-footer">
	            <button class="btn btn-primary" ng-click="login()">Login</button>
	            <button class="btn btn-warning" ng-click="cancel()">Cancel</button>
	        </div>
    	</script>
</body>
</html>