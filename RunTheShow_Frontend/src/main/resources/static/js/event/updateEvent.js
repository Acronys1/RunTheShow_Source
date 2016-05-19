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
            });
        });
    };
});

updateEvent.directive('datepicker', function() {
    return {
        restrict: 'A',
        require : 'ngModel',
        link : function (scope, element, attrs, ngModCtrl) {
            $(function () {
                var min = new Date();
                
                if(attrs.id.indexOf("in") != -1)
                {
                    element.datetimepicker({
                        locale: 'fr',
                        format: 'DD/MM/YYYY HH:mm',
                        minDate: min,
                        calendarWeeks: true
                    });
                }
                else
                {
                    ngModCtrl.$setViewValue(document.getElementById('checkout').value);
                }
                
                
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

updateEvent.directive('sdatepicker', function() {
    return {
        restrict: 'A',
        require : 'ngModel',
        link : function (scope, element, attrs, ngModelCtrl) {
            $(function(){
                var cpt = 6;
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

updateEvent.directive('sinput', function() {
    return {
        restrict: 'A',
        require : 'ngModel',
        link : function (scope, element, attrs, ngModelCtrl) {
            $(function(){
                //ngModelCtrl.$setViewValue(attrs.id);
                
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