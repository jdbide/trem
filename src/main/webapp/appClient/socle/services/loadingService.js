'use strict';

/**
 * Service pour afficher/cacher l'écran de chargement (bloquant l'application)
 */
socle_app.service('loadingService', ['$rootScope', function($rootScope) {
    var self = this;
    
    self.show = function () {
        $rootScope.$emit('m-show');
    }
    
    self.hide = function () {
        $rootScope.$emit('m-hide');
    }
    
    return self;
}]);