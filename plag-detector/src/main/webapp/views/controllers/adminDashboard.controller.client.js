(function() {
	angular.module("PlagApp").controller("adminDashboardController",
			adminDashboardController);

	function adminDashboardController(AdminService, $location, $routeParams,
			$rootScope) {
		var vm = this;
		vm.adminName = "admin";
		//vm.findUserByTime =findUserByTime;
		vm.findCourseBySem=findCourseBySem;
		vm.changeRole=changeRole;
		//vm.createUser = createUser;
		//vm.createCourse = createCourse;
		vm.fetchSemesters = fetchSemesters;
		//vm.updateUser = updateUser;
		//vm.updateCourse = updateCourse;
		
//		vm.users = [ {
//			
//			id :11,
//			firstname : "m",
//			lastname : "ing",
//			ID : 111,
//			role : 0
//		}, {
//			id :12,
//		
//			firstname : "ch",
//			lastname : "ao",
//			ID : 222,
//			role : 0
//		},
//		{
//			id :13,
//			firstname : "ccis",
//			lastname : "ao",
//			ID : 333,
//			role : 1
//		},
//		{
//			id :13,
//			firstname : "krish",
//			lastname : "cc",
//			ID : 444,
//			role : 1
//		}];
//
//		vm.courses = [ {
//			id: 9,
//			name : "MSD"
//		},
//		{
//			id :99,
//			name : "DB"
//		}];
//		
//		  vm.semesters = [ {
//			id : "Fall2017"
//		},
//		{
//			
//			id : "Fall2018"
//		}];
		
		 function init() {
			fetchSemesters();
			 var promise = AdminService.getAllUsers();
		
		   promise
           .then(function (response) {
               if(response.data){
                   vm.semesters = response.data;
               }
           })
           .catch(function (err) {
               console.log("error find users for the semester -> " + semID);
           })
		
		   var promise1 = AdminService.getAllUsers();
			
		   promise1
           .then(function (response) {
               if(response.data){
                   vm.users = response.data;
               }
           })
           .catch(function (err) {
               console.log("error find users for the semester -> " + uID);
           })
		    var promise2 = AdminService.getAllCourses();
			
		   promise2
           .then(function (response) {
               if(response.data){
                   vm.courses = response.data;
               }
           })
           .catch(function (err) {
               console.log("error find users for the course -> " + cID);
           })
		   var promise3 = AdminService.getAllSemesters();
			
		   promise3
           .then(function (response) {
               if(response.data){
                   vm.semesters = response.data;
               }
           })
           .catch(function (err) {
               console.log("error find users for the semester -> " + semID);
           })
		 }
		 init();

//		function findUserByTime(year,semid) {
//            var promise = AdminService.findUserByTime(year,semid);
//
//            promise
//                .then(function (response) {
//                    if(response.data){
//                        vm.users = response.data;
//                    }
//                })
//                .catch(function (err) {
//                    console.log("error find users for the semester -> " + semid);
//                })
//            $location.url("/admin");
//        }
		
		function findCourseBySem(semid) {
            var promise = AdminService.findCourseBySem(semid);

            promise
                .then(function (response) {
                    if(response.data){
                        vm.courses = response.data;
                    }
                })
                .catch(function (err) {
                    console.log("error find coursees for the semester -> " + semID);
                })
            $location.url("/admin");
        }
		
//		 function createUser(user) {
//
//	            if(!validateData(user)){
//	                vm.error = "Username and Password are required fields";
//	                return;
//	            }
//
//	            if(!user){
//	                vm.error = "Please give a valid Input";
//	            }
//	            if(!user.role)
//	                user.role = "Student";
//	            
//	            if(user.role == "Professor"){
//	            		user.uid = "Kariotis";
//	            }
//	            else{
//	            		user.uid = 100;
//	            	}
//	            
//
//	            var promise = AdminService.createUser(user);
//
//	            promise
//	                .then(function (response) {
//	                    if(response){
//	                        var user = response.data;
//
//	                        $rootScope.currentUser = user;
//
//	                        $location.url("/results");
//
//	                    }
//	                    else{
//	                        vm.error = "Error in creating user";
//	                    }
//	                })
//	                .catch(function (err) {
//
//	                    vm.error = "Error in  creating User";
//	                })
//	            $location.url("/admin");
//	        }
//		 function createCourse(course) {
//	            var promise = AdminService.createCourse(course);
//
//	            promise
//	                .then(function (response) {
//	                    if(response){
//	                        var course = response.data;
//
//	                    }
//	                    else{
//	                        vm.error = "Error in creating course";
//	                    }
//	                })
//	                .catch(function (err) {
//
//	                    vm.error = "Error in  creating course";
//	                })
//	            $location.url("/admin");
//
//	        }
		 
		   function fetchSemesters() {
	            var promise = AdminService.getAllSemesters();

	            promise
	                .then(function (response) {
	                    if(response.data){
	                        vm.semesters = response.data;
	                    }
	                })
	                .catch(function (err) {
	                    console.log("Error in fetching the semesters")
	                })
	        }
		   function changeRole(id, type){
			   var promise = AdminService.getAllSemesters();
			   promise
               .then(function (response) {
                   if(response.data){
                	   $location.url("/admin");
                   }
               })
               .catch(function (err) {
                   console.log("error find coursees for the semester -> " + semID);
               })
           $location.url("/admin");
		   }
//		 function updateUser(id,user) {
//			 var promise = AdminService.updateUser(id,user);
//			 $location.url("/admin");
//		 }
//		 
//		 function updateCourse(id,course) {
//			 var promise = AdminService.updateCourse(id,course);
//			 $location.url("/admin");
//		 }

	}
})();