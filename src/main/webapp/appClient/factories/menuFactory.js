'use strict';

/**
 * Appelle le WebService pour récupérer le JSON du menu de l'application
 */
socle_app.factory('menuFactory',['$http', function($http) {
	var menuFactory = [];
	
    menuFactory.getMenu = function () {                                                                           
        return $http({
                method: "get",
                url: "http://localhost:8080/appSocle/webService/menu",
                async: false,
                dataType: 'json',            
                crossDomain: false,
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                    'Accept':'text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8',            
                }
        });
        
    };
    
    return menuFactory;
}]);