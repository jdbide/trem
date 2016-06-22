
    'use strict';
    
    /**
     * Déclaration du module principal pour notre application angularjs (socle)
     * Déclaration des modules utilisés dans notre application
     * ngRoute : Module pour la gestion des routers (@see documentation officielle AngularJs : ngRoute)
     * ngRessource : Module pour la gestion des requêtes AJAX ($Ajaxa -> $Http -> $Ressource)(@see documentation officielle AngularJs : ngRessource)
     * angularShamSpinner : Module pour l'affichage du loading pour chaque requête
     * ngCookies : Module de gestion des cookie (@see documentation officielle AngularJs : ngCookies)
     * myModal : C'est notre Directive (@see documentation officielle AngularJs : Directive) : pour la gestion des modal BootStrap (@see Bootstrap / Javascript / Modal)
     * <br>
     * Ces modules seront instanciés une seule fois au début de l'application (Principe IOC (Injection des dépendances "Principe pour la POO"))
     * <br>
     * $httpProvider : Service déclaré dans angular.js pour gérer nos requêtes
     * .config : Configuration de notre application ici : On configure les header des requêtes
     * <br>
     * <br>
     * Documentation officielle AngularJs : https://docs.angularjs.org/api
     * <br>
     * Author : Hamza LATEREM
     * <br>
     * Version : 1.0
     * <br>
     * Avancial
     *
     */
    var socle_app = angular.module('socle_app', [
      'ngRoute',
      'ngTable',
      'm-loader',
      'ui.bootstrap',
      'myModal',
      'environment',
      'ngStorage'
    ])
    
    .run(['ngTableDefaults', function(ngTableDefaults) {
    	ngTableDefaults.params.page = 1;
    	ngTableDefaults.params.count = 10;
    	ngTableDefaults.settings.counts = [];
    	ngTableDefaults.settings.paginationMaxBlocks = 2;
    	ngTableDefaults.settings.paginationMinBlocks = 2;

    }])
    
    .constant('pageAccueil', {
    	rubrique: "Socle Administration",
    	chapitre: "Accueil",
    	page: "Bonjour"
    })
    ;
    
    /**
     * Configuration de httpProvider
     */
    
    socle_app.config(function ($provide, $httpProvider) {
    	 // Add the interceptor to the $httpProvider.
    	$httpProvider.interceptors.push('socleHttpProvider');
    });
    
    socle_app.config(function(envServiceProvider) {
    	// set the domains and variables for each environment
		envServiceProvider.config(
            {
                domains: {
                    development: ['http://localhost:8080', 'localhost:8080', '127.0.0.1:8080'],
                    development_b: ['http://localhost:8081', 'localhost:8081', '127.0.0.1:8081'],
                    recette: ['http://tremas.rec-avancial.com:8080', 'tremas.rec-avancial.com:8080'],
                    recette_client: [],
                    recette_client_secu: [],
				    production: [],
					production_secu: []
				    // anotherStage: []
                },
                
                vars: {
                    development: {
                        apiUrl: 'http://localhost:8080/tremas/',
                        loginUrl: 'http://localhost:8080/tremas/login'
                        // antoherCustomVar: ''
                    },
                    development_b: {
                        apiUrl: 'http://localhost:8081/tremas/',
                        loginUrl: 'http://localhost:8081/tremas/login'
                        // antoherCustomVar: ''
                    },
                    recette: {
                        apiUrl: 'http://tremas.rec-avancial.com:8080/tremas/',
                        loginUrl: 'http://tremas.rec-avancial.com:8080/tremas/login'
                        // antoherCustomVar: ''
                    },
                    recette_client: {
                        apiUrl: ''
                        // antoherCustomVar: ''
                    },
                    recette_client_secu: {
                        apiUrl: ''
                        // antoherCustomVar: ''
                    },
                    production: {
                        apiUrl: ''
                        // antoherCustomVar: ''
                    },
					production_secu: {
                        apiUrl: ''
                        // antoherCustomVar: ''
                    },
                }
            }
       );
		// run the environment check, so the comprobation is made
		// before controllers and services are built
		envServiceProvider.check(); 
    });
