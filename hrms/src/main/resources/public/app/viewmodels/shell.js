define(['plugins/router', 'durandal/app', 'knockout', 'modules/securityservice', 'modules/userservice', 'modules/constantsservice', 'viewmodels/profile', 'viewmodels/passwordform', 'viewmodels/forgotpassword', 'viewmodels/settings'], 
		function (router, app, ko, securityService, userService, constantsService, Profile, PasswordForm, ForgotPasswordForm, Settings) {
	var homeroute = [
	    { route: ['', 'home'], moduleId: 'viewmodels/home', title: 'Home', nav: false }
	];
	
	var userroute = [
 	    { route: 'user', moduleId: 'viewmodels/user/user', title: 'Users', nav: true, hash: '#user' }
 	];
	
	var Shell = function() {
		this.router = router;
		
		this.routes = homeroute;
		this.systemVersion = ko.observable();
		
		this.userDetails = {
			id: ko.observable(),
			fullName: ko.observable(),
			userType: ko.observable(),
			itemsPerPage: ko.observable(),
			imagePath: ko.observable()
		};
		
		this.loginForm = {
			username: ko.observable(),
			password: ko.observable()
		};
	};
	
	Shell.prototype.activate = function() {
		var self = this;
		
		constantsService.getVersion().done(function(version) {
			self.systemVersion(version);
		});
		
		if(app.user) {
			self.userDetails.id(app.user.id);
    		self.userDetails.fullName(app.user.fullName);
    		self.userDetails.userType(app.user.userType);
    		self.userDetails.itemsPerPage(app.user.itemsPerPage);
    		self.userDetails.imagePath(userService.getUserImageByFileName(app.user.image));
    		
    		switch(app.user.userType.name) {
	    		case 'ADMINISTRATOR':
	    			self.routes = self.routes.concat(userroute);
    		}
		}
    		
    	$.each(self.routes, function(index, route) {
            if (route.childRoutes === undefined)
                return
            $.each(route.childRoutes, function(index, childRoute) {
                childRoute.route = route.route + '/' + childRoute.route;
                childRoute.moduleId = route.moduleRootId + '/' + childRoute.moduleId;
                childRoute.title = childRoute.title;
                childRoute.hash = route.hash + '/' + childRoute.hash;
                childRoute.parent = route.moduleRootId;
            });
            self.routes = self.routes.concat(route.childRoutes);
        });
    	
        self.router.map(self.routes)
        	.buildNavigationModel()
        	.mapUnknownRoutes('viewmodels/home');
        
        return router.activate();
	};
	
	Shell.prototype.refreshUser = function() {
		var self = this;
		
		securityService.getUser().done(function(user) {
    		app.user = user;
    		self.userDetails.id(user.id);
    		self.userDetails.fullName(user.fullName);
    		self.userDetails.userType(user.userType);
    		self.userDetails.itemsPerPage(user.itemsPerPage);
    		self.userDetails.imagePath(userService.getUserImageByFileName(user.image));
        });
	};
	
	Shell.prototype.profile = function() {
		var self = this;
		
		Profile.show(app.user).done(function() {
			self.refreshUser();
		});
	};
	
	Shell.prototype.changePassword = function() {
		var self = this;
		
		PasswordForm.show();
	};
	
	Shell.prototype.changeSettings = function() {
		var self = this;
		
		Settings.show().done(function() {
			self.refreshUser();
		});
	};
	
	Shell.prototype.logout = function() {
		securityService.logout().done(function() {
    		location.href = '/';
    	});
	};
	
	return Shell;
});