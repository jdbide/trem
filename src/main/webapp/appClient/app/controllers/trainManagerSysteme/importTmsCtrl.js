'use strict';

/**
 * Contrôleur qui gère la page import du chapitre "Train manager systeme", 
 *
 */
socle_app.controller("importTmsCtrl", ["$rootScope", "$scope", "envService", "importTmsService",
                                 function($rootScope, $scope, envService, importTmsService) {
	$scope.title = "Import";
	/**
	 * La liste des données à afficher : liste List<ImportTmsDto>
	 */
	$scope.datas = null;
	/**
	 * Boolean pour lancement du modal d'authentification à la BDD Motrice
	 */
	$scope.authentificationDb = false;
	
	$scope.deleteImportTms = false;
	
	$scope.validateImportTms = false;

	$scope.currentData = null;
	
	$scope.currentDataDelete = null;
	
	$scope.currentDataValidate = null;

	$scope.authError = null;

	$scope.reponse = null;

	$scope.disabledButton = false;

	$scope.startImport =  -1;
	
	$scope.urlDownloadFile = envService.read('appWebService') + "/importTms/downloadFile/";
	
	$scope.t = 20;

	/**
	 * Constructeur du controlleur, il récupère la liste List<ImportTmsDto>
	 */
	function constructor () {
		importTmsService.getDataByServer().then(
			function(datas) {
				$scope.datas = datas;
			}, function() {
				alert("Erreur serveur!!");
			}
		);		
	}

	/**
	 * Mise à jour des données aprés le lancement d'un import
	 */
	function startImportByData() {
		$scope.disabledButton = true;
		angular.forEach($scope.datas, function(value, key) {
			if (value == $scope.currentData)
				$scope.startImport = key;
		})
	}

	/**
	 * Mise à jour des données en fin d'un import
	 */
	function endImportByData() {
		$scope.disabledButton = false;
		$scope.startImport = -1;
	}
	
	/**
	 * Suppression d'un element d'un tableau
	 */
	function deleteElementArray(array, element) {
		for (var key in array) {
			if (array[key] == element) {
				array.splice(key, 1);
			}
		}
	}

	/**
	 * Suppression d'un element d'un tableau
	 */
	function mergeElementArray(array, element, newElement) {
		for (var key in array) {
			if (array[key] == element) {
				array[key] = newElement;
			}
		}
	}

	/**
	 * Lancer le modal pour l'authentification à la BDD Motrice
	 */
	$scope.authentificateExternDb = function (data) {
		$scope.currentData = data;
		$scope.authentificationDb = !$scope.authentificationDb;
	}
	
	/**
	 */
	$scope.startModalDeleteImportTms = function (data) {
		$scope.currentDataDelete = data;
		$scope.deleteImportTms = !$scope.deleteImportTms;
	}
	
	/**
	 */
	$scope.startModalValidateImportTms = function (data) {
		$scope.currentDataValidate = data;
		$scope.validateImportTms = !$scope.validateImportTms;
	}

	/**
	 * Lancement d'un import aprés l'authentification avec les identifiant de la BDD Motrice
	 */
	$scope.executeImport = function () {
		if ($scope.currentData.username == null || $scope.currentData.password == null
				|| $scope.currentData.username.size == 0 || $scope.currentData.password.size == 0) {			
			$scope.currentData.username = null;
			$scope.currentData.password = null;
			
			$scope.authError = "Veuillez inserer vos identifiants !";
		} else {
			$scope.authentificationDb = !$scope.authentificationDb;
			startImportByData();

			importTmsService.executeImport($scope.currentData).then(function() {
				$scope.reponse = importTmsService.getReponse();
				$scope.currentData = null;
				endImportByData();
			}, function() {
				$scope.reponse = importTmsService.getReponse();
				$scope.currentData = null;
				endImportByData();
			});
		}
	}

	$scope.executeDeleteDraft = function () {
		$scope.deleteImportTms = !$scope.deleteImportTms;
		/*Aprés reponse du webService*/
		importTmsService.executeDeleteDraft($scope.currentDataDelete).then(function() {
			$scope.reponse = importTmsService.getReponse();
			$scope.currentDataDelete = null;
		}, function() {
			$scope.reponse = importTmsService.getReponse();
			$scope.currentDataDelete = null;
		});

		//mergeDeleteElementInDatas($scope.datas, $scope.currentDataDelete);
	}
	
	$scope.executeValidateDraft = function () {
		$scope.validateImportTms = !$scope.validateImportTms;
		/*Aprés reponse du webService*/
		importTmsService.executeValidateDraft($scope.currentDataValidate).then(function() {
			$scope.reponse = importTmsService.getReponse();
			mergeElementArra($scope.datas, $scope.currentDataValidate, $scope.reponse.data);
			$scope.currentDataValidate = null;
		}, function() {
			$scope.reponse = importTmsService.getReponse();
			$scope.currentDataValidate = null;
		});
	}

	constructor ();	
}])