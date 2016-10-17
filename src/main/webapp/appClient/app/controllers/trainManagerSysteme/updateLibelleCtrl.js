'use strict';

/**
 * Contrôleur global de du chapitre "Train manager systeme",
 * 
 * Ce controlleur gère la gestion des tabs de la page.
 */
socle_app.controller("updateLibelleCtrl", ["$scope","envService","partitionTmsService","updateLibelleService", function($scope,envService,partitionTmsService,updateLibelleService) {
	
	// Liste des CompagnieEnvironnement
	$scope.partitions = null;
	// CompagnieEnvironnement sélectionné (Par défault c'est
	// $scope.partitions[0])
	$scope.selectedPartition = null;
	// Liste des jeuDonneesControl
	$scope.datas = null;
	$scope.result = null;
	
	$scope.selectedDraft1 = null;
	$scope.selectedDraft2 = null;
	
	$scope.oldRow={};
	
	// $scope.urlDownloadFileCompFile = envService.read('appWebService') +
	// "/importTms/downloadFile/Comparaison";
	
	/*
	 * Methode pour la selection d'un draft
	 */
	$scope.changeSelectedDraft1 = function () {
		 // alert('hey, myVar has changed!');
	}
	/*
	 * Methode pour la selection d'un draft
	 */
	$scope.changeSelectedDraft2 = function () {
		 // alert('hey, myVar has changed!');
	}
	

	/*
	 * Methode pour la selection d'une autre compagnie
	 */
	$scope.changeSelectedCompagnie = function () {
		console.log("==> changeSelectedPartition <==");
		updateGaresDatas();
	}
	
	// save a line
	$scope.save=function (row, tableForm){
		row.isEditing=false;
		tableForm.isEditing=false;
		$scope.id = row.idMotriceRefGare;
		$scope.label = row.labelMotriceRefGare;
		updateLibelleService.saveRow($scope.id,$scope.label).then(
						
				function(){
					
					$scope.reponse = updateLibelleService.getReponse();
					if (!reponse.status) {
						alert("Erreur en bd:" + reponse.message);
					}

				}, function() {
					alert("Erreur serveur!!");
				}
		);
		
	}
	
	$scope.cancel=function (row, tableForm){
		angular.copy($scope.oldRow,row);
		row.isEditing=false;
		tableForm.isEditing=false;
	}
	
	$scope.onBeforeEdit=function (row){
		angular.copy(row,$scope.oldRow);
		}
		

		function updateGaresDatas(){
			// Recuperation de la liste des gares par idcompagnie
			updateLibelleService.getAllGaresByCompagnie($scope.selectedCompagnie).then(
				function() {
					var reponse = updateLibelleService.getReponse();
					if (reponse.status) {
						$scope.datas = reponse.data
					} else {
						alert("Erreur " + reponse.message);
					}

				}, function() {
					alert("Erreur serveur!!");
				}
			);
		}
		
		/*
		 * Constructeur du controlleur, il récupère la liste des partitions et
		 * les gares correspondantes
		 */
		function constructor () {
			
			updateLibelleService.getAllCompagnies().then(
					function(datas) {
						$scope.compagnies = datas;
						$scope.selectedCompagnie = datas[0].idCompagnie;
						updateGaresDatas();
						}
					);
		
		}
		constructor();
		
}]);


