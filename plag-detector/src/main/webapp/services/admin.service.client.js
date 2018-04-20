(function(){
    angular
        .module("PlagApp")
        .factory('AdminService', adminService);

    function adminService($http) {


        var api = {
        	//"createUser": createUser,
            "updateUser": updateUser,
            //"findUserByCredentials": findUserByCredentials,
            //"getCurrentUser": getCurrentUser,
           // "findUserByUsername":findUserByUsername,
            "deleteUser":deleteUser,
            "getAllUsers": getAllUsers,
            "login":login,
            "logout": logout,
            "register": register,
            //"deleteUserById":deleteUserById,
            
            //"updateUserByID":updateUserByID,
            //following are new api
            //"findUserByTime": findUserByTime,
            "findCourseBySem": findCourseBySem,
            "createCourse": createCourse,
            "updateCourse": updateCourse,
            //"findCourseByname":findCourseByname,
            "deleteCourse":deleteCourse,
            "getAllCourses": getAllCourses,
            //"deleteCourseById":deleteCourseById,
            //"updateCourseByID":updateCourseByID            	
            "changeRole" :changeRole,
            
            "createSemester" :createSemester,
            "getAllSemesters" : getAllSemesters,
            "updateSemester" : updateSemester,
            "deleteSemester" : deleteSemester
            
            
        };
        return api;
//        function createUser(user) {
//            return $http.get("/rest/user/register?email=" + user.email + "&username=" + user.username + "&password=" + user.password
//            + "&firstname="+ user.firstname + "&lastname="+ user.lastname + "&type="+ user.role +  "&uid="+ user.uid);
//        }

        function register(user) {
            return $http.get("/rest/user/register?email=" + user.email + "&username=" + user.username + "&password=" + user.password
            + "&firstname="+ user.firstname + "&lastname="+ user.lastname + "&type="+ user.role);
        }
        
        function changeRole(id, type){
        	return $http.get("/rest/user/change?id=" + id + "&type=" + type);
        }
       
        function logout(user) {
            return $http.post("/api/logout");
        }


        function login(user) {
            return $http.get("/rest/user/login?username=" +user.username + "&password=" + user.password +"&type="+ user.role);
        }


        function getAllUsers() {
            return $http.get("/rest/user/all");
        }

//        function getCurrentUser() {
//            return $http.get("/universalSearch/api/loggeduser");
//        }

//        function findUserByCredentials(username, password, usernameAvail) {
//            if(username && password){
//                return $http.get("/universalSearch/api/user?username="+username+"&password="+password);
//            }
//            else if(usernameAvail){
//                return $http.get("/rest/user/available?username="+username+"&useravail="+true);
//            }
//            else {
//                return $http.get("/universalSearch/api/user?username="+username);
//            }
//        }
        //new api
//        function findUserByTime(year,semid) {
//            return $http.get("/universalSearch/api/user?semId=" +semid+"year="+year);
//        }
        
//        function findUserByUsername(username) {
//            return $http.get("/universalSearch/api/user?username="+username);
//        }
        //

        function updateUser(id,user) {
            return $http.get("/rest/user/update?email=" + user.email + "&username=" + user.username + "&password=" + user.password
                    + "&firstname="+ user.firstname + "&lastname="+ user.lastname + "&type="+ user.role +  "&id="+ id + "&type="+ user.role);
               
        }

//        function updateUserByID(user) {
//            return $http.put("/universalSearch/api/user/"+user._id, user);
//        }
        
        function deleteUser(id, type) {
              return $http.get('/rest/user/delete?id='+ id + '&type=' + type);
        }
        //new api
      
        function createCourse(course) {
            return $http.get("/rest/course/insert?name=" + course.coursename + "&semester_id=" + course.semesterId);
        }
        function getAllCourses() {
            return $http.get("/rest/course/all");
        }

        function updateCourse(id, course) {
            return $http.get("/rest/course/edit?name=" + course.coursename + "&semester_id=" + course.semesterId  + "&id=" + id);
               
        }
        function deleteCourse(id) {
            return $http.get('/rest/course/delete?id=' + id);
        }
        
        function findCourseBySem(semid) {
            return $http.get("/rest/course/semester?id="+semid);
        }
        
        function createSemester(semester) {
            return $http.get("/rest/semester/insert?name=" + semester);
        }
        
        function getAllSemesters() {
            return $http.get("/rest/semester/all");
        }
        function updateSemester(id, semester) {
            return $http.get("/rest/semester/update?name=" + semester + "&id=" + id);
               
        }
        function deleteSemester(id) {
            return $http.get('/rest/semester/delete?id=' + id);
        }
    }
})();