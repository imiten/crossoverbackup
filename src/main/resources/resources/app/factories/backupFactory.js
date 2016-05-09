app.factory('backupsFactory', function($http) {
  var factory = {};
  var backups = [ { name: 'wordpress', srcip: '10.135.128.159'}, 
                  { name: 'bitbucket', srcip: '10.135.128.160'}];

  factory.getBackups = function() {
    backups = $http.get('/user/backup');
    return backups;
  } 
  
  factory.getBackup = function(id) {
    var backup = $http.get('/user/backup/' + id);
    return backup;
  } 

  factory.insertBackup = function(name, srcip) {
    var resp = $http.post('/admin/backup', { name: name, srcip: srcip });
    return resp;
  }

  factory.updateBackup = function(id, name, srcip) {
    return $http.put('/admin/backup', {id: id, name: name, srcip: srcip});

  }

  factory.deleteBackup = function(id) {
    var resp = $http.delete('/admin/backup/' + id, {});
    return resp;
  }

  return factory;
});
