'use strict';

/**
 * Cette page présente les données sous forme d'un tableau.
 * Les lignes sont éditables une à une, directement dans le tableau
 */


socle_app.controller("pagesCtrl", [
		"$scope",
		"NgTableParams",
		"pageService",
		"chapitreService",
		'$q',
		'$filter',
		function($scope, ngTableParams, mainService, chapitreService, $q,
				$filter) {
			
			//=======  Déclarations communes  =======
			$scope.datas = null;
			$scope.oldRow = {};
			$scope.reponse = null;
			$scope.isFormEditing=false;
			$scope.isFormAdding=false;
			$scope.modalConfirmDelete=false;
			$scope.selectedRow=null;
			
			
			//Déclarations particulières
			$scope.chapitres = {};
			$scope.selectedChapitre = {};

			//=======  CODE COMMUN  =========

			$scope.beforeAdd=function(){
				$scope.selectedChapitre=$scope.chapitres[0];
				$scope.ngTableParams.settings().dataset.unshift({
						id:null,
						idChapitre:$scope.selectedChapitre.id,
						libelleChapitre:$scope.selectedChapitre.libelle,
						libelle:"",
						actif:true,
						toutRole:false,
						publique:false,
						ordre:0,
						isEditing:true
				});
				$scope.isFormEditing=true;
				$scope.isFormAdding=true;
				$scope.ngTableParams.sorting({});
				$scope.ngTableParams.page(1);
				$scope.ngTableParams.reload();
			}
			
			$scope.onBeforeDelete=function(row){
				$scope.modalConfirmDelete=true;
				$scope.selectedRow=row;
			}
			
			/**
			 * mise à jour
			 */
			$scope.save = function(row) {
				
				if ($scope.isFormAdding){
					mainService.add(row).then(function() {
						$scope.reponse = mainService.getReponse();
						loadDatas();
					
					}, function() {
						$scope.reponse = mainService.getReponse();
						$scope.isFormEditing = false;
						$scope.isFormAdding = false;
						$scope.ngTableParams.reload();
					});	
				}
				else{
					mainService.update(row).then(function() {
						$scope.reponse = mainService.getReponse();
						row.isEditing = false;
						$scope.isFormEditing = false;
					}, function() {
					});	
				}
				
				
			}

			/**
			 * supression de la ligne
			 */
			$scope.delete = function(row) {
				mainService.delete(row).then(function() {
					$scope.reponse = mainService.getReponse();
					loadDatas();
						
						
				}, function() {
				});	
			}

			/**
			 * On annule les changements de l'utilisateur
			 */
			$scope.cancel = function(row, tableForm) {
				angular.copy($scope.oldRow, row);
				row.isEditing = false;
				if ($scope.isFormAdding){
					loadDatas();
				}
				else{
					$scope.isFormEditing=false;
					$scope.isFormAddind=false;	
				}
				
			}

			
			/**
			 * Avant d'éditer , on sauvegarde l'état de la ligne
			 */
			$scope.onBeforeEdit = function(row) {
				$scope.isFormEditing=true;
				angular.copy(row, $scope.oldRow);
				
				//Code particulier à la page
				var promise = getChapitreById(row.idChapitre);
				promise.then(function(chapitre) {
					$scope.selectedChapitre = chapitre;
				});
			}
			
			
			/**
			 * 
			 */
			function loadDatas(){
				$scope.isFormEditing = false;
				$scope.isFormAdding = false;
				mainService.getDataByServer().then(function(data) {
					$scope.datas = mainService.getReponse().data;
					$q.when(getChapitres($scope.datas));
					$scope.ngTableParams.settings().dataset=$scope.datas;
					$scope.ngTableParams.reload();
					$scope.ngTableParams.page(1);
				}, function() {
					alert("erreur serveur !!");
				});
			};
			
			
			//Déclarations particulières
			
			
			$scope.onChapitreChange = function(row, chapitre) {
				row.idChapitre = chapitre.id;
				row.libelleChapitre = chapitre.libelle;
			}

			

			/**
			 * Récupère le chapitre par son id 
			 */
			var getChapitreById = function(idChapitre) {
				var deffered = $q.defer();

				angular.forEach($scope.chapitres, function(chapitre, key) {
					if (chapitre.id == idChapitre) {
						deffered.resolve($scope.chapitres[key]);
					}
				}, this);
				return deffered.promise;
			}

			/**
			 * Pour chaque page, on va tenter de renseigner le libellé du
			 * chapitre
			 */
			var setLibelleChapitre = function(page) {
				angular.forEach($scope.chapitres, function(chapitre, key) {
					if (chapitre.id == page.idChapitre) {
						page.libelleChapitre = chapitre.libelle;
						page.idChapitre = chapitre.id;
						$scope.selectedChapitre = chapitre;

					}
				}, this);
			}
			
			/**
			 *Récupère les chapitres 
			 */
			var getChapitres = function(data) {
				chapitreService.getDataByServer().then(function() {
					$scope.chapitres = chapitreService.getReponse().data;
					angular.forEach(data, function(page, key) {
						setLibelleChapitre(page);
					})
				}, function() {
					alert("erreur serveur !!");
				})
			}

			
			
			
			function constructor() {

				
					$scope.ngTableParams = new ngTableParams({
						page : 1,
						count : 10
					}, {
						paginationMaxBlocks : 2,
						paginationMinBlocks : 2,
						count : []
						//dataset : $scope.datas,
					
					});// end ngTableParam
				
				loadDatas();

			}// end of constructor

			constructor();

		} ]

);