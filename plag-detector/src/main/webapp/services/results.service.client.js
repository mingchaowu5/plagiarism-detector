(function(){
    angular
        .module("PlagApp")
        .factory('ResultsService', ResultsService);

    function ResultsService($http) {


        var api = {
            "assignmentResults":assignmentResults
        };
        return api;

        function assignmentResults(assignmentID) {
            return $http.get("/rest/result/all?id="+assignmentID);
        }

        


    }
})();