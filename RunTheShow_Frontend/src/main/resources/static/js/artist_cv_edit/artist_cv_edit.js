angular.module('artist_cv_edit', ['ngCookies']).controller('artist_cv_edit', function ($scope, $http, $rootScope, $window, $filter, $sce, $route) {

    $scope.artist = {};
    $scope.errorMessage = "";
    $scope.typesArtiste = {};
    $scope.artistRegions = [];
    $scope.regions = {};
    $scope.facebookURL = "";
    $scope.youtubeURL = "";
    $scope.error = false;
    $scope.errorUrlFb = "";
    $scope.errorUrlYoutube = "";
    $scope.bannerStyle = {};
    $scope.imgProfileStyle = {};
    $scope.imgProfileStyle = {};
    $scope.localisation = [];
    $scope.errorBannerImport = null;
    $scope.importBannerOK = null;
    $scope.urlImageProfile = "";
    $scope.lstInvitation = {};

    //initialise le CV des artistes
    $scope.initArtistPresentation = function () {
        //récupère les types d'artiste dans un tableau utilisé dans un select
        $http.get("/resource/artiste/types").success(function (data) {
            for (var i = 0; i < data.length; i++) {
                $scope.typesArtiste[i] = {'value': data[i]['id'], 'text': data[i]['nom']};
            }
            $scope.error = false;
            console.log("récup rôles ok");
        }).error(function (data) { // called asynchronously if an error occurs
            // or server returns response with an error status.
            $scope.errorMessage.init = "erreur récupération type artiste.";
            $scope.error = true;
            console.log("récup roles KO");
        });
        
        //récupère les invitations reçues par un artiste
        $http.get("/resource/invitation/retreiveReceivedInvit").success(function (data, status) {
            $scope.lstInvitation = data;
            console.log(data);
        }).error(function (data, status) { 

        });

        //récupère l'utilisateur et check si c'est un artiste.
        $http.get("/resource/artiste/current").success(function (data, status) {
            $scope.artist = data;
            console.log($scope.artist);
            if ($scope.artist != null) {
                console.log("user is an artist")
                $scope.facebookURL = $scope.getFbUrlArtist();
                $scope.initBanner();
                $scope.initRegion();
                $scope.initImgProfile();
            } else {
                $scope.errorMessage.init = "not an artist";
                $scope.error = true;
            }
        }).error(function (data, status) { // called asynchronously if an error occurs
            // or server returns response with an error status.
            $scope.errorMessage.init = "Erreur lors de la récupération des informations serveur";
            $scope.error = true;
            console.log("user is not an artist")
        });

        $rootScope.processUploadBanniere = false;
        $rootScope.processUploadImgProfile = false;
    };

    $scope.showArtistTypes = function () {
        //renvoie la liste des types d'artiste utilisés dans le select de la vue
        //et préselectionne le select avec le type de l'artise
        if ($scope.artist == null || $scope.typesArtiste[0] == null)
            return 'no set';
        if ($scope.artist.typeArtiste == null)//default selected typeArtist
            return "Type d'artiste non précisé, veuilliez en cliquez pour choisir un type d'artiste.";
        var selected = null;
        var idArtiste = $scope.artist.typeArtiste.id;
        for (var i = 0; i < $scope.typesArtiste[i].value != null; i++) {
            var currIdArt = $scope.typesArtiste[i].value;
            var libTypeArtiste = $scope.typesArtiste[i].text;
            if (currIdArt == idArtiste)
                return libTypeArtiste;//par défault, le type d'artiste de l'artiste est selected
        }
        return 'Not set';
    };

    //met à jour les informations de l'artiste
    $scope.updateUser = function () {
        if ($scope.localisation.length > 0)
            $scope.artist.localisation = $scope.localisation;
        var data = $scope.artist;
        $http.put("/resource/artiste/update", data).success(function (data, status) {
            $scope.response = data;
            $scope.artistUpdateOK = true;
            console.log("update artiste OK");
        }).error(function (data, status) { // called asynchronously if an error occurs
            // or server returns response with an error status.
            $scope.response = data;
            $scope.artistUpdateOK = false;
            console.log("update artiste KO");
        });
    };

    //formatte l'url pour qu'elle soit trusted
    $scope.trustSrc = function (src) {
        return $sce.trustAsResourceUrl(src);
    };

    //obtient l'url fb de du groupe fb de l'artiste
    $scope.getFbUrlArtist = function () {
        var fbUrlStart = "https://www.facebook.com/plugins/page.php?href=https%3A%2F%2Fwww.facebook.com%2F";
        var fbArtist = $scope.artist != null && $scope.artist.facebookArtiste != null && $scope.artist.facebookArtiste != "" ? $scope.artist.facebookArtiste : "LesInconnusOfficiel";
        var fbUrlEnd = "&tabs=timeline&width=340&height=500&small_header=false&adapt_container_width=true&hide_cover=false&show_facepile=true&appId";
        console.log(fbUrlStart + fbArtist + fbUrlEnd);
        return $scope.trustSrc(fbUrlStart + fbArtist + fbUrlEnd);
    };

    //obtient l'url de la chaîne youtube de l'artiste
    $scope.getYtUrlArtist = function () {
        var fbUrlStart = "http://www.youtube.com/embed/?listType=user_uploads&list=";
        var fbArtist = $scope.artist != null && $scope.artist.youtubeArtiste != null && $scope.artist.youtubeArtiste != "" ? $scope.artist.youtubeArtiste : "lesinconnusVEVO";
        console.log(fbUrlStart + fbArtist);
        return $scope.trustSrc(fbUrlStart + fbArtist);
    };

    $scope.initRegion = function () {
        $http.get('/resource/artiste/regions').success(function (data, status) {
            $scope.regions = data;
            console.log("Récupération regions OK");
            //Récupère les regions de l'artiste
            for (var i = 0; i < $scope.regions.length; i++) {
                $scope.artistRegions[i] = {'value': $scope.regions[i].id, 'text': $scope.regions[i].nom};
            }
        }).error(function (data, status) { // called asynchronously if an error occurs
            // or server returns response with an error status.
            $scope.error = true;
            console.log("Récupération regions KO");
        });
    };

    //récupère les regions sélectionné par l'artiste
    $scope.showArtistRegions = function () {
        var selected = [];
        var regionFound;
        angular.forEach($scope.artistRegions, function (s) {
            regionFound = $filter('filter')($scope.artist.localisation, {'id': s.value});
            if (regionFound != null && regionFound.length > 0)
                selected.push(regionFound[0].nom);
        });
        return selected.length ? selected.join(', ') : 'Localisation non précisée';
    };

    //mise à jour des régions de l'artiste
    $scope.updateArtistRegions = function (data) {
        //reconstruire le le tableau id, nom pour les regions   
        $scope.localisation = [];
        for (var i = 0; i < data.length; i++) {
            if (i == 0)
                $scope.artist.localisation = [];
            var idRegion = data[i];
            //utilisation de filter
            var regionFound = $filter('filter')($scope.artistRegions, {'value': data[i]});
            var regionName = regionFound[0].text;
            $scope.localisation[i] = {'id': idRegion, 'nom': regionName};
        }
        //data = $scope.localisation;
        $scope.updateUser();
    };
    // upload des images
    $scope.partialDownloadLink = 'http://localhost:8080/resource/file/download2?filename=';

    //propriétés de la bannière
    $scope.initBanner = function () {
        //bannière
        if ($scope.artist.imageBanniere != null && $scope.artist.imageBanniere != "") {
            $scope.bannerStyle = {
                'background-image': "url(" + $scope.artist.imageBanniere + ")",
                'height': '300px'
            };
        }
    };

    //propriétés de l'image de profil
    $scope.initImgProfile = function () {
        //profil img
        $scope.urlImageProfile = ($scope.artist.imageProfile != null && $scope.artist.imageProfile != "" ? $scope.artist.imageProfile : 'img/portfolio-1.jpg');
        $scope.imgProfileStyle = {
            'background-image': "url(" + $scope.urlImageProfile + ")",
            'height': '200px',
            'width': '280px'
        };
    };

    //upload de la bannière
    $scope.uploadFileBanniere = function () {
        $scope.processDropzone();
    };

    //upload de l'image de profile
    $scope.uploadImgProfile = function () {
        $scope.reInitUploadModal();
        $scope.processUploadImgProfile = true;
        $scope.processUploadBanniere = false;
    };

    $scope.uploadBanniere = function () {
        $scope.reInitUploadModal();
        $scope.processUploadBanniere = true;
        $scope.processUploadImgProfile = false;
    };

    $scope.uploadImages = function (filepath) {
        sleep(1000);
        if ($scope.processUploadBanniere == true) {
            $scope.artist.imageBanniere = $scope.filePath;
            console.log("image banniere mise à jour");
            $scope.initBanner();
        } else if ($scope.processUploadImgProfile == true) {
            console.log("image profile mise à jour");
            $scope.artist.imageProfile = filepath;
            $scope.initImgProfile();
        }
        $scope.updateUser();
        $window.location.reload();
    };

    $scope.reset = function () {
        $scope.filename = '';
        $scope.importBannerOK = null;
        $scope.errorBannerImport = null;
        $rootScope.processUploadBanniere = false;
        $rootScope.processUploadImgProfile = false;
        //$scope.initBanner();
        $scope.resetDropzone();
    };

    $scope.reInitUploadModal = function () {
        $scope.reset();
    };
    
    $scope.checkTailleGroupe = function(data) {
        if(isNaN(data))
            return 'Veuillez entrez un nombre entier';
    };

});

