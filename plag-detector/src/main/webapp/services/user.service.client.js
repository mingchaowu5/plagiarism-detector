(function(){
    angular
        .module("PlagApp")
        .factory('UserService', userService);

    function userService($http) {


        var api = {
            "updateUser": updateUser,
            "findUserByCredentials": findUserByCredentials,
            "getCurrentUser": getCurrentUser,
            "createUser": createUser,
            "findUserByUsername":findUserByUsername,
            "deleteUser":deleteUser,
            "getAllUsers": getAllUsers,
            "login":login,
            "logout": logout,
            "register": register,
            "deleteUserById":deleteUserById,
            "updateUserByID":updateUserByID
        };
        return api;

        function deleteUserById(id) {
            return $http.delete("/universalSearch/api/user/"+id);
        }

        function register(user) {
            return $http.get("/rest/user/register?email=" + user.email + "&username=" + user.username + "&password=" + user.password);
        }

        function logout(user) {
            return $http.post("/api/logout");
        }


        function login(user) {
            return $http.get("/rest/user/login?username=" +user.username + "&password=" + user.password);
        }


        function getAllUsers() {
            return $http.get("/universalSearch/api/user/all");
        }

        function getCurrentUser() {
            return $http.get("/universalSearch/api/loggeduser");
        }

        function findUserByCredentials(username, password, usernameAvail) {
            if(username && password){
                return $http.get("/universalSearch/api/user?username="+username+"&password="+password);
            }
            else if(usernameAvail){
                return $http.get("/rest/user/available?username="+username+"&useravail="+true);
            }
            else {
                return $http.get("/universalSearch/api/user?username="+username);
            }

        }

        function createUser(user) {
            return $http.post("/universalSearch/api/user", user);
        }

        function findUserByUsername(username) {
            return $http.get("/universalSearch/api/user?username="+username);
        }


        function updateUser(user) {
            return $http.put("/universalSearch/api/user", user);
        }

        function updateUserByID(user) {
            return $http.put("/universalSearch/api/user/"+user._id, user);
        }

        function deleteUser() {
            return $http.delete('/universalSearch/api/user');
        }
    }
})();