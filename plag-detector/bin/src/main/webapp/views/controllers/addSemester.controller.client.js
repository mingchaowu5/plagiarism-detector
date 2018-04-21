
(function(){
    angular
        .module("PlagApp")
        .controller("addSemesterController", addSemesterController);

    function addSemesterController(AdminService, $location, $routeParams,$rootScope) {
        var vm = this;
        vm.createSemester = createSemester;
        vm.routeLogin = routeLogin;
        vm.showAdmin = true;

        
       
        function createSemester(semester) {

            var promise = AdminService.createSemester(semester);
            $location.url("/admin");
	           window.location.reload(); 
            promise
                .then(function (response) {
                    if(response){
                    	console.log("add semester");
                        vm.semester = response.data;

                    }
                    else{
                        vm.error = "Error in creating semester";
                    }
                })
                .catch(function (err) {

                    vm.error = "Error in  creating semester";
                })

        }
        
        function routeLogin() {
            $location.url("/login");
        }
    }
})();