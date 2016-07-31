app.factory('securityInterceptor',
    ['$injector', '$log', 'securityRetryQueue', 'sessionHolder',
        function ($injector, $log, securityRetryQueue, sessionHolder) {
            $log.info('init securityInterceptor');

            var securityInterceptor = {
                request: function(config) {
                    if (sessionHolder.isAuthorized()){
                        config.headers['token'] = sessionHolder.getToken();
                    }
                    return config;
                },

                responseError: function(response) {

                        var $http = $injector.get('$http');
                        $log.info('response.status = ' + response.status);
                        var promise = null;
                        if (response.status === 401) {
                            promise = securityRetryQueue.pushRetryFn('unauthorized-server',
                                function () {
                                    return $http(response.config);
                                }
                            );
                            return promise;
                        }
                        return response;
                }

            };
            return securityInterceptor;

            //return function (promise) {
            //
            //};
        }]);