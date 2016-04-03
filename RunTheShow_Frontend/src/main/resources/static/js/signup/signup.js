angular.module('signup', []).controller('signup', function ($scope, $http, $rootScope, $window) {
    $scope.signUp = function () {
        console.log("test signup");
        $window.alert($scope.newUser.submitForm);
    }
});

