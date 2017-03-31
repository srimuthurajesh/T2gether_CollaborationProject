console.log("start of chat controller")
app.controller("ChatController", function($scope,$rootScope, ChatService) {
  $scope.messages = [];
  $scope.message = "";
  $scope.max = 140;

  $scope.addMessage = function() {
    ChatService.send(  $rootScope.currentUser.firstname+" : " +$scope.message);
    $scope.message = "";
  };

  ChatService.receive().then(null, null, function(message) {
    $scope.messages.push(message);
  });
});