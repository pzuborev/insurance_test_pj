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