app.controller(	'UserController', [	'$scope', 'UserService','$location','$rootScope','$cookieStore','$http',
						function($scope, UserService, $location, $rootScope,$cookieStore,
								$http) {
							console.log("UserController...")
							var self = this;
							this.user = {
									username : '',
									firstname : '', 
									secondname : '', 
									password : '',	
									mobile : '',
									address : '', 
									email : '',
									is_online : '',	
									role : '',
									errorCode : '',	
									errorMessage : '' , 
									imageUrl:''
							};
							
							this.users = []; //json array
							this.manageuser=[];
							self.createUser = function(user) {
								UserService
										.createUser(user)
										.then(
												function(d)
												
												{   													self.user = d;

													console.log("hiiii"+self.user.errorCode)
													if(self.user.errorCode!="404"){
												alertify.alert("Successfully registered")
													
														self.authenticate(user)
													}else{
														alertify.alert(self.user.username +" already exist, Try new username")	
														
													}				self.reset();
																									
												});
							};							
						

							self.authenticate = function(user) {
								console.log("authenticate...")
								UserService
										.authenticate(user)
										.then(

												function(d) {

													self.user = d;
													console.log(self.user.errorMessage+"invalie "+self.user.errorCode)

													if (self.user.errorCode != "200")

													{
														console.log(self.user.errorMessage+"inv "+self.user.errorCode)
														alertify.error('invalid credentials, Try again')

														self.user.username = "";
														self.user.password = "";

													} else { 
														$rootScope.currentUser = self.user
														$cookieStore.currentUser=self.user
														console.log(self.user);
	                                                 	alertify.success('You are loggedin successfully')
														 $location.path('/');
													}		self.reset();
													

												},
												function(errResponse) {
													
												});
							};

							self.logouts = function() {
								console.log("inside logout")
UserService.logout().then(
		function(d){
//								$rootScope.currentUser = {};
								 $rootScope.currentUser = undefined
								$cookieStore.remove('currentUser');
								//UserService.logout()
								//$location.path('/login');
								 $location.path('/');	alertify.success('You are Logged Out successfully')
									
									
							});
							};
							
							
		
							
							
							
		
							
							self.manageuser=function(){
								UserService.manageuser().then(
										function(d){
											self.manageuser=d;
											console.log(d)
											console.log(self.manageuser)
											
											$location.path('/manageuser');
												
										})
							}

							
							self.login = function() {
								{	self.authenticate(self.user);
								}

							};

							self.register = function() {
								{
									self.createUser(self.user);
								}
							};

							self.logout = function() {
								{
									self.logouts();
								}
							};

							self.reset = function() {
								self.user = {
										username : '',
										firstname : '', 
										secondname : '', 
										password : '',	
										mobile : '',
										address : '', 
										email : '',
										is_online : '',	
										role : '',
										errorCode : '',	
										errorMessage : '' , 
										imageUrl:''
								};
							};


						

						
						} ]);

