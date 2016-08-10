var app = angular.module('insuranceApp', ['ngRoute', 'ui.bootstrap', 'ngMessages', 'ngCookies']);
//-------------------------------------------------------------------------------
// добавляем перехвачик http ответов
app.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.interceptors.push(
        'securityInterceptor');
}]);
//-------------------------------------------------------------------------------
app.constant ('serverConfig', {
   'url': 'http://localhost',
   'port': '8080'
});
//-------------------------------------------------------------------------------
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
            templateUrl: "calculator/calculator.html",
            controller: "CalcController"
        })
});
//-------------------------------------------------------------------------------
// контролер главной страницы
app.controller('mainInsuranceCtrl',
    function ($scope, $rootScope, $uibModal, $log, $q, $location, sessionHolder, securityService) {
        $scope.isAuthorized = sessionHolder.isAuthorized();
        $scope.authorizedUserName = sessionHolder.getUserName();


        $scope.navClass = function (page) {
            var currentRoute = $location.path().substring(1) || 'home';
            return page === currentRoute ? 'active' : '';
        };

        $scope.login = function (size) {
            securityService.showLogin();
        };

        $scope.logout = function () {
            securityService.logout();
        };

        $scope.$on('handleLogin', function () {
            $log.debug('**************** on handleLogin');
            $scope.isAuthorized = sessionHolder.isAuthorized();
            $scope.authorizedUserName = sessionHolder.getUserName();
        });
    });

//pz test
app.controller('DropdownCtrl', function($scope) {

    $scope.items = [
        "The first choice!",
        "And another choice for you.",
        "but wait! A third!"
    ];
});