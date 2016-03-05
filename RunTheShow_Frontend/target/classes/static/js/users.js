angular.module('hello').controller('users', function($scope, $http) {
	$http.get('resource/user/current').success(function(data) {
                cache: true;
		$scope.greeting = data;
	})
        
        $http.get('resource/user/all').success(function(data) {
                cache: true;
		$scope.allusers = data;
	})
});
