var app = angular.module('backupsApp', ['ngRoute', 'ngResource']);

app.config(function ($routeProvider) {
  $routeProvider
    .when('/backups',
      {
        controller: 'backupsController',
        templateUrl: 'app/partials/backups.html'
      })
    .when('/backups/:backupID',
      {
        controller: 'backupsController',
        templateUrl: 'app/partials/backuplogs.html'
      })
    .when('/backups/update/:backupID',
      {
        controller: 'editBackupsController',
        templateUrl: 'app/partials/update_backup.html'
      })
    .otherwise({redirectTo: '/backups'});
});


app.config(function ($provide, $httpProvider) {
    $provide.factory('httpInterceptor', function ($q) {
        return {
          response: function (response) {
            return response || $q.when(response);
          },
          responseError: function (rejection) {
            //if(rejection.status === 401) {
              // you are not autorized
            //}
        	alert(JSON.stringify(rejection.data));
        	console.log(rejection.status + ":" + JSON.stringify(rejection.data));
            return $q.reject(rejection);
          }
        };
      });
      $httpProvider.interceptors.push('httpInterceptor');
});

