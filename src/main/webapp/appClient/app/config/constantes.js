'use strict';

/**
 * App : Gestion de la navigation
 */
socle_app
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