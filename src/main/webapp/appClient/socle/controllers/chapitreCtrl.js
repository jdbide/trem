'use strict';

socle_app.controller("chapitreCtrl", ["$scope", "NgTableParams","chapitreService","rubriqueService", function($scope, NgTableParams, chapitreService, rubriqueService) {
	$scope.datas = null;
	//$scope.currentData = null;
	$scope.reponse = null;
	$scope.modalAdd = false;
	$scope.modaEdit = false;
	$scope.modalConfirmDelete=false;
	$scope.libChapitre = null;
	$scope.idChapitre = null;
	$scope.idRubrique = null;
	$scope.actifChapitre=null;
	$scope.ordreChapitre=null;
	$scope.selectedRow=null;
	$scope.idChapitre = null;
	 $scope.imageSource=null;
	$scope.dataAdd= {libelle:null, actif:null,ordre:null};
	$scope.oldRow={};
	$scope.rubriques={};
	$scope.selectedRubrique=null;
	
	
	
	function constructor () {
		chapitreService.getDataByServer().then(
			function () {
				$scope.datas = chapitreService.getReponse().data;
			}, function () {
				alert("erreur serveur !!");
			}
		)
	}
	
	angular.module('checkboxExample', [])
    .controller('ExampleController', ['$scope', function($scope) {
      $scope.actifChapitre = {
       value1 : true,
       value2 : false
     };
    }]);
	

	
	$scope.openModalAddChapitre = function () {
		$scope.modalAdd = !$scope.modalAdd;
		
	}
	
	$scope.openModalConfirmDelete = function (data) {
		$scope.modalConfirmDelete = !$scope.modalConfirmDelete;
		$scope.selectedRow=angular.copy(data);
		
	}
	$scope.openModalEditChapitre = function (data) {
		$scope.modalEdit = !$scope.modalEdit;
		$scope.selectedRow=angular.copy(data);
		
		

	}
	

	$scope.addChapitre = function () {
	console.log($scope.dataAdd);
		chapitreService.addChapitre($scope.dataAdd).then(
			
			function () {
				
				$scope.reponse = chapitreService.getReponse();
				if ($scope.reponse.status)
					$scope.modalAdd = !$scope.modalAdd;
				
				console.log($scope.reponse);
				constructor();
				
			}, function () {
				alert("erreur serveur !");
			}
		);
		
	}
	
	$scope.editChapitre = function () {
		console.log($scope.data)
		chapitreService.editChapitre($scope.selectedRow).then(
				function () {
					$scope.reponse = chapitreService.getReponse();
					if ($scope.reponse.status)
						$scope.modalEdit = !$scope.modalEdit;
					console.log($scope.reponse);
					constructor();
				}, function () {
					alert("erreur serveur !");
				}
			);
	}
	
	$scope.deleteChapitre = function (idChapitre) {
		console.log(idChapitre)
		chapitreService.deleteChapitre(idChapitre).then(
			function () {
					$scope.reponse = chapitreService.getReponse();
					console.log($scope.reponse);
					constructor();
					
				}, function () {
					alert("erreur serveur !");
				}
			);
	}
	
	$scope.save=function (data, tableForm){
		data.isEditing=false;
		tableForm.isEditing=false;
	}
	$scope.del=function (data, tableForm){
	}
	
	$scope.cancel=function (data, tableForm){
		angular.copy($scope.oldRow,data);
		data.isEditing=false;
		tableForm.isEditing=false;
	}
	
	$scope.onBeforeEdit=function (row,tableForm){
	angular.copy(row,$scope.oldRow);
	}
	
	var getRubriqueIndex=function(idRubrique){
		angular.forEach ($scope.Rubriques, function(Rubrique, key) {
			console.log("Rubrique : "+rubrique.id==idRubrique+" --- "+idRubrique );
			if (rubrique.id==idRubrique) {
				return key;
			}
		}, this);
	}
	/**
	 * Pour chaque chapitre, on va tenter de renseigner le libellé du rubrique
	 */
	 var setLibelleRubrique=function(chapitre){
			angular.forEach ($scope.rubriques, function(rubrique, key) {
				if (rubrique.id==chapitre.idrubrique) {
					chapitre.libelleRubrique=rubrique.libelle;
				}
			}, this);
		}
		

		 var getRubriques=function(){
			 rubriqueService.getDataByServer().then(
						function () {
							$scope.rubriques = rubriqueService.getReponse().data;
							angular.forEach($scope.datas,function(rubrique, key){
								setLibellerubrique(rubrique);
							})
						}, function () {
							alert("erreur serveur !!");
						}
					)
		 }
		 
	constructor();
	
}]);