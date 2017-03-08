var app = angular.module('myApp', ['ngRoute']);

app.config(function($routeProvider) {
  $routeProvider

  .when('/login', {
    templateUrl : 'login.html',
    controller  : 'UserController'
  })
  .when('/logout', {
			    templateUrl : '',
			    controller  : 'UserController'
			  })
  .when('/register', {
	    templateUrl : 'register.html',
	    controller  : 'UserController'
	  })
	  .when('/addblog', {
		    templateUrl : 'addblog.html',
		    controller  : 'BlogController'
		  })
		   .when('/viewblog', {
		    templateUrl : 'viewblog.html',
		    controller  : 'BlogController'
		  })
		  .when('/listblog', {
			    templateUrl : 'listblog.html',
			    controller  : 'BlogController'
			  })
			  
		  
});




app.run( function ($rootScope, $location,$cookieStore, $http) {

	 $rootScope.$on('$locationChangeStart', function (event, next, current) {
		 console.log("$locationChangeStart")
		 
		 var userPages = ['/myProfile','/createblog','/add_friend','/search_friend','/view_friend', '/viewFriendRequest','/chat']
		 var adminPages = ["/post_job","/manage_users"]
		 
		 var currentPage = $location.path()
		 
		 var isUserPage = $.inArray(currentPage, userPages) ==1;
		 var isAdminPage = $.inArray(currentPage, adminPages) ==1;
		 
		 var isLoggedIn = $rootScope.currentUser.username;
	        
	     console.log("isLoggedIn:" +isLoggedIn)
	     console.log("isUserPage:" +isUserPage)
	     console.log("isAdminPage:" +isAdminPage)
	        
	        if(!isLoggedIn)
	        	{
	        	
	        	 if (isUserPage || isAdminPage) {
		        	  console.log("Navigating to login page:")
		        	  alert("You need to loggin to do this operation")

						            $location.path('/');
		                }
	        	}
	        
			 else //logged in
	        	{
	        	
				 var role = $rootScope.currentUser.role;
				 
				 if(isAdminPage && role!='admin' )
					 {
					 
					  alert("You can not do this operation as you are logged as : " + role )
					   $location.path('/login');
					 
					 }
				     
	        	
	        	}
	        
	 }
	       );
	 
	 
	 // keep user logged in after page refresh
     $rootScope.currentUser = $cookieStore.get('currentUser') || {};
     if ($rootScope.currentUser) {
         $http.defaults.headers.common['Authorization'] = 'Basic' + $rootScope.currentUser; 
     }

});


 
    
    
