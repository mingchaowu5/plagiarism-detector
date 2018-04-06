(function(){
    angular
        .module("PlagApp")
        .controller("loginController", loginController);

    function loginController(UserService, $location,$rootScope) {
        var vm = this;
        vm.login = login;
        vm.routeRegister = routeRegister;

        function login(user) {
            if(!user)
            {
                vm.error = "Template returns NULL";
                return;
            }
            if(user.username == "admin"){
            		$location.url("/admin");
            }
            UserService
                .login(user)
                .then(function (response) {
                    var user = response.data;
                    if(user){
                        console.log(user);
                         $rootScope.currentUser = user;
                        // $location.url("/");
                        console.log("User found... logging in");
                        if(user.username == "admin" && user.password == "admin"){
                        		$location.url("/admin");
                        }
                        else if(user.officeLocation){
                            $location.url("/dashboard/"+user.id);
                        }
                        else {
                            $location.url("/student/"+user.id);
                        }

                    }
                    else {
                        vm.error = "user not found";
                    }
                })
                .catch(function (err) {
                    vm.error = "User Not Found"
                })

        }

        function routeRegister() {
            $location.url("/register/");
        }
    }
})();