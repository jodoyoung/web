controllers.controller('MainController', [ '$scope', '$routeParams', '$location', '$log', function($scope, $routeParams, $location, $log) {
    $log.debug('[MainController] >>> Start >>>');
    
    var currentPath = $location.path().toUpperCase();
    $log.debug('[MainController] Currnet Path : ' + currentPath);
    
    $scope.isMenuActive = function(menu) {
        if(currentPath.indexOf(menu.toUpperCase()) > -1) {
            return true;
        }
        return false;
    };
    
    $scope.logout = function() {
        $log.debug('[MainController] Log Out !!!');
        location.href = '/auth/logout';
    };
    
    $scope.$on('navigation:changeMenu', function(e, currentMenu) {
        currentPath = currentMenu;
    });
} ]);