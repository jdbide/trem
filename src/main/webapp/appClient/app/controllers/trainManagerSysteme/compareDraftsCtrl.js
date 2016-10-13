'use strict';

/**
 * Contrôleur global de du chapitre "Train manager systeme", 
 *
 * Ce controlleur gère la gestion des tabs de la page.
 */
socle_app.controller("compareDraftsCtrl", ["$scope","envService","partitionTmsService","compareDraftsService", function($scope,envService,partitionTmsService,compareDraftsService) {
	
	// Liste des CompagnieEnvironnement
	$scope.partitions = null;
	// CompagnieEnvironnement sélectionné (Par défault c'est $scope.partitions[0])
	$scope.selectedPartition = null;
	// Liste des jeuDonneesControl
	$scope.datas = null;
	$scope.result = null;
	
	$scope.selectedDraft1 = null;
	$scope.selectedDraft2 = null;
	
	$scope.urlDownloadFileCompFile = envService.read('appWebService') + "/importTms/downloadFile/Comparaison";
	
	/*
	 * Methode pour la selection d'un  draft
	 */
	$scope.changeSelectedDraft1 = function () {
		 //alert('hey, myVar has changed!');	
	}
	/*
	 * Methode pour la selection d'un  draft
	 */
	$scope.changeSelectedDraft2 = function () {
		 //alert('hey, myVar has changed!');	
	}
	

	/*
	 * Methode pour la selection d'une autre partition
	 */
	$scope.changeSelectedPartition = function () {
		console.log("==> changeSelectedPartition <==");
		compareDraftsService.getJeuDonneesParIdCompagnieEnvironnement($scope.selectedPartition).then(
				function(datas) {
					$scope.datas = datas;
					//initialisation de la valeur par défaut
					//$scope.selectedDraft1 = datas[0].titleJeuDonneesControl
				}, function() {
					alert("Erreur serveur!!");
				}
			);
	}
	
	$scope.executeValidateControl = function(){
		//console.log("==> execute control <==");
		compareDraftsService.compareJd($scope.selectedDraft1,$scope.selectedDraft2).then(
				function(datas) {
					$scope.result = datas;
				}, function() {
					alert("Erreur serveur!!");
				}
			);
	}
	
	
	//disable compare button
	$scope.disable = function() {
		 if ($scope.selectedDraft1 == undefined || $scope.selectedDraft2 == undefined ) { 
		   return true;
		  }
		  else {
		   return false;
		  }
		};

	
	/*
	 * Constructeur du controlleur, il récupère la liste des partitions
	 */
	function constructor () {
		// Recuperation de la liste des partitions
		partitionTmsService.getAllPartitions().then(
			function(datas) {
				$scope.partitions = datas;
				$scope.selectedPartition = datas[0].idCompagnieEnvironnement;
				// Recuperation de la liste des drafts par id partition
				compareDraftsService.getJeuDonneesParIdCompagnieEnvironnement($scope.selectedPartition).then(
					function(datas) {
						$scope.datas = datas;
					}, function() {
						alert("Erreur serveur!!");
					}
				);
			}, function() {
				alert("Erreur serveur!!");
			}
		);
	}
	
	constructor();
	
}]);




