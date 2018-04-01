// script.js

    // create the module and name it app
        // also include ngRoute for all our routing needs
    var app = angular.module('app', ['ngRoute']);

    /*// configure our routes
    app.config(function($routeProvider) {
        $routeProvider

            // route for the home page
            .when('/', {
                templateUrl : 'pages/home.html',
                controller  : 'mainController'
            })

            // route for the about page
            .when('/about', {
                templateUrl : 'pages/about.html',
                controller  : 'aboutController'
            })

            // route for the contact page
            .when('/contact', {
                templateUrl : 'pages/contact.html',
                controller  : 'contactController'
            });
    });
*/
    // create the controller and inject Angular's $scope
    app.controller('mainController', function($scope, todoService) {
        // create a message to display in our view
        $scope.message = 'Everyone come and see how good I look!';
        
        todoService.getToDo().then(function(todoData){
        	$scope.todoList = todoData;
        });
        
        
        /*
        $scope.todoList = [
        					{"task":"TO BE DONE 1", "isCompleted": true},
        					{"task":"TO BE DONE 2", "isCompleted": false},
        					{"task":"TO BE DONE 3", "isCompleted": true}
        	
        				]
        */
    
        
        $scope.addTODO = function(){
        	if($scope.todoList == undefined){
        		$scope.todoList = []
        	}
        	//var todo = {"id": 0, "task":$scope.todoTask, "isCompleted":$scope.todoComplete};
        	var todo = {"task":$scope.todoTask, "completed":$scope.todoComplete};
        	//$scope.todoList.push(todo);
        	
        	todoService.addToDo(todo).then(function(todoData){
        		$scope.todoList = todoData.data;
        	});
        	$scope.todoTask ="";
        	$scope.todoComplete = false;
        }
        
        
        $scope.deleteTodo = function(id){
        	
        	todoService.deleteTodo(id).then(function(todoData){
        		$scope.todoList = todoData.data;
        	});
        }
        
        
        
        
    });
    
    
    
    
    app.service('todoService', function($q, $http) {
    	
    	
    	var cache = {};
    	
    	this.addToDo = function(todo){
    		//return executeCachedPost('addTodo', todo, 'addTodo');
    		//return executeCached('addTodo', {}, 'getTodo');
    		return $http({
                method: 'POST',
                url: 'http://localhost:8080/todo/addTodo',
                headers: {"Content-Type" : "application/json"},
                data: todo
            }).success(function(data, status) {
            			//alert("Success ... " + status);
                })
                .error(function(data, status) {
                        //alert("Error ... " + data);
                });
    	}
    	
    	this.getToDo = function(){
    		return executeCached('getTodo', {}, 'getTodo');
            
    	}
    	
    	
    	this.deleteTodo = function(id){
    		
    		return $http({
                method: 'DELETE',
                url: 'http://localhost:8080/todo/deleteTodo1',
                headers: {"Content-Type" : "application/json"},
                params: {"id" : id }
            }).success(function(data, status) {
            			//alert("Success ... " + status);
                })
                .error(function(data, status) {
                       // alert("Error ... " + data);
                });
    	}
    	
    	
    	function executeCached(url, parameter, cacheName) {
            var deferred = $q.defer();
          /*  if(cache[cacheName]) {
                deferred.resolve(cache[cacheName]);
            } else {*/
            
           // http://localhost:8080/todo/test/getTodo
            
               $http.get("http://localhost:8080/todo/"+url,{ params: parameter })
                    .success(function(data) {
                        cache[cacheName] = data;
                        console.log("Success");
                        deferred.resolve(cache[cacheName]);
                    })
                    .error(function(err) {
                        console.log("fail"+err)
                        deferred.reject(err);
                    });
           // }
            return deferred.promise;
        }

    	
    	
    	
    	function executeCachedPost(url, parameter, cacheName) {
            var deferred = $q.defer();
            $http.post("http://localhost:8080/todo/"+url, parameter)
                .success(function(data) {
                    cache[cacheName] = data;
                    console.log("Success");
                    deferred.resolve(cache[cacheName]);
                })
                .error(function(err) {
                    console.log("fail"+err)
                    deferred.reject(err);
                });

            return deferred.promise;
        }
    	    	
    });
    
    
    
    
    
/*    app.controller('aboutController', function($scope) {
        $scope.message = 'Look! I am an about page.';
    });

    app.controller('contactController', function($scope) {
        $scope.message = 'Contact us! JK. This is just a demo.';
    });*/