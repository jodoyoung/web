var Anajo = angular.module('Anajo', [ 'ngRoute', 'ngResource', 'controllers' ]);

Anajo.config([ '$routeProvider', '$locationProvider', '$logProvider', function($routeProvider, $locationProvider, $logProvider) {
    $routeProvider
    	.when('/', { redirectTo : '/home' })
    	.when('/home', { templateUrl : '/resources/view/home.html', controller: 'HomeController' })
    	.when('/resource', { templateUrl: '/resources/view/resource/resource.html', controller: 'ResourceController' })
    	.when('/create', { templateUrl: '/resources/view/resource/create.html', controller: 'ResourceCreateController' })
        .when('/picture', { templateUrl: '/resources/view/picture/list.html', controller: 'PictureListController' })
        .when('/picture/directory/:path*', { templateUrl: '/resources/view/picture/list.html', controller: 'PictureListController' })
        .otherwise({ redirectTo : '/' });
    
    $locationProvider.html5Mode({
    	enabled: true,
    	requireBase: false
    });
    
    $logProvider.debugEnabled(true);
} ]);

Anajo.directive('imageLazyLoad', function($timeout) {
    return function(scope, element, attrs) {
        if(scope.$last) {
            $timeout(function() { 
                angular.element('img.lazy').lazyload({
                    thresold: 200,
                    placeholder: '/resources/img/loading.gif'
                });
            });
        }
    };
});

var controllers = angular.module('controllers', []);