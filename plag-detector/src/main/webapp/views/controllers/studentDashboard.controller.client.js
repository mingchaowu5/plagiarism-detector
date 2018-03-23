
(function(){
    angular
        .module("PlagApp")
        .controller("studentDashboardController", studentDashboardController);

    function studentDashboardController(StudentService, $location, $routeParams,$rootScope) {
        var vm = this;
        // vm.fetchCourses = fetchCourses;
        vm.fetchAssignments = fetchAssignments;
        vm.studentName = "xyz";
        vm.studentId = $routeParams.sid;

        // vm.semesters = [{
        //     name: "Spring2018",
        //     ID: 111
        // },
        //     {
        //         name:"Fall2017",
        //         ID:222
        //     }];

        // vm.courses = [{
        //     name: "MapReduce",
        //     ID: 111
        // },
        //     {
        //         name:"DataMining",
        //         ID:222
        //     }];

        // vm.assignments = [{
        //     name: "assignment-1",
        //     ID: 111
        // },
        //     {
        //         name:"assignment-2",
        //         ID:222
        //     }];


        function init() {
            var promise = StudentService.fetchCourses(vm.studentId);

            promise
                .then(function (response) {
                    console.log(response);
                    if(response.data){
                        vm.courses = response.data;
                    }
                })
                .catch(function () {
                    console.log("error fetching te courses for the semester -> " + semID);
                })
        }
        init();

        function fetchAssignments(courseId) {
            var promise = StudentService.fetchAssignments(courseId);

            promise
                .then(function (response) {
                    if(response.data){
                        vm.assignments = response.data;
                    }
                })
                .catch(function () {
                    console.log("error in fetching student assignments");
                })
        }
    }
})();