
(function(){
    angular
        .module("PlagApp")
        .controller("professorDashboardController", professorDashboardController);

    function professorDashboardController(ProfessorService, $location, $routeParams,$rootScope) {
        var vm = this;
        vm.fetchCourses = fetchCourses;
        vm.fetchAssignments = fetchAssignments;
        vm.viewAllSubmissions = viewAllSubmissions;
        vm.addCourse = addCourse;
        vm.addAssignment = addAssignment;
        vm.editCourse = editCourse;
        vm.editAssignment = editAssignment;
        vm.pushAssignment = pushAssignment;
        vm.pushCourse = pushCourse;
        vm.pushUpdateAssignment = pushUpdateAssignment;
        vm.pushUpdateCourse = pushUpdateCourse;
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
            var promise = ProfessorService.fetchNotifications();

            promise
                .then(function (res) {
                    vm.notifications = res.data;
                })
                .catch(function (err) {
                    console.log("Error in fetching notifications")
                });
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

        function viewAllSubmissions() {
           // TODO
        }
        
        function addCourse() {
            document.getElementById("myButton").click();
        }

        function addAssignment() {
            document.getElementById("myButton").click();
        }

        function editCourse(course) {
            vm.selectedCourse = {};
            vm.selectedCourse.name = course.name;
            vm.selectedCourse.sid = vm.sid;
            vm.selectedCourse.id = course.id;
            document.getElementById("myButton").click();
        }

        function editAssignment(assignment) {
            vm.selectedAssignment = {};
            vm.selectedAssignment.name = assignment.name;
            vm.selectedAssignment.cid = vm.cid;
            vm.selectedAssignment.id = assignment.id;
            document.getElementById("myButton").click();
        }

        function pushAssignment(newAssignment) {
            console.log("insied the controller")
            newAssignment.cid = vm.cid;
            var promise = ProfessorService.addNewAssignemnt(newAssignment);

            promise
                .then(function (res) {
                    if(res.data){
                        $location.url("#!/dashboard/"+ vm.pid +"/semester/"+vm.sid+"/course/"+vm.cid);
                    }
                })
                .catch(function (err) {
                    alert("error adding the assignment.\n Contact Admin")
                })
        }

        function pushCourse(newCourse) {
            console.log("insied the controller")
            newCourse.sid = vm.sid;
            var promise = ProfessorService.addNewCourse(newCourse);

            promise
                .then(function (res) {
                    if(res.data){
                        $location.url("#!/dashboard/"+ vm.pid +"/semester/"+vm.sid);
                    }
                })
                .catch(function (err) {
                    alert("error adding the assignment.\n Contact Admin")
                })
        }

        function pushUpdateAssignment(assignment) {
            var promise = ProfessorService.updateAssignemnt(assignment);

            promise
                .then(function (res) {
                    if(res.data){
                        $location.url("#!/dashboard/"+ vm.pid +"/semester/"+vm.sid+"/course/"+vm.cid);
                    }
                })
                .catch(function (err) {
                    alert("error adding the assignment.\n Contact Admin")

                })
        }

        function pushUpdateCourse(course) {
            var promise = ProfessorService.updateCourse(course);

            promise
                .then(function (res) {
                    if(res.data){
                        $location.url("#!/dashboard/"+ vm.pid +"/semester/"+vm.sid);
                    }
                })
                .catch(function (err) {
                    alert("error adding the course.\n Contact Admin")

                })
        }
    }
})();