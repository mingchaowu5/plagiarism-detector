
(function(){
    angular
        .module("PlagApp")
        .controller("courseUpdateController", courseUpdateController);

    function courseUpdateController(AdminService, $location, $routeParams,$rootScope) {
        var vm = this;
        vm.updateCourse = updateCourse;
        vm.id = $routeParams.cid;
        vm.fetchSemesters = fetchSemesters;
//        function init() {
//        	 vm.id =id;
//        }
//        init();
        function init() {
			fetchSemesters();
        }
        init();
        function fetchSemesters(){
        var promise = AdminService.getAllSemesters();
		
		   promise
        .then(function (response) {
            if(response.data){
                vm.semesters = response.data;
            }
        })
        
        .catch(function (err) {
            console.log("error find users for the semester -> " + semID);
        })
    }
        function updateCourse(id,course) {


            var promise = AdminService.updateCourse(id,course);
            $location.url("/admin");
	           window.location.reload(); 
            promise
                .then(function (response) {
                    if(response){
                        //var course = response.data;

                        $location.url("/admin");

                    }
                    else{
                        vm.error = "Error in update course";
                    }
                })
                .catch(function (err) {

                    vm.error = "Error in  update course";
                })

        }
        
//        function updateCourse(id,newcourse) {
//			 var promise = AdminService.updateCourse(id,newCourse);
//			 $location.url("/admin");
//		 }
        
       
    }
})();