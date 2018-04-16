
(function(){
    angular
        .module("PlagApp")
        .controller("courseUpdateController", courseUpdateController);

    function courseUpdateController(AdminService, $location, $routeParams,$rootScope) {
        var vm = this;
        vm.updateCourse = updateCourse;
        vm.cid = $routeParams.cid;
        
//        function init() {
//        	 vm.id =id;
//        }
//        init();

        function updateCourse(id,course) {


            var promise = AdminService.updateCourse(id,course);

            promise
                .then(function (response) {
                    if(response){
                        //var course = response.data;

                        $location.url("/admin");

                    }
                    else{
                        vm.error = "Error in adding course";
                    }
                })
                .catch(function (err) {

                    vm.error = "Error in  adding course";
                })

        }
        
//        function updateCourse(id,newcourse) {
//			 var promise = AdminService.updateCourse(id,newCourse);
//			 $location.url("/admin");
//		 }
        
       
    }
})();