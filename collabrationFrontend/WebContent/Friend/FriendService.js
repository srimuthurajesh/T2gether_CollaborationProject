'use strict';
 console.log("start of friend service")
app.factory('FriendService', ['$http', '$q','$rootScope', function($http, $q,$rootScope){
	
	console.log("FriendService...")
	
	var BASE_URL='http://localhost:8080/collabrationBackend'
    return {
         
		getMyFriends: function() {
                    return $http.get(BASE_URL+'/myFriends')
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                   null
                            );
            },
             
            addfriend: function(username){
                    return $http.get(BASE_URL+'/addfriend/'+username)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while creating friend');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
             
            updateFriendRequest: function(friend, id){
                    return $http.put(BASE_URL+'/friend/'+id, friend)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while updating friend');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
             
          /*  deleteFriend: function(id){
                    return $http delete(BASE_URL+'/friend/'+id)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while deleting friend');
                                        return $q.reject(errResponse);
                                    }
                            );
            }
            
           */
         
    };
 
}]);
 