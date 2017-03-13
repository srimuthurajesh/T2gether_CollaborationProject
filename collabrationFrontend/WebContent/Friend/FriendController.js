'use strict';
app.controller('FriendController', ['UserService','$scope', 'FriendService','$location',
   '$rootScope',function(UserService,$scope, FriendService,$location,$routeParams,$rootScope) {
	console.log("inside FriendController")
          var self = this;
          self.Friend={friendid:'',username1:'',username2:'',friendstatus:''};
          self.friends=[];
          
          self.UserModel = {
  				username : '',
  				password : '',
  				mobile : '',
  				address : '',
  				email : '',
  				is_online:'',
  				role : '',
  				errorMessage : '',
  				status : '',
  				firstname : '',
  				secondname : '',
          };
  			self.users = [];
          
         self.addFriend=addfriend
         
         function addfriend(username)
         {

       	  console.log("inside addfriend controller")
        	 FriendService.addfriend(username)
                 .then(
                              function(d) {
                                   self.Friend = d;
                              },
                               function(errResponse){
                                   console.error('Error while sending friend request');
                               }
                      );
         
        	 
         }
          
             
          self.getMyFriends = function(){
        	  console.log("Getting my friends")
              FriendService.getMyFriends()
                  .then(
                               function(d) {
                                    self.friends = d;
                                    console.log("Got the friends list")
                                     	/* $location.path('/view_friend');*/
                               },
                                function(errResponse){
                                    console.error('Error while fetching Friends');
                                }
                       );
          };
            
       
         self.updateFriendRequest = function(Friend, id){
              FriendService.updateFriendRequest(Friend, id)
                      .then(
                              self.fetchAllFriends, 
                              function(errResponse){
                                   console.error('Error while updating Friend.');
                              } 
                  );
          };
 
         self.deleteFriend = function(id){
              FriendService.deleteFriend(id)
                      .then(
                              self.fetchAllFriends, 
                              function(errResponse){
                                   console.error('Error while deleting Friend.');
                              } 
                  );
          };
          
          self.fetchAllUsers = function() {
				UserService.fetchAllUsers().then(function(d) {
					console.error('inside function, fetching Users');
					self.users = d;
					console.log(d)
				}, function(errResponse) {
					console.error('Error while fetching Users');
				});
			};
            
 
          self.fetchAllUsers();
          self.getMyFriends();
 
     
 
      }]);
