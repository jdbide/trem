'use strict';

/**
 * Directive angularjs
 * Directive pour la création et manipulation  d'un modal
 *
 */
var app = angular.module('myModal', []);

app.directive('modal', function() {
    
    var directiveModal = {
      templateUrl: function(element, attr) {
          return attr.url;
      },
      restrict: 'E',
      transclude: true,
      replace:true,
      scope:true,
          
      link: function postLink(scope, element, attrs) {
          scope.title = attrs.title;

          scope.$watch(attrs.visible, function(value){
              if(value == true)
                  $(element).modal('show');
              else
                  $(element).modal('hide');
          });
          
          $(element).on('shown.bs.modal', function(){
              scope.$apply(function(){
                  scope.$parent[attrs.visible] = true;
              });
          });
          $(element).on('hidden.bs.modal', function(){
              scope.$apply(function(){
                  scope.$parent[attrs.visible] = false;
              });
          });         
      }
    };    
    
    return directiveModal;    
});