/**
 * calendarDemoApp - 0.9.0
 */
'use strict';
var app = angular.module('calendarDemoApp', ['ui.calendar', 'ui.bootstrap']);


app.controller('calendarCtrl', function ($scope, $http, $timeout, $compile,uiCalendarConfig) {
    
    $scope.bindCtrl = "Ok";
    $scope.eventTab = [];
    $scope.sousEventTab = [];
    $scope.url = "";
    
    var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();
    
    //$scope.changeTo = 'Hungarian';
    
    $scope.showModal = false;
    $scope.toggleModal = function(){
        $scope.showModal = !$scope.showModal;
    };
    
    $scope.close = function(){
        $scope.showModal = false;
    };
    
    
    $scope.initCalendar = function ()
    {
        /* Return all event for the User */
        $http.get('/resource/event/all').success(function (data) {
            $scope.allEvent = data;

            for(var i = 0; i<$scope.allEvent.length; i++)
            {
                if($scope.compareDateForAdd($scope.allEvent[i].dateHeureDebut, $scope.allEvent[i].dateHeureFin))
                {
                    var eventJson = JSON.stringify({
                        id: $scope.allEvent[i].id,
                        title: $scope.allEvent[i].intitule,
                        start: $scope.changeDate($scope.allEvent[i].dateHeureDebut),
                        end: $scope.changeDate($scope.allEvent[i].dateHeureFin)
                    })
                }
                else
                {
                    var eventJson = JSON.stringify({
                        id: $scope.allEvent[i].id,
                        title: $scope.allEvent[i].intitule,
                        start: $scope.changeDate($scope.allEvent[i].dateHeureDebut),
                        end: $scope.changeDateAddOneDay($scope.allEvent[i].dateHeureFin)
                    })
                }

                var eventParse = JSON.parse(eventJson);

                $scope.eventTab.push(eventParse);
            }
        });

        /* Return all Sous-event for the User */
        $http.get('/resource/sousEvent/all').success(function (data) {
            $scope.allSousEvent = data;

            for(var i = 0; i<$scope.allSousEvent.length; i++)
            {
                if($scope.compareDateForAdd($scope.allSousEvent[i].dateDebut, $scope.allSousEvent[i].dateFin))
                {
                    var eventJson = JSON.stringify({
                        id: $scope.allSousEvent[i].id,
                        title: $scope.allSousEvent[i].intitule,
                        start: $scope.changeDate($scope.allSousEvent[i].dateDebut),
                        end: $scope.changeDate($scope.allSousEvent[i].dateFin)
                    })
                }
                else
                {
                    var eventJson = JSON.stringify({
                        id: $scope.allSousEvent[i].id,
                        title: $scope.allSousEvent[i].intitule,
                        start: $scope.changeDate($scope.allSousEvent[i].dateDebut),
                        end: $scope.changeDateAddOneDay($scope.allSousEvent[i].dateFin)
                    })
                }

                var eventParse = JSON.parse(eventJson);

                $scope.sousEventTab.push(eventParse);
            }
        });
    };
    
    $scope.changeDate = function(dateChange) {
        var array = dateChange.split(' ');
        var dateSplit = array[0].split('/');
        
        var dateRecompose = dateSplit[2] + "-" + dateSplit[1] + "-" + dateSplit[0] + " " + array[1];
        
        return dateRecompose;
    };
    
    $scope.compareDateForAdd = function(dateDeb, dateFin) {
        /* On sépare la date et l'heure car format "dd/mm/yyyy hh:mm" */
        var dateDebArray = dateDeb.split(' ');
        var dateFinArray = dateFin.split(' ');
        
        /* On sépare la date car format "dd/mm/yyyy" */
        var dateDebSplit = dateDebArray[0].split('/');
        var dateFinSplit = dateFinArray[0].split('/');
        
        /* On compare la date de début et la date de fin sont les mêmes */
        if(dateDebSplit[0] == dateFinSplit[0] && dateDebSplit[1] == dateFinSplit[1] && dateDebSplit[2] == dateFinSplit[2]) return true;
        else return false;
    };
    
    $scope.changeDateAddOneDay = function(dateChange) {
        var array = dateChange.split(' ');
        var dateSplit = array[0].split('/');
        
        var dateRecompose = dateSplit[2] + "-" + dateSplit[1] + "-" + dateSplit[0];
        
        var myAddDate = new Date(dateRecompose);
        myAddDate.setMonth(myAddDate.getMonth()+1);
        myAddDate.setDate(myAddDate.getDate()+1);
        
        var mois;
        var jour;
        
        if(myAddDate.getMonth() < 10 && myAddDate.getDate() < 10)
        {
            mois = "0" + myAddDate.getMonth();
            jour = "0" + myAddDate.getDate();
        }
        else if(myAddDate.getMonth() < 10 && myAddDate.getDate() > 9)
        {
            mois = "0" + myAddDate.getMonth();
            jour = myAddDate.getDate();
        }
        else if (myAddDate.getMonth() > 9 && myAddDate.getDate() < 10) 
        {
            mois = myAddDate.getMonth();
            jour = "0" + myAddDate.getDate();
        }
        else
        {
            mois = myAddDate.getMonth();
            jour = myAddDate.getDate();
        }
        
        
        var dateRecomposeAdd = myAddDate.getFullYear() + "-" + mois + "-" + jour + " " + array[1];
        
        return dateRecomposeAdd;
    };
    
    
    $scope.infoSousEvent = function(idSousEvent) {
        $scope.idSousEvent = idSousEvent;
    }
    
    /* event source that pulls from google.com */
    /*$scope.eventSource = {
            url: "http://www.google.com/calendar/feeds/usa__en%40holiday.calendar.google.com/public/basic",
            className: 'gcal-event',           // an option!
            currentTimezone: 'America/Chicago' // an option!
    };*/
    /* event source that contains custom events on the scope */
    /*$scope.events = [
      {title: 'All Day Event',start: new Date(y, m, 1)},
      {title: 'Long Event',start: new Date(y, m, d - 5),end: new Date(y, m, d - 2)},
      {id: 999,title: 'Repeating Event',start: new Date(y, m, d - 3, 16, 0),allDay: false},
      {id: 999,title: 'Repeating Event',start: new Date(y, m, d + 4, 16, 0),allDay: false},
      {title: 'Birthday Party',start: new Date(y, m, d + 1, 19, 0),end: new Date(y, m, d + 1, 22, 30),allDay: false},
      {title: 'Click for Google',start: new Date(y, m, 28),end: new Date(y, m, 29),url: 'http://google.com/'}
    ];*/
    
    
    
    
    
    /* event source that calls a function on every view switch */
    /*$scope.eventsF = function (start, end, timezone, callback) {
      var s = new Date(start).getTime() / 1000;
      var e = new Date(end).getTime() / 1000;
      var m = new Date(start).getMonth();
      var events = [{title: 'Feed Me ' + m,start: s + (50000),end: s + (100000),allDay: false, className: ['customFeed']}];
      callback(events);
    };*/
    
    /* Retourne tous les sous-évènements dans la une couleur définie */
    $scope.calEventsExt = {
       color: '#40A447',
       textColor: 'white',
       events: $scope.sousEventTab
    };
    
    /* alert on eventClick */
    $scope.alertOnEventClick = function( date, jsEvent, view){
        $scope.idEvent = date.id;
        $scope.titleEvent = date.title;
        
        $http.get('/resource/sousEvent/filter/'+$scope.idEvent).success(function (data) {
            $scope.allSousEventForOneEvent = data;
            
            if($scope.allSousEventForOneEvent.length == 0)
            {
                $http.get('/resource/sousEvent/'+$scope.idEvent).success(function (data) {
                    $scope.allSousEventForOneEvent = data;
                });
            }
            
            for(var i = 0; i<$scope.allSousEventForOneEvent.length; i++)
            {
                $scope.idEventForClick = $scope.allSousEventForOneEvent[i].evenement.id;
            }
        });
        
        
        
        $scope.toggleModal();
        $scope.alertMessage = (date.title + ' was clicked ');
    };
    /* alert on Drop */
     $scope.alertOnDrop = function(event, delta, revertFunc, jsEvent, ui, view){
       $scope.alertMessage = ('Event Droped to make dayDelta ' + delta);
    };
    /* alert on Resize */
    $scope.alertOnResize = function(event, delta, revertFunc, jsEvent, ui, view ){
       $scope.alertMessage = ('Event Resized to make dayDelta ' + delta);
    };
    /* add and removes an event source of choice */
    $scope.addRemoveEventSource = function(sources,source) {
      var canAdd = 0;
      angular.forEach(sources,function(value, key){
        if(sources[key] === source){
          sources.splice(key,1);
          canAdd = 1;
        }
      });
      if(canAdd === 0){
        sources.push(source);
      }
    };
    
    /* remove event */
    $scope.remove = function(index) {
      $scope.events.splice(index,1);
    };
    /* Change View */
    $scope.changeView = function(view,calendar) {
      uiCalendarConfig.calendars[calendar].fullCalendar('changeView',view);
    };
    /* Change View */
    $scope.renderCalender = function(calendar) {
      if(uiCalendarConfig.calendars[calendar]){
        uiCalendarConfig.calendars[calendar].fullCalendar('render');
      }
    };
    

     /* Render Tooltip */
    $scope.eventRender = function( event, element, view ) { 
        element.attr({'tooltip': event.title,
                     'tooltip-append-to-body': true});
        $compile(element)($scope);
    };
    /* config object */
    $scope.uiConfig = {
      calendar:{
        lang: 'fr',
        height: 450,
        editable: false,
        timeFormat: 'HH:mm',
        events: $scope.eventSources,
        header:{
          left: 'title',
          center: '',
          right: 'today prev,next'
        },
        defaultDate: new Date(y, m , d),
        eventClick: $scope.alertOnEventClick,
        eventDrop: $scope.alertOnDrop,
        eventResize: $scope.alertOnResize,
        eventRender: $scope.eventRender
      }
    };

    /* event sources array*/
    $scope.eventSources = [$scope.eventTab, $scope.calEventsExt];
    //$scope.eventSources2 = [$scope.calEventsExt, $scope.eventsF, $scope.eventTab];
});

