var app = angular.module('insuranceApp', ['ngRoute', 'ui.bootstrap']);
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

app.controller('mainInsuranceCtrl',
    function ($scope, $uibModal, $log, $q, $location) {
        $scope.navClass = function (page) {

            //console.log('************************');
            //console.log($location.path());
            //console.log($location.path().substring(1));

            var currentRoute = $location.path().substring(1) || 'home';
            return page === currentRoute ? 'active' : '';
        };

        $scope.login = function (size) {
            console.log("loginExecute");
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'view/loginModalForm.html',
                controller: 'loginModalCtrl',
                size: size,
                resolve: {
                    username: function () {
                        return 'admin';
                    },
                    password: function () {
                        return '';
                    }
                }
            });

            modalInstance.result.then(
                function (result) {
                    console.log('username: ' + result.username);
                    console.log('password: ' + result.password);
                },
                function () {
                    console.log('Modal dismissed at: ' + new Date());
                });


        };
    });