'use strict';

socle_app.controller("testCtrl", ["$scope", "NgTableParams", function($scope, NgTableParams) {
	$scope.cols = [ {
		field : "name",
		title : "Name",
		sortable : "name",
		filter: {name: "text"},
		show : true
	}, {
		field : "age",
		title : "Age",
		sortable : "age",
		filter: {age: "number"},
		show : true
	}, {
		field : "money",
		title : "Money",
		sortable: "money",
		show : true
	} ];

	$scope.params = new NgTableParams({
		// initial sort order
		sorting : {
			age : "desc"
		}
	}, {
		dataset : [ 
        {name: "Moroni", age: 50, money: 0},
        {name: "Simon", age: 43, money: 10},
        {name: "Jacob", age: 27, money: 20},
        {name: "Nephi", age: 29, money: 30},
        {name: "Christian", age: 34, money: 40},
        {name: "Tiancum", age: 43, money: 50},
        {name: "Jacob", age: 27, money: 60} ]
	});
	
	$scope.title = "Test";
}]);
