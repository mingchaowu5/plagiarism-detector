(function(){
    angular
        .module("PlagApp")
        .factory('ResultsService', ResultsService);

    function ResultsService($http) {


        var api = {
            "assignmentResults":assignmentResults,
            "fetchEdgeStudents":fetchEdgeStudents,
            "fetchFilesToCompare":fetchFilesToCompare
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

    }
})();