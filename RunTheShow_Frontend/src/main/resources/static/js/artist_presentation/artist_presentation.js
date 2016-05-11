angular.module('artist_presentation', []).controller('artist_presentation', function ($scope, $http, $rootScope, $window) {

    $scope.artist = {};
    $scope.errorMessage = "";
    $scope.typesArtiste = {};
    $scope.error = false;
    
    $scope.initArtistPresentation = function () {
        //récupère les types d'artiste
        $http.get("/resource/artiste/types").success(function (data) {
            for(var i=0;i<data.length;i++){
                $scope.typesArtiste[i] = data['nom'];
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
            if ($scope.artist.roles[0] == "ROLE_ARTISTE") {
                $scope.errorMessage.init = "OKKKKKKKKKKKKKK";
            }
        }).error(function (data, status) { // called asynchronously if an error occurs
            // or server returns response with an error status.
            $scope.errorMessage.init = "Pas un artiste.";
        });
    }
    
    $scope.updateUser = function() {
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
                    }
                    else
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
                    }
                    else
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
