angular.module('users', []).controller('users', function ($scope, $http) {

    $scope.editingUser = {};
    $scope.user = {};

    $scope.initFirst = function ()
    {
        $scope.updateData = $http.get('/resource/user/all').success(function (data) {
            $scope.users = data;
            for (var i = 0; lenght = $scope.editingUser.length; i++) {

                $scope.editingUser[users[i].id] = false;
            }
        });

        $http.get('/resource/user/role/all').success(function (data) {
            $scope.roles = data;
        });
    }

    //Update & delete



    //Mettre les champs modifiable liste des utilisateurs
    $scope.modifyUser = function (user) {
        $scope.userEdit = user;
        $scope.editingUser[user.id] = true;
    };

    //Modification d'un utilisateur
    $scope.updateUser = function () {
        var data = JSON.stringify({
            id: $scope.userEdit.id,
            login: $scope.userEdit.login,
            enabled: $scope.userEdit.enabled,
            roles: [{id: $scope.userEdit.userRole.id}]
        })
        $http.put("/resource/user/update", data).success(function (data, status) {
            $scope.response = data;
            $scope.initFirst();
        })
        $scope.editingUser[$scope.userEdit.id] = false;
    };

    //Supprimer un utilisateur
    $scope.deleteUser = function (user) {
        $scope.userEdit = user;
        var data = JSON.stringify({
            id: $scope.userEdit.id
        })
        $http.post("/resource/user/delete", data).success(function (data, status) {
            $scope.response = data;
            $scope.initFirst();
        })
        $scope.editingUser[$scope.userEdit.id] = false;
        $scope.editingUser[$scope.userEdit.id] = false;
    };



    //Ajout d'un utilisateur
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
            $scope.errorAjout = false;
        }).error(function (data, status) { // called asynchronously if an error occurs
            // or server returns response with an error status.
            $scope.errorAjout = true;
            $scope.errorMessage = "Erreur lors de l'ajout, un ou plusieurs champs sont manquants";
        });


    }
});
