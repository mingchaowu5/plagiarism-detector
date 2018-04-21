(function(){
    angular
        .module("PlagApp")
        .controller("compareFilesController", compareFilesController);

    function compareFilesController(ResultsService, $location,$rootScope, $routeParams) {
        var vm = this;
        vm.id =  $routeParams.id;

        function init() {
            var promise = ResultsService.fetchFilesToCompare(vm.id);
            promise
                .then(function (data, status, headers) {
                    if(data.data){
                        vm.results = data.data;
                        var text = atob(vm.results.file1);
                        var text2 = atob(vm.results.file2);
                        var contentType =  "application/octet-stream";
                        var urlCreator = window.URL || window.webkitURL || window.mozURL || window.msURL;
                        if (urlCreator) {
                            var blob = new Blob([text], { type: contentType });
                            var url = urlCreator.createObjectURL(blob);
                            var a = document.createElement("a");
                            document.body.appendChild(a);
                            a.style = "display: none";
                            a.href = url;
                            a.download = "download.html"; //you may assign this value from header as well 

                            //
                            var doc = document.getElementById('iframe1').contentWindow.document;
                            doc.open();
                            doc.write(text);
                            doc.close();

                            var doc2 = document.getElementById('iframe2').contentWindow.document;
                            doc2.open();
                            doc2.write(text2);
                            doc2.close();

                            // a.click();
                            // window.URL.revokeObjectURL(url);
                        }
                    }
                })
                .catch(function (err) {
                    console.log("Error in fetching the files to compare")
                })
        }
        init();
    }
})();