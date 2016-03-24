angular.module('users', []).controller('users', function ($scope, $http) {

    $scope.initFirst = function ()
    {
        $scope.updateData = $http.get('/resource/user/all').success(function (data) {
            $scope.users = data;
        });

        $http.get('/resource/user/role/all').success(function (data) {
            $scope.roles = data;
        });
    }

    $scope.user = {};

    $scope.addUser = function () {

        if (!$scope.user.userRole) {
            $scope.errorAjout = true;
            $scope.errorMessage = "Vous devez sélectionnez un role";
        }

        var data = JSON.stringify({
            login: $scope.user.userLogin,
            password: $scope.user.userPassword,
            enabled: true,
            roles: [{id: $scope.user.userRole.id}]
        })
        $http.post("/resource/user/add", data).success(function (data, status) {
            $scope.response = data;
            $scope.initFirst();
        })

        if (!$scope.response) {
            $scope.errorAjout = true;
            $scope.errorMessage = "Erreur lors de l'ajout, un ou plusieurs champs est manquant";
        }
        else
            $scope.errorAjout = false;


    }
});
