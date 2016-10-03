'use strict';

/**
 * Contrôleur qui gère la page "Control" du chapitre "Train manager systeme", 
 *
 */
socle_app.controller("controlTmsCtrl", ["$rootScope", "$scope", "envService", '$interval',"controlTmsService", "Upload",'$http','traitementControlTmsService',
                                 function($rootScope, $scope, envService, $interval, controlTmsService, Upload, $http, traitementControlTmsService) {
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
	
	$scope.confNgFileUpload = {
		ngfAccept:"'image/*'",
		ngfModelInvalid:"invalidFiles",
		
		
	}
	
	function initFiles() {
		traitementControlTmsService.init();
		$scope.files = traitementControlTmsService.files();
	}
	
	
	
	//TODO Gestion d'affichage des erreurs
	$scope.addFile = function () {
		console.log("================");
		$(".image-preview-clear").show();
        $(".image-preview-filename").val(file.name); 
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
	
	/**
	 * Suppression du nouveau controle et redirection vers la page de controle
	 */
	$scope.cancelControl = function () {
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
				}, function() {
					alert("Erreur serveur!!");
				}
			);
	}
	
	
	
	$scope.executeValidateControl = function() {
		console.log(myForm.input_id_timetable.$valid);
	}
	
	$scope.files = traitementControlTmsService.files();
	
	$scope.showBtnFile = function (idInputFile) {
		if (idInputFile == 1) {
			return ($scope.files.firstFile.file != null)
		} else if (idInputFile == 2) {
			return ($scope.files.secondFile.file != null)
		}
	}
	
	$scope.selectedFile = function (idInputFile) {
		console.log("==> selectedFirstFile");
		$scope.files = traitementControlTmsService.files();
		console.log($scope.files);
	}
	
	
	$scope.clickBtnClearFile = function (idInputFile) {
		console.log("==> clickBtnClearFirsFile");
		if (idInputFile == 1) {
			traitementControlTmsService.initFirstFile();
		} else if (idInputFile == 2) {
			traitementControlTmsService.initSecondFile();
		}
		
		$scope.files = traitementControlTmsService.files();
	}
	$scope.clickBtnUploadFile = function (idInputFile) {
		console.log("==> clickBtnUploadFirsFile");
	}
	
	constructor();
}]);