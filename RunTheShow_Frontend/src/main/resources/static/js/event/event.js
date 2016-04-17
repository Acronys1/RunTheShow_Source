var event = angular.module('event', []);

event.controller('event', function ($scope, $http) {
    
    $scope.choices = [];
    $scope.scheckin = [];
    $scope.scheckout = [];
    $scope.result;
  
    $scope.addNewChoice = function() {
        var newItemNo = $scope.choices.length+1;
        $scope.choices.push({'id':'choice'+newItemNo});
        $scope.scheckin.push({});
        $scope.scheckout.push({});
    };

    $scope.removeChoice = function() {
      var lastItem = $scope.choices.length-1;
      $scope.choices.splice(lastItem);
      $scope.scheckin.splice(lastItem);
      $scope.scheckout.splice(lastItem);
    };
    
    $scope.removeAllChoice = function() {
        var lastItem = $scope.choices.length-1;
        
        for (var i = 0; i <= $scope.choices.length; i++) 
        {
            $scope.choices.splice(i);
            $scope.scheckin.splice(i);
            $scope.scheckout.splice(i);
        }
    };
    
    $scope.lastDateTimePickerSousEvent = function(index) 
    {
        if($scope.scheckin.length-1 == index)
        {
            return false;
        }
        
        return true;
    }
    
    $scope.initFirst = function ()
    {
        
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
                        scope.removeAllChoice();
                        ngModCtrl.$setViewValue(document.getElementById('checkin').value);
                        //$("#checkout").data("DateTimePicker").minDate(e.date);
                    }
                    else
                    {
                        //alert("Check out value = " + attrs.id + "   CPT = " + cpt);
                        scope.removeAllChoice();
                        ngModCtrl.$setViewValue(document.getElementById('checkout').value);
                        $("#checkin").data("DateTimePicker").maxDate(e.date);
                    }
                    
                });
                
                
                
                /*$('#datetimepicker6').datetimepicker({
                    format: 'DD/MM/YYYY HH:mm',
                    minDate: min
                });
                $('#datetimepicker7').datetimepicker({
                    format: 'DD/MM/YYYY HH:mm',
                    minDate: min
                });
                $("#datetimepicker6").on("dp.change", function (e) {
                    $('#datetimepicker7').data("DateTimePicker").minDate(e.date);
                    ngModelCtrl.$setViewValue(document.getElementById('checkin').value);
                });
                $("#datetimepicker7").on("dp.change", function (e) {
                    $('#datetimepicker6').data("DateTimePicker").maxDate(e.date);
                    ngModelCtrl.$setViewValue(document.getElementById('checkout').value);
                });*/
                
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