app.factory('securityRetryQueue',
    ['$q', '$log',
        function ($q, $log) {
            $log.info('init securityRetryQueue');
            var retryQueue = [];
            var service =  {
                onItemAddedCallbacks: [],

                hasMore: function () {
                    return (retryQueue.length > 0);
                },

                retryAll: function () {
                    while (service.hasMore()) {
                        retryQueue.shift().retry();
                    }
                },

                cancelAll: function() {
                    while(service.hasMore()) {
                        retryQueue.shift().cancel();
                    }
                },

                pushRetryFn: function (reason, retryFn) {
                    var deferredRetry = $q.defer();
                    var retryItem = {
                        reason: reason,
                        retry: function () {
                            $q.when(retryFn()).then(function (value) {
                                deferredRetry.resolve(value);
                            }, function (value) {
                                deferredRetry.reject(value);
                            });
                        },
                        cancel: function () {
                            deferredRetry.reject();
                        }
                    };
                    service.push(retryItem);
                    return deferredRetry.promise;
                },

                push: function (retryItem) {
                    $log.info('securityRetryQueue.push');
                    retryQueue.push(retryItem);
                    angular.forEach(service.onItemAddedCallbacks, function (cb) {
                        try {
                            cb(retryItem);
                        } catch (e) {
                            $log.error('securityRetryQueue.push(retryItem): callback threw an error' + e);
                        }
                    })
                }

            };

            return service;

        }

    ]);