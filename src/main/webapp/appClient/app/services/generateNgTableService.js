'use strict';

/**
 * Service se chargeant de renvoyer les objets nécessaires pour générer un tableau
 */
socle_app.service('generateNgTableService', ["$filter", function($filter) {
	
    var self = this;
    
	var dateComparer = function(actualDate, expressionDate) {
		var expressionString = $filter('date')(expressionDate, 'yyyy-MM-dd');
		var actualString = $filter('date')(actualDate, 'yyyy-MM-dd');
		return actualDate && actualString.indexOf(expressionString) > -1;
	}
	
    self.getTableColumns = function(dataColumns) {
    	var res = [];
    	for (var i = 0; i < dataColumns.length; i++) {
    		var col = dataColumns[i];
    		var type = col.fieldType;
    		if (type === "Date") {
    			col.format = {
    				name : "date",
    				params : ["yyyy-MM-dd HH:mm:ss"]
    			}
    		} else {
    			col.format = {
    				name : "identity"
    			}
    		}
    		res.push(col);
    	}
    	return res;
    };
    
    self.applyComparer = function(actual, expression) {
		if (expression instanceof Date) {
			return dateComparer(actual, expression);
		} else {
			var strValue = actual + '';
			return strValue.contains(expression);
		}
	};

	self.format = function(model, filter) {
		var paramsArray = ([model]).concat(filter.params);
		return $filter(filter.name).apply(this, paramsArray);
	};

    return self;
}]);