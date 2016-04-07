angular
        .module('app', ['ngRoute', 'auth', 'home', 'users', 'navigation', 'datatables', 'event', 'userprofile','signup','artist_presentation'])
        .config(
                function ($routeProvider, $httpProvider, $locationProvider) {

                    $locationProvider.html5Mode(true);

                    $routeProvider.when('/', {
                        templateUrl: 'js/home/home.html',
                        controller: 'home'
                    }).when('/users', {
                        templateUrl: 'js/users/users.html',
                        controller: 'users'
                    }).when('/event', {
                        templateUrl: 'js/event/event.html',
                        controller: 'event'
                    }).when('/login', {
                        templateUrl: 'js/navigation/login.html',
                        controller: 'navigation'
                    }).when('/userprofile', {
                        templateUrl: 'js/userprofile/userprofile.html',
                        controller: 'userprofile'
                    }).when('/artist_presentation', {
                        templateUrl: 'js/artist_presentation/artist_presentation.html',
                        controller: 'artist_presentation'
                    }).when('/invitation', {
                        templateUrl: 'js/event/invitation.html',
                        controller: 'invitation'
                    }).otherwise('/');

                    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

                    /**/

                }
        ).run(function (auth, $rootScope, DTOptionsBuilder) {

    // Initialize auth module with the home page and login/logout path
    // respectively
    auth.init('/', '/login', '/logout');

    $rootScope.dtOptions = DTOptionsBuilder.fromSource().withLanguageSource('/resources/French.json');

});
