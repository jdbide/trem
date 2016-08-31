'use strict';

socle_app.controller("pagesCtrl", ["$scope", "NgTableParams","pageService","chapitreService",'$q', function($scope, NgTableParams, pageService, chapitreService,$q) {
	
	
	$scope.datas=null;
	$scope.chapitres={};
	$scope.oldRow={};
	$scope.selectedChapitre={};
	
	$scope.save=function (row, tableForm){
		row.isEditing=false;
		tableForm.isEditing=false;
	}
	
	
	$scope.del=function (row, tableForm){
	}
	
	$scope.cancel=function (row, tableForm){
		angular.copy($scope.oldRow,row);
		row.isEditing=false;
		tableForm.isEditing=false;
	}
	
	
	
	$scope.onChapitreChange=function(row,chapitre){
		row.idChapitre=chapitre.id;
		row.libelleChapitre=chapitre.libelle;
	}
	
	
	$scope.onBeforeEdit=function (row){
	angular.copy(row,$scope.oldRow);
	var promise=getChapitreIndex(row.idChapitre);
	promise.then(
		function(chapitre){
			$scope.selectedChapitre=chapitre;
			 console.log ("OnbeforeEdit1 : "+$scope.selectedChapitre.libelle);
		}	
	
	);
	
	
	console.log ("OnbeforeEdit : "+$scope.selectedChapitre.libelle);
	
	}
	
	
	var getChapitreIndex=function(idChapitre){
		var deffered = $q.defer();
		
		angular.forEach ($scope.chapitres, function(chapitre, key) {
			if (chapitre.id==idChapitre) {
				deffered.resolve($scope.chapitres[key]);
			}
		}, this);
		console.log(deffered);
		return deffered.promise;
	}
	
	
	
	
	/**
	 * Pour chaque page, on va tenter de renseigner le libellé du chapitre
	 */
	 var setLibelleChapitre=function(page){
		angular.forEach ($scope.chapitres, function(chapitre, key) {
			if (chapitre.id==page.idChapitre) {
				page.libelleChapitre=chapitre.libelle;
				$scope.selectedChapitre=chapitre;
				
			}
		}, this);
	}
	

	 var getChapitres=function(){
		 chapitreService.getDataByServer().then(
					function () {
						$scope.chapitres = chapitreService.getReponse().data;
						angular.forEach($scope.datas,function(page, key){
							setLibelleChapitre(page);
						})
					}, function () {
						alert("erreur serveur !!");
					}
				)
	 }
	 
	 
	function constructor(){
	
		pageService.getDataByServer().then(
				function () {
					$scope.datas = pageService.getReponse().data;
					getChapitres();
				}, function () {
					alert("erreur serveur !!");
				}
			);
			
			
//			
			
	}
	
	constructor();	
	
}]


);