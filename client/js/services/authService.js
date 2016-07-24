app.service('authService', function ($log) {
    $log.log('initialize authService');
    var token = '1';
    this.getToken = function () {
        return token;
    };
});