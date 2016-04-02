/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module('userprofile', []).controller('userprofile', function ($scope, $http, $rootScope, $window) {


    $scope.userToEdit = {};
    $scope.errorMessage = {};
    $scope.updateOK = null;

    function isEmail(myVar) {
        // La 1ère étape consiste à définir l'expression régulière d'une adresse email
        var regEmail = new RegExp('^[0-9a-z._-]+@{1}[0-9a-z.-]{2,}[.]{1}[a-z]{2,5}$', 'i');

        return regEmail.test(myVar);
    }

    function isTelephone(myVar) {
        var regTel = new RegExp('([0-9]{10})', 'i');

        return regTel.test(myVar);
    }

    function isDate(myVar) {
        var regDate = new RegExp('([0-9]{2}\/{1}[0-9]{2}\/{1}[0-9]{4})', 'i');

        return regDate.test(myVar);
    }

    $scope.updateUser = function () {
        var noError = true;
        $scope.errorMessage = {};        
        $scope.updateOK = null;
        if (!isEmail($rootScope.currentUser.mailContact)) {
            $scope.errorMessage["emailContact"] =  "L'email de contact n'est pas valide.";
            noError = false;
        }
        if (!isEmail($rootScope.currentUser.login)) {
            $scope.errorMessage["login"] = "Le login doit être un email valide.";
            noError = false;
        }
        if (!isTelephone($rootScope.currentUser.telephoneFixe)) {
            $scope.errorMessage["telFixe"] =  "Le télephone fixe n'est pas valide: il doit comporter 10 chiffres.";
            noError = false;
        }
        if (!isTelephone($rootScope.currentUser.telephonePortable)) {
            $scope.errorMessage["telPortable"] =  "Le télephone portable n'est pas valide: il doit comporter 10 chiffres.";
            noError = false;
        }
        if ($rootScope.currentUser.nom.length < 2 || $rootScope.currentUser.nom.length > 100) {
            $scope.errorMessage["nom"] = "Le nom doit avoir au minimum deux caractères et au maximum 100.";
            noError = false;
        }
        if ($rootScope.currentUser.prenom.length < 2 || $rootScope.currentUser.prenom.length > 100) {
            $scope.errorMessage["prenom"] = "Le prenom doit avoir au minimum deux caractères et au maximum 100.";
            noError = false;
        }
        if ($rootScope.currentUser.codePostal.length < 5) {
            $scope.errorMessage["codePostal"] = "Le code postal n'est pas valide.";
            noError = false;
        }
        if ($rootScope.currentUser.adresse.length < 5) {
            $scope.errorMessage["adresse"] = "L'adresse n'est pas valide.";
            noError = false;
        }       
        if ($rootScope.currentUser.ville.length < 2) {
            $scope.errorMessage["ville"] = "La ville doit comporter au moins deux caractères.";
            noError = false;
        }
        if (!isDate($rootScope.currentUser.dateDeNaissance)) {
            $scope.errorMessage["dateNaissance"] = "La date doit être sous la forme jj/mm/aaaa";
            noError = false;
        }
        if (noError == true) {
            $scope.errorMessage = {};
            var data = JSON.stringify({
                id: $rootScope.currentUser.id,
                mailContact: $rootScope.currentUser.mailContact,
                login: $rootScope.currentUser.login,
                telephoneFixe: $rootScope.currentUser.telephoneFixe,
                telephonePortable: $rootScope.currentUser.telephonePortable,
                nom: $rootScope.currentUser.nom,
                prenom: $rootScope.currentUser.prenom,
                dateDeNaissance: $rootScope.currentUser.dateDeNaissance,
                sexe: $rootScope.currentUser.sexe,
                codePostal: $rootScope.currentUser.codePostal,
                ville: $rootScope.currentUser.ville,
                description: $rootScope.currentUser.description,
                adresse: $rootScope.currentUser.adresse
            })
            $http.put("/resource/user/update", data).success(function (data, status) {
                $scope.response = data;                
                console.log("User update");
                $scope.updateOK = "Informations mises à jour";
            }).error(function (data, status) { // called asynchronously if an error occurs
                // or server returns response with an error status.
                $scope.errorMessage["erreurServeur"] = "Erreur lors de la mise à jours des infos";
                console.log("User NOT update");
            });
        }
        else{
            console.log("User NOT update");
        }
    }

});

