var app = angular.module('insuranceApp', ['ngRoute', 'ui.bootstrap', 'ngMessages']);
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
//app.directive('toNumber', function () {
//        return {
//            require: 'ngModel',
//            link: function (scope, elem, attrs, ctrl) {
//                ctrl.$parsers.push(function (value) {
//                    return parseFloat(value || '');
//                });
//            }
//        }
//    }
//);
//-------------------------------------------------------------------------------
// контролер главной страницы
app.controller('mainInsuranceCtrl',
    function ($scope, $rootScope, $uibModal, $log, $q, $location, sessionHolder, securityService) {
        $scope.isAuthorized = false;
        $scope.authorizedUserName = '';


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
            $scope.isAuthorized = sessionHolder.isAuthorized();
            $scope.authorizedUserName = sessionHolder.getUserName();
        });
    });