angular.module('artist_cv_edit').directive('imageonload', function () {
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
            element.bind('load', function () {
                alert('image is loaded');
            });
            element.bind('error', function () {
                alert('image could not be loaded');
            });
        }
    };
});

function sleep(milliseconds) {
  var start = new Date().getTime();
  for (var i = 0; i < 1e7; i++) {
    if ((new Date().getTime() - start) > milliseconds){
      break;
    }
  }
}


//angular.module('artist_cv_edit').directive('dropZone', dropzone);
angular.module('artist_cv_edit').directive('dropzonecv', function ($cookies, $http, $route) {
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
                    scope.importBannerOK = "Image importée avec succes";
                },
                'complete': function (file, response) {
                    console.log(file);
                },
                'error': function (file, errorMessage) {
                    scope.errorBannerImport = errorMessage;
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
artist_cv_edit.directive('datepicker', function () {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function (scope, element, attrs, ngModCtrl) {
            $(function () {
                var min = new Date();
                var myHours = min.getHours();
                min.setHours(myHours + 6)

                element.datetimepicker({
                    format: 'DD/MM/YYYY HH:mm',
                    minDate: min,
                    calendarWeeks: true
                });
                element.on("dp.hide", function (e) {


                    if (attrs.id.indexOf("debut") != -1)
                    {
                        //alert("Check in value = " + attrs.id);
                        ngModCtrl.$setViewValue(document.getElementById('datedebut').value);
                        //$("#checkout").data("DateTimePicker").minDate(e.date); 
                    } else
                    {
                        //alert("Check out value = " + attrs.id + "   CPT = " + cpt);
                        ngModCtrl.$setViewValue(document.getElementById('datefin').value);
                    }


                });

                element.on("dp.change", function (e) {

                    if (attrs.id.indexOf("debut") != -1)
                    {
                        //alert("Check in value = " + attrs.id);
                        ngModCtrl.$setViewValue(document.getElementById('datedebut').value);
                        //$("#checkout").data("DateTimePicker").minDate(e.date); 
                    } else
                    {
                        //alert("Check out value = " + attrs.id + "   CPT = " + cpt);
                        ngModCtrl.$setViewValue(document.getElementById('datefin').value);
                        $("#datedebut").data("DateTimePicker").maxDate(e.date);
                    }

                });
            });
        }
    }
});
