(function(){
    angular
        .module("PlagApp")
        .factory('ProfessorService', ProfessorService);

    function ProfessorService($http) {


        var api = {
            "getAllSemesters":getAllSemesters,
            "fetchCourses":fetchCourses,
            "fetchAssignments": fetchAssignments,
            "getSnapshots":getSnapshots,
            "addNewAssignemnt":addNewAssignemnt,
            "addNewCourse":addNewCourse,
            "fetchNotifications":fetchNotifications,
            "updateAssignemnt":updateAssignemnt,
            "updateCourse":updateCourse
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

        //new API
        function getSnapshots(aid) {
            return $http.get("/rest/snapshot/assignment?id="+aid);
        }

        //New API
        function addNewAssignemnt(newAssignemnt) {
            return $http.get("/rest/assignment/insert?course_id="+newAssignemnt.cid+"&name="+newAssignemnt.name);
        }

        //New API
        function addNewCourse(newCourse) {
            return $http.get("/rest/course/insert?semester_id="+newCourse.sid+"&name="+newCourse.name);
        }

        //New API
        function fetchNotifications() {
            return $http.get("/rest/notification/all");
        }

        //New API
        function updateAssignemnt(assignemnt) {
            return $http.get("/rest/assignment/update?course_id="+assignemnt.cid+"&name="+assignemnt.name+"&assignment_id="+assignemnt.id);
        }

        //New API
        function updateCourse(course) {
            return $http.get("/rest/course/edit?semester_id="+course.sid+"&name="+course.name+"&id="+course.id);
        }

    }
})();