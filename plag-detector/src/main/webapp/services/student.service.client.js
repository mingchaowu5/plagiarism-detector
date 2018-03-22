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

        function fetchCourses(semID) {
            return $http.get("/rest/professor/course/" + semID);
        }

        function fetchAssignments(courseId) {
            return $http.get("/rest/professor/assignments/" + courseId);
        }


    }
})();