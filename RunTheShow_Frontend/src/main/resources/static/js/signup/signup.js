angular.module('signup', ['auth']).controller('signup', function ($scope, $http, $rootScope, $window, auth) {

    $scope.errorMessage = {};
    $scope.signUpOK = null;
    
    function isEmail(myVar) {
        // La 1ère étape consiste à définir l'expression régulière d'une adresse email
        var regEmail = new RegExp('^[0-9a-z._-]+@{1}[0-9a-z.-]{2,}[.]{1}[a-z]{2,5}$', 'i');

        return regEmail.test(myVar);
    }
    
    //Fonction de réinitialisation du modal d'inscription
    //seulement quand l'inscription est validé ou qu'il y'a une erreur serveur
    $scope.reInitSignUpModal = function (){
        if($scope.signUpOK != null || $scope.errorMessage.erreurServeur != null){
            $scope.errorMessage = {};
            $scope.signUpOK = null;
            $scope.newUser = {};
            console.log("Signup modal reinitialized");
        }
    };
    
    //Fonction d'inscription de l'utilisateur
    $scope.signUp = function () {

        var noError = true;
        $scope.errorMessage = {};
        $scope.signUpOK = null;

        console.log("test signup");
        if ($scope.newUser.username == null || !isEmail($scope.newUser.username)) {
            $scope.errorMessage.emailContact = "L'email n'est pas valide.";
            noError = false;
        }
        if ($scope.newUser.password == null || $scope.newUser.password.length < 5) {
            $scope.errorMessage.motDePasse = "Le mot de passe doit contenir au moins 5 caractères.";
            noError = false;
        }
        if ($scope.newUser.passwordConfirmed == null || $scope.newUser.passwordConfirmed.length < 5) {
            $scope.errorMessage.motDePasseConfirmed = "Le mot de passe doit contenir au moins 5 caractères.";
            noError = false;
        }
        if ($scope.newUser.password != $scope.newUser.passwordConfirmed) {
            $scope.errorMessage.motDePasseNotEquals = "Les mots de passe ne correspondent pas.";
            noError = false;
        }
        if ($scope.newUser.sexe == null) {
            $scope.errorMessage.sexe = "Veuillez renseigner le sexe.";
            noError = false;
        }
        if (($scope.newUser.typeArtist == null || $scope.newUser.typeArtist == true)) {
            if ($scope.newUser.userlastname == null || ($scope.newUser.userlastname.length < 2 || $scope.newUser.userlastname.length > 100)) {
                $scope.errorMessage.nom = "Le nom doit avoir au minimum deux caractères et au maximum 100.";
                noError = false;
            }
            if ($scope.newUser.userfirstname == null || ($scope.newUser.userfirstname.length < 2 || $scope.newUser.userfirstname.length > 100)) {
                $scope.errorMessage.prenom = "Le prenom doit avoir au minimum deux caractères et au maximum 100.";
                noError = false;
            }
            if (($scope.newUser.typeArtist == true) && ($scope.newUser.role == true)) {
                if ($scope.newUser.nomArtiste != null && $scope.newUser.nomArtiste != "" && $scope.newUser.nomArtiste.length < 5) {
                    $scope.errorMessage.nomArtiste = "Le nom d'artiste doit comporter au moins 5 caractère.";
                    noError = false;
                } else {
                    $scope.newUser.nomArtiste = "";
                }
                $scope.newUser.userRole = 3;
            }
            if ($scope.newUser.role == false) {
                $scope.newUser.nomArtiste = "";
                $scope.newUser.userRole = 1;
            }
            if (noError) {
                var data = JSON.stringify({
                    login: $scope.newUser.username,
                    password: $scope.newUser.password,
                    nom: $scope.newUser.userlastname,
                    prenom: $scope.newUser.userfirstname,
                    nomArtiste: $scope.newUser.nomArtiste,
                    sexe: $scope.newUser.sexe,
                    roles: [{id: $scope.newUser.userRole}]
                })
                console.log("request signup...");
                $http.post("/resource/user/add", data).success(function (data, status) {
                    $scope.response = data;
                    $scope.signUpOK = "Inscription réussie.";
                    console.log("signup OK");
                }).error(function (data, status) { // called asynchronously if an error occurs
                    // or server returns response with an error status.
                    $scope.errorMessage["erreurServeur"] = "Erreur lors de l'envoie des informations, inscription echouée.";
                });


            }
        }
        //Artiste Troupe
        if ($scope.newUser.role == true && $scope.newUser.typeArtist == false) {
            if ($scope.newUser.nomTroupe == null || ($scope.newUser.nomTroupe.length < 2 || $scope.newUser.nomTroupe.length > 100)) {
                $scope.errorMessage.nomTroupe = "Le nom de la troupe doit avoir au minimum deux caractères et au maximum 100.";
                noError = false;
            }
            if ($scope.newUser.nomRespTroupe == null || ($scope.newUser.nomRespTroupe.length < 2 || $scope.newUser.nomRespTroupe.length > 100)) {
                $scope.errorMessage.nomRespTroupe = "Le nom du responsable de la troupe doit avoir au minimum deux caractères et au maximum 100.";
                noError = false;
            }
            if ($scope.newUser.prenomRespTroupe == null || ($scope.newUser.prenomRespTroupe.length < 2 || $scope.newUser.prenomRespTroupe.length > 100)) {
                $scope.errorMessage.prenomRespTroupe = "Le prenom du responsable de la troupe doit avoir au minimum deux caractères et au maximum 100.";
                noError = false;
            }
            if ($scope.newUser.mailRespTroupe == null || !isEmail($scope.newUser.mailRespTroupe)) {
                $scope.errorMessage.mailRespTroupe = "L'email du responsable de la troupe n'est pas valide.";
                noError = false;
            }
            if (noError && $scope.newUser.role == true && $scope.newUser.typeArtist == false) {
                $scope.newUser.nomArtiste = "";
                $scope.newUser.userRole = 3;
                var data = JSON.stringify({
                    login: $scope.newUser.username,
                    password: $scope.newUser.password,
                    nom: $scope.newUser.nomRespTroupe,
                    prenom: $scope.newUser.prenomRespTroupe,
                    nomArtiste: $scope.newUser.nomTroupe,
                    mailContact: $scope.newUser.mailRespTroupe,
                    sexe: $scope.newUser.sexe,
                    roles: [{id: $scope.newUser.userRole}]
                })
                console.log("request signup troupe artiste...");
                $http.post("/resource/user/add", data).success(function (data, status) {
                    $scope.response = data;
                    $scope.signUpOK = "Inscription réussie.";
                    console.log("signup troupe artist OK");
                }).error(function (data, status) { // called asynchronously if an error occurs
                    // or server returns response with an error status.
                    $scope.errorMessage["erreurServeur"] = "Erreur lors de l'envoie des informations, inscription echouée.";
                });
            }
        }
    };
});

