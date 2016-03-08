angular.module('users', []).controller('users', function($scope, $http) {
	$http.get('/resource/user/all').success(function(data) {
		$scope.users = data;
	});
});
