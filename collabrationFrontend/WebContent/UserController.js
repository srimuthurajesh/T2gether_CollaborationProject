
app.controller(	'UserController', [	'$scope', 'UserService', '$location','$rootScope','$http',
						function($scope, UserService, $location, $rootScope,
								$http) {
							console.log("inside UserController...")
							var self = this;
							this.user = {	username : '',
											firstname : '', 
											secondname : '',	
											email : '',
											address : '', 
											mobile : '',
											is_online : '',	
											role : '',
											errorCode : '',	
											errorMessage : '' , 
											imageUrl:''
							};
							
							this.users = []; //json array
							
							 $scope.orderByMe = function(x) {
							        $scope.myOrderBy = x;
							    }
					

							 this.fetchAllUsers = function() {
								console.log("fetchAllUsers...")
								UserService
										.fetchAllUsers()
										.then(
												function(d) {
													self.users = d;
												},
												function(errResponse) {
													console
															.error('Error while fetching Users');
												});
							};
							
							self.fatchAllUsers();

							self.createUser = function(user) {
								UserService
										.createUser(user)
										.then(
												function(d)
												{
													alert("Thank you for registration")
												},
												function(errResponse) {
													console
													alert("not registered")
													
												});
							};							
						
							
							self.login = function(user) {
								console.log("authenticate...")
								UserService
										.authenticate(user)
										.then(

												function(d) {
													
													self.user = d;
													if (self.user.errorCode == "404")

													{
														alert(self.user.errorMessage)

														self.user.username = "";
														self.user.password = "";

													} else { //valid credentials
																
//																self.fetchAllUsers(); 
																alert(self.user.errorMessage)
														
//														$rootScope.currentUser = self.user
//                                                     	$cookieStore.put('currentUser', self.user);
//														
//														$http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.currentUser; 
////														$location.path('/chat_forum');

													}

												},
												function(errResponse) {

													console
															.error('Error while authenticate Users');
												});
							};

							self.logout = function() {
								console.log("logout")
								$rootScope.currentUser = {};
								$cookieStore.remove('currentUser');
								UserService.logout()
								$location.path('/');

							}

						

													self.register = function() {
								{
									self.createUser(self.user);
								}
								self.reset();
							};


							self.reset = function() {
								self.user = {
									id : '',
									name : '',
									password : '',
									mobile : '',
									address : '',
									email : '',
									isOnline : '',
									errorCode : '',
									errorMessage : ''
								};
								$scope.myForm.$setPristine(); // reset Form
							};
						} ]);













