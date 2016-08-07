app.factory('sessionHolder', ['$log', '$rootScope', '$cookies', function ($log, $rootScope, $cookies) {
    //var username_;
    //var token_ = null;
    var  exp = new Date();
    exp.setDate(exp.getDate() + 1);

    return {
        isAuthorized: function () {
            var token_ = $cookies.get('token');
            return !(token_ == null);
        },

        setToken: function (value) {
            if (value == null) {
                $cookies.remove('token');
                $cookies.remove('username');
            } else {
                $cookies.put('token', value,{
                    expires: exp
                });
            }

            $rootScope.$broadcast('handleLogin');
        },

        getToken: function () {
            var token_ = $cookies.get('token');

            $log.debug('setToken = ' + token_);
            return token_;
        },

        setUserName: function (value) {
            $cookies.put('username', value);
        },

        getUserName: function () {
            var username_ = $cookies.get('username');
            return username_;
        }
    };


}]);