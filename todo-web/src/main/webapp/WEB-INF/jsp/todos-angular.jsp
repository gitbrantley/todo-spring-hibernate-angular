<!DOCTYPE html>
<html ng-app="todoApp">
<head>
	<title>Todos in Angular.js</title>
	<link rel="stylesheet" type="text/css" href="resources/lib/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="resources/css/style.css">
	<script type="text/javascript" src="resources/lib/jquery-2.1.0.js"></script>
	<script type="text/javascript" src="resources/lib/angular.js"></script>
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
				<button ng-click="save()" class="save">Save</button>
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
				<button ng-click="newTodo()" class="new-todo">New Todo</button>
				<button ng-click="save()" class="save">Save</button>
			</div>
		</div>
	</div>
</body>
</html>