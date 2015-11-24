controllers.controller('ChatController', [ '$scope', '$routeParams', '$log', function($scope, $routeParams, $log) {
    $log.debug('[ChatController] >>> Start >>>');
    
    $scope.ws = new WebSocket('ws://localhost/chat');
    
    $scope.ws.onopen = function() {
    	$log.debug('websocket opened');
    }
    
    $scope.ws.onmessage = function(message) {
    	$log.debug(message);
    	$log.debug('receive message: ' + message.data);
    }
    
    $scope.ws.onclose = function(event) {
    	$log.debug(event);
    	$log.debug('websocket closed');
    }
}]);