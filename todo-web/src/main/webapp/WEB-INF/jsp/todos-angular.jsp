<!DOCTYPE html>
<html ng-app="todoApp">
<head>
	<title>Todos in Angular.js</title>
	<script type="text/javascript" src="resources/js/lib/jquery-2.1.0.js"></script>
	<script type="text/javascript" src="resources/js/lib/angular.js"></script>
	<script type="text/javascript" src="resources/js/todo-ds.js"></script>
</head>
<body ng-controller="TodoControl">

	<ul>
		<li ng-repeat="todo in todos">
			<input type="text" ng-model="todo.name" />
			<button ng-click="addTodo(todo)">Add Item</button>
			<ol ng-if="todo.items.length > 0">
				<li ng-repeat="item in todo.items">
					<input type="text" ng-model="item.value"/>
				</li>
			</ol>
		</li>
	</ul>
	<button ng-click="newTodo()">New Todo</button>
	<button ng-click="update()">Update</button>

</body>
</html>