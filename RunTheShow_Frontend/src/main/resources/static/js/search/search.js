angular.module('search', ['navigation']).controller('search', function ($rootScope, $scope, $http) {

    $scope.initFirst = function ()
    {
        $scope.type = '';
        $scope.region = '';
        
        $http.get('/resource/artiste/types').success(function (data) {
            $scope.typesArtiste = data;
            console.log(data);
        });
        
        $http.get('/resource/artiste/regions').success(function (data) {
            $scope.regions = data;
            console.log(data);
        });

        for (var i = 0; i < $rootScope.artisteSearch.type.length; i++) {
            if(i===$rootScope.artisteSearch.type.length-1) $scope.type += $rootScope.artisteSearch.type[i];
            else $scope.type += $rootScope.artisteSearch.type[i]+",";
            $rootScope.artisteSearch.type[i] = parseInt($rootScope.artisteSearch.type[i], 10);
        }
        for (var i = 0; i < $rootScope.artisteSearch.region.length; i++) {
            if(i===$rootScope.artisteSearch.type.length-1) $scope.region += $rootScope.artisteSearch.region[i]
            else $scope.region += $rootScope.artisteSearch.region[i]+",";
            $rootScope.artisteSearch.region[i] = parseInt($rootScope.artisteSearch.region[i], 10);
        }

        var search = JSON.stringify({
            type: $scope.type+"]",
            localisation: $rootScope.artisteSearch.region+"]"
        })
        
        $http.get("/resource/artiste/search"+"/"+$scope.type+"/"+$scope.region).success(function (data, status) {
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
