angular.module('navigation', ['ngRoute', 'auth']).controller(
		'navigation',

		function($scope, $route, auth) {

			$scope.credentials = {};

			$scope.tab = function(route) {
				return $route.current && route === $route.current.controller;
			};

			$scope.authenticated = function() {
				return auth.authenticated;
			};
                        
                        $scope.admin = function() {
				return auth.isAdmin;
			};

			$scope.login = function() {
				auth.authenticate($scope.credentials, function(authenticated) {
					if (authenticated) {
						console.log("Login succeeded")
						$scope.error = false;
                                                $("#login-modal").modal('hide');
                                                location.reload();
					} else {
						console.log("Login failed")
						$scope.error = true;
					}
				})
			};

                        $scope.logout = auth.clear;

		});
