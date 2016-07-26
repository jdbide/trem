'use strict';

/**
 * Contrôleur global de du chapitre "Train manager systeme", 
 *
 * Ce controlleur gère la gestion des tabs de la page.
 */
socle_app.controller("trainTmsCtrl", ["$scope", "readerFileJson",
                                 function($scope, readerFileJson) {
	var URL_FILE_CONFIG_LOCAL = 'app/controllers/trainManagerSysteme/config/tab.json';
	
	$scope.tabs = null;
	$scope.currentTab = {
			title:null,
			url:null
	};
	
	
	function constructor () {		
		readerFileJson.get(URL_FILE_CONFIG_LOCAL).then(function(data) {
			$scope.tabs = data;
			angular.forEach($scope.tabs, function(value, key) {
				if (value.active)
					$scope.currentTab = value;
			});
		}, function () {
			alert("Erreur de chargement de données");
		});	
	}
	
	function desactiveDefaultTab () {
		angular.forEach($scope.tabs, function(value, key) {
			if (value.active)
				value.active = false;
		});
	}
	
	$scope.onClickTab = function (tab) {
		$scope.currentTab = tab;		
	}
	
	$scope.isActiveTab = function (tabTitle) {
		return tabTitle == $scope.currentTab.title
	}
		
	constructor ();	
}])