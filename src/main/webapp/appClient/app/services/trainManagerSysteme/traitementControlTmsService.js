'use strict';

/**
 * 
 */
socle_app.service('traitementControlTmsService', function() {
	var etatUpdate = {
		idTraitement:null,
		isStartTraitement:null,
		isFinishTraitement:null,
		isFinishTraitementSuccess:null,
		isUploadSuccess:null,
		isFormatOk:null,
		isDataValidate:null,
		msgDataValidate:null,
		
		init: function () {
			this.idTraitement=null;
			this.isStartTraitement=null;
			this.isFinishTraitement=null;
			this.isFinishTraitementSuccess=null;
			this.isUploadSuccess=null;
			this.isFormatOk=null;
			this.isDataValidate=null;
			this.msgDataValidate=null;
			
			return this;
		}
	}
	
	var files = {
		firstFile : {
			file: null,
			etat: etatUpdate.init(),
			msgError:null,
			invalidFile:null
		},
		secondFile : {
			file: null,
			etat: etatUpdate.init(),
			msgError:null,
			invalidFile:null
		}
	};

	self = this;

	self.files = function() {
		return files;
	}
	
	self.init = function () {
		files.firstFile = {file:null,etat:etatUpdate.init(),msgError:null,invalidFile:null};
		files.secondFile = {file:null,etat:etatUpdate.init(),msgError:null,invalidFile:null};
	}
	
	self.initFirstFile = function () {
		files.firstFile.file = null;
		files.firstFile.msgError = null;
		files.firstFile.invalidFile = null;
		files.firstFile.etat = etatUpdate.init();
	}
	
	self.initSecondFile = function () {
		files.secondFile.file = null;
		files.secondFile.msgError = null;
		files.secondFile.invalidFile = null;
		files.secondFile.etat = etatUpdate.init();
	}

	return self;
});