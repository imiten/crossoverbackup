app.controller('backupsController', function($scope, $routeParams, configurationFactory) {
  $scope.currentBackup = '';
  if($routeParams.activeID) {
    $scope.active = $routeParams.activeID;
  } else {
    $scope.active = 0;
  }
  init();

  function init() {
	  configurationFactory.getConfigurationList().success(function(data, status) {
      $scope.backups = data;
      console.log("init:"  + JSON.stringify(data));

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
             console.log("update:" + JSON.stringify(data));
          });
    };

  $scope.deleteBackup = function(id, index) {
    console.log(id);
    configurationFactory.deleteConfiguration(id).success(function(data, status) {
      console.log(JSON.stringify(data));
      $scope.backups.splice(index, 1);
   
    });
   
  };

  if($routeParams.activeID) {
    $scope.active = $routeParams.activeID;
    console.log("route active id=" + $scope.active);
  }
  $scope.doBack = function() { $scope.active = 1; window.history.back(); }


});


