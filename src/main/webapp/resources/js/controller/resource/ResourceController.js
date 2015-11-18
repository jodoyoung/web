controllers.controller('ResourceController', [ '$scope', '$http', '$routeParams', '$log', function($scope, $http, $routeParams, $log) {
    $log.debug('[ResourceController] >>> Start >>>');
    
    $http({
    	method: 'GET',
    	url: '/resource',
    	headers: { 'Accept': 'application/json; charset=utf-8' }
    })
    .success(function(result) {
    	$scope.resources = result;
    });
}]);