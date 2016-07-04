var app = angular.module('a1', ['ngRoute', 'ui.bootstrap']);

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
});