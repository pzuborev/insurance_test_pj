var app = angular.module('insuranceApp', ['ngRoute','ui.bootstrap']);
app.config(function ($routeProvider) {
    $routeProvider
        .when("/first", {
            templateUrl: "view/first.html",
            controller: "firstController"
        })
        .when("/second", {
            templateUrl: "view/second.html",
            controller: "secondController"
        })
        .when("/calculator", {
            templateUrl: "view/calculator.html",
            controller: "CalcController"
        })
});

app.controller('NavCtrl',
    ['$scope', '$location', function ($scope, $location) {
        $scope.navClass = function (page) {

            //console.log('************************');
            //console.log($location.path());
            //console.log($location.path().substring(1));

            var currentRoute = $location.path().substring(1) || 'home';
            return page === currentRoute ? 'active' : '';
        };
    }]);