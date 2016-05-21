/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module('userprofile', []).controller('userprofile', function ($scope, $http, $rootScope, $window) {


    $scope.userToEdit = {};
    $scope.errorMessage = {};
    $scope.imgProfileStyle = {};
    $scope.updateOK = null;
    $scope.uploadFileOK = false;
    $scope.filePath = null;
    $scope.processUploadOK = false;
    $scope.errorProfilePicUpload = null;

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
            $scope.errorMessage.emailContact = "L'email de contact n'est pas valide.";
            noError = false;
        }
        if (!isEmail($rootScope.currentUser.login)) {
            $scope.errorMessage.login = "Le login doit être un email valide.";
            noError = false;
        }
        if (!isTelephone($rootScope.currentUser.telephoneFixe)) {
            $scope.errorMessage.telFixe = "Le télephone fixe n'est pas valide: il doit comporter 10 chiffres.";
            noError = false;
        }
        if (!isTelephone($rootScope.currentUser.telephonePortable)) {
            $scope.errorMessage.telPortable = "Le télephone portable n'est pas valide: il doit comporter 10 chiffres.";
            noError = false;
        }
        if ($rootScope.currentUser.nom.length < 2 || $rootScope.currentUser.nom.length > 100) {
            $scope.errorMessage.nom = "Le nom doit avoir au minimum deux caractères et au maximum 100.";
            noError = false;
        }
        if ($rootScope.currentUser.prenom.length < 2 || $rootScope.currentUser.prenom.length > 100) {
            $scope.errorMessage.prenom = "Le prenom doit avoir au minimum deux caractères et au maximum 100.";
            noError = false;
        }
        if ($rootScope.currentUser.codePostal.length < 5) {
            $scope.errorMessage.codePostal = "Le code postal n'est pas valide.";
            noError = false;
        }
        if ($rootScope.currentUser.adresse.length < 5) {
            $scope.errorMessage.adresse = "L'adresse n'est pas valide.";
            noError = false;
        }
        if ($rootScope.currentUser.ville.length < 2) {
            $scope.errorMessage.ville = "La ville doit comporter au moins deux caractères.";
            noError = false;
        }
        if (!isDate($rootScope.currentUser.dateDeNaissance)) {
            $scope.errorMessage.dateNaissance = "La date doit être sous la forme jj/mm/aaaa";
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
            });
            $scope.sendData(data);

        } else {
            console.log("User NOT update");
        }
    };

    //propriétés de l'image de profil
    $scope.initImgProfile = function () {
        //profil img
        $scope.urlImageProfile = ($rootScope.currentUser.photo != null && $rootScope.currentUser.photo != "" ? $rootScope.currentUser.photo : 'img/portfolio-1.jpg');
        $scope.imgProfileStyle = {
            'background-image': "url(" + $scope.urlImageProfile + ")",
            'height': '200px',
            'width': '170px'
        };
        return $scope.imgProfileStyle;
    };

    $scope.sendData = function (data) {
        $http.put("/resource/user/update", data).success(function (data, status) {
            $scope.response = data;
            console.log("User update");
            $scope.updateOK = "Informations mises à jour";
        }).error(function (data, status) { // called asynchronously if an error occurs
            // or server returns response with an error status.
            $scope.errorMessage["erreurServeur"] = "Erreur lors de la mise à jours des infos";
            console.log("User NOT update");
        });
    };

    //upload de l'image de profile
    $scope.uploadImgProfile = function () {
        $scope.filePath = null;
        $scope.uploadFileOK = false;
        $scope.processDropzone();
    };

    $scope.reset = function () {
        $scope.errorProfilePicUpload = null;
        $scope.importProfilePic = null;
        $scope.updateOK = false;        
        $scope.resetDropzone();
    };

    $scope.uploadImages = function (filepath) {
        sleep(1000);
        if ($scope.uploadFileOK  == true) {
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
                adresse: $rootScope.currentUser.adresse,
                photo:$scope.filePath,
            });
            $scope.sendData(data);
            console.log("profil pic mise à jour");
            $window.location.reload();
        }
        //$window.location.reload();
    };

});


function sleep(milliseconds) {
    var start = new Date().getTime();
    for (var i = 0; i < 1e7; i++) {
        if ((new Date().getTime() - start) > milliseconds) {
            break;
        }
    }
}

angular.module('userprofile').directive('dropzoneuserprofile', function ($cookies, $http, $route) {
    return {
        restrict: 'C',
        link: function (scope, element, attrs) {

            var config = {
                url: 'http://localhost:8080/resource/file/upload',
                maxFilesize: 20,
                withCredentials: true,
                paramName: "uploadfile",
                maxThumbnailFilesize: 10,
                parallelUploads: 1, headers: {
                    'X-XSRF-TOKEN': $cookies.get('XSRF-TOKEN')
                },
                autoProcessQueue: false
            };

            var eventHandlers = {
                'addedfile': function (file) {
                    scope.file = file;
                    if (this.files[1] != null) {
                        this.removeFile(this.files[0]);
                    }
                    scope.$apply(function () {
                        scope.fileAdded = true;
                    });
                },
                'success': function (file, response) {
                    scope.filePath = response.filePath;
                    scope.uploadFileOK = true;
                    scope.uploadImages(scope.filePath);
                    console.log("File path retrieved OK");
                    scope.importProfilePic = "Image importée avec succes";
                },
                'complete': function (file, response) {
                    console.log(file);
                },
                'error': function (file, errorMessage) {
                    scope.errorProfilePicUpload = errorMessage;
                    console.log("Erreur import image");
                }
            };

            dropzone = new Dropzone(element[0], config);

            angular.forEach(eventHandlers, function (handler, event) {
                dropzone.on(event, handler);
            });

            scope.processDropzone = function () {
                scope.uploadFileOK = false;
                dropzone.processQueue();
            };

            scope.resetDropzone = function () {
                scope.uploadFileOK = false;
                dropzone.removeAllFiles();
            };
        }
    }
});
