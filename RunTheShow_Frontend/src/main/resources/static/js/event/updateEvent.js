var updateEvent = angular.module('updateEvent', []);

updateEvent.controller('updateEvent', function ($scope, $routeParams, $http) {
    $scope.param = $routeParams.param;
    $scope.event = {};
    $scope.sousEvent = {};
    
    $scope.initFirst = function ()
    {
        $http.get('/resource/event/'+$scope.param).success(function (data) {
            $scope.event = data;
            
            $http.get('/resource/sousEvent/filter/'+$scope.param).success(function (data) {
                $scope.allSousEventForOneEvent = data;
                
                $scope.sizeSEvent = $scope.allSousEventForOneEvent.length;
            });
        });
    };
    
    $scope.changeDate = function(dateChange) {
        var array = dateChange.split(' ');
        var dateSplit = array[0].split('/');
        
        var dateRecompose = dateSplit[2] + "-" + dateSplit[1] + "-" + dateSplit[0] + " " + array[1];
        
        return new Date(dateRecompose);
    };
    
    $scope.modifier = function ()
    {
        var data = JSON.stringify({
            id: $scope.event.id,
            intitule: $scope.event.intitule,
            description: $scope.event.description,
            dateHeureDebut: $scope.event.dateHeureDebut,
            dateHeureFin: $scope.event.dateHeureFin,
            infoComp: $scope.event.infoComp
        })
        
        $http.put("/resource/event/update", data).success(function (data, status) {
            $scope.response = data;
            
            for(var i = 0; i<$scope.allSousEventForOneEvent.length; i++)
            {
                var data = JSON.stringify({
                    id: $scope.allSousEventForOneEvent[i].id,
                    dateDebut: $scope.allSousEventForOneEvent[i].dateDebut,
                    dateFin: $scope.allSousEventForOneEvent[i].dateFin,
                    intitule: $scope.allSousEventForOneEvent[i].intitule,
                    etage: $scope.allSousEventForOneEvent[i].etage
                })
                
                $http.put("/resource/sousEvent/update", data).success(function (data, status) {
                    $scope.response = data;
                    
                    $scope.successUpdate = true;
                    $scope.successMessage = "La modification est un succès";
                    
                }).error(function (data, status) { // called asynchronously if an error occurs
                    // or server returns response with an error status.
                    $scope.errorAjout = true;
                    $scope.errorMessage = "Erreur lors de la modification d'un sous-évènement. Data : " + data + "    Status : " + status;
                });
            }
        }).error(function (data, status) { // called asynchronously if an error occurs
            // or server returns response with an error status.
            $scope.errorAjout = true;
            $scope.errorMessage = "Erreur lors de la modification d'un évènement. Data : " + data + "    Status : " + status;
        });
    };
});

updateEvent.directive('udatepicker', function() {
    return {
        restrict: 'A',
        require : 'ngModel',
        link : function (scope, element, attrs, ngModCtrl) {
            $(function () {
                var min = new Date();
                
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
                        //scope.removeAllChoice();
                        ngModCtrl.$setViewValue(document.getElementById('checkin').value);
                        //$("#checkout").data("DateTimePicker").minDate(e.date);
                    }
                    else
                    {
                        //alert("Check out value = " + attrs.id + "   CPT = " + cpt);
                        //scope.removeAllChoice();
                        ngModCtrl.$setViewValue(document.getElementById('checkout').value);
                        $("#checkin").data("DateTimePicker").maxDate(e.date);
                    }
                    
                });
            });
        }
    }
});

updateEvent.directive('usdatepicker', function() {
    return {
        restrict: 'A',
        require : 'ngModel',
        link : function (scope, element, attrs, ngModelCtrl) {
            $(function(){
                
                var index = attrs.id.split('-');
                var cpt = index[1];
                var datemin;
                var datemax;
                
                datemax = scope.changeDate(scope.event.dateHeureFin);
                
                if(cpt == 0)
                {
                    if(attrs.id.indexOf("in") != -1)
                    {
                        datemin = scope.changeDate(scope.event.dateHeureDebut);
                    }
                    else
                    {
                        datemin = scope.changeDate(scope.allSousEventForOneEvent[cpt].dateDebut);
                    }
                   //alert("Cpt = 0 donc " + datemin);
                }
                else
                {
                    if(attrs.id.indexOf("in") != -1)
                    {
                        datemin = scope.changeDate(scope.allSousEventForOneEvent[cpt-1].dateFin);
                        //alert("Je suis dans scheckout-"+(cpt-1) + " :  datemin = " + datemin);
                    }
                    else
                    {
                        datemin = scope.changeDate(scope.allSousEventForOneEvent[cpt-1].dateDebut);
                        //alert("Je suis dans scheckin-"+(cpt) + " :  datemin = " + datemin);
                        //alert("Je suis dans scheckin-"+cpt)
                        //datemin = $("#scheckin-"+cpt).data("DateTimePicker").date();
                    }
                }
                
                if(attrs.id.indexOf("in") != -1)
                {
                    element.datetimepicker({
                        format: 'DD/MM/YYYY HH:mm',
                        locale: 'fr',
                        minDate: datemin,
                        maxDate: datemax,
                        calendarWeeks: true
                    });
                }
                else
                {
                    element.datetimepicker({
                        format: 'DD/MM/YYYY HH:mm',
                        locale: 'fr',
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
                        //$("#scheckout-"+cpt).data("DateTimePicker").minDate(e.date);
                        //ngModelCtrl.$setViewValue(document.getElementById('scheckin-'+cpt).value);
                    }
                    else
                    {
                        //alert("Check out value = " + attrs.id + "   CPT = " + cpt);
                        //$("#scheckin-"+cpt).data("DateTimePicker").maxDate(e.date);
                        //ngModelCtrl.$setViewValue(document.getElementById('scheckout-'+cpt).value);
                    }
                    
                });
                
                element.on("dp.hide", function (e) {
                    if(attrs.id.indexOf("in") != -1)
                    {
                        //$("#scheckout-"+cpt).data("DateTimePicker").minDate(e.date);
                        //ngModelCtrl.$setViewValue(document.getElementById('scheckin-'+cpt).value);
                    }
                    else
                    {
                        //ngModelCtrl.$setViewValue(document.getElementById('scheckout-'+cpt).value);
                    }
                });
            })
        }
    }
});

updateEvent.directive('usinput', function() {
    return {
        restrict: 'A',
        require : 'ngModel',
        link : function (scope, element, attrs, ngModelCtrl) {
            $(function(){
                
                var index = attrs.id.split('-');
                var cpt = index[1];
                ngModelCtrl.$setViewValue(scope.allSousEventForOneEvent[cpt].etage);
                attrs.value = scope.allSousEventForOneEvent[cpt].etage;
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