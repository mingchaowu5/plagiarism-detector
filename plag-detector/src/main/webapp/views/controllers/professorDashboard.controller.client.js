
(function(){
    angular
        .module("PlagApp")
        .controller("professorDashboardController", professorDashboardController);

    function professorDashboardController(ProfessorService, $location, $routeParams,$rootScope) {
        var vm = this;
        vm.fetchCourses = fetchCourses;
        vm.fetchAssignments = fetchAssignments;
        vm.professorName = "xyz";
        vm.pid = $routeParams.pid;
        vm.sid = $routeParams.sid;
        vm.cid = $routeParams.cid;
        vm.aid = $routeParams.aid;

        // vm.semesters = [{
        //         name: "Spring2018",
        //         ID: 111
        //     },
        //     {
        //         name:"Fall2017",
        //         ID:222
        //     }];

        // vm.courses = [{
        //         name: "MapReduce",
        //         ID: 111
        //     },
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
            if(vm.pid && vm.sid && vm.cid && vm.aid){
                fetchSnapshots(vm.aid);
            }
            else if(vm.pid && vm.sid && vm.cid){
                fetchAssignments(vm.cid);
            }
            else if(vm.pid && vm.sid ){
                fetchCourses(vm.sid);
            }
            else if(vm.pid){
                fetchSemesters();
            }
        }
        init();

        function fetchSnapshots(aid) {
            var promise  = ProfessorService.getSnapshots(aid);

            promise
                .then(function (response) {
                    if(response.data){
                        vm.snapshots = response.data;
                        vm.loadProfileEditdiv = "SNAPSHOTS";
                    }
                })

        }
        function fetchSemesters() {
            var promise = ProfessorService.getAllSemesters();

            promise
                .then(function (response) {
                    if(response.data){
                        vm.semesters = response.data;
                    }
                })
                .catch(function (err) {
                    console.log("Error in fetching the semesters")
                })
        }

        function fetchCourses(semID) {
            var promise = ProfessorService.fetchCourses(semID);

            promise
                .then(function (response) {
                    if(response.data){
                        vm.courses = response.data;
                        vm.loadProfileEditdiv = "COURSES";
                    }
                })
                .catch(function (err) {
                    console.log("error fetching te courses for the semester -> " + semID);
                })
        }

        function fetchAssignments(courseId) {
            var promise = ProfessorService.fetchAssignments(courseId);

            promise
                .then(function (response) {
                    if(response.data){
                        vm.assignments = response.data;
                        vm.loadProfileEditdiv = "ASSIGNMENTS";
                    }
                })
        }
    }
})();