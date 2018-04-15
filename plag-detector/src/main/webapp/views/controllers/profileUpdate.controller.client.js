
(function(){
    angular
        .module("PlagApp")
        .controller("profileUpdateController", profileUpdateController);

    function profileUpdateController(AdminService, $location, $routeParams,$rootScope) {
        var vm = this;
        vm.profileUpdate = profileUpdate;
        vm.userId = $routeParams.uid;
        vm.userType = $routeParams.type;
//        function init() {
//       	vm.id =id;
//       }
//       init();
        
        function profileUpdate(id,user) {
        	
            var promise =AdminService.profileUpdate(id,user);

            promise
                .then(function (response) {
                    if(response){
                        var courses = response.data;

                        $rootScope.currentCourse = course;

                        $location.url("/admin");

                    }
                    else{
                        vm.error = "Error in creating course";
                    }
                })
                .catch(function (err) {

                    vm.error = "Error in  creating course";
                })
        }

//        function routeLogin() {
//            $location.url("/login");
//        }
    }
})();