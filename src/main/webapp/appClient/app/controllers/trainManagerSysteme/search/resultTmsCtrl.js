'use strict';

/**
 * Contrôleur qui gère la page search du chapitre "Train manager systeme / search", 
 *
 */
socle_app.controller("resultTmsCtrl", ['$rootScope', '$scope', 'loadingService', '$q', 'resultService', 'searchService', 'resultTmsService', 'ClassSousRegime', 
                                       function($rootScope, $scope, loadingService, $q, resultService, searchService, resultTmsService, ClassSousRegime) {
	$scope.datas = resultTmsService.getReponse().data;
	$scope.tab = resultService.getTbody();
	
	$scope.showTab = function () {
		console.log($scope.tab);
	}
	
	$scope.getTbody = function () {
		console.log(resultService.getTbody());
	}
	
	//recuperation des code RM + Capacité par voiture->Compartiment->nbr Siege
	function getComposition (attribut, jour) {
		// capacity = nombre de sieges par voiture
		var capacity = 0;
		angular.forEach(attribut, function(sousRegime, keySousRegime) {
			if (sousRegime.regime.listeJours.indexOf(jour) != -1) {
				resultService.setCurrentRM(sousRegime.codeRm);
				// TODO  add d'une promise pour le calcul
				// Parcourire les voitures
				angular.forEach(sousRegime.voitures, function(voiture, keyVoiture) {
					if (voiture.compartiments != null) {
						angular.forEach(voiture.compartiments, function(compartiment, keyVoiture) {
							capacity += compartiment.sieges.length;
						});
					}
				});
			}
		});

		resultService.setCurrentCapacity(capacity);
	}

	//TypeEquipement->typeEquipement
	function getTypeEquipement (attribut, jour) {
		angular.forEach(attribut, function(sousRegime, keySousRegime) {
			if (sousRegime.regime.listeJours.indexOf(jour) != -1) {
				resultService.setCurrentEquip(sousRegime.typeEquipement);
			}
		});
	}
	
	// Desserte->gareHoraires( List<GareHoraire>)->gare(Gare)->codeGare
	function getDesserte (attribut, jour) {
		angular.forEach(attribut, function(sousRegime, keySousRegime) {
			if (sousRegime.regime.listeJours.indexOf(jour) != -1) {
				var stops = "";
				angular.forEach(attribut, function(sousRegime, keySousRegime) {
					if (sousRegime.regime.listeJours.indexOf(jour) != -1) {
						angular.forEach(sousRegime.gareHoraires, function(gareHoraire, keyGareHoraires) {
							if (stops == "") {
								stops += gareHoraire.gare.codeGare;
							} else {
								stops += " / "+  gareHoraire.gare.codeGare;
							}
							
						});
					}
				});

				resultService.setCurrentTosp(stops);
			}
		});
	}
	
	//CodeSat->codeSat
	function getCodeSat (attribut, jour) {
		angular.forEach(attribut, function(sousRegime, keySousRegime) {
			if (sousRegime.regime.listeJours.indexOf(jour) != -1) {
				resultService.setCurrentSat(sousRegime.codeSat);
			}
		});
	}
	
	//Tosp->oureCode
	function getTosp (attribut, jour) {
		angular.forEach(attribut, function(sousRegime, keySousRegime) {
			if (sousRegime.regime.listeJours.indexOf(jour) != -1) {
				resultService.setCurrentTosp(sousRegime.oureCode);
			}
		});
	}

	function getDataRowByDate(tranche, jour) {
		console.log("--- getDataRowByDate ----");
		var deffered  = $q.defer();
		var lengthAttributs = 0;
		var lengthTab = 0;
		
		for (var k in tranche.attributs) {
			lengthAttributs += 1;
		}

		angular.forEach(tranche.attributs, function(attribut, keyAttribut) {
			switch (keyAttribut) {
				case ClassSousRegime.Composition:
					getComposition(attribut, jour);
					break;
				case ClassSousRegime.TypeEquipement:
					getTypeEquipement(attribut, jour);
					break;
				case ClassSousRegime.Desserte:
					getDesserte(attribut, jour);
					break;
				case ClassSousRegime.CodeSat:
					getCodeSat(attribut, jour);
					break;
				case ClassSousRegime.Tosp:
					getTosp(attribut, jour);
					break;
			}
			
			lengthTab += 1;

			if (lengthTab == lengthAttributs) {
				deffered.resolve();
			}
		});
		
		return deffered.promise;
	}
	
	function buildTable() {
		console.log("-- buildTable --");
		var deffered  = $q.defer();
		
		//On boucle sur les trains du plan de transport
		angular.forEach($scope.datas.trains, function(train, keyTrain) {
			//On boucle sur les tranches du train
			angular.forEach(train.tranches, function(tranche, keyTranche) {
				//On boucle sur la liste de jours du régime
				angular.forEach(tranche.regime.listeJours, function(jour, keyJour) {
					//if(tranche.attributs.ClassSousRegime.Desserte[0]);
					var tr = new Object({currentTrain:null, currentTranche:null, currentRunDate : null, currentRM : null, currentEquip : null, currentCapacity : null, currentStatut : null, currentStops : null, currentSat : null, currentTosp : null, currentLoadedOn : null,currentLastModif : null});

					tr.currentTrain = train.numeroTrain;
					tr.currentTranche = tranche.numeroTranche;
					tr.currentStatut = tranche.trancheStatut;
					tr.currentRunDate = jour;
					resultService.addLine(tr);
					
					
					
					
					console.log((keyJour == (tranche.regime.listeJours.length - 1 )) && (keyTranche == (train.tranches.length - 1 )) && (keyTrain == ($scope.datas.trains.length - 1 )));
					if ((keyJour == (tranche.regime.listeJours.length - 1 )) && (keyTranche == (train.tranches.length - 1 )) && (keyTrain == ($scope.datas.trains.length - 1 ))) {
//						console.log("-- buildTable (resolve) --");
//						
//						console.log("keyTrain : " + keyTrain);
//						console.log("$scope.datas.trains.length : " + $scope.datas.trains.length);
//						console.log("-> " + (keyTrain == $scope.datas.trains.length - 1));
						console.log("Fin buildTable");
						deffered.resolve();
					}
//					getDataRowByDate(tranche, jour).then(
//						function () {
//							resultService.addLine(tr);
//							
//							if ((keyJour == (tranche.regime.listeJours.length - 1 )) && (keyTranche == (train.tranches.length - 1 )) && (keyTrain == ($scope.datas.trains.length - 1 ))) {
//								console.log("-- buildTable (resolve) --");
//								
//								console.log("keyTrain : " + keyTrain);
//								console.log("$scope.datas.trains.length : " + $scope.datas.trains.length);
//								console.log("-> " + (keyTrain == $scope.datas.trains.length - 1));
//								
//								deffered.resolve();
//							}
//						}, function () {
//							console.error("Error d'ajout de la ligne");
//							console.log("-- buildTable (reject) --");
//							deffered.reject();
//						}
//					);
				});
			});
		});
		
		return deffered.promise;
	}
	
	function constructor() {
		var data = searchService.searchToResult();
		if ($scope.datas == null) {
			resultTmsService.getResult(data).then(
				function() {
					console.warn("==> Récupération des resultat de la recherche <==");
					var reponse = resultTmsService.getReponse();
					if (reponse.status) {
						$scope.datas = reponse.data;
						
						buildTable().then(
							function () {
								$scope.tab = resultService.getTbody();
//								console.log($scope.tab);
								alert("construction du tableau");
							}, function () {
								alert("Erreur de construction du tableau");
							}
						);
					} else {
						alert("Erreur : " + reponse.message);
					}
					
				}, function() {
					alert("Erreur serveur!!");
				}
			);
		}
	}

	constructor();
	
	$scope.newSearch = function () {
		searchService.initNewSearch();
		$scope.datas = null;
		resultTmsService.initReponse();
		$rootScope.changePage();
	}
	
	$scope.modifSearch = function () {
		$scope.datas = null;
		resultTmsService.initReponse();
		$rootScope.isModif = true;
		$rootScope.changePage();
	}
}]);