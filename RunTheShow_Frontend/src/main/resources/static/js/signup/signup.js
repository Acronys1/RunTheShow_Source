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
        if($scope.newUser.userlastname.length < 2 || $scope.newUser.userlastname.length > 100){
            $scope.errorMessage["nom"] = "Le nom doit avoir au minimum deux caractères et au maximum 100.";
            noError = false;
        }
        if($scope.newUser.userfirstname.length < 2 || $scope.newUser.userfirstname.length > 100){
            $scope.errorMessage["prenom"] = "Le prenom doit avoir au minimum deux caractères et au maximum 100.";
            noError = false;
        }
        if (!isEmail($scope.newUser.username)) {
            $scope.errorMessage["emailContact"] =  "L'email n'est pas valide.";
            noError = false;
        }
        if($scope.newUser.password < 5){
            $scope.errorMessage["motDePasse"] =  "Le mot de passe doit contenir au moins 5 caractères.";
            noError = false;
        }
        if($scope.newUser.passwordConfirmed < 5){
            $scope.errorMessage["motDePasse"] =  "Le mot de passe doit contenir au moins 5 caractères.";
            noError = false;
        }
        if($scope.newUser.password != $scope.newUser.passwordConfirmed){
            $scope.errorMessage["motDePasseNotEquals"] =  "Les mots de passe ne correspondent pas.";
            noError = false;
        }
        //Appel inscription d'organisateur
        if(noError && newUser.role == true){
            var data = JSON.stringify({
            login: $scope.newUser.username,
            password: $scope.newUser.password,
            nom : $scope.newUser.userlastname,
            prenom : $scope.newUser.userfirstname,
            enabled: true,
            roles: 1
            })

            $http.post("/resource/user/add", data).success(function (data, status) {
                $scope.response = data;
                $scope.initFirst();
                $scope.updateOK = "Inscription réussie.";
            }).error(function (data, status) { // called asynchronously if an error occurs
                // or server returns response with an error status.
                $scope.errorMessage["erreurServeur"] = "Erreur lors de l'envoie des informations, inscription echouée.";
            });
        }
    }
});

