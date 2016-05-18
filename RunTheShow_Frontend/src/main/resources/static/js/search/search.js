angular.module('search', []).controller('search', function ($rootScope, $scope, $http) {

    $scope.initFirst = function ()
    {
        $scope.type = '';
        $scope.region = '';

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
});
