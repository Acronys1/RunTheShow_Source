/* js/fileAppControllers.js */

function fileCtrl ($scope, $http) {
    $scope.partialDownloadLink = 'http://localhost:8080/resource/file/download2?filename=';
    $scope.filename = '';

    $scope.uploadFile = function() {
        $scope.processDropzone();
		
    };

    $scope.reset = function() {
        $scope.resetDropzone();
    };
	
	$scope.downloadFile= function() {
			$http.get($scope.partialDownloadLink+$scope.filename).success(function(data) {
			scope.image = data;
		})
		};
}

angular.module('fileApp').controller('fileCtrl', fileCtrl);

function dropzone($http) {

    return function(scope, element, attrs) {

        var config = {
            url: 'http://localhost:8080/upload',
            maxFilesize: 100,
            paramName: "uploadfile",
            maxThumbnailFilesize: 10,
            parallelUploads: 2,
            autoProcessQueue: false
        };

        var eventHandlers = {
            'addedfile': function(file) {
                scope.file = file;
                if (this.files[1]!=null) {
                    this.removeFile(this.files[0]);
                }
                scope.$apply(function() {
                    scope.fileAdded = true;
                });
            },

            'success': function (file, response) {
            }
        };

        dropzone = new Dropzone(element[0], config);
		
		scope.downloadFile= function() {
			$http.get(scope.partialDownloadLink+scope.filename).success(function(data) {
			scope.image = data;
		})
	};
	
		scope.downloadAllFile= function() {
			$http.get('http://localhost:8080/resource/file/downloadAll').success(function(data) {
			scope.all_image = data;
		})
	};

        angular.forEach(eventHandlers, function(handler, event) {
            dropzone.on(event, handler);
        });

        scope.processDropzone = function() {
            dropzone.processQueue();
        };

        scope.resetDropzone = function() {
            dropzone.removeAllFiles();
        }
    }
}

angular.module('fileApp').directive('dropzone', dropzone);