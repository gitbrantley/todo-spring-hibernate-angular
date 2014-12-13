


var todoApp = angular.module('todoApp', []);

todoApp.controller('TodoControl', function($scope, $http) {
	
	$scope.todos = [];
	
	$http.get('todos').success(function(json) {
		if (json && json.data) {
			$scope.todos = json.data;
		}
	});
	
	$scope.newTodo = function() {
		$scope.todos.push({name:'', id: 0, items:[], addItem:function(){console.log('arg')}});
	}
	
	$scope.addTodo = function(todo) {
		todo.items.push({id: 0, value: ''})
	}
		
	$scope.save = function() {
		$http.put('todos', $scope.todos).success(function(json) {
			if (json && json.message) {
				alert(json.message);
			}
		});
	}
	
	$scope.deleteItem = function(todo, item) {
		var items = todo.items;
		for (var i=0; i < items.length; i+=1) {
			if (items[i] === item) {
				items.splice(i, 1);
				break;
			}
		}
	}
});