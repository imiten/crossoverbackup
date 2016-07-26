	describe('configurations', function () {
		var $controller, $httpBackend;
		
		beforeEach(module('backupsApp'));
		
		beforeEach(inject(function(_$controller_, _$httpBackend_){
			$controller = _$controller_ ;
		    $httpBackend = _$httpBackend_;
		    $httpBackend.whenGET("user/configuration").respond(200, []);
		    $httpBackend.whenGET("user/adminrole").respond(200, {"data":true});
		    
		    var newBackup = {};
		    newBackup.id  = 1;
			newBackup.name = 'testbackup';
			newBackup.runStartTimestamp = "2020/01/01 12:00";//yyyy/MM/dd HH:mm
			newBackup.sourceIP = "192.168.1.5";
			newBackup.sourcePath = "/usr/data";
			newBackup.sourceUser = "srcuser";
			newBackup.sourcePassword = "password";
			newBackup.destinationIP = "192.168.1.6";
			newBackup.destinationPath = "/usr/archives";
			newBackup.destinationUser = "destuser";
			newBackup.destinationPassword = "password";
			
		    $httpBackend.whenPOST("admin/configuration").respond(201, newBackup);
		}));
		
		it('insert new configuration', inject(function ($routeParams, configurationFactory) {
			var $scope = {};
			$controller('backupsController', { $scope: $scope, $routeParams: $routeParams, configurationFactory: configurationFactory });
			var newBackup = {};
			newBackup.name = 'testbackup';
			newBackup.runStartTimestamp = "2020/01/01 12:00";//yyyy/MM/dd HH:mm
			newBackup.sourceIP = "192.168.1.5";
			newBackup.sourcePath = "/usr/data";
			newBackup.sourceUser = "srcuser";
			newBackup.sourcePassword = "password";
			newBackup.destinationIP = "192.168.1.6";
			newBackup.destinationPath = "/usr/archives";
			newBackup.destinationUser = "destuser";
			newBackup.destinationPassword = "password";
			$scope.newBackup = newBackup;
			$scope.insertBackup();
		    $httpBackend.flush();
		    expect($scope.backups.length).toEqual(1);
		}));	
	});
