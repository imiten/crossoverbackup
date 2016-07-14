app.controller('backupsController', function($scope, $routeParams, configurationFactory) {
  $scope.currentBackup = '';
  $scope.configLogList = [];
  $scope.isAdmin = true;
  if($routeParams.activeID) {
    $scope.active = parseInt($routeParams.activeID);
  } 
  init();

  function init() {
	  configurationFactory.getConfigurationList().success(function(data, status) {
      $scope.backups = data;
      console.log("init:"  + JSON.stringify(data));

    });
	
	  configurationFactory.isAdmin.success(function(data, status) {
		  if(data.data == 'true') {
			  $scope.isAdmin = true;
		  } else {
			  $scope.isAdmin = false;
		  }
	      console.log("init isAdmin:"  + JSON.stringify(data));

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
    //prevents live refresh.  need to add refresh button 
    if($routeParams.configBackupID) {
       	 configurationFactory.getConfigLogList($routeParams.configBackupID).success(
       		       function(data, status) {
       		    	   console.log($routeParams.configBackupID + ":" + data.length);
       		    	   $scope.configLogList = data;
       		    	   console.log("config log:");
       		    	   console.log(JSON.stringify($scope.configLogList));
       		       });	 
       	 
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



});


