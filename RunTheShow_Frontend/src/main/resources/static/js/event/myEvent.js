var myEvent = angular.module('myEvent', []);

myEvent.controller('myEvent', function ($scope, $routeParams, $http) {
    $scope.param = $routeParams.param;
    
    $scope.initFirst = function ()
    {
        $http.get('/resource/event/'+$scope.param).success(function (data) {
            $scope.oneEvent = data;
            
            $http.get('/resource/sousEvent/filter/'+$scope.param).success(function (data) {
                $scope.allSousEventForOneEvent = data;
            });
        });
    };
});

myEvent.directive('changesign', [function() {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            element.on("click", function (e) {
                
                var array = attrs.id.split('-');
                var ele = document.getElementById("contentDivImg-" + array[1]);
                var imageEle = document.getElementById("imageDivLink-" + array[1]);
                
                if(ele.style.display == "block") {
                        ele.style.display = "none";
                        imageEle.innerHTML = '<img src="img/plus.png">';
                }
                else {
                        ele.style.display = "block";
                        imageEle.innerHTML = '<img src="img/minus.png">';
                }
            });
        }
    };
}]);
