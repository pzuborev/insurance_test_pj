app.factory('authService', ['$log', '$q', '$http', '$rootScope', '$uibModal','securityRetryQueue',
    function ($log, $q, $http, $rootScope, $uibModal, securityRetryQueue) {
        $log.log('initialize authService');

        var username_;
        var token_ = null;

        // Register a handler for when an item is added to the retry queue
        securityRetryQueue.onItemAddedCallbacks.push(function(retryItem) {
            if ( securityRetryQueue.hasMore() ) {
                showLogin();
            }
        });


        function isAuthorized() {
            return !(token_ == null);
        }

        function setToken(value) {
            token_ = value;
            $rootScope.$broadcast('handleLogin');
        }

        function getToken() {
            return token_;
        }

        function setUserName(value) {
            username_ = value;
        }

        function getUserName() {
            return username_;
        }

        function login(username, password) {
            var deferred = $q.defer();
            $http
                .get("http://localhost:8080/user/" + username, {params: {'username': 'admin', 'password': 'admin'}})
                .success(function (response, status, headers, config) {
                    setUserName(username);
                    setToken(headers()['token']);

                    deferred.resolve(response, status, headers, config);
                })
                .error(function (response, status, headers, config) {
                    deferred.reject(response, status, headers, config);
                });

            return deferred.promise;
        }

        function logout() {
            var deferred = $q.defer();
            $http
                .get("http://localhost:8080/logout/", {params: {'token': getToken()}})
                .success(function (response, status, headers, config) {
                    setToken(null);
                    deferred.resolve(response, status, headers, config);
                })
                .error(function (response, status, headers, config) {
                    deferred.reject(response, status, headers, config);
                });

            return deferred.promise;
        }
        function showLogin(size) {
            $log.info('run showLogin');
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
                    securityRetryQueue.retryAll();
                },
                function () {
                    securityRetryQueue.cancelAll();
                    $log.debug('Modal dismissed at: ' + new Date());
                });
        }
        return {
            isAuthorized: isAuthorized,
            setToken: setToken,
            getToken: getToken,
            setUserName: setUserName,
            getUserName: getUserName,
            login: login,
            logout: logout,
            showLogin: showLogin
        };


    }]);