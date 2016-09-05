'use strict';

socle_app.controller("userCtrl", ["$scope", 'userService',function($scope, userService) {
	$scope.datas = null;
	//$scope.currentData = null;
	$scope.reponse = null;
	$scope.modalAdd = false;
	$scope.modaEdit = false;
	$scope.modalConfirmDelete=false;
	$scope.nomUser = null;
	$scope.prenomUser = null;
	$scope.loginUser=null;
	$scope.passwordUser=null;
	$scope.robotUser=null;
	$scope.selectedRow=null;
	$scope.idUser = null;
	$scope.dataAdd= {nomUser :null,prenomUser:null,loginUser:null,passwordUser:null,mailUser:null,cpUser:null,tomcatRoleUser:null,robotUser:null};
	$scope.oldRow={};
	
	function constructor () {
		userService.getDataByServer().then(
			function () {
				$scope.datas = userService.getReponse().data;
			}, function () {
				alert("erreur serveur !!");
			}
		)
	}
	
	

	
	$scope.openModalAddUser = function () {
		$scope.modalAdd = !$scope.modalAdd;
		
	}
	
	$scope.openModalConfirmDelete = function (data) {
		$scope.modalConfirmDelete = !$scope.modalConfirmDelete;
		$scope.selectedRow=angular.copy(data);
		
		
	}
	$scope.openModalEditUser = function (data) {
		$scope.modalEdit = !$scope.modalEdit;
		$scope.selectedRow=angular.copy(data);
		
		

	}
	

	$scope.addUser = function () {
	console.log($scope.dataAdd);
		userService.addUser($scope.dataAdd).then(
			
			function () {
				
				$scope.reponse = userService.getReponse();
				if ($scope.reponse.status)
					$scope.modalAdd = !$scope.modalAdd;
				
				console.log($scope.reponse);
				constructor();
				
			}, function () {
				alert("erreur serveur !");
			}
		);
		
	}
	
	$scope.editUser = function () {
		console.log($scope.data)
		userService.editUser($scope.selectedRow).then(
				function () {
					$scope.reponse = userService.getReponse();
					if ($scope.reponse.status)
						$scope.modalEdit = !$scope.modalEdit;
					console.log($scope.reponse);
					constructor();
				}, function () {
					alert("erreur serveur !");
				}
			);
	}
	
	$scope.deleteUser = function (idUser) {
		console.log(idUser)
		userService.deleteUser(idUser).then(
			function () {
					$scope.reponse = userService.getReponse();
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