/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module('artist_cv_consult', []).controller('artist_cv_consult', function ($scope, $http, $rootScope, $window, $filter, $sce, $routeParams) {

    $scope.artist = {};
    $scope.ArtistID = null;

    $scope.initArtistCvConsult = function () {
        console.log("Ctr artist_cv_consult");
        console.log("Artiste ID = " + $routeParams.artistId);
        $scope.ArtistID = $routeParams.artistId;

        if ($scope.ArtistID != null) {
            //récupère les informations de l'artiste:
            $http.get('/resource/artiste/' + $scope.ArtistID).success(function (data, status) {
                $scope.artist = data;
                console.log("Récupération artiste OK");
                $scope.initImgProfile();
                $scope.initBanner();                
            }).error(function (data, status) { // called asynchronously if an error occurs
                // or server returns response with an error status.
                $scope.error = true;
                console.log("Récupération regions KO");
            });
        }
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

});

