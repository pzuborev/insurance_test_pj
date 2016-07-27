app.controller('loginModalCtrl', function ($scope, $uibModalInstance, $location, $q, authService, username) {

    $scope.username = username;
    $scope.password;

    $scope.ok = function () {
        authService.login($scope.username, $scope.password)
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