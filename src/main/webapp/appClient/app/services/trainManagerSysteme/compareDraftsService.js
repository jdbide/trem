'use strict';

/**
 * Service se chargeant des fonctionnalités de la page comparant les jd en base
 */
socle_app.service('compareDraftsService', ['jsonFactory', 'loadingService', '$q', function(jsonFactory, loadingService, $q) {
	
	var statut = false;
	
	var reponse= 
	{
		status : null,
		message : null,
		data : null
	};
	
	var progressImport= {
		endTraitement : null,
		traitementOk : null,
		lastMsg : null,
		msgErr : null,
	}
	
	function initProgressImport() {
		progressImport.endTraitement = null;
		progressImport.traitementOk = null;
		progressImport.lastMsg = null;
		progressImport.msgErr = null;
	}
	
	function initResponse () {
		reponse.status=null;
		reponse.message=null;
		reponse.data=null;
	}
	
	self = this;
	
	self.getStatut = function (){
		return statut;
	}
	
	self.changeStatut = function(){
		statut =  statut?false:true;
	}
	
	self.getProgressImport = function (idTask) {
		//var deffered  = $q.defer();
		
		var promissJsonFactory = jsonFactory.postJson("webService/app/progressImport", idTask);
		return promissJsonFactory;
	}
	
	self.initReponse = function () {
		reponse.status=null;
		reponse.message=null;
		reponse.data=null;
	}

	self.getPartition = function () {
		//loadingService.show();
        var deffered  = $q.defer();

        var promissJsonFactory = jsonFactory.getJson("webService/app/compareDrafts/");
        promissJsonFactory
            .success(function (datas, status, headers, config) {
            	//loadingService.hide();
                deffered.resolve(datas);
            })
            .error(function (datas, status, headers, config) {
            	//loadingService.hide();
                deffered.reject();
        });
        
        return deffered.promise;
    }
	
	self.getJeuDonneesParIdCompagnieEnvironnement = function (id) {
		loadingService.show();
	    var deffered  = $q.defer();
	    var promissJsonFactory = jsonFactory.getJson("webService/app/compareDrafts/getAllJeuDonneesoParIdCompagnieEnvironnement/"+id);
	    promissJsonFactory
	        .success(function (datas, status, headers, config) {
	        	loadingService.hide();
	        	deffered.resolve(datas);
	        })
	        .error(function (datas, status, headers, config) {
	        	loadingService.hide();
	            deffered.reject();
	    });
	    
	    return deffered.promise;
	}
	
	self.compareJd = function (id1,id2) {
		statut = true;
		loadingService.show();
		
	    var deffered  = $q.defer();
	    var promissJsonFactory = jsonFactory.getJson("webService/app/compareDrafts/compareJd/"+id1+"/"+id2);
	    promissJsonFactory
	        .success(function (data, status, headers, config) {
	        	reponse.status = data.status;
	        	reponse.message = data.message;
	        	reponse.data = data.data;
	        	alert(reponse.data);
	        	loadingService.hide(); 
	            deffered.resolve();
	            statut =false;
	        })
	        .error(function (data, status, headers, config) {
	        	reponse.message = data.message;
	        	reponse.status = data.status;
	        	reponse.data = data.data;
	        	loadingService.hide();
	            deffered.reject();
	            statut =false;
	    });
	    
	    return deffered.promise;
	}
	
	self.getReponse = function () {
		return reponse;
	}
	
	return self;
	
}]);