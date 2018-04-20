
(function(){
    angular
        .module("PlagApp")
        .controller("profileUpdateController", profileUpdateController);

    function profileUpdateController(AdminService, $location, $routeParams,$rootScope) {
        var vm = this;
        vm.profileUpdate = profileUpdate;
        vm.userId = $routeParams.uid;
        vm.userType = String($routeParams.tid);
        var users;
        function init() {
    		
        	console.log("just start" );
 		   var promise1 = AdminService.getAllUsers();
 			
 		   promise1
            .then(function (response) {
                if(response.data){
                    vm.users = response.data;
                    users=vm.users;
                    console.log(users);
           		  for( var user in users){
          			 //console.log("test get user" ,user );
           			 if(users[user].id == vm.userId){
           				vm.user = users[user];
           				//console.log(vm.user);
           			 }
           		 }
                }
            })
            .catch(function (err) {
                console.log("error find users for the semester -> " + uID);
            })
// 		  for( var i in vm.users){
// 			 console.log("test get user" );
//  			 if(i.id == vm.userId){
//  				 vm.user=i;
//  				console.log("user is" , vm.user);
//  			 }
//  		 }
 		 
 		  console.log(users);
// 		  for (var i = 0; i < users.length; i++) {
// 		        var user = users[i];
// 		        if (user.id == vm.userId) {
// 		            vm.user= user; 		    
// 		            }
// 		    }
// 		users.forEach(function(val,index,input){
//		       console(val,input[index]);
////		        if (user.id == vm.userId) {
////		            vm.user= user; 		
////		            console.log("user is" , vm.user);
////		            }
//		    });
 		 }
 		 
 		 
 		 init();
        vm.id = vm.userId;
        function profileUpdate(id,user) {
//        	user.type =vm.userType;
            var promise =AdminService.updateUser(id,user);
            $location.url("/admin");
	           window.location.reload(); 
            promise
                .then(function (response) {
                    if(response){
                        //var courses = response.data;

                        //$rootScope.currentCourse = course;

                        $location.url("/admin");

                    }
                    else{
                        vm.error = "Error in update profile";
                    }
                })
                .catch(function (err) {

                    vm.error = "Error in  cupdate profile";
                })
        }

//        function routeLogin() {
//            $location.url("/login");
//        }
    }
})();