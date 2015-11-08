'use strict';

angular.module('myApp.view4', ['ngRoute'])
        .controller('myUserInformationCtrl', ['$scope', '$http', function ($scope, $http) {
                var self = this;
                $http({
                    type: "GET",
                    url: "api/admin/user/complete"
                }).then(function succesCallback(response) {
                    self.userData = response.data;
                }, function errorCallback(response) {
                    self.userData = "No matches found";
                });
                self.userDelete = function (id) {
                    $http
                            .delete("admin/delete/"+id)
                };
            }])
        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view4', {
                    templateUrl: 'view4/view4.html'
                });
            }]);

        