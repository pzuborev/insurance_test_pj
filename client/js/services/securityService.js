app.factory('securityService', ['$log', '$q', '$http', '$uibModal', 'securityRetryQueue', 'sessionHolder',
    function ($log, $q, $http, $uibModal, securityRetryQueue, sessionHolder) {
        $log.log('initialize securityService');

        var loginInProgress = false;

        // Register a handler for when an item is added to the retry queue
        securityRetryQueue.onItemAddedCallbacks.push(function (retryItem) {
            if (securityRetryQueue.hasMore()) {
                service.showLogin();
            }
        });


        var service = {
            authentication: function (username, password) {
                var deferred = $q.defer();
                $http
                    .get("http://localhost:8080/user/" + username, {
                        headers: {
                            'username': 'admin',
                            'password': 'admin'
                        }
                    })
                    .success(function (response, status, headers, config) {
                        sessionHolder.setUserName(username);
                        sessionHolder.setToken(headers()['token']);

                        deferred.resolve(response, status, headers, config);
                    })
                    .error(function (response, status, headers, config) {
                        deferred.reject(response, status, headers, config);
                    });

                return deferred.promise;
            },

            logout: function () {
                var deferred = $q.defer();
                $http
                    .get("http://localhost:8080/logout/", {params: {'token': getToken()}})
                    .success(function (response, status, headers, config) {
                        sessionHolder.setToken(null);
                        deferred.resolve(response, status, headers, config);
                    })
                    .error(function (response, status, headers, config) {
                        deferred.reject(response, status, headers, config);
                    });

                return deferred.promise;
            },

            showLogin: function (size) {
                if (loginInProgress) return;

                $log.info('run showLogin');

                loginInProgress = true;
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
                            return '-';
                        }
                    }
                });

                modalInstance.result.then(
                    function (result) {
                        $log.debug('showLogin : username: ' + result.username);
                        $log.debug('showLogin : password: ' + result.password);
                        loginInProgress = false;
                        securityRetryQueue.retryAll();
                    },
                    function () {
                        $log.debug('Modal dismissed at: ' + new Date());
                        loginInProgress = false;
                        securityRetryQueue.cancelAll();

                    });
            }
        };

        return service;

    }]);