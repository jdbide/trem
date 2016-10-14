'use strict';

/**
 * Contrôleur qui gère la page "Control" du chapitre "Train manager systeme", 
 *
 */
socle_app.controller("controlTmsCtrl", ["$rootScope", "$scope", "envService", '$interval',"controlTmsService", 'traitementControlTmsService', 'partitionTmsService',
                                 function($rootScope, $scope, envService, $interval, controlTmsService, traitementControlTmsService, partitionTmsService) {
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
	// status pour jeuDonnees selectionne
	$scope.selectedStatusJeuDonnees = $rootScope.StatusImportForControl.DRAFT;
	// La liste des jeuDonnees (Récuperation par idPartition)
	var listJeuDonnees = null;
	$scope.listSelectedJeuDonnees = new Array();
	// status pour jeuDonnees selectionne
	$scope.selectedIdJeuDonnees = null;
	//Boolean pour disabled all sur le modal
	$scope.disabledAll = true;
	
	$scope.confNgFileUpload = {
		ngfAccept:"'image/*'",
		ngfModelInvalid:"invalidFiles",
		
		
	}
	
	
	/*
	 * Initialisation de l'objet traitementControlTmsService.files
	 */
	function initFiles() {
		traitementControlTmsService.init();
		$scope.files = traitementControlTmsService.files();
	}
	
	
	
	
	
	$scope.uploadPic = function (file) {
	    $scope.formUpload = true;
	    if (file != null) {
	      $scope.upload(file);
	    }
	  };
	  
	  $scope.upload = function (file, resumable) {
		    $scope.errorMsg = null;
		    $scope.howToSend = 2;
		    if ($scope.howToSend === 1) {
		      uploadUsingUpload(file, resumable);
		    } else if ($scope.howToSend == 2) {
		      uploadUsing$http(file);
		    } else {
		      uploadS3(file);
		    }
		  };
		  
		  function uploadUsing$http(file) {
			    file.upload = Upload.http({
			      url: 'http://localhost:8081/ng-file-upload-demo-server/upload',
			      method: 'POST',
			      headers: {
			        'Content-Type': file.type
			      },
			      data: file
			    });

			    file.upload.then(function (response) {
			      file.result = response.data;
			    }, function (response) {
			      if (response.status > 0)
			        $scope.errorMsg = response.status + ': ' + response.data;
			    });

			    file.upload.progress(function (evt) {
			      file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
			    });
			  }

	/*
	 * Recuperation de la liste des jeuDonneesControl
	 * MAJ des $scope.data
	 */
	function getData() {
		// Recuperation de la liste des jeuDonneesControl
		controlTmsService.getDataByIdPartition($scope.selectedPartition).then(
			function(datas) {
				$scope.datas = datas;
			}, function() {
				alert("Erreur serveur!!");
			}
		);
	}
	/*
	 * Constructeur du controlleur, il récupère la liste List<ImportTmsDto>
	 */
	function constructor () {
		// Recuperation de la liste des partitions
		partitionTmsService.getPartition().then(
			function(datas) {
				$scope.partitions = datas;
				$scope.selectedPartition = datas[0].idCompagnieEnvironnement;
				getData();
			}, function() {
				alert("Erreur serveur!!");
			}
		);
	}
	
	/*
	 * Methode pour la selection d'une autre partition
	 */
	$scope.changeSelectedPartition = function () {
		getData();
	}
	
	/*
	 * Creation d'un nouveau controle et ouverture du modal controle
	 */
	$scope.createControl = function () {
		$scope.disabledAll = true;
		controlTmsService.createControl($scope.selectedPartition).then(
			function() {
				var reponse = controlTmsService.getReponse();
				if (reponse.status) {
					$scope.jeuDonneesControl = reponse.data;
					controlTmsService.initReponse();
					// Recuperation de la liste des import par partition
					controlTmsService.getImportParIdPartition($scope.selectedPartition).then(
						function() {
							var reponse = controlTmsService.getReponse();
							if (reponse.status) {
								listJeuDonnees = reponse.data;
								getJeuDonneesByStatusSelected();
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
				
				$scope.disabledAll = false;
			}, function() {
				alert("Erreur serveur!!");
			}
		);
	}
	
	/*
	 * Suppression du nouveau controle et redirection vers la page de controle
	 */
	$scope.cancelControl = function () {
		$scope.disabledAll = true;
		controlTmsService.deleteControl($scope.jeuDonneesControl.idJeuDonneesControl).then(
				function() {
					var reponse = controlTmsService.getReponse();
					if (reponse.status) {
						$scope.jeuDonneesControl = null;
						$scope.listJeuDonnees = null;
						initFiles();
						$scope.modalCreateControl = !$scope.modalCreateControl;
					}else {
						// TODO erreur a gérer
					}
					
					$scope.disabledAll = false;
				}, function() {
					alert("Erreur serveur!!");
				}
			);
	}
	
	
	
	$scope.executeValidateControl = function() {
		console.log(myForm.input_id_timetable.$valid);
	}

	/*
	 * Objet qui représente les fichier et leur traitement d'upload (@see traitementControlTmsService)
	 */
	$scope.files = traitementControlTmsService.files();

	/*
	 * Methode qui affiche ou non les button clear & upload apres le choix des fichiers
	 */
	$scope.showBtnFile = function (idInputFile) {
		if (idInputFile == 1) {
			return ($scope.files.firstFile.file != null);
		} else if (idInputFile == 2) {
			return ($scope.files.secondFile.file != null);
		}
	}

	/*
	 * Button Browser
	 * Methode pour la selection des fichier par idInputFile(1:TimeTable file | 2:Yield file)
	 * TimeTable file doit etre uploadé avnt Yield file
	 */
	$scope.selectedFile = function (idInputFile) {
		console.log("==> selectedFile");
		$scope.files = traitementControlTmsService.files();

		if (idInputFile == 2 && $scope.files.firstFile.file == null) {
			console.log("====== je suis la 1");
			traitementControlTmsService.init();
			
			$scope.files.firstFile.msgError="Please upload the TimeTable file";
		}
		
		else if (idInputFile == 2 && $scope.files.firstFile.file != null && $scope.files.firstFile.etat.isFinishTraitementSuccess != true) {
			console.log("====== je suis la 2");
			traitementControlTmsService.initSecondFile();
			$scope.files.firstFile.msgError="Please upload the TimeTable file";
		}
		
		else if (idInputFile == 2) {
			console.log("====== je suis la 3");
			$scope.files.secondFile.msgError=null;
			$scope.files.firstFile.invalidFile=null;
		} else if (idInputFile == 1){
			console.log("====== je suis la 4");
			$scope.files.firstFile.msgError=null;
			$scope.files.firstFile.invalidFile=null;
//			$scope.files.firstFile.etat.isFinishTraitementSuccess = true;
		}

		console.log($scope.files);
	}

	/*
	 * 	Button clear
	 *	Vider le choix du fichier a uploader 
	 */
	$scope.clickBtnClearFile = function (idInputFile) {
		console.log("==> clickBtnClearFirsFile");
		if (idInputFile == 1) {
			traitementControlTmsService.init();
		} else if (idInputFile == 2) {
			traitementControlTmsService.initSecondFile();
		}
		
		$scope.files = traitementControlTmsService.files();
		console.log($scope.files);
	}

	/*
	 * Button Upload
	 * Lancement d'un upload file
	 */
	$scope.clickBtnUploadFile = function (idInputFile) {
		console.log("==> clickBtnUploadFirsFile");
		var typeFile = null;
//		$scope.files = traitementControlTmsService.files();
		
		if (idInputFile == 1) {
			console.log("==> 1 " + $scope.jeuDonneesControl);
			console.log(traitementControlTmsService.files().firstFile.file);
			
			$scope.files.firstFile.etat.isStartTraitement = true;
			controlTmsService.uploadUsing$http($scope.files.firstFile.file, $scope.jeuDonneesControl.idJeuDonneesControl, "timetable");
			$scope.files.firstFile.etat.isFinishTraitementSuccess = true;
			$scope.files.firstFile.etat.isFinishTraitement = true;
			
			console.log($scope.files.firstFile.etat);
			console.log($scope.files.secondFile.etat);
			
		} else if (idInputFile == 2) {
			console.log("==> 2");
			
			$scope.files.secondFile.etat.isStartTraitement = true;
			controlTmsService.uploadUsing$http($scope.files.secondFile.file,$scope.jeuDonneesControl, "yield");
			$scope.files.secondFile.etat.isFinishTraitementSuccess = true;
			$scope.files.secondFile.etat.isFinishTraitement = true;
			
			console.log($scope.files.firstFile.etat);
			console.log($scope.files.secondFile.etat);
		}
	}
	
	/*
	 * ng-change sur le status du jeuDonnees => mise à jour de la liste des jeuDonnees
	 */
	$scope.changeStatusJeuDonnees = function () {
		console.log("====> changeStatusJeuDonnees");
		getJeuDonneesByStatusSelected();
	}
	
	function getJeuDonneesByStatusSelected() {
		console.log("==== getJeuDonneesByStatusSelected ==> " + $scope.selectedStatusJeuDonnees);
		$scope.listSelectedJeuDonnees = new Array();
		$scope.selectedIdJeuDonnees = null;
		
		angular.forEach(listJeuDonnees, function(jeuDonnees, key) {
			  if (jeuDonnees.statusJeuDonnees == $scope.selectedStatusJeuDonnees) {
				  $scope.listSelectedJeuDonnees.push(jeuDonnees);
			  }
		});
		
		if ($scope.listSelectedJeuDonnees.length > 0) {
			$scope.selectedIdJeuDonnees = $scope.listSelectedJeuDonnees[0].idJeuDonnees;
		}
		
		console.log("==> $scope.selectedIdJeuDonnees " + $scope.selectedIdJeuDonnees);
	}

	/*
	 * Call constructor
	 */
	constructor();
}]);