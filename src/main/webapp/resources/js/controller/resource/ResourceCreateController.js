controllers.controller('ResourceCreateController', [ '$scope', '$routeParams', '$log', function($scope, $routeParams, $log) {
    $log.debug('[ResourceCreateController] >>> Start >>>');
    
    $scope.types = [
        {id: 'MEMBER', name: '회원'},
        {id: 'MENU', name: '메뉴'},
        {id: 'ROLE', name: '권한'},
        {id: 'IMAGE', name: '사진'},
        {id: 'VIDEO', name: '동영상'},
        {id: 'POST', name: '게시글'}
	];
}])
.controller('CreateMemberController', [ '$scope', '$http', '$log', function($scope, $http, $log) {
	$scope.createMember = function() {
		$log.debug($scope.Member);
		
		$http({
			method: 'PUT',
			url: '/',
			data: $scope.Member,
			headers: {
				'Content-Type': 'application/json; charset=utf-8',
				'X-Resource-Type': $scope.type
			}
		})
		.success(function(result) {
			alert('member create success.');
		});
	}
}])
.directive('createmember', function() {
	return {
		restrict: 'E',
		controller: 'CreateMemberController',
		templateUrl: '/resources/view/resource/create_member.html'
	};
})
.controller('CreateMenuController', [ '$scope', '$http', '$log', function($scope, $http, $log) {
	$scope.visibility = [
        {id: 'VISIBLE', name: '노출'},
        {id: 'DISABLE', name: '숨김'}
	];
	
	$scope.createMenu = function() {
		$log.debug($scope.Menu);
		
		$http({
			method: 'PUT',
			url: '/',
			data: $scope.Menu,
			headers: {
				'Content-Type': 'application/json; charset=utf-8',
				'X-Resource-Type': $scope.type
			}
		})
		.success(function(result) {
			alert('menu create success.');
		});
	}
}])
.directive('createmenu', function() {
	return {
		restrict: 'E',
		controller: 'CreateMenuController',
		templateUrl: '/resources/view/resource/create_menu.html'
	};
});