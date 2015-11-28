controllers.controller('ChatController', [ '$scope', '$routeParams', '$log', '$timeout', function($scope, $routeParams, $log, $timeout) {
    $log.debug('[ChatController] >>> Start >>>');
    
    $scope.message = '';
    $scope.ws;
    $scope.echoMessages = [];
    
    $scope.ws = new WebSocket('ws://localhost/chat');
    
    $scope.ws.onopen = function() {
    	$log.debug('websocket opened');
    }
    
    $scope.ws.onmessage = function(message) {
    	$log.debug(message);
    	$log.debug('receive message: ' + message.data);
    	$scope.echoMessages.unshift(message.data);
        $timeout(function() {
        	$scope.$apply('echoMessages');
        })
    }
    
    $scope.ws.onclose = function(event) {
    	$log.debug(event);
    	$log.debug('websocket closed');
    }

    $scope.send = function() {
    	$scope.ws.send($scope.message);
    };
}]);