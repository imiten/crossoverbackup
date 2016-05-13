app.factory('configurationFactory', function($http) {
  var factory = {};
  var configurationList = [ ];

  factory.getConfigurationList = function() {
    configurationList = $http.get('user/configuration');
    return configurationList;
  } 
  
  factory.getConfiguration = function(id) {
    var configuration = $http.get('user/configuration/' + id);
    return configuration;
  } 

  factory.insertConfiguration = function(configuration) {
    var resp = $http.post('admin/configuration', configuration);
    return resp;
  }

  factory.updateConfiguration = function(configuration) {
    return $http.put('admin/configuration', configuration);

  }

  factory.deleteConfiguration = function(id) {
    var resp = $http.delete('admin/configuration/' + id, {});
    return resp;
  }

  return factory;
});
