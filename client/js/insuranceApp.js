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
    function ($scope, $rootScope, $uibModal, $log, $q, $location, authService) {
        $scope.isAuthorized = false;
        $scope.authorizedUserName = '';
        $scope.items = [
            'The first choice!',
            'And another choice for you.',
            'but wait! A third!'
        ];

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
                        return '11';
                    }
                }
            });

            modalInstance.result.then(
                function (result) {
                    console.log('username: ' + result.username);
                    console.log('password: ' + result.password);
                    console.log('password: ' + result.password);
                },
                function () {
                    console.log('Modal dismissed at: ' + new Date());
                });


        };

        $scope.logout = function (){
           authService.logout ();
        };

        $scope.$on('handleLogin', function() {
            $scope.isAuthorized         = authService.isAuthorized();
            $scope.authorizedUserName   = authService.getUserName();
        });
    });