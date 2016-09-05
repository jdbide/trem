'use strict';

socle_app.controller("rubriqueCtrl", ["$scope", 'rubriqueService',function($scope, rubriqueService) {
	$scope.datas = null;
	//$scope.currentData = null;
	$scope.reponse = null;
	$scope.modalAdd = false;
	$scope.modaEdit = false;
	$scope.modalConfirmDelete=false;
	$scope.libRubrique = null;
	$scope.idRubrique = null;
	$scope.actifRubrique=null;
	$scope.ordreRubrique=null;
	$scope.selectedRow=null;
	$scope.idRubrique = null;
	 $scope.imageSource=null;
	$scope.dataAdd= {libelle:null, actif:null,ordre:null};
	$scope.oldRow={};
	
	function constructor () {
		rubriqueService.getDataByServer().then(
			function () {
				$scope.datas = rubriqueService.getReponse().data;
			}, function () {
				alert("erreur serveur !!");
			}
		)
	}
	
	

	
	$scope.openModalAddRubrique = function () {
		$scope.modalAdd = !$scope.modalAdd;
		
	}
	
	$scope.openModalConfirmDelete = function (data) {
		$scope.modalConfirmDelete = !$scope.modalConfirmDelete;
		$scope.selectedRow=angular.copy(data);
		
		
	}
	$scope.openModalEditRubrique = function (data) {
		$scope.modalEdit = !$scope.modalEdit;
		$scope.selectedRow=angular.copy(data);
		
		

	}
	

	$scope.addRubrique = function () {
	console.log($scope.dataAdd);
		rubriqueService.addRubrique($scope.dataAdd).then(
			
			function () {
				
				$scope.reponse = rubriqueService.getReponse();
				if ($scope.reponse.status)
					$scope.modalAdd = !$scope.modalAdd;
				
				console.log($scope.reponse);
				constructor();
				
			}, function () {
				alert("erreur serveur !");
			}
		);
		
	}
	
	$scope.editRubrique = function () {
		console.log($scope.data)
		rubriqueService.editRubrique($scope.selectedRow).then(
				function () {
					$scope.reponse = rubriqueService.getReponse();
					if ($scope.reponse.status)
						$scope.modalEdit = !$scope.modalEdit;
					console.log($scope.reponse);
					constructor();
				}, function () {
					alert("erreur serveur !");
				}
			);
	}
	
	$scope.deleteRubrique = function (idRubrique) {
		console.log(idRubrique)
		rubriqueService.deleteRubrique(idRubrique).then(
			function () {
					$scope.reponse = rubriqueService.getReponse();
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
	
	
	constructor();
	
}]);