'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
  'ngRoute',
  'ngAnimate',
  'ui.bootstrap',
  'myApp.security',
  'myApp.view1',
  'myApp.view2',
  'myApp.view3',
  'myApp.view4',
  'myApp.view5',
  'myApp.filters',
  'myApp.directives',
  'myApp.factories',
  'myApp.services',
  'myCVRSearchWidget',
  'myApp.routing',
  'myCurrencyWidget'
]).
config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/view1', {
    templateUrl: 'view1/view1.html',
    controller: 'View1Ctrl',
    controllerAs : 'ctrl'
  });
        $routeProvider.when('/view2', {
    templateUrl: 'view2/view2.html',
    controller: 'View2Ctrl',
    controllerAs : 'ctrl'
  });
        $routeProvider.when('/view3', {
    templateUrl: 'view3/view3.html',
    controller: 'myCVRSearchWidgetController',
    controllerAs : 'ctrl'
  });
        $routeProvider.when('/view4', {
    templateUrl: 'view4/view4.html',
    controller: 'myUserInformationCtrl',
    controllerAs : 'ctrl'
  });
  
        $routeProvider.when('/view5', {
    templateUrl: 'view5/view5.html',
    controller: 'myCVRSearchWidgetController',
    controllerAs : 'ctrl'
  });
  
  
  
  $routeProvider.otherwise({redirectTo: '/view1'});
}]).
config(function ($httpProvider) {
   $httpProvider.interceptors.push('authInterceptor');
});


