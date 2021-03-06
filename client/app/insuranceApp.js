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
            templateUrl: "components/first/first.html",
            controller: "firstController"
        })
        .when("/second", {
            templateUrl: "components/second/second.html",
            controller: "secondController"
        })
        .when("/calculator", {
            templateUrl: "components/calculator/calculator.html",
            controller: "CalcController"
        })
        .otherwise("/first", {
            templateUrl: "components/first/first.html",
            controller: "firstController"
        })
});
//-------------------------------------------------------------------------------
// контролер главной страницы
app.controller('mainInsuranceCtrl',
    function ($scope, $rootScope, $uibModal, $log, $q, $location, sessionHolder, securityService) {
        $log.debug('*** initialize mainInsuranceCtrl');
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