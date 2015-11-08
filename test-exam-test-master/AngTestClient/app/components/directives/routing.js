angular.module('myApp.routing', [])
        .controller('appRoutingCtrl', function ($location) {

            var routView2 = function () {
                $location.path("/view3");
            }.
                    factory('authInterceptor', function ($rootScope, $q, $window) {
                        return {
                            request: function (config) {
                                config.headers = config.headers || {};
                                if ($window.sessionStorage.token) {
                                    config.headers.Authorization = 'Bearer ' + $window.sessionStorage.token;
                                }
                                $rootScope.error = "";
                                return config;
                            },
                            responseError: function (rejection) {
                                var err = rejection.data;
                                if (err == null) {
                                    $rootScope.error = "Unknown error while trying to connect to the server";
                                    var err = "\n########################################################################\n" +
                                            "Unknown error while trying to connect to the server\n" +
                                            "Did you execute the AngularSeedClient project, and not The AngularSeedServer Project?\n" +
                                            "#######################################################################\n";
                                    throw err;
                                    return;
                                }
                                $rootScope.error = err.error.code + ": ";

                                if (err.error.code === 401) {
                                    $rootScope.error += " You are are not Authenticated - did you log on to the system"
                                }
                                else {
                                    $rootScope.error += err.error.message;
                                }
                                if (err.error.code === 403) {

                                }

                                return $q.reject(rejection);
                            }

                        };
                    });
        });