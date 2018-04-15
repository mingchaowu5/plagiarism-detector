(function(){
    angular
        .module("PlagApp")
        .controller("uploadController", uploadController);

    function uploadController(UserService, $location,$rootScope,$routeParams) {
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
                url : '/rest/file/uploadFile', // or whatever
                dataType : 'json',
                success : function (response) {
                    alert("The server says: " + response);
                },
                error: function (err) {
                    alert("Error from the server side" + err);
                }
            })
        ;
    }
})();