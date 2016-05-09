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
         //$scope.editBackup.name = data.name;
         //$scope.editBackup.srcip = data.srcip;
       }
      );

    }
  }//init

  $scope.insertBackup = function () {
        //var name = $scope.newBackup.name;
        //var srcip = $scope.newBackup.srcip;
      console.log(JSON.stringify($scope.newBackup));

        configurationFactory.insertConfiguration($scope.newBackup).success( function(data, status) {
          $scope.backups.push(data);
          console.log(JSON.stringify(data));
        });
        //$scope.newBackup.name = '';
        //$scope.newBackup.srcip = '';
        $scope.newBackup = {};
    };

  $scope.updateBackup = function () {
        //var id = $scope.currentBackup;
        //var name = $scope.editBackup.name;
        //var srcip = $scope.editBackup.srcip;
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
        //$scope.newBackup.name = '';
        //$scope.newBackup.srcip = '';
    };

  $scope.deleteBackup = function(id, index) {
    console.log(id);
    configurationFactory.deleteConfiguration(id).success(function(data, status) {
      console.log(JSON.stringify(data));
      $scope.backups.splice(index, 1);
   
    });
   
  };

});



