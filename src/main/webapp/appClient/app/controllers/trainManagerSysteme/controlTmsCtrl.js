'use strict';

/**
 * Contrôleur qui gère la page "Control" du chapitre "Train manager systeme", 
 *
 */
socle_app.controller("controlTmsCtrl", ["$rootScope", "$scope", "envService", '$interval',"controlTmsService",
                                 function($rootScope, $scope, envService, $interval, controlTmsService) {
	$scope.title = "Control";
	// Liste des CompagnieEnvironnement
	$scope.partitions = null;
	// CompagnieEnvironnement sélectionné (Par défault c'est $scope.partitions[0])
	$scope.selectedPartition = null;
	// Liste des jeuDonneesControl
	$scope.datas = null;
	//
	$scope.startControl = -1;
	// Boolean pour l'ouverture du modal de création d'un controle
	$scope.modalCreateControl = false;
	// IdJeuDonneesControl
	$scope.jeuDonneesControl = null;
	// La liste des jeuDonnees (Récuperation par idPartition)
	$scope.listJeuDonnees = null;
	
	/**
	 * Constructeur du controlleur, il récupère la liste List<ImportTmsDto>
	 */
	function constructor () {
		// Recuperation de la liste des partitions
		controlTmsService.getPartition().then(
			function(datas) {
				$scope.partitions = datas;
				$scope.selectedPartition = datas[0].idCompagnieEnvironnement;
				// Recuperation de la liste des jeuDonneesControl
				controlTmsService.getDataByIdPartition($scope.selectedPartition).then(
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
	
	/**
	 * Methode pour la selection d'une autre partition
	 */
	$scope.changeSelectedPartition = function () {
		controlTmsService.getDataByIdPartition($scope.selectedPartition).then(
			function(datas) {
				$scope.datas = datas;
			}, function() {
				alert("Erreur serveur!!");
			}
		);
	}
	
	/**
	 * Creation d'un nouveau controle et ouverture du modal controle
	 */
	$scope.createControl = function () {
		controlTmsService.initReponse();
		controlTmsService.createControl($scope.selectedPartition).then(
			function() {
				reponse = controlTmsService.getReponse();
				if (reponse.status) {
					$scope.jeuDonneesControl = reponse.data;
					controlTmsService.initReponse();
					// Recuperation de la liste des import par partition
					controlTmsService.getImportParIdPartition($scope.selectedPartition).then(
						function() {
							reponse = controlTmsService.getReponse();
							if (reponse.status) {
								$scope.listJeuDonnees = reponse.data;
								$scope.modalCreateControl = !$scope.modalCreateControl;
							}else {
								// TODO erreur a gérer
							}
						}, function() {
							alert("Erreur serveur!!");
						}
					);
				} else {
					// TODO erreur a gérer
				}
			}, function() {
				alert("Erreur serveur!!");
			}
		);
	}

	constructor();
}]);