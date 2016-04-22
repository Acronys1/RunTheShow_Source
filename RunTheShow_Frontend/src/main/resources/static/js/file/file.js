/* js/fileAppControllers.js */

angular.module('file', ['ngCookies']).controller('file', ['$scope', function ($scope, $http) {
        $scope.partialDownloadLink = 'http://localhost:8080/resource/file/download2?filename=';
        $scope.filename = '';

        $scope.uploadFile = function () {
            $scope.processDropzone();

        };

        $scope.reset = function () {
            $scope.resetDropzone();
        };

        $scope.downloadFile = function () {
            $http.get($scope.partialDownloadLink + $scope.filename).success(function (data) {
                $scope.image = data;
            })
        };
    }]);

function dropzone($http, $cookies) {

    return function (scope, element, attrs) {

        var config = {
            url: 'http://localhost:8080/resource/file/upload',
            withCredentials: true,
            maxFilesize: 100,
            paramName: "uploadfile",
            maxThumbnailFilesize: 10,
            parallelUploads: 2,
            headers: {
                'X-XSRF-TOKEN': $cookies.get('XSRF-TOKEN')
            },
            autoProcessQueue: false
        };

        var eventHandlers = {
            'addedfile': function (file) {
                scope.file = file;
                if (this.files[1] != null) {
                    this.removeFile(this.files[0]);
                }
                scope.$apply(function () {
                    scope.fileAdded = true;
                });
            },
            'success': function (file, response) {
            }
        };

        dropzone = new Dropzone(element[0], config);

        scope.downloadFile = function () {
            $http.get(scope.partialDownloadLink + scope.filename).success(function (data) {
                scope.image = data;
            })
        };

        scope.downloadAllFile = function () {
            $http.get('http://localhost:8080/resource/file/downloadAll').success(function (data) {
                scope.all_image = data;
            })
        };

        angular.forEach(eventHandlers, function (handler, event) {
            dropzone.on(event, handler);
        });

        scope.processDropzone = function () {
            dropzone.processQueue();
        };

        scope.resetDropzone = function () {
            dropzone.removeAllFiles();
        }
    }
}

angular.module('file').directive('dropzone', dropzone);