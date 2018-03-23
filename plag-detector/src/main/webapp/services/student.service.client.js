(function(){
    angular
        .module("PlagApp")
        .factory('StudentService', StudentService);

    function StudentService($http) {


        var api = {
            "getAllSemesters":getAllSemesters,
            "fetchCourses":fetchCourses,
            "fetchAssignments": fetchAssignments
        };
        return api;

        function getAllSemesters() {
            return $http.get("/rest/professor/allSemesters");
        }

        function fetchCourses(sid) {
            return $http.get("/rest/course/student?id=" + sid);
        }

        function fetchAssignments(courseId) {
            return $http.get("/rest/assignment/course?id=" + courseId);
        }


    }
})();