(function(){
    angular
        .module("PlagApp")
        .controller("uploadController", uploadController);

    function uploadController(UserService,StudentService, $location,$rootScope,$routeParams,$window) {
        var vm = this;

        vm.sid = $routeParams.sid;
        vm.aid = $routeParams.aid;
        vm.vid = $routeParams.vid;

        vm.uploadGit = uploadGit;

        function uploadGit() {
            var promise = StudentService.uploadGitLink(vm.gitLink, vm.sid, vm.aid);
            promise
                .then(function(params) {
                    if(params){
                        alert("successfully uploaded git link")
                        // $location.url("#!/student/"+vm.sid);
                    }
                })
                .catch(function (err) {
                    console.log("error in uploadign git link")
                })
        }


        // $("#uploadBtn").click( function()
        //     {
        //         alert('button clicked');
        //     }
        // );

        // $("#formUpload").submit(function(event) {
        //
        //     /* stop form from submitting normally */
        //     event.preventDefault();
        //
        //     /* get the action attribute from the <form action=""> element */
        //     var $form = $( this ), url = $form.attr( 'action' );
        //
        //     /* Send the data using post with element id name and name2*/
        //     var posting = $.post( url, { name: $('#name').val()} );
        //
        //     /* Alerts the results */
        //     posting.done(function( data ) {
        //         alert('success');
        //     });
        // });


        $('#formUpload')
            .ajaxForm({
                url : '/rest/assignment/upload', // or whatever
                dataType : 'json',
                success : function (response) {
                    alert("successfully uploaded Project to server")
                		//alert("The server says: " + response + vid);
                },
                error: function (err) {
                    alert("Error from the server side" + err);
                }
            })
        ;
    }
})();