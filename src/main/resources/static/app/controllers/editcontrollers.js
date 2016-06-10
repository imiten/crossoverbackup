app.controller('editBackupsController', function($scope, $routeParams, configurationFactory) {
  $scope.currentBackup = '';

  init();

  function init() {
    $scope.currentBackup = $routeParams.backupID;
    if($routeParams.backupID) {
    	configurationFactory.getConfiguration($routeParams.backupID).success(
       function(data, status) {
         console.log($routeParams.backupID + ":" + JSON.stringify(data));
         if($scope) {
           //$scope.editBackup_name = data.name;
           //$scope.editBackup_srcip = data.srcip;
        	 $scope.editBackup = data;
         }
       }
      );

    }
  }//init


  $scope.updateBackup = function () {
        //var id = $scope.currentBackup;
        //var name = $scope.editBackup_name;
        //var srcip = $scope.editBackup_srcip;
	  console.log("editController.updateBackup:" + JSON.stringify($scope.editBackup));
        configurationFactory.updateConfiguration($scope.editBackup).success(
          function(data, status) {
           /*for (var j=0; j< $scope.backups.length; j++) {
             if ($scope.backups[j].id == data.id) {
               $scope.backups.splice(j, 0, data);
               break;
             }
             console.log("update:" + JSON.stringify(data));
           }//for
           */
           console.log("updated:" + JSON.stringify(data));

          });
          
        //$scope.newBackup.name = '';
        //$scope.newBackup.srcip = '';
    };

  $scope.doBack = function() { $scope.active = 1; window.history.back(); }

});



