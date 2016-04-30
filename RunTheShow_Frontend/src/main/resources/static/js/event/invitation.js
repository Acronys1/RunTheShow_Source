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

app.controller('DemoCtrl', function ($scope, $http, $timeout) {
  var vm = this;
  
  $scope.bindCtrl = "Ok";
  
  vm.disabled = undefined;
  vm.searchEnabled = undefined;
  
  
  $scope.refreshArtiste = function(caracteres){
      if(caracteres.length > 3){
          $timeout(function() {
            $http.get("/resource/invitation/filter/"+caracteres).success(function (data, status) {
                vm.people = [];    
                for(var i in data){
                    vm.people.push({ name: data[i]['nomArtiste'],      image: 'http://lorempixel.com/50/50/people'});
                }
                
                }).error(function (data, status) { // called asynchronously if an error occurs
                    console.log("Erreur lors de l'envoie des informations.");
                });
          }, 1000, true);//Délais de 1 seconde entre chaque appel
      }
         
  };
  
  $scope.sendInvitation = function(){
      /*var data = JSON.stringify({
            0:[{expediteur: $scope.user.userLogin,
                destinataire: $scope.user.userPassword,
                sousEvent : $scope.user.userNom,
                commentaire : $scope.user.userPrenom,
                roles: [{id: $scope.user.userRole.id}]
            }],
            1:[{expediteur: $scope.user.userLogin,
                destinataire: $scope.user.userPassword,
                sousEvent : $scope.user.userNom,
                commentaire : $scope.user.userPrenom,
                roles: [{id: $scope.user.userRole.id}]
            }],
        });
       
       $http.post("/resource/invitation/add", data).success(function (data, status) {
            $scope.response = data;
            $scope.initFirst();
            $scope.errorAjout = false;
        }).error(function (data, status) { // called asynchronously if an error occurs
            // or server returns response with an error status.
            $scope.errorAjout = true;
            $scope.errorMessage = "Erreur lors de l'ajout, un ou plusieurs champs sont manquants";
        });*/
        
      console.log($scope.msgPerso);
      console.log($scope.ctrl.multipleDemo.selectedPeople);
  }

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
