(function(){
    angular
        .module("PlagApp")
        .factory('ProfessorService', ProfessorService);

    function ProfessorService($http) {


        var api = {
            "getAllSemesters":getAllSemesters,
            "fetchCourses":fetchCourses,
            "fetchAssignments": fetchAssignments,
            "getSnapshots":getSnapshots
        };
        return api;

        function getAllSemesters() {
            return $http.get("/rest/semester/all");
        }

        function fetchCourses(semID) {
            return $http.get("/rest/course/semester?id=" + semID);
        }

        function fetchAssignments(courseId) {
            return $http.get("/rest/assignment/course?id=" + courseId);
        }

        function getSnapshots(aid) {
            return $http.get("/rest/snapshots/assignment?id="+aid);
        }

    }
})();