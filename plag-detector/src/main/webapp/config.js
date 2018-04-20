
(function() {
    angular
        .module('PlagApp')
        .config(Config);

    // var loggOut = function($q, $timeout, $http, $location, $rootScope, $cookies) {
    //     var deferred = $q.defer();
    //     var obj = $cookies.remove('loggedUser');
    //     $location.url('/');
    //     return deferred.promise;
    // };

    var checkLoggedin = function($q, $timeout, $http, $location, $rootScope, $cookies) {
        var deferred = $q.defer();

        var cookieWObject = $cookies.getObject('loggedUser');

        if(cookieWObject){
            $rootScope.currentUser = cookieWObject;
            deferred.resolve();
        }else{
            deferred.reject();
             $location.url('/');
        }

        // $http.get('/api/loggedin')
        //     .then(function(user) {

        //     $rootScope.errorMessage = null;


        //     if (user.data !== '0') {

        //         $rootScope.currentUser = user.data;
        //         deferred.resolve();
        //     } else {

        //         deferred.reject();
        //         $location.url('/');
        //     }
        // });

        return deferred.promise;
    };

    function Config($routeProvider, $httpProvider) {

        $httpProvider.defaults.headers.put['Content-Type'] = 'application/json;charset=UTF-8';
        $httpProvider.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8';

        $routeProvider
            .when("/",{
                templateUrl:"views/templates/login.view.client.html",
                controller: 'loginController',
                controllerAs: 'model'
            })
            .when("/main/:sid/:aid/:vid", {
                templateUrl:'views/templates/dataUpload.view.client.html',
                controller: 'uploadController',
                controllerAs: 'model',
                resolve: { loggedin: checkLoggedin }
            })
            .when("/results/:aid",{
                templateUrl:"views/templates/result.view.client.html",
                controller: 'resultController',
                controllerAs: 'model',
                resolve: { loggedin: checkLoggedin }
            })
            .when("/register",{
                templateUrl:"views/templates/register.view.client.html",
                controller: 'registerController',
                controllerAs: 'model',
                resolve: { loggedin: checkLoggedin }
            })
            .when("/dashboard/:pid",{
                templateUrl:"views/templates/professorDashboard.view.client.html",
                controller: 'professorDashboardController',
                controllerAs: 'model',
                resolve: { loggedin: checkLoggedin }
            })
            .when("/dashboard/:pid/semester/:sid",{
                templateUrl:"views/templates/professorDashboard.view.client.html",
                controller: 'professorDashboardController',
                controllerAs: 'model',
                resolve: { loggedin: checkLoggedin }
            })
            .when("/dashboard/:pid/semester/:sid/course/:cid",{
                templateUrl:"views/templates/professorDashboard.view.client.html",
                controller: 'professorDashboardController',
                controllerAs: 'model',
                resolve: { loggedin: checkLoggedin }
            })
            .when("/dashboard/:pid/semester/:sid/course/:cid/snapshot/:aid",{
                templateUrl:"views/templates/professorDashboard.view.client.html",
                controller: 'professorDashboardController',
                controllerAs: 'model',
                resolve: { loggedin: checkLoggedin }
            })
            .when("/student/:sid",{
                templateUrl:"views/templates/studentDashboard.view.client.html",
                controller: 'studentDashboardController',
                controllerAs: 'model',
                resolve: { loggedin: checkLoggedin }
            })
            .when("/student/:sid/course/:cid",{
                templateUrl:"views/templates/studentDashboard.view.client.html",
                controller: 'studentDashboardController',
                controllerAs: 'model',
                resolve: { loggedin: checkLoggedin }
            })
            .when("/student/:sid/course/:cid/assignment/:aid",{
                templateUrl:"views/templates/studentDashboard.view.client.html",
                controller: 'studentDashboardController',
                controllerAs: 'model',
                resolve: { loggedin: checkLoggedin }
            })
            .when("/compare/:id",{
                templateUrl:"views/templates/compareFiles.view.client.html",
                controller: 'compareFilesController',
                controllerAs: 'model',
                resolve: { loggedin: checkLoggedin }
            })
            //admin
             .when("/admin",{
                templateUrl:"views/templates/adminDashboard.view.client.html",
                controller: 'adminDashboardController',
                controllerAs: 'model'
            })
           
            //addcourse
             .when("/admin/courseUpdate/:cid",{
                templateUrl:"views/templates/courseUpdate.view.client.html",
                controller: 'courseUpdateController',
                controllerAs: 'model',
                resolve: { loggedin: checkLoggedin }
            })
            //profile
             .when("/admin/profileUpdate/:uid",{
                templateUrl:"views/templates/profileUpdate.view.client.html",
                controller: 'profileUpdateController',
                controllerAs: 'model',
                resolve: { loggedin: checkLoggedin }
            })
             .when("/admin/addCourse",{
                templateUrl:"views/templates/addCourse.view.client.html",
                controller: 'adminDashboardController',
                controllerAs: 'model',
                resolve: { loggedin: checkLoggedin }
            })
            .when("/admin/addSemester",{
                templateUrl:"views/templates/addSemester.view.client.html",
                controller: 'adminDashboardController',
                controllerAs: 'model',
                resolve: { loggedin: checkLoggedin }
            })
            .when("/admin/semesterUpdate/:sid",{
                templateUrl:"views/templates/semesterUpdate.view.client.html",
                controller: 'semesterUpdateController',
                controllerAs: 'model',
                resolve: { loggedin: checkLoggedin }
            })
            .when("/logout",{
                templateUrl:"views/templates/login.view.client.html",
                controller: 'logoutController',
                controllerAs: 'model'
            })
            
            .otherwise({
                templateUrl:"views/templates/login.view.client.html",
                controller: 'loginController',
                controllerAs: 'model'
            })
    }
})();