app.directive('modal', function () {
    return {
      template: '<div class="modal fade" id="eventModal">' + 
          '<div class="modal-dialog">' + 
            '<div class="modal-content">' + 
              '<div class="modal-header">' + 
                '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>' + 
                '<h4 class="modal-title">{{ title }}</h4>' + 
              '</div>' + 
              '<div class="modal-body" ng-transclude></div>' + 
            '</div>' + 
          '</div>' + 
        '</div>',
      restrict: 'E',
      transclude: true,
      replace:true,
      scope:true,
      link: function postLink(scope, element, attrs) {
        scope.title = attrs.title;

        scope.$watch(attrs.visible, function(value){
          if(value == true)
            $(element).modal('show');
          else
            $(element).modal('hide');
        });

        $(element).on('shown.bs.modal', function(){
          scope.$apply(function(){
            scope.$parent[attrs.visible] = true;
          });
        });

        $(element).on('hidden.bs.modal', function(){
          scope.$apply(function(){
            scope.$parent[attrs.visible] = false;
          });
        });
        
        $(element).on('click',"a[data-window='external']", function() {
            //alert('Toto ' + scope.showModal);
            
            $('#eventModal').modal('hide');
            $('.modal-backdrop').hide();
            $('body').removeClass('modal-open');
            $('.modal-backdrop').remove();
            //alert('Toto ' + scope.showModal);
            window.location($(this).attr('href'));
            return false; 
        });
      }
    };
  });