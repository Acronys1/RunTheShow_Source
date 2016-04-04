angular.module('signup', []).controller('signup', function ($scope, $http, $rootScope, $window) {

    $scope.errorMessage = {};
    $scope.signUpOK = null;

    function isEmail(myVar) {
        // La 1ère étape consiste à définir l'expression régulière d'une adresse email
        var regEmail = new RegExp('^[0-9a-z._-]+@{1}[0-9a-z.-]{2,}[.]{1}[a-z]{2,5}$', 'i');

        return regEmail.test(myVar);
    }


    $scope.signUp = function () {

        var noError = true;
        $scope.errorMessage = {};
        $scope.signUpOK = null;

        console.log("test signup");
        if ($scope.newUser.userlastname == null || ($scope.newUser.userlastname.length < 2 || $scope.newUser.userlastname.length > 100)) {
            $scope.errorMessage["nom"] = "Le nom doit avoir au minimum deux caractères et au maximum 100.";
            noError = false;
        }
        if ($scope.newUser.userfirstname == null || ($scope.newUser.userfirstname.length < 2 || $scope.newUser.userfirstname.length > 100)) {
            $scope.errorMessage["prenom"] = "Le prenom doit avoir au minimum deux caractères et au maximum 100.";
            noError = false;
        }
        if ($scope.newUser.username == null || !isEmail($scope.newUser.username)) {
            $scope.errorMessage["emailContact"] = "L'email n'est pas valide.";
            noError = false;
        }
        if ($scope.newUser.password == null || $scope.newUser.password < 5) {
            $scope.errorMessage["motDePasse"] = "Le mot de passe doit contenir au moins 5 caractères.";
            noError = false;
        }
        if ($scope.newUser.passwordConfirmed == null || $scope.newUser.passwordConfirmed < 5) {
            $scope.errorMessage["motDePasse"] = "Le mot de passe doit contenir au moins 5 caractères.";
            noError = false;
        }
        if ($scope.newUser.password != $scope.newUser.passwordConfirmed) {
            $scope.errorMessage["motDePasseNotEquals"] = "Les mots de passe ne correspondent pas.";
            noError = false;
        }
        //Appel inscription d'organisateur
        if (noError && $scope.newUser.role == false) {
            var data = JSON.stringify({
                login: $scope.newUser.username,
                password: $scope.newUser.password,
                nom: $scope.newUser.userlastname,
                prenom: $scope.newUser.userfirstname,
                //enabled: true,
                roles: 1
            })
            console.log("request signup organisator...");
            $http.post("/resource/user/add", data).success(function (data, status) {
                $scope.response = data;
                $scope.initFirst();
                $scope.updateOK = "Inscription réussie.";
                console.log("signup organisator OK");
            }).error(function (data, status) { // called asynchronously if an error occurs
                // or server returns response with an error status.
                $scope.errorMessage["erreurServeur"] = "Erreur lors de l'envoie des informations, inscription echouée.";
            });
        }
        //artiste solo
        if (($scope.newUser.typeArtist == true) && ($scope.newUser.role == true)){
            if ($scope.newUser.nomArtiste == null || $scope.newUser.nomArtiste < 5) {
                $scope.errorMessage["nomArtiste"] = "Le mot de passe doit contenir au moins 5 caractères.";
                noError = false;
            }
            if (noError && $scope.newUser.role == true) {
                var data = JSON.stringify({
                    login: $scope.newUser.username,
                    password: $scope.newUser.password,
                    nom: $scope.newUser.userlastname,
                    prenom: $scope.newUser.userfirstname,
                    nomArtiste: $scope.newUser.nomArtiste,
                    //enabled: true,
                    roles: 3
                })
                console.log("request signup solo artiste...");
                $http.post("/resource/user/add", data).success(function (data, status) {
                    $scope.response = data;
                    $scope.initFirst();
                    $scope.updateOK = "Inscription réussie.";
                    console.log("signup solo artist OK");
                }).error(function (data, status) { // called asynchronously if an error occurs
                    // or server returns response with an error status.
                    $scope.errorMessage["erreurServeur"] = "Erreur lors de l'envoie des informations, inscription echouée.";
                });
            }
        }
        //Artiste Troupe
        if ($scope.newUser.role == true && $scope.newUser.typeArtist == false) {
            if ($scope.newUser.nomTroupe == null || ($scope.newUser.nomTroupe.length < 2 || $scope.newUser.nomTroupe.length > 100)) {
                $scope.errorMessage["nomTroupe"] = "Le nom de la troupe doit avoir au minimum deux caractères et au maximum 100.";
                noError = false;
            }
            if ($scope.newUser.nomRespTroupe == null || ($scope.newUser.nomRespTroupe.length < 2 || $scope.newUser.nomRespTroupe.length > 100)) {
                $scope.errorMessage["nomRespTroupe"] = "Le nom du responsable de la troupe doit avoir au minimum deux caractères et au maximum 100.";
                noError = false;
            }
            if ($scope.newUser.prenomRespTroupe == null || ($scope.newUser.prenomRespTroupe.length < 2 || $scope.newUser.prenomRespTroupe.length > 100)) {
                $scope.errorMessage["prenomRespTroupe"] = "Le prenom du responsable de la troupe doit avoir au minimum deux caractères et au maximum 100.";
                noError = false;
            }
            if ($scope.newUser.mailRespTroupe == null || !isEmail($scope.newUser.mailRespTroupe)) {
                $scope.errorMessage["mailRespTroupe"] = "L'email du responsable de la troupe n'est pas valide.";
                noError = false;
            }
            if (noError && $scope.newUser.role == true && newUser.typeArtist == false) {
                var data = JSON.stringify({
                    login: $scope.newUser.username,
                    password: $scope.newUser.password,
                    nom: $scope.newUser.userlastname,
                    prenom: $scope.newUser.userfirstname,
                    nomArtiste: $scope.newUser.nomArtiste,
                    mailContact:$scope.newUser.mailRespTroupe,
                    //enabled: true,
                    roles: 3
                })
                console.log("request signup troupe artiste...");
                $http.post("/resource/user/add", data).success(function (data, status) {
                    $scope.response = data;
                    $scope.initFirst();
                    $scope.updateOK = "Inscription réussie.";
                    console.log("signup troupe artist OK");
                }).error(function (data, status) { // called asynchronously if an error occurs
                    // or server returns response with an error status.
                    $scope.errorMessage["erreurServeur"] = "Erreur lors de l'envoie des informations, inscription echouée.";
                });
            }
        }

    }
});

