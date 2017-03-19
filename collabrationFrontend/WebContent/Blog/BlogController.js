app.controller('BlogController', ['$scope', 'BlogService','$location','$rootScope','$cookieStore',
            function($scope, BlogService,$location,$routeParams,$rootScope,$cookieStore) {
	console.log("inside BlogController...")
          var self = this;
          self.blogModel={blogname:'',blogdescription:'',username:'',blogdateTime:'',blogstatus:'',blogreason:''};
        self.commentModel={comments:'',username:'',commentid:''}
          self.blogs=[];
        self.comment=[];
          self.userblog=[];
          self.editblog=[];
           
          self.getBlog = getblog
//-------------------------------------------------------------------------GET BLOG ---------------------------------------------------------------------------------------
          function getblog(blogname){
        	  BlogService.getblog(blogname)
                  .then(  
                		       function(d) {
                            	   console.log('inside getselected')
                                  console.log(d.blogname)
                                  
                            	   self.getcomments(d.blogname)
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
													$location.path('/listblog');
												

									}}

                  );
          };
//--------------------------------------------------------------------------UPDATE BLOG--------------------------------------------------------------------------------------          
         self.editBlog = function(blogname){
        	 console.log('inside editblog')
              BlogService.editBlog(blogname)
                      .then( function(d) {
                    	  console.log('inside edit'+d)
                    	  console.log(d)
                    	  self.editblog=d;
                    	  $location.path("/addblog")
							
						 },
                              self.fetchAllBlogs, 
                              function(errResponse){
                                   console.error('Error while updating Blog.');
                              } 
                  );
          };
          
          
//--------------------------------------------------------------------------DELETE BLOG--------------------------------------------------------------------------------------          
          self.deleteBlog = function(blogname){
              BlogService.deleteBlog(blogname)
                      .then( function(d) {
                    	  $location.path("/userblogs")
							
							 },
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
			
			
			
//--------------------------------------------------------------------------USER BLOG--------------------------------------------------------------------------------------          			
			  self.userblogs = function() {
					BlogService
							.userblogs()
							.then(
									function(d) {
										self.userblog = d;
										console.log('getting userblog my boy')
										console.log(d)
										
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

			
			
			
			self.addcomment = function(commentModel) {
				console.log('this is  '+commentModel.comments)
				BlogService
						.addcomment(commentModel)
						.then(
								function(d) {
							//self.getcomments(commmentModel.blogname)
								//	$route.reload();
								},
								function(errResponse) {
									console
											.error('Error while adding comment.');
								});
			};

			
			
			self.getcomment = function(blogname) {
				console.log('inside getcomments my rajesh')
				BlogService
						.getcomments(blogname)
						.then(
								
								function(d) {
									console.log('hiii amd inside function of getcomment')
									self.comment = d;
									console.log(d)
									$location.path("/comments")
									
								},
								function(errResponse) {
									console
											.error('Error while adding comment.');
								});
			};

			
			
			

     self.fetchAllBlogs();
     self.userblogs();
     
          self.addblog = function() {
           
                  self.createBlog(self.blogModel);
              	self.reset();
				
            
          };
          

          self.getcomments = function() {
           
                  self.getcomment('sampe');
              	self.reset();
				
            
          }; 
          self.addcomments = function() {
              
              self.addcomment(self.commentModel);
          	//self.reset();
			
        
      };
    
               
          
           
          self.reset = function(){
        	  self.blogModel={blogname:'',blogdescription:'',username:'',blogdateTime:'',blogstatus:'',blogreason:''};
                // $scope.myForm.$setPristine(); 
          };
 
      }]);