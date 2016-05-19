angular
        .module('app', ['ngRoute', 'auth', 'home', 'users', 'navigation', 'datatables', 'demo', 'event', 'userprofile', 'signup', 'artist_cv_edit', 'artist_cv_consult', 'xeditable', 'disableAll', 'file', 'calendarDemoApp', 'myEvent', 'updateEvent'])
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
                    }).when('/myEvent/:param', {
                        templateUrl: 'js/event/myEvent.html',
                        controller: 'myEvent'
                    }).when('/updateEvent/:param', {
                        templateUrl: 'js/event/updateEvent.html',
                        controller: 'updateEvent'
                    }).when('/login', {
                        templateUrl: 'js/navigation/login.html',
                        controller: 'navigation'
                    }).when('/userprofile', {
                        templateUrl: 'js/userprofile/userprofile.html',
                        controller: 'userprofile'
                    }).when('/artist_cv_edit', {
                        templateUrl: 'js/artist_cv_edit/artist_cv_edit.html',
                        controller: 'artist_cv_edit'
                    }).when('/artist_cv_consult/:artistId', {
                        templateUrl: 'js/artist_cv_consult/artist_cv_consult.html',
                        controller: 'artist_cv_consult'
                    }).when('/invitation', {
                        templateUrl: 'js/event/invitation.html',
                        controller: 'DemoCtrl'
                    }).when('/file', {
                        templateUrl: 'js/file/file_example.html',
                        controller: 'file'
                    }).when('/accueil', {
                        templateUrl: 'js/home/accueil.html'
                    }).otherwise('/');

                    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

                    /**/

                }
        ).run(function (auth, $rootScope, DTOptionsBuilder, editableOptions) {

    // Initialize auth module with the home page and login/logout path
    // respectively
    auth.init('/', '/login', '/logout');

    $rootScope.dtOptions = DTOptionsBuilder.fromSource().withLanguageSource('/resources/French.json');

    //xeditable
    editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2', 'default'

});
