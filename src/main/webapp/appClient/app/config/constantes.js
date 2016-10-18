'use strict';

/**
 * App : Gestion de la navigation
 */
socle_app
.constant("ClassSousRegime", {
	"Desserte" : "class com.avancial.app.data.objetsMetier.PlanTransport.Desserte",
	"ServiceABord" : "class com.avancial.app.data.objetsMetier.PlanTransport.ServiceABord",
	"Tosp" : "class com.avancial.app.data.objetsMetier.PlanTransport.Tosp",
	"Repas" : "class com.avancial.app.data.objetsMetier.PlanTransport.Repas",
	"Restriction" : "class com.avancial.app.data.objetsMetier.PlanTransport.Restriction",
	"OrigineDestination" : "class com.avancial.app.data.objetsMetier.PlanTransport.OrigineDestination",
	"Distribution" : "class com.avancial.app.data.objetsMetier.PlanTransport.Distribution",
	"TypeEquipement" : "class com.avancial.app.data.objetsMetier.PlanTransport.TypeEquipement",
	"Composition" : "class com.avancial.app.data.objetsMetier.PlanTransport.Composition",
	"CodeSat" : "class com.avancial.app.data.objetsMetier.PlanTransport.CodeSat"
})
.constant("Status", {
	"IMPORT" : "IMPORT",
	"DRAFT" : "DRAFT",
	"ACTIVE" : "ACTIVE",
	"LASTACTIVE" : "LASTACTIVE"
})
.constant("StatusImportForControl", {
	"ACTIVE" : "ACTIVE",
	"DRAFT" : "DRAFT"
})
.run(function ($rootScope, Status, StatusImportForControl) {
	$rootScope.Status = Status;
	$rootScope.StatusImportForControl = StatusImportForControl;
});