angular
        .module('app', ['ngRoute', 'auth', 'home', 'users', 'navigation', 'datatables'])
        .config(
                function ($routeProvider, $httpProvider, $locationProvider) {

                    $locationProvider.html5Mode(true);

                    $routeProvider.when('/', {
                        templateUrl: 'js/home/home.html',
                        controller: 'home'
                    }).when('/users', {
                        templateUrl: 'js/users/users.html',
                        controller: 'users'
                    }).when('/login', {
                        templateUrl: 'js/navigation/login.html',
                        controller: 'navigation'
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
