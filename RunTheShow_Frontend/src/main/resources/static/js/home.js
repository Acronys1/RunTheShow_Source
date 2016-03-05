angular.module('hello').controller('home', function($scope, $http) {
	$http.get('resource/user/current').success(function(data) {
		$scope.greeting = data;
	})
});