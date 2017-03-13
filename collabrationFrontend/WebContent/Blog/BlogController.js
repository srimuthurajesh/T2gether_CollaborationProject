app.controller('BlogController', ['$scope', 'BlogService','$location','$rootScope',function($scope, BlogService,$location,$routeParams,$rootScope) {
	console.log("inside BlogController...")
          var self = this;
          self.blogModel={blogname:'',blogdescription:'',username:'',blogdateTime:'',blogstatus:'',blogreason:''};
          self.blogs=[];
          self.blogs1=[];
          
          
         self.getBlog = getblog

//-------------------------------------------------------------------------GET BLOG ---------------------------------------------------------------------------------------
          function getblog(blogname){
        	  BlogService.getblog(blogname)
                  .then(  
                		       function(d) {
                            	   console.log('inside getselected')
                                   
                            	   self.blogs1 = d;
                            	   console.log(d)
                            	   
                                     $location.path('/viewblog'); 
                               },
                                function(errResponse){
                                    console.error('Error while get Blogs controller');
                                }
                       );
          };
          
//---------------------------------------------------------------------------ALL BLOG LIST-------------------------------------------------------------------------------------        
          self.fetchAllBlogs = function(){
              BlogService.fetchAllBlogs()
                  .then(
                               function(d) {
                                    self.blogs = d;
                                 console.log('inside fetch all blog')
                           
                               },
                                function(errResponse){
                                    console.error('Error while fetching Blogs');
                                }
                       );
          };
  //--------------------------------------------------------------------------ADD BLOG--------------------------------------------------------------------------------------          
          self.createBlog = function(blogModel){
              BlogService.createBlog(blogModel)
                      .then(
                    		  
                    		  function(d) {
									
									self.blogModel = d;
									if (self.blogModel.errorCode == "404")

									{
										alert(self.blogModel.errorMessage)

										self.blogModel.username = "";
										self.blogModel.password = "";

									} else { 
												
												self.fetchAllBlogs(); 
										self.reset();
													$location.path('/addblog');
												

									}}

                  );
          };
//--------------------------------------------------------------------------UPDATE BLOG--------------------------------------------------------------------------------------          
         self.updateBlog = function(blog, id){
              BlogService.updateBlog(blog, id)
                      .then(
                              self.fetchAllBlogs, 
                              function(errResponse){
                                   console.error('Error while updating Blog.');
                              } 
                  );
          };

//--------------------------------------------------------------------------ACCEPT BLOG--------------------------------------------------------------------------------------          
          self.accept = function(id) {
				console.log("accept...")
				JobService
						.accept(id)
						.then(
								function(d) {
									self.job = d;
									self.fetchAllBlogs
									$location.path("/manage_jobs")
									alert(self.job.errorMessage)
									
								},
								
								function(errResponse) {
									console
											.error('Error while accepting the blog.');
								});
			};  
//--------------------------------------------------------------------------REJECT BLOG--------------------------------------------------------------------------------------          
			self.reject = function( id) {
				console.log("reject...")
				var reason = prompt("Please enter the reason");
				JobService
						.reject(id,reason)
						.then(
								function(d) {
									self.job = d;
									self.fetchAllBlogs
									$location.path("/manage_jobs")
									alert(self.job.errorMessage)
									
								},
								function(errResponse) {
									console
											.error('Error while updating User.');
								});
			};

			
			
			
			
			
			
			
			

     self.fetchAllBlogs();
 
          self.addblog = function() {
           
                  self.createBlog(self.blogModel);
              	self.reset();
				
            
          };
    
               
          self.edit = function(id){
              console.log('id to be edited', id);
              for(var i = 0; i < self.blogs.length; i++){
                  if(self.blogs[i].id === id) {
                     self.blogModel = angular.copy(self.blogs[i]);
                     break;
                  }
              }
          };
               
          self.remove = function(id){
              console.log('id to be deleted', id);
              if(self.blogModel.id === id) {//clean form if the blog to be deleted is shown there.
                 self.reset();
              }
              self.deleteBlog(id);
          };
 
           
          self.reset = function(){
        	  self.blogModel={blogname:'',blogdescription:'',username:'',blogdateTime:'',blogstatus:'',blogreason:''};
                // $scope.myForm.$setPristine(); 
          };
 
      }]);