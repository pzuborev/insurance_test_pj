app.controller('loginModalCtrl', function ($scope, $uibModalInstance, $location, $q, securityService, username) {

    $scope.username = username;
    $scope.password;

    $scope.ok = function () {
        securityService.authentication($scope.username, $scope.password)
            .then(function () {
                    $uibModalInstance.close({'username': $scope.username, 'password': $scope.password});
                },
                function () {
                    alert('Не корректны имя пользователя или пароль.');
                });
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});