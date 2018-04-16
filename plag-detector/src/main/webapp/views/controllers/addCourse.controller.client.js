
(function(){
    angular
        .module("PlagApp")
        .controller("addCourseController", addCourseController);

    function addCourseController(AdminService, $location, $routeParams,$rootScope) {
        var vm = this;
        vm.createCourse = createCourse;
        vm.routeLogin = routeLogin;
        vm.fetchSemesters = fetchSemesters;
        vm.showAdmin = true;

        
        function init() {
        	fetchSemesters();
        }
        init();
        function fetchSemesters() {
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
        function createCourse(course) {

            var promise = AdminService.createCourse(course);

            promise
                .then(function (response) {
                    if(response){
                        //var course = response.data;
                        console.log("add course");
                        $location.url("/admin");

                    }
                    else{
                        vm.error = "Error in creating course";
                    }
                })
                .catch(function (err) {

                    vm.error = "Error in  creating course";
                })
            $location.url("/admin");
        }
//        function fetchSemesters() {
//            var promise = ProfessorService.getAllSemesters();
//
//            promise
//                .then(function (response) {
//                    if(response.data){
//                        vm.semesters = response.data;
//                    }
//                })
//                .catch(function (err) {
//                    console.log("Error in fetching the semesters")
//                })
//        }
        function routeLogin() {
            $location.url("/login");
        }
    }
})();