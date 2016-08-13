app.controller('errorModalFormCtrl', function ($scope, $uibModalInstance, error) {
    $scope.ok = function () {
        $uibModalInstance.close();
    };

});