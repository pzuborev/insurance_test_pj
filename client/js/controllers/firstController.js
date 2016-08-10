app.controller('firstController', function ($scope, $log, serviceTest){
   $log.log('initialize firstController  ');

   $scope.helloMessage = serviceTest.getHello();
   $scope.test = function () {
      var test = "http://localhost:8080/api/username/test";

   };

});