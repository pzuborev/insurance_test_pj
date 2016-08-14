app.factory('messageService', ['$log', '$q', '$http', '$uibModal',
    function ($log, $q, $http, $uibModal) {
        $log.log('initialize messageService');


        var service = {
            ERROR: "error",
            show: function (messageText, messageType, size) {
                $log.debug('*** showError ');
                var modalInstance = $uibModal.open({
                    animation: true,
                    templateUrl: 'shared/modalForms/messageBox/messageForm.tpl.html',
                    controller: 'errorFormCtrl',
                    size: size,
                    resolve: {
                        message: function () {
                            return {text: messageText, type: messageType};
                        }
                    }
                });

            }
        };

        return service;

    }]);