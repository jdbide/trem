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
	
	$scope.traitementEnCours = compareDraftsService.getStatut();
	
	$scope.urlDownloadFileCompFile = envService.read('appWebService') + "/importTms/downloadFile/Comparaison";
	
	
	$scope.progressImport = {
			endTraitement : null,
			traitementOk : null,
			lastMsg : null,
			msgErr : null,
		}
	
	/*
	 * Méthode pour la sélection d'un  draft
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
		//console.log("==> changeSelectedPartition <==");
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
		compareDraftsService.compareJd($scope.selectedDraft1,$scope.selectedDraft2).then(
				function(){					
					$scope.result = compareDraftsService.getReponse();
					if (result.status) {
						compareDraftsService.changeStatut();
						startCheckProgressImport(result.data);
						compareDraftsService.changeStatut();
					}else{
						alert("Erreur en bd:" + result.message);
					}
				}, function() {
					alert("Erreur serveur!!");
				}
			);
		alert('fin');
		$scope.traitementEnCours = compareDraftsService.getStatut();
	}
	
	
	//disable compare button
	$scope.disable = function() {
		 if ($scope.selectedDraft1 == undefined || $scope.selectedDraft2 == undefined ) { 
		   return true;
		  }
		  else {
		   return false;
		  }
		}
		

		
		function initProgressImport() {
			progressImport.endTraitement = null;
			progressImport.traitementOk = null;
			progressImport.lastMsg = null;
			progressImport.msgErr = null;
		}

		function startCheckProgressImport(idTask) {
			compareDraftsService.initReponse();
			$scope.reponse = compareDraftsService.getReponse();
			var myInterval = $interval(function() {
				compareDraftsService.getProgressImport(idTask)
				.success(function (data, status, headers, config) {
	            	$scope.progressImport = data.data;
	            	if ($scope.progressImport != null && $scope.progressImport.endTraitement == true && $scope.progressImport.traitementOk == false) {
	            		end();
	            		$scope.reponse.status = false;
	            		$scope.reponse.message = $scope.progressImport.msgErr;
	            		$interval.cancel(myInterval);
	            	}
	            	else if ($scope.progressImport == null || $scope.progressImport.endTraitement == true && $scope.progressImport.traitementOk == true) {
	            		end();
	            		$scope.reponse.status = true;
	            		$scope.reponse.message = "Comparaison terminée avec succès";
	            		$interval.cancel(myInterval);
	            		constructor();
	            	}
	            })
	            .error(function (data, status, headers, config) {
	            	end();
	            	$interval.cancel(myInterval);
	            });
		    }, 1000);
		}

		/**
		 * Mise à jour des données en fin d'un import
		 */
		function end() {
			$scope.traitementEnCours = false;
		}
	
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




