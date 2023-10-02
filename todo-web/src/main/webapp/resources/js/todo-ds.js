


var todoApp = angular.module('todoApp', ['todoServices', 'ui.bootstrap']);



todoApp.controller('TodoControl', function($scope, $http, $modal, Security) {
	
	$scope.todos = [];
	$scope.slbText = "Login";
	$scope.showLogout = false;
	
	function setSlbText(text) {
		console.log('setting slb text to '+text);
		$scope.slbText = text;
	}
	
	Security.test(function(){
		setSlbText("Save");
		loadTodos();
	});
	
	$scope.saveOrLogin = function() {
		if ($scope.slbText === "Login") {
			doLogin();
		} else {
			$scope.save();
		}
	}
	
	$scope.logout = function() {
		Security.logout(function(){
			$scope.todos = [];
			$scope.slbText = "Login";
			$scope.showLogout = false;
		});
	}
	
	function doLogin() {
		var modalInstance = $modal.open({
			templateUrl: 'login.html',
			controller: 'LoginControl',
			size: 'sm',
			resolve: {}
		});

		modalInstance.result.then(function (result) {
			if (result === "success") {
				setSlbText("Save");
				loadTodos();
				$scope.showLogout = true;
			} else {
				setSlbText($scope.slbText+"-");
			}
		}, function () {
			// no-op
		});
	}
	
	function loadTodos() {
		$http.get('rest/todos').success(function(json) {
			if (json && json.data) {
				if ($scope.todos.length > 0) {
					var list = json.data;
					for (var i=0; i < list.length; i+=1) {
						$scope.todos.push(list[i]);
					}
				} else {
					$scope.todos = json.data;
				}
			}
		});
	}
	
	$scope.newTodo = function() {
		$scope.todos.push({name:'', id: 0, items:[], addItem:function(){console.log('arg')}});
	}
	
	$scope.addTodo = function(todo) {
		todo.items.push({id: 0, value: ''})
	}
		
	$scope.save = function() {
		var todos = $scope.todos;
		for (var i=0; i < todos.length; i+=1) {
			var td = todos[i];
			for (var k=0; k < td.items.length; k+=1) {
				td.items[k].ordering = k+1;
			}
		}
		$http.put('rest/todos', $scope.todos).success(function(json) {
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
				// TODO: delete from server
				break;
			}
		}
	}
});

todoApp.controller('LoginControl', function($scope, Security, $modalInstance) {
	$scope.username = '';
	$scope.password = '';
	
	$scope.login = function() {
		var success = function() {
			$modalInstance.close("success"); // TODO: Pass in some callback via $modal.open({resolve:})
		};
		var failure = function() {
			$modalInstance.close("failure");
		};
		Security.login($scope.username, $scope.password, success, failure);
	};
	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	}
});