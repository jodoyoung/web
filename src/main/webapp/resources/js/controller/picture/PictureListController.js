controllers.controller('PictureListController', [ '$scope', '$routeParams', '$resource', '$log', function($scope, $routeParams, $resource, $log) {
    $log.debug('[PictureListController] >>> Start >>>');
    
    $scope.$emit('navigation:changeMenu', 'PICTURE');
    
    $log.debug('[PictureListController] Directory Path : ' + $routeParams.path);
    
    if($routeParams.path) {
        var DirectoryResource = $resource('/picture/api/directory/:path');
        
        DirectoryResource.get({path: $routeParams.path}, function(result) {
            $scope.directories = result.directories;
            $scope.thumbnails = result.thumbnails;
            $scope.videos = result.videos;
        });
    } else {
        var RootDirectoryResource = $resource('/picture/api/directory');
        
        RootDirectoryResource.get({}, function(result) {
            $scope.directories = result.directories;
            $scope.thumbnails = result.thumbnails;
            $scope.videos = result.videos;
        });
    }
    
    $scope.previewImage = function(name, path) {
       $scope.previewImageName = name;
       $scope.previewImagePath = path;
    };
    
    $scope.previewVideo = function(name, path) {
    	$('#video').html("<video width='100%' height='auto' controls autoplay src='/video" + encodeURI(path) + "'>Your browser does not support video</video>");
    };
    
} ]);