var event = angular.module('event', []);

event.controller('event', function ($scope, $http) {
    
    $scope.choices = [];
    $scope.scheckin = [];
    $scope.scheckout = [];
    $scope.sdescription = [];
    $scope.setage = [];
    $scope.result;
    $scope.event = {};
    $scope.sousEvent = {};
    $scope.allEvent = {};
    $scope.eventTab = [];
    
  
    $scope.addNewChoice = function() {
        var newItemNo = $scope.choices.length+1;
        $scope.choices.push({'id':'choice'+newItemNo});
        $scope.scheckin.push({});
        $scope.scheckout.push({});
        $scope.sdescription.push({});
        $scope.setage.push({});
    };

    $scope.removeChoice = function() {
      var lastItem = $scope.choices.length-1;
      $scope.choices.splice(lastItem);
      
      delete $scope.sousEvent.scheckin[lastItem];
      delete $scope.sousEvent.scheckout[lastItem];
      delete $scope.sousEvent.sdescription[lastItem];
      delete $scope.sousEvent.setage[lastItem];
      
      $scope.scheckin.splice(lastItem);
      $scope.scheckout.splice(lastItem);
      $scope.sdescription.splice(lastItem);
      $scope.setage.splice(lastItem);
    };
    
    $scope.removeAllChoice = function() {
        var lastItem = $scope.choices.length-1;
        
        for (var i = 0; i <= $scope.choices.length+1; i++) 
        {
            $scope.choices.splice(i);
            
            delete $scope.sousEvent.scheckin[i];
            delete $scope.sousEvent.scheckout[i];
            delete $scope.sousEvent.sdescription[i];
            delete $scope.sousEvent.setage[i];
            
            $scope.scheckin.splice(i);
            $scope.scheckout.splice(i);
            $scope.sdescription.splice(i);
            $scope.setage.splice(i);
        }
    };
    
    $scope.lastDateTimePickerSousEvent = function(index) 
    {
        if($scope.choices.length-1 == index)
        {
            return false;
        }
        
        return true;
    }
    
    $scope.initFirst = function ()
    {
        
    };
    
    $scope.deleteEvent = function (idEvent)
    {
        
    };
    
    $scope.addEvent = function ()
    {
        var data = JSON.stringify({
            intitule: $scope.event.intitule,
            description: $scope.event.description,
            dateHeureDebut: $scope.event.datetimedebevent,
            dateHeureFin: $scope.event.datetimefinevent,
            infoComp: $scope.event.info
        })
        
        //console.log("JSON1 " + data);
        
        $http.post("/resource/event/add", data).success(function (data, status) {
            $scope.response = data;
            
            for(var i = 0; i<$scope.choices.length; i++)
            {
                var ssEvent = JSON.stringify({
                    dateDebut: $scope.sousEvent.scheckin[i],
                    dateFin: $scope.sousEvent.scheckout[i],
                    intitule: $scope.sousEvent.sdescription[i],
                    etage: $scope.sousEvent.setage[i]
                })
                
                console.log("JSON " + ssEvent + "    Taille choices " + $scope.choices.length);
                
                $http.post("/resource/sousEvent/add", ssEvent).success(function (ssEvent, status) {
                    $scope.response = ssEvent;
                    
                    $scope.successAjout = true;
                    $scope.successMessage = "Vous venez d'ajouter un évènement et ses sous-évènements";
                    
                }).error(function (ssEvent, status) { // called asynchronously if an error occurs
                    // or server returns response with an error status.
                    $scope.errorAjout = true;
                    $scope.errorMessage = "Erreur lors de l'ajout d'un sous-évènement, un ou plusieurs champs sont manquants. Data : " + ssEvent + "    Status : " + status;
                });
            }
        }).error(function (data, status) { // called asynchronously if an error occurs
            // or server returns response with an error status.
            $scope.errorAjout = true;
            $scope.errorMessage = "Erreur lors de l'ajout d'un évènement, un ou plusieurs champs sont manquants. Data : " + data + "    Status : " + status;
        });
    };
    
    
    
});

