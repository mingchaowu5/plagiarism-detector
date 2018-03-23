
(function() {
    angular
        .module('PlagApp')
        .config(Config);


    function Config($routeProvider, $httpProvider) {

        $httpProvider.defaults.headers.put['Content-Type'] = 'application/json;charset=UTF-8';
        $httpProvider.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8';

        $routeProvider
            .when("/",{
                templateUrl:"views/templates/login.view.client.html",
                controller: 'loginController',
                controllerAs: 'model'
            })
            .when("/main/:sid/:aid", {
                templateUrl:'views/templates/dataUpload.view.client.html',
                controller: 'uploadController',
                controllerAs: 'model'
            })
            .when("/results/:aid",{
                templateUrl:"views/templates/result.view.client.html",
                controller: 'resultController',
                controllerAs: 'model'
            })
            .when("/register",{
                templateUrl:"views/templates/register.view.client.html",
                controller: 'registerController',
                controllerAs: 'model'
            })
            .when("/dashboard/:pid",{
                templateUrl:"views/templates/professorDashboard.view.client.html",
                controller: 'professorDashboardController',
                controllerAs: 'model'
            })
            .when("/student/:sid",{
                templateUrl:"views/templates/studentDashboard.view.client.html",
                controller: 'studentDashboardController',
                controllerAs: 'model'
            })
            .otherwise({
                templateUrl:"views/templates/result.view.client.html",
                controller: 'resultController',
                controllerAs: 'model'
            })
    }



})();