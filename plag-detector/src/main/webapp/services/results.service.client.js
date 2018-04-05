(function(){
    angular
        .module("PlagApp")
        .factory('ResultsService', ResultsService);

    function ResultsService($http) {


        var api = {
            "assignmentResults":assignmentResults,
            "fetchEdgeStudents":fetchEdgeStudents
        };
        return api;

        function assignmentResults(assignmentID) {
            return $http.get("/rest/result/all?id="+assignmentID);
        }

        function fetchEdgeStudents(student1, student2, aid) {
            return $http.get("rest/result/files?sid1="+student1 + "&sid2="+student2+"&aid="+aid);
        }


    }
})();