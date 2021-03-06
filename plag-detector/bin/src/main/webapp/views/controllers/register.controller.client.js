
(function(){
    angular
        .module("PlagApp")
        .controller("registerController", registerController);

    function registerController(UserService, $location, $routeParams,$rootScope) {
        var vm = this;
        vm.register = register;
        vm.routeLogin = routeLogin;
        vm.showAdmin = true;

        vm.user = {}
        vm.user.role = "0"

        

        function validateData(user) {
            var ret = false;

            if(!user){
                vm.error = "Please give a valid Input";
                return false;
            }

            if(user.username && user.password)
            {
                ret = true;

            }

            return ret;
        }

        function register(user) {

            if(!validateData(user)){
                vm.error = "Username and Password are required fields";
                return;
            }


            if(!user){
                vm.error = "Please give a valid Input";
            }
            if(!user.role)
                user.role = "Professor";

            /**
             * hardcoding university ID
             */
            if(user.role == "Professor"){
            		user.uid = "Kariotis";
            }
            else{
            		user.uid = 100;
            	}
            

            var promise = UserService.register(user);

            promise
                .then(function (response) {
                    if(response){
                        var user = response.data;
                        console.log("success in registering ")
                        console.log(user);
                        $rootScope.currentUser = user;

                        if(user.role == 0){
                            $location.url("/student/"+user.id);
                        }else{
                            $location.url("/dashboard/"+user.id);
                        }
                        

                    }
                    else{
                        vm.error = "Error in creating user";
                    }
                })
                .catch(function (err) {

                    vm.error = "Error in  creating User";
                })

            // var checkPromise = UserService.findUserByCredentials(user.username,null,true);
            //
            // checkPromise
            //     .then(function (res) {
            //         var promise = UserService.register(user);
            //
            //         promise
            //             .then(function (response) {
            //                 if(response){
            //                     var user = response.data;
            //
            //                     $rootScope.currentUser = user;
            //
            //                     $location.url("/results");
            //
            //                 }
            //                 else{
            //                     vm.error = "Error in creating user";
            //                 }
            //             })
            //             .catch(function (err) {
            //
            //                 vm.error = "Error in  creating User";
            //             })
            //
            //     })
            //     .catch(function (err) {
            //         vm.error = "Username Not available, Please use another Username";
            //     })


        }

        function routeLogin() {
            $location.url("/login");
        }
    }
})();