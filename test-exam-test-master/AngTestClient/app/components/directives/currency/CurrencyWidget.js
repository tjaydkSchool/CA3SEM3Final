angular.module('myCurrencyWidget', [])
        .controller('myCurrencyWidgetController', ['$scope', '$http', function ($scope, $http) {
                var self = this;
                self.result = 0;
                self.currencies = [
                    {"name": "helle"}
                ];

                $http.get("api/user/currency")
                        .success(function (response) {
                            self.currencies = response;
                        });

                self.calculate = function (amount, to, from) {
                    self.result = (amount * (from / to)).toFixed(2);
                };

            }])
        .directive('currencyWidget', function () {
            return {
                templateUrl: "components/directives/currency/CurrencyWidgetTemplate.html",
                restrict: "EA"
            };
        });

