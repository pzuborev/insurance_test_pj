app.controller('secondController', function ($log, serviceTest){
    $log.log('initialize secondController' + serviceTest.getHello());
});