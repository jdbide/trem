
    'use strict';    
    
    /**
     * Déclaration du module principal pour notre application angularjs (socle)
     * Déclaration des modules utilisés dans notre application
     * ngRoute : Module pour la gestion des routers (@see documentation officielle AngularJs : ngRoute)
     * ngRessource : Module pour la gestion des requêtes AJAX ($Ajaxa -> $Http -> $Ressource)(@see documentation officielle AngularJs : ngRessource)
     * angularShamSpinner : Module pour l'affichage du loading pour chaque requête
     * ngCookies : Module de gestion des cookie (@see documentation officielle AngularJs : ngCookies)
     * myModal : C'est notre Directive (@see documentation officielle AngularJs : Directive) : pour la gestion des modal BootStrap (@see Bootstrap / Javascript / Modal)
     * 
     * Ces modules seront instanciés une seule fois au début de l'application (Principe IOC (Injection des dépendances "Principe pour la POO"))
     *
     * $httpProvider : Service déclaré dans angular.js pour gérer nos requêtes
     * .config : Configuration de notre application ici : On configure les header des requêtes
     *
     *
     * Documentation officielle AngularJs : https://docs.angularjs.org/api
     *
     * Author : Hamza LATEREM
     *
     * Version : 1.0
     *
     * Avancial
     *
     */
    var socle_app = angular.module('socle_app', [
      'ngRoute',
      'ngTable',
      'm-loader'
    ])
    
    .run(['ngTableDefaults', function(ngTableDefaults) {
    	ngTableDefaults.params.page = 1;
    	ngTableDefaults.params.count = 10;
    	ngTableDefaults.settings.counts = [];
    	ngTableDefaults.settings.paginationMaxBlocks = 2;
    	ngTableDefaults.settings.paginationMinBlocks = 2;

    }]);
