app.factory('securityInterceptor',
    ['$injector', '$log', 'securityRetryQueue', 'sessionHolder', 'serverConfig',
        function ($injector, $log, securityRetryQueue, sessionHolder, serverConfig) {
            $log.info('init securityInterceptor');
            var prefix = serverConfig.url + ':' + serverConfig.port;

            function urlContains(url, searchText) {
                return url.indexOf(searchText) !== -1;
            }

            var securityInterceptor = {

                request: function (config) {

                    if (urlContains(config.url, '/api/')) {
                        if (!urlContains(config.url, prefix)){
                            $log.debug("added rest domain prefix to url");
                            config.url = prefix + config.url;
                        }
                        if (sessionHolder.isAuthorized()){
                            $log.debug("added token to header");
                            config.headers['token'] = sessionHolder.getToken();
                        }
                    }


                    return config;
                },

                responseError: function (response) {

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