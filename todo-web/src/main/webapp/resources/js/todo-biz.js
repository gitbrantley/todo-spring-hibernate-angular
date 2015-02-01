


var todoBiz = angular.module('todoServices', []);





todoBiz.factory('Security', function($http) {
	var $scope = {};
	$scope.login = function(username, password, goodFun, badFun) {
		goodFun = goodFun || function(){};
		badFun = goodFun || function(){};
		//var body = "j_username="+username+"&j_password="+password+"&submit=Login";
		var body = "j_username=brantley&j_password=brantley&submit=Login";
		var ajax = {
			method: 'POST',
			url: 'j_spring_security_check',
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
			},
			data: body
		};
		$http(ajax).
			success(function(data, status, headers, config) {
				goodFun();
			}).
			error(function(data, status, headers, config) {
				badFun();
			});
	};
	
	$scope.logout = function(goodFun, badFun) {
		goodFun = goodFun || function(){};
		badFun = goodFun || function(){};
		$http.get('logout').
			success(function(data, status, headers, config) {
				goodFun();
			}).
			error(function(data, status, headers, config) {
				badFun();
			});
	}
	
	$scope.test = function(authedFun, unauthedFun) {
		authedFun = authedFun || function(){};
		unauthedFun = unauthedFun || function(){};
		$http.get('rest/todos').
			success(function(data, status, headers, config) {
				if (typeof(data.indexOf) == "function" && data.indexOf('Login with Username and Password') > -1) {
					unauthedFun();
				} else {
					authedFun();
				}
				//console.log("success test ", data);
			}).
			error(function(data, status, headers, config) {
				unauthedFun();
			});
	}
	
	return $scope;
});








