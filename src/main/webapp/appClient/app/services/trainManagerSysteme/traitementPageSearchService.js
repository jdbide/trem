'use strict';

/**
 * 
 */
socle_app.service('traitementPageSearchService', function() {
	var currentPage = {
		pages : null,
		active:false,
		page : {
			idPage:null,
			titlePage:null,
			urlPage:null,
			active:null
		},
		
		/*
		 * Initialisation de l'objet currentPage
		 */
		init: function () {
			self = this;

			self.active=true;
			angular.forEach(self.pages, function(value, key) {
				if (value.active) {
					self.page = value;
				}
			});

			return self;
		},
		
		isInit: function () {
			self = this;
			
			if (!self.active || self.pages == null || self.page.idPage == null) {
				return false;
			}

			return true;
		},
		
		/*
		 * Change current page
		 */
		changePage: function () {
			self = this;
			
			if (!self.active || self.page.idPage == null) {
				return currentPage.init();
			}
			console.log(self);
			if (self.page.idPage == self.pages[0].idPage) {
				self.page = null;
				self.page = self.pages[1];
			} else {
				self.page = null;
				self.page = self.pages[0];
			}

			self.active=true;
			
			return self;
		}
	}
	
	self = this;

	self.getCurrentPage = function() {
		return currentPage.page;
	}
	
	self.isInit = function () {
		return currentPage.isInit();
	}
	
	self.init = function () {
		currentPage.init();
	}
	
	self.changePage = function () {
		return currentPage.changePage();
	}

	self.setPages = function (pages) {
		currentPage.pages = pages;
	}

	return self;
});