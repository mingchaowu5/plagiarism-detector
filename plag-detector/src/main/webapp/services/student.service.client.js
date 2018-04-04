(function(){
    angular
        .module("PlagApp")
        .factory('StudentService', StudentService);

    function StudentService($http) {


        var api = {
            "getAllSemesters":getAllSemesters,
            "fetchCourses":fetchCourses,
            "fetchAssignments": fetchAssignments,
            "fetchAssignmentVersions":fetchAssignmentVersions,
            "fetchAllCoursesAvailable":fetchAllCoursesAvailable,
            "addCourseToStudent":addCourseToStudent
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

        function fetchAssignmentVersions(assignmentID) {
            return $http.get("/rest/assignment/versions?id="+assignmentID);
        }

        function fetchAllCoursesAvailable(studentID) {
            return $http.get("/rest/course/all?id=" +studentID);
        }

        function addCourseToStudent(courseID, studentId) {
            return $http.get("/rest/course/add?sid=" + studentId + "&cid=" + courseID);
        }

    }
})();