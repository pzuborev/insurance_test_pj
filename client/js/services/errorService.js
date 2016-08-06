app.factory('errorService', ['$log', '$q', '$http', '$uibModal',
    function ($log, $q, $http, $uibModal) {
        $log.log('initialize errorService');


        var service = {
            showError: function (errorMessage, size) {
                var modalInstance = $uibModal.open({
                    animation: true,
                    templateUrl: 'view/errorForm.tmpl.html',
                    controller: 'errorFormCtrl',
                    size: size,
                    resolve: {
                        error: function () {
                            return {message: errorMessage};
                        }
                    }
                });

            }
        };

        return service;

    }]);