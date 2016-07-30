var app = angular.module('insuranceApp', ['ngRoute', 'ui.bootstrap']);
//-------------------------------------------------------------------------------
// добавляем перехвачик http ответов
app.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.interceptors.push(
        'securityInterceptor');
}]);
//-------------------------------------------------------------------------------
//
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

//-------------------------------------------------------------------------------
// контролер главной страницы
app.controller('mainInsuranceCtrl',
    function ($scope, $rootScope, $uibModal, $log, $q, $location, authService) {
        $scope.isAuthorized = false;
        $scope.authorizedUserName = '';


        $scope.navClass = function (page) {
            var currentRoute = $location.path().substring(1) || 'home';
            return page === currentRoute ? 'active' : '';
        };

        $scope.login = function (size) {
            authService.showLogin();
        };

        $scope.logout = function () {
            authService.logout();
        };

        $scope.$on('handleLogin', function () {
            $scope.isAuthorized = authService.isAuthorized();
            $scope.authorizedUserName = authService.getUserName();
        });
    });