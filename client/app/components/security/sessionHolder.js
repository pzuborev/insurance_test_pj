app.factory('sessionHolder', ['$log', '$rootScope', '$cookies', function ($log, $rootScope, $cookies) {
    var tokenExpire = new Date();
    tokenExpire.setDate(tokenExpire.getDate() + 1);

    return {
        isAuthorized: function () {
            var token_ = $cookies.get('token');
            return !(token_ == null);
        },

        setToken: function (value) {
            $log.debug('*** set token = ' + value);
            if (value == null) {
                $cookies.remove('token');
                $cookies.remove('username');

            } else {
                $cookies.put('token', value, {expires: tokenExpire});
            }

            $rootScope.$broadcast('handleLogin');
        },

        getToken: function () {
            var token_ = $cookies.get('token');
            return token_;
        },

        setUserName: function (value) {
            $log.debug('*** set userName = ' + value);
            $cookies.put('username', value, {expires: tokenExpire});
        },

        getUserName: function () {
                var username_ = $cookies.get('username');
                return username_;
        }
    };


}]);