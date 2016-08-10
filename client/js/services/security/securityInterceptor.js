app.factory('securityInterceptor',
    ['$injector', '$log', 'securityRetryQueue', 'sessionHolder', 'serverConfig',
        function ($injector, $log, securityRetryQueue, sessionHolder, serverConfig) {
            $log.info('init securityInterceptor');

            var securityInterceptor = {

                request: function(config) {
                    var prefix = serverConfig.url + ':' + serverConfig.port;
                    //$log.debug('request config.url =' + config.url);
                    if (config.url.indexOf('/api/') != -1 && config.url.indexOf(prefix) == -1) {
                        config.url = prefix + config.url;
                        //$log.debug('changed config.url =' + config.url);
                    }

                    if (sessionHolder.isAuthorized()){
                        config.headers['token'] = sessionHolder.getToken();
                    }
                    return config;
                },

                responseError: function(response) {

                        var $http = $injector.get('$http');
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


        }]);