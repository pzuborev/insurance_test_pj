app.service('authService', function ($log, $q, $http) {
    $log.log('initialize authService');

    var username;
    var password;
    var isAuthentication = false;

    this.isAuthentication = function () {
        return isAuthentication;
    };

    this.login = function (username, password) {
        var deferred = $q.defer();
        $http
            .get("http://localhost:8080/user/" + username, {params: {'username': 'admin', 'password': 'admin'}})
            .success(function (response, status, headers, config) {
                this.username = username;
                this.password = password;
                this.token = headers()['token'];
                if (token == null) {
                    isAuthentication = false;
                } else {
                    console.log('token = '+ this.token);
                    isAuthentication = true;
                }
                deferred.resolve(response, status, headers, config);
            })
            .error(function (response, status, headers, config) {
                deferred.reject(response, status, headers, config);
            });

        return deferred.promise;
    };

    var token = '1';
    this.getToken = function () {
        return token;
    };
});