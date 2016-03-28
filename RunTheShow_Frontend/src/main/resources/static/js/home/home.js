angular.module('home', []).controller('home', function ($scope, $http) {

    $scope.initFirstHome = function ()
    {
        $http.get('/resource/user/current').success(function (data) {
            $scope.user = data;
        });
    }
});
