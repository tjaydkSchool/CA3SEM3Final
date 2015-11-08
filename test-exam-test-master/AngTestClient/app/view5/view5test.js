/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


descripe('View5Ctrl', function(){
    var $httpBackend, $rootscope, ctrl;
    
    beforeEach(inject(function($controller, $rootScope){
        scope = $rootScope.$new();
        ctrl = $controller("View5Ctrl", {$scope : scope});
    }));
    
    
})