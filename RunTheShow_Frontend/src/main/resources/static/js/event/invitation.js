'use strict';
var app = angular.module('demo', ['ngSanitize', 'ui.select']);

app.filter('propsFilter', function() {
  return function(items, props) {
    var out = [];
    
    
    if (angular.isArray(items)) {
      var keys = Object.keys(props);
        
      items.forEach(function(item) {
        var itemMatches = false;


        for (var i = 0; i < keys.length; i++) {
          var prop = keys[i];
          var text = props[prop].toLowerCase();
          if (item[prop].toString().toLowerCase().indexOf(text) !== -1) {
            itemMatches = true;
            break;
          }
        }

        if (itemMatches) {
          out.push(item);
        }
      });
    } else {
      // Let the output be the input untouched
      out = items;
    }

    return out;
  };
});

app.controller('DemoCtrl', function ($scope, $http) {
  var vm = this;
  
  $scope.bindCtrl = "Ok";

  vm.disabled = undefined;
  vm.searchEnabled = undefined;
  
  vm.selectedPeople = function(){
      console.log("TEST");
  };


  vm.enable = function() {
    vm.disabled = false;
  };

  vm.disable = function() {
    vm.disabled = true;
  };

  vm.enableSearch = function() {
    vm.searchEnabled = true;
  };

  vm.disableSearch = function() {
    vm.searchEnabled = false;
  };

  
  /**
   * Tableau : nom/prenom + img
   * 
   */
  vm.people = [
    { name: 'Adam',      image: 'http://lorempixel.com/50/50/people'},
    { name: 'Amalie',    image: 'http://lorempixel.com/50/50/people'},
    { name: 'Estefana', image: 'http://lorempixel.com/50/50/people'},
    { name: 'Adrian',    image: 'http://lorempixel.com/50/50/people'},
    { name: 'Wladimir',  image: 'http://lorempixel.com/50/50/people'},
    { name: 'Samantha azymepetepaslescouilles',  image: 'http://lorempixel.com/50/50/people'},
    { name: 'Nicole',    image: 'http://lorempixel.com/50/50/people'},
    { name: 'Natasha',   image: 'http://lorempixel.com/50/50/people'},
    { name: 'Michael',   image: 'http://lorempixel.com/50/50/people'},
    { name: 'NicolÃ¡s',   image: 'http://lorempixel.com/50/50/people'}
  ];
  

  
});
