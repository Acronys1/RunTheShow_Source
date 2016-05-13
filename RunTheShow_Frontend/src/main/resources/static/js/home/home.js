angular.module('home', []).controller('home', function ($scope, $http) {
    
    $scope.initFirst = function ()
    {
        $http.get('/resource/user/current').success(function (data) {
            $scope.user = data;
        });
        
        $http.get('/resource/artiste/types').success(function (data) {
            $scope.typesArtiste = data;
            console.log(data);
        });
        
        $http.get('/resource/artiste/regions').success(function (data) {
            $scope.regions = data;
            console.log(data);
        });
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
