controllers.controller('PictureListController', [ '$scope', '$routeParams', '$resource', '$log', function($scope, $routeParams, $resource, $log) {
    $log.debug('[PictureListController] >>> Start >>>');
    
    $scope.$emit('navigation:changeMenu', 'PICTURE');
    
    $log.debug('[PictureListController] Directory Path : ' + $routeParams.path);
    
    if($routeParams.path) {
        var DirectoryResource = $resource('/picture/api/directory/:path');
        
        DirectoryResource.get({path: $routeParams.path}, function(result) {
            $scope.directories = result.directories;
            $scope.thumbnails = result.thumbnails;
        });
    } else {
        var RootDirectoryResource = $resource('/picture/api/directory');
        
        RootDirectoryResource.get({}, function(result) {
            $scope.directories = result.directories;
            $scope.thumbnails = result.thumbnails;
        });
    }
    
    $scope.previewImage = function(name, path) {
       $scope.previewImageName = name;
       $scope.previewImagePath = path;
    };
    
} ]);