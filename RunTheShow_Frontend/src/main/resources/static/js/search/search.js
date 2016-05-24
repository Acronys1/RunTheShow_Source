angular.module('search', ['navigation']).controller('search', function ($rootScope, $scope, $http, $routeParams, $location) {

    $scope.initFirst = function ()
    {
        $scope.type = $routeParams.type;
        $scope.region = $routeParams.region;

        $rootScope.artisteSearch = {};

        $http.get('/resource/artiste/types').success(function (data) {
            $scope.typesArtiste = data;
            console.log(data);
        });

        $http.get('/resource/artiste/regions').success(function (data) {
            $scope.regions = data;
            console.log(data);
        });

        $scope.search = function () {

            $scope.type = '';
            $scope.region = '';

            $scope.typeAffichage = '';
            $scope.regionAffichage = '';
            
            $scope.request = "/search/";

            if ($rootScope.artisteSearch.type == null)
                $scope.type = "0";
            else
                for (var i = 0; i < $rootScope.artisteSearch.type.length; i++) {
                    if (i === $rootScope.artisteSearch.type.length - 1) {
                        $scope.typeAffichage += $scope.typesArtiste[$rootScope.artisteSearch.type[i] - 1].nom;
                        $scope.type += $rootScope.artisteSearch.type[i];
                    } else {
                        $scope.typeAffichage += $scope.typesArtiste[$rootScope.artisteSearch.type[i] - 1].nom + ", ";
                        $scope.type += $rootScope.artisteSearch.type[i] + ",";
                        $rootScope.artisteSearch.type[i] = parseInt($rootScope.artisteSearch.type[i], 10);
                    }
                }

            if ($rootScope.artisteSearch.region == null)
                $scope.region = "0";
            else
                for (var i = 0; i < $rootScope.artisteSearch.region.length; i++) {
                    if (i === $rootScope.artisteSearch.region.length - 1) {
                        $scope.regionAffichage += $scope.regions[$rootScope.artisteSearch.region[i] - 1].nom;
                        $scope.region += $rootScope.artisteSearch.region[i]
                    } else {
                        $scope.regionAffichage += $scope.regions[$rootScope.artisteSearch.region[i] - 1].nom + ", ";
                        $scope.region += $rootScope.artisteSearch.region[i] + ",";
                        $rootScope.artisteSearch.region[i] = parseInt($rootScope.artisteSearch.region[i], 10);
                    }
                }

            if ($rootScope.artisteSearch.type != null || $rootScope.artisteSearch.region != null){
                $scope.request += $scope.type + "/" + $scope.region;
                $location.path($scope.request);
            }
        }

        $http.get("/resource/artiste/search" + "/" + $scope.type + "/" + $scope.region).success(function (data, status) {
            $scope.response = data;
        })
    }
}).directive('selectWatcher', function ($timeout) {
    return {
        link: function (scope, element, attr) {
            var last = attr.last;
            if (last === "true") {
                $timeout(function () {
                    $(element).parent().selectpicker('val', 'any');
                    $(element).parent().selectpicker('refresh');
                });
            }
        }
    };
});
