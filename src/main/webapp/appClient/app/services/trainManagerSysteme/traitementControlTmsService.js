'use strict';

/**
 * 
 */
socle_app.service('traitementControlTmsService', function() {
	var files = {
		firstFile : {
			file: null,
			isStartTraitement:null,
			isFinishTraitement:null,
			isFinishTraitementSuccess:null,
			isUploadSuccess:null,
			isFormatOk:null,
			isDataValidate:null,
			msgDataValidate:null
		},
		secondFile : {
			file: null,
			isStartTraitement:null,
			isFinishTraitement:null,
			isFinishTraitementSuccess:null,
			isUploadSuccess:null,
			isFormatOk:null,
			isDataValidate:null,
			msgDataValidate:null
		}
	};

	self = this;

	self.files = function() {
		return files;
	}
	
	self.init = function () {
		files.firstFile = {file:null,isStartTraitement:null,isFinishTraitement:null,isFinishTraitementSuccess:null,isUploadSuccess:null,isFormatOk:null,isDataValidate:null,msgDataValidate:null};
		files.secondFile = {file:null,isStartTraitement:null,isFinishTraitement:null,isFinishTraitementSuccess:null,isUploadSuccess:null,isFormatOk:null,isDataValidate:null,msgDataValidate:null};
	}
	
	self.initFirstFile = function () {
		files.firstFile = {file:null,isStartTraitement:null,isFinishTraitement:null,isFinishTraitementSuccess:null,isUploadSuccess:null,isFormatOk:null,isDataValidate:null,msgDataValidate:null};
	}
	
	self.initSecondFile = function () {
		files.secondFile = {file:null,isStartTraitement:null,isFinishTraitement:null,isFinishTraitementSuccess:null,isUploadSuccess:null,isFormatOk:null,isDataValidate:null,msgDataValidate:null};
	}

	return self;
});