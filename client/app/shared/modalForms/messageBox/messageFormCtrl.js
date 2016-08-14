app.controller('errorFormCtrl', function ($scope, $log, $uibModalInstance, messageService, message) {

    $scope.message = message;

    $scope.isError = function () {
        return ($scope.message.type == messageService.ERROR);
    };
    $scope.message.title = $scope.message.title || $scope.isError ? 'Ошибка' : '';

    $scope.ok = function () {
        $uibModalInstance.close();
    };

});