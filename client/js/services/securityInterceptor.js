app.factory('securityInterceptor',
    ['$injector', '$log', 'securityRetryQueue',
        function ($injector, $log, securityRetryQueue) {
            $log.info('init securityInterceptor');
            //var $http = $injector.get('$http');

            var securityInterceptor = {
                request: function(config) {
                    var authService = $injector.get('authService');
                    if (authService.isAuthorized()){
                        $log.debug(config);
                        //config.params = {'token': authService.getToken()};
                        config.params = config.params || {};
                        config.params['token'] = authService.getToken();
                        //config.headers['token'] = authService.getToken();
                        //config.headers['Content-Type'] = 'text/html;charset=utf-8';
                        //config.headers['Access-Control-Allow-Origin'] =  '*';
                        //config.headers['Access-Control-Allow-Credentials'] = 'true';
                        //config.headers['Access-Control-Expose-Headers'] = 'token';

                       // params.token = authService.getToken();
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