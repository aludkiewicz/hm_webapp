angular.module('hm_webapp').controller('hm_ctrl', function ($scope, $http, $log) {
	
	$scope.displaySuccess = false;
	$scope.displayFail = false;
	
	$scope.sortAndStore = function () {
    	$http({
        	method: 'PUT', 
        	url: '/backend/sortandstore',
            params: {
            	list: $scope.inputList
            }
    	}).
        success(function (data, status, headers, config) {
        	$scope.status = data;
        	if(data.msg === 'OK'){
        		$scope.displaySuccess = true;
        		$scope.displayFail = false;
        	} else {
        		$scope.displaySuccess = false;
        		$scope.displayFail = true;
        	}
        }).
        error(function (data, status, headers, config) {
        	$log.error(status);
        });
	};
	
	$scope.getAll = function () {
    	$http({
        	method: 'GET', 
        	url: '/backend/getall'
    	}).
        success(function (data, status, headers, config) {
       		$scope.results = data;
        }).
        error(function (data, status, headers, config) {
            $log.error(status);
        });
	};
});