'use strict';

/**
 * App : Gestion de la navigation
 */
socle_app
.constant("Status", {
	"IMPORT" : "IMPORT",
	"DRAFT" : "DRAFT",
	"ACTIVE" : "ACTIVE",
	"LASTACTIVE" : "LASTACTIVE",
})
.run(function ($rootScope, Status) {
	$rootScope.Status = Status;
});