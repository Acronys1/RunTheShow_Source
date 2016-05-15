/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module('artist_cv_consult', []).controller('artist_cv_consult', function ($scope, $http, $rootScope, $window, $filter, $sce, $routeParams) {
    
    $scope.initArtistCvConsult = function (){
        console.log("Ctr artist_cv_consult");
        console.log("Artiste ID = "+$routeParams.artistId);
    };
    
});

