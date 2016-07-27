app.service('authService', function ($log, $q, $http, $rootScope) {
    $log.log('initialize authService');

    var username_;
    var token_ = null;

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
            .get("http://localhost:8080/logout/" , {params: {'token': getToken()}})
            .success(function (response, status, headers, config) {
                setToken(null);
                deferred.resolve(response, status, headers, config);
            })
            .error(function (response, status, headers, config) {
                deferred.reject(response, status, headers, config);
            });

        return deferred.promise;
    }
    return {
        isAuthorized: isAuthorized,
        setToken: setToken,
        getToken: getToken,
        setUserName: setUserName,
        getUserName: getUserName,
        login: login,
        logout: logout
    };


});