event.directive('datepicker', function() {
    return {
        restrict: 'A',
        require : 'ngModel',
        link : function (scope, element, attrs, ngModCtrl) {
            $(function () {
                var min = new Date();
                var myHours = min.getHours();
                min.setHours(myHours+6)
                
                element.datetimepicker({
                    locale: 'fr',
                    format: 'DD/MM/YYYY HH:mm',
                    minDate: min,
                    calendarWeeks: true
                });
                
                element.on("dp.hide", function (e) {
                    if(attrs.id.indexOf("in") != -1)
                    {
                        ngModCtrl.$setViewValue(document.getElementById('checkin').value);
                    }
                    else
                    {
                        ngModCtrl.$setViewValue(document.getElementById('checkout').value);
                    }
                    
                });
                
                element.on("dp.change", function (e) {
                    if(attrs.id.indexOf("in") != -1)
                    {
                        //alert("Check in value = " + attrs.id);
                        if(scope.choices.length>0) scope.removeAllChoice();
                        ngModCtrl.$setViewValue(document.getElementById('checkin').value);
                        //$("#checkout").data("DateTimePicker").minDate(e.date);
                    }
                    else
                    {
                        //alert("Check out value = " + attrs.id + "   CPT = " + cpt);
                        if(scope.choices.length>0) scope.removeAllChoice();
                        ngModCtrl.$setViewValue(document.getElementById('checkout').value);
                        //$("#checkin").data("DateTimePicker").maxDate(e.date);
                    }
                    
                });
            });
        }
    }
});

event.directive('sdatepicker', function() {
    return {
        restrict: 'A',
        require : 'ngModel',
        link : function (scope, element, attrs, ngModelCtrl) {
            $(function(){
                var cpt = scope.choices.length-1;
                var datemin;
                var datemax;
                
                datemax = $('#checkout').data("DateTimePicker").date();
                
                if(cpt == 0)
                {
                   datemin = $('#checkin').data("DateTimePicker").date();
                }
                else
                {
                    if(attrs.id.indexOf("in") != -1)
                    {
                        //alert("Je suis dans scheckout-"+(cpt-1));
                        datemin = $("#scheckout-"+(cpt-1)).data("DateTimePicker").date();
                    }
                    else
                    {
                        datemin = $('#checkin').data("DateTimePicker").date();
                        //alert("Je suis dans scheckin-"+cpt)
                        //datemin = $("#scheckin-"+cpt).data("DateTimePicker").date();
                    }
                }
                
                if(attrs.id.indexOf("in") != -1)
                {
                    element.datetimepicker({
                        format: 'DD/MM/YYYY HH:mm',
                        minDate: datemin,
                        maxDate: datemax,
                        calendarWeeks: true
                    });
                }
                else
                {
                    element.datetimepicker({
                        format: 'DD/MM/YYYY HH:mm',
                        minDate: datemin,
                        maxDate: datemax,
                        useCurrent: false,
                        calendarWeeks: true
                    });
                }
                
                
                element.on("dp.change", function (e) {
                    if(attrs.id.indexOf("in") != -1)
                    {
                        //alert("Check in value = " + attrs.id + "   CPT = " + cpt);
                        $("#scheckout-"+cpt).data("DateTimePicker").minDate(e.date);
                        ngModelCtrl.$setViewValue(document.getElementById('scheckin-'+cpt).value);
                    }
                    else
                    {
                        //alert("Check out value = " + attrs.id + "   CPT = " + cpt);
                        $("#scheckin-"+cpt).data("DateTimePicker").maxDate(e.date);
                        ngModelCtrl.$setViewValue(document.getElementById('scheckout-'+cpt).value);
                    }
                    
                    if(cpt == 0)
                    {
                        
                    }
                    
                });
                
                element.on("dp.hide", function (e) {
                    if(attrs.id.indexOf("in") != -1)
                    {
                        $("#scheckout-"+cpt).data("DateTimePicker").minDate(e.date);
                        ngModelCtrl.$setViewValue(document.getElementById('scheckin-'+cpt).value);
                    }
                    else
                    {
                        ngModelCtrl.$setViewValue(document.getElementById('scheckout-'+cpt).value);
                    }
                });
            })
        }
    }
});

event.directive('sinput', function() {
    return {
        restrict: 'A',
        require : 'ngModel',
        link : function (scope, element, attrs, ngModelCtrl) {
            $(function(){
                ngModelCtrl.$setViewValue(attrs.id);
                
                element.on("keypress", function (e) {
                    //alert("toto");
                    
                    if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                       //alert("Seul les chiffres sont autorisés")
                    }
                });
            })
        }
    }
});