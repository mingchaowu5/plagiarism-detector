(function(){
    angular
        .module("PlagApp")
        .controller("uploadController", uploadController);

    function uploadController(UserService, $location,$rootScope,$routeParams,$window) {
        var vm = this;

        vm.sid = $routeParams.sid;
        vm.aid = $routeParams.aid;
        vm.vid = $routeParams.vid;


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
                		$window.location.href = "#!/student/"+ vm.sid + "/assignment/" + vm.aid;
                     $window.location.reload();
                		//alert("The server says: " + response + vid);
                },
                error: function (err) {
                    alert("Error from the server side" + err);
                }
            })
        ;
    }
})();