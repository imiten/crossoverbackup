app.controller('backupsController', function($scope, $routeParams, configurationFactory) {
  $scope.currentBackup = '';

  init();

  function init() {
	  configurationFactory.getConfigurationList().success(function(data, status) {
      $scope.backups = data;
      console.log(JSON.stringify(data));

    });
    $scope.currentBackup = $routeParams.backupID;
    if($routeParams.backupID) {
    	configurationFactory.getConfiguration($routeParams.backupID).success(
       function(data, status) {
         console.log($routeParams.backupID + ":" + data.name + ":" +  data.sourceIP);
         $scope.editBackup = data;
       }
      );

    }
  }//init

  $scope.insertBackup = function () {
       configurationFactory.insertConfiguration($scope.newBackup).success( function(data, status) {
          $scope.backups.push(data);
        });
        $scope.newBackup = {};
     
    };

  $scope.updateBackup = function () {
	  configurationFactory.updateConfiguration($scope.editBackup).success(
          function(data, status) {
           for (var j=0; j< $scope.backups.length; j++) {
             if ($scope.backups[j].id == data.id) {
               $scope.backups.splice(j, 0, data);
               break;
             }
             console.log("update:" + JSON.stringify(data));
           }//for
          });
    };

  $scope.deleteBackup = function(id, index) {
    console.log(id);
    configurationFactory.deleteConfiguration(id).success(function(data, status) {
      console.log(JSON.stringify(data));
      $scope.backups.splice(index, 1);
   
    });
   
  };

});



