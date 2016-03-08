angular.module('home', []).controller('home', function($scope, $http) {
	$http.get('/resource/user/current').success(function(data) {
		$scope.user = data;
	});
});
