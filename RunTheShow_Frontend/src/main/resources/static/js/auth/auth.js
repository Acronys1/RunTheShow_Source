angular.module('auth', ['home']).factory(
        'auth',
        function ($rootScope, $http, $location) {

            //Peut servir à restreindre l'accès au page
            /*enter = function() {
             if ($location.path() == auth.loginPath) {
             auth.path = $location.path();
             if (!auth.authenticated) {
             $location.path(auth.loginPath);
             }
             }					
             }*/

            var auth = {
                authenticated: false,
                loginPath: '/login',
                logoutPath: '/logout',
                homePath: '/',
                path: $location.path(),
                authenticate: function (credentials, callback) {

                    var headers = credentials && credentials.username ? {
                        authorization: "Basic "
                                + btoa(credentials.username + ":"
                                        + credentials.password)
                    } : {};

                    $http.get('user', {
                        headers: headers
                    }).success(function (data) {
                        if (data.name) {

                            angular.forEach(data.authorities, function (authorities) {
                                if (authorities.authority === 'ROLE_ADMIN') {
                                    auth.isAdmin = true;
                                    $rootScope.isAdmin = true;
                                } else {
                                    auth.isAdmin = false;
                                    $rootScope.isAdmin = false;
                                }
                            })

                            //on positionne les flags en rootScope pour les utiliser dans le header
                            auth.authenticated = true;
                            $rootScope.authenticated = true;
                            $rootScope.name = data.name;
                            
                        } else {
                            auth.authenticated = false;
                            $rootScope.authenticated = false;
                        }
                        callback && callback(auth.authenticated);
                        $location.path(auth.path == auth.loginPath ? auth.homePath : auth.path);
                    }).error(function () {
                        auth.authenticated = false;
                        callback && callback(false);
                    });

                },
                clear: function () {
                    $location.path(auth.homePath);
                    auth.authenticated = false;
                    $http.post(auth.logoutPath, {}).success(function () {
                        console.log("Logout succeeded");
                        location.reload();
                    }).error(function (data) {
                        console.log("Logout failed");
                    });
                },
                init: function (homePath, loginPath, logoutPath, DTOptionsBuilder) {

                    auth.homePath = homePath;
                    auth.loginPath = loginPath;
                    auth.logoutPath = logoutPath;

                    auth.authenticate({}, function (authenticated) {
                        if (authenticated) {
                            $location.path(auth.path);
                        }
                    })

                    // Guard route changes and switch to login page if unauthenticated
                    $rootScope.$on('$routeChangeStart', function () {
                        enter();
                    });
                }

            };

            return auth;

        });
