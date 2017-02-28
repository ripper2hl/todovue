//public/main.js

var angularTodo = angular.module('angularTodo', []);

function mainController($scope, $http) {
	var apiUrl = 'api/v1/todo/';
	$scope.formData = {};

	// Cuando se cargue la página, pide del API todos los TODOs
	$http.get(apiUrl + '?page=0&size=1000')
		.success(function(data) {
			$scope.todos = data.content;
			console.log(data)
		})
		.error(function(data) {
			console.log('Error: ' + data);
		});

	// Cuando se añade un nuevo TODO, manda el texto a la API
	$scope.createTodo = function(){
		$http.post(apiUrl, $scope.formData)
			.success(function(data) {
				$scope.formData = {};
				$scope.todos.push(data);
				console.log(data);
			})
			.error(function(data) {
				console.log('Error:' + data);
			});
	};

	// Borra un TODO despues de checkearlo como acabado
	$scope.deleteTodo = function(id) {
		$http.delete(apiUrl + id)
			.success(function(data) {
				findAndRemove($scope.todos, 'id', data.id);
				console.log(data);
			})
			.error(function(data) {
				console.log('Error:' + data);
			});
	};
}

function findAndRemove(array, property, value) {
	  array.forEach(function(result, index) {
	    if(result[property] === value) {
	      array.splice(index, 1);
	    }
	  });
	}
