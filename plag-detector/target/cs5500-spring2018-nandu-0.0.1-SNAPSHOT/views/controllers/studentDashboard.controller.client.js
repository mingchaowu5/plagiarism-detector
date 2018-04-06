
(function(){
    angular
        .module("PlagApp")
        .controller("studentDashboardController", studentDashboardController);

    function studentDashboardController(StudentService, $location, $routeParams,$rootScope, $scope) {
        var vm = this;
        // vm.fetchCourses = fetchCourses;
        vm.fetchAssignments = fetchAssignments;
        vm.loadAllcourses = loadAllcourses;
        vm.addCourseToStudent = addCourseToStudent;
        vm.studentName = "XYZ";
        vm.studentId = $routeParams.sid;
        vm.courseID = $routeParams.cid;
        vm.assignmentID = $routeParams.aid;
        // vm.fetchAssignmentsVersions = fetchAssignmentsVersions;

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
            if(vm.studentId && vm.courseID && vm.assignmentID){
                fetchAssignmentsVersions(vm.assignmentID);
            }
            else if(vm.studentId && vm.courseID){
                fetchAssignments(vm.courseID);
            }
            else if(vm.studentId){
                fetchCourses(vm.studentId);
            }
        }
        init();

        function fetchCourses(sid) {
            var promise = StudentService.fetchCourses(sid);

            promise
                .then(function (response) {
                    // console.log(response);
                    if(response.data){
                        vm.courses = response.data;
                    }
                })
                .catch(function () {
                    console.log("error fetching te courses for the semester -> " + semID);
                })
        }

        function fetchAssignmentsVersions(assignmentID) {
            vm.currentAssignmentID = assignmentID;
            var promise = StudentService.fetchAssignmentVersions(assignmentID, vm.studentId);
            
            promise
                .then(function (response) {
                    if(response){
                        vm.loadProfileEditdiv = "VERSIONS";
                        vm.assignemntVersions = response.data;
                        vm.currentVersion = vm.assignemntVersions.length + 1 ;
                        // $scope.$apply();

                    }
                })
        }

        function fetchAssignments(courseId) {
            var promise = StudentService.fetchAssignments(courseId);

            promise
                .then(function (response) {
                    if(response.data){
                        vm.assignments = response.data;
                        vm.loadProfileEditdiv = 'ASSIGNMENTS';
                    }
                })
                .catch(function () {
                    console.log("error in fetching student assignments");
                })
        }
        
        function loadAllcourses() {
            var promise = StudentService.fetchAllCoursesAvailable(vm.studentId);
            
            promise
                .then(function (response) {
                    if(response.data){
                        vm.allCourses = response.data;
                    }
                })
                .catch(function (err) {
                    console.log("error in fetching all the courses")
                })
        }

        function addCourseToStudent(courseID, studentId) {
            var promise = StudentService.addCourseToStudent(courseID, studentId);

            promise
                .then(function (response) {
                    if(response.data){
                        $("#modalCloseBtn").click( function()
                            {
                                $location.url("#!/student/"+ vm.studentId);
                            }
                        );
                    }
                })
                .catch(function (err) {
                    alert("error in adding course to Student")
                })
        }

    }
})();