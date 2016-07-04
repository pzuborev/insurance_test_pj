app.service('serviceTest', function ($log){
    $log.log('initialize serviceTest');

    var counter = 0;

    this.getHello = function () {
        return "Hello " + (++counter);
    }
});
