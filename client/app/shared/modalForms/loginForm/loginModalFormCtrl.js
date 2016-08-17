app.controller('loginModalCtrl', function ($scope, $log, $uibModalInstance, $location, $q, securityService, sessionHolder, username) {

    $scope.username = username;
    $scope.password;
    $scope.errorMessage = "";
    $scope.errorDebugMessage = "";

    $scope.ok = function () {
        securityService.authentication($scope.username, $scope.password)
            .then(function (response) {
                    $log.debug('performed securityService.authentication')
                    if (sessionHolder.isAuthorized()) {
                        $uibModalInstance.close();
                    }
                    else {
                        $scope.errorMessage = 'Не удалось аутентифицировать пользователя.';
                        $scope.errorDebugMessage = (response.data && response.data.message) || '';
                    }

                },
                function (response) {
                    $scope.errorMessage('Не корректны имя пользователя или пароль.');
                    $scope.errorDebugMessage = $scope.errorDebugMessage = (response.data && response.data.message) || '';
                });
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});