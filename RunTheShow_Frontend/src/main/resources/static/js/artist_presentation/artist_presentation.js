angular.module('artist_presentation', []).controller('artist_presentation', function ($scope, $http, $rootScope, $window, $filter, $sce) {

    $scope.artist = {};
    $scope.errorMessage = "";
    $scope.typesArtiste = {};
    $scope.facebookURL = "";
    $scope.error = false;
    $scope.errorUrlFb = "";

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

        //récupère l'utilisateur et check si c'est un artiste.
        $http.get("/resource/artiste/current").success(function (data, status) {
            $scope.artist = data;
            if ($scope.artist != null) {
                console.log("user is an artist")
                $scope.facebookURL = $scope.getFbUrlArtist();
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
    };

    $scope.showArtistTypes = function () {
        //renvoie la liste des types d'artiste utilisés dans le select de la vue
        //et préselectionne le select avec le type de l'artise
        if ($scope.artist == null || $scope.typesArtiste[0] == null)
            return 'no set';
        if ($scope.artist.typeArtiste == null)//default selected typeArtist
            return $scope.typesArtiste[0].text;
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
        var fbArtist = $scope.artist != null && $scope.artist.facebookArtiste != null && $scope.artist.facebookArtiste != "" ? $scope.artist.facebookArtiste : "assassinscreed.france";
        var fbUrlEnd = "&tabs=timeline&width=340&height=500&small_header=false&adapt_container_width=true&hide_cover=false&show_facepile=true&appId";
        console.log(fbUrlStart + fbArtist + fbUrlEnd);
        return $scope.trustSrc(fbUrlStart + fbArtist + fbUrlEnd);
    };

    //Vérifie l'url du groupe Facebook
    /*$scope.checkFbUrl = function (data) {
        $scope.errorUrlFb = "";
        var urlExample = "https://www.facebook.com/FacebookFrance/";
        var regex = new RegExp("(https:\/\/www.facebook.com\/)((\w+)\/)");
        var monTableau = data.match(regex);
        console.log(monTableau)
        if (data !== 'awesome') {
            $scope.errorUrlFb = "L'url devrait être sous la forme:`https://www.facebook.com/nom_groupe_facebook/";
        }
    };*/

});

artist_presentation.directive('datepicker', function () {
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
