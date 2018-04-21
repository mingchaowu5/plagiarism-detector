
(function(){
    angular
        .module("PlagApp")
        .controller("semesterUpdateController", semesterUpdateController);

    function semesterUpdateController(AdminService, $location, $routeParams,$rootScope) {
        var vm = this;
        vm.updateSemester = updateSemester;
        vm.semid = $routeParams.sid;
        
//        function init() {
//        	 vm.id =id;
//        }
//        init();

        function updateSemester(semid,semester) {


            var promise = AdminService.updateSemester(semid,semester);
            $location.url("/admin");
	           window.location.reload(); 
            promise
                .then(function (response) {
                    if(response){
                        //var semester = response.data;

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