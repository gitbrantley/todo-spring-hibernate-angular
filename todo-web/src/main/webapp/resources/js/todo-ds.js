


var todoApp = angular.module('todoApp', []);

todoApp.controller('TodoControl', function($scope, $http) {
	
	$scope.todos = [];
	
	$http.get('todos').success(function(json) {
		if (json && json.data) {
			$scope.todos = json.data;
		}
	});
	
	$scope.newTodo = function() {
		$scope.todos.push({name:'New Todo List', id: 0, items:[], addItem:function(){console.log('arg')}});
	}
	
	$scope.addTodo = function(todo) {
		todo.items.push({id: 0, value: ''})
	}
		
	$scope.update = function() {
		$http.put('todos', $scope.todos).success(function(json) {
			if (json && json.message) {
				alert(json.message);
			}
		});
	}
});