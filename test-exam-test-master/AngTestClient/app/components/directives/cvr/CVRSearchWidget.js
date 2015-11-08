angular.module('myCVRSearchWidget', [])
        .controller('myCVRSearchWidgetController', ['$scope', '$http', function ($scope, $http) {
                var self = this;
                self.searchTypes = [
                    {"name": "Almindelig", "type": "search"},
                    {"name": "CVR", "type": "vat"},
                    {"name": "Navn", "type": "name"},
                    {"name": "Produktionsenhed", "type": "produ"},
                    {"name": "Telefon", "type": "phone"}
                ];

                self.searchContries = [
                    "DK",
                    "NO"
                ];




                self.searchFunction = function () {
                    var urls = "http://cvrapi.dk/api?" + self.searchType.type + "=" + self.searchCvr + "&country=" + self.searchContry.toLowerCase();

                    $.ajax({
                        type: "GET",
                        url: urls,
                        dataType: "JSONP",
                        jsonpCallback: 'callback',
                        success: function (response) {
                            alert(response.productionunits[0].name);
                            self.cvrData = response;
                        },
                        error: function (xhr, errorType, exception) {
                            var errorMessage = exception || xhr.statusText;
                            alert("Excep:: " + exception + "Status:: " + xhr.statusText);
                        }
                    });
//                    $http({
//                        type: "GET",
//                        url: "http://cvrapi.dk/api?"+ self.searchType.type +"=" + self.searchCvr + "&country="+ self.searchContry.toLowerCase()
//                    }).then(function succesCallback(response) {
//                        self.cvrData = response.data;
//                    }, function errorCallback(response) {
//                        self.cvrData = "No matches found";
//                    });
                };
            }])
        .directive('cvrSearchWidget', function () {
            return {
                templateUrl: "components/directives/CVRSearchWidgetTemplate.html",
                restrict: "EA"
            };
        });

