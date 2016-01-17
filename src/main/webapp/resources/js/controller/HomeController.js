	controllers.controller('HomeController', [ '$scope', '$http', '$routeParams', '$log', function($scope, $http, $routeParams, $log) {
    $log.debug('[HomeController] >>> Start >>>');
    
    $http({
    	method: 'GET',
    	url: '/menu/list',
    	headers: { 'Accept': 'application/json; charset=utf-8' }
    })
    .success(function(result) {
    	$scope.menus = result;
    });
} ]);