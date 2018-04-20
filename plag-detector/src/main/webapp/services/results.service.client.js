(function(){
    angular
        .module("PlagApp")
        .factory('ResultsService', ResultsService);

    function ResultsService($http) {


        var api = {
            "assignmentResults":assignmentResults,
            "fetchEdgeStudents":fetchEdgeStudents,
            "fetchFilesToCompare":fetchFilesToCompare,
            "sendActionMail":sendActionMail
        };
        return api;

        function assignmentResults(assignmentID) {
            return $http.get("/rest/snapshot/result?id="+assignmentID);
        }

        function fetchEdgeStudents(student1, student2, aid) {
            return $http.get("/rest/snapshot/submission?id1="+student1 + "&id2="+student2);
        }

        function fetchFilesToCompare(id) {
            return $http.get("/rest/snapshot/file?id="+id);
        }

        function sendActionMail(sid1, sid2,aid, pid) {
            return $http.get("/rest/user/mail?student_id1="+sid1 + "&student_id2="+sid2 + "&assignment_id="+aid + "&professor_id="+pid);
        }

    }
})();