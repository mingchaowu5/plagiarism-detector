(function(){
    angular
        .module("PlagApp")
        .controller("uploadController", uploadController);

    function uploadController(UserService, $location,$rootScope,$routeParams) {
        var vm = this;

        vm.sid = $routeParams.sid;
        vm.aid = $routeParams.aid;
        
    }
})();