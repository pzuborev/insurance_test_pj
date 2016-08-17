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
                    .get("/api/user/login",
                        {headers: {'username': username, 'password': password || username}}
                    )
                    .then(function (response) {
                            $log.debug('*** finished authentication');

                            var token = response && response.headers && response.headers()['token'];
                            $log.debug(response);
                            $log.debug(token);
                            $log.debug(response.status);
                            if (response.status == 401) {
                                $log.error("authentication failed. response = " + response);
                                sessionHolder.setToken(null);
                            } else if (token == null) {
                                $log.error("authentication failed. response = " + response);
                                sessionHolder.setToken(null);
                            } else {
                                $log.debug('authentication success token = ' + token);
                                sessionHolder.setUserName(username);
                                sessionHolder.setToken(token);
                            }

                            deferred.resolve(response);
                        },
                        function (response) {
                            $log.debug('*** failure authentication');
                            deferred.reject(response);
                        })
                ;

                return deferred.promise;
            },

            logout: function () {
                var deferred = $q.defer();
                $http
                    .get("/api/logout/")
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
                    templateUrl: 'shared/modalForms/loginForm/loginModalForm.tpl.html',
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
                    function () {
                        //todo return to previous page
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