app.controller('firstController', function ($scope, $log, serviceTest){
   $log.log('initialize firstController  ');

   $scope.helloMessage = serviceTest.getHello();

});