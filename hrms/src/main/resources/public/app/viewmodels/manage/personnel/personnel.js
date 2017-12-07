define(['durandal/app', 'knockout', 'modules/personnelservice'/*, 'viewmodels/personnel/personnelform', 'viewmodels/personnel/personnelview'*/],
		function (app, ko, personnelService/*, PersonnelForm, PersonnelView*/) {
    var Personnel = function() {
    	this.personnelList = ko.observable();
    	
    	this.searchKey = ko.observable();
    	
    	this.itemsPerPage = ko.observable(app.user.itemsPerPage);
		this.totalItems = ko.observable();
		this.currentPage = ko.observable(1);
		this.currentPageSubscription = null;
    };
    
    Personnel.prototype.activate = function() {
    	var self = this;
    	
    	self.currentPage(1);
		self.currentPageSubscription = self.currentPage.subscribe(function() {
			self.refreshPersonnelList();
		});
		
		self.searchKey.subscribe(function(searchKey) {
			if(searchKey.length >= 3) {
				self.search();
			}
		});
		
		self.refreshPersonnelList();
    };
    
    Personnel.prototype.refreshPersonnelList = function() {
    	var self = this;
    	
    	personnelService.getPersonnelList(self.currentPage(), self.searchKey()).done(function(data) {
    		/*for(i = 0; i < data.list.length; i++) {
    			data.list[i].imagePath = personnelService.getPersonnelImageByFileName(data.list[i].image);
    		}*/
    		self.personnelList(data.list);
    		self.totalItems(data.total);
    	});
    };
    
    Personnel.prototype.add = function() {
    	var self = this;
    	alert('add');
    	/*PersonnelForm.show(new Object(),  'Create Personnel').done(function() {
    		self.refreshPersonnelList();
    	});*/
    };
    
    Personnel.prototype.view = function(personnelId) {
    	var self = this;
    	alert('view');
    	/*personnelService.getPersonnel(personnelId).done(function(personnel) {
    		PersonnelView.show(personnel);
    	});*/
    };
    
    Personnel.prototype.edit = function(personnelId) {
    	var self = this;
    	alert('edit');
    	/*personnelService.getPersonnel(personnelId).done(function(personnel) {
    		PersonnelForm.show(personnel,  'Edit Personnel').done(function() {
        		self.refreshPersonnelList();
        	});
    	});*/
    };
    
    Personnel.prototype.remove = function(personnelId, lastName, firstName) {
    	var self = this;
    	
    	app.showMessage('<p>Are you sure you want to remove Personnel <span class="text-primary">' + firstName + ' ' + lastName + '</span>?</p>',
				'<p class="text-danger">Confirm Remove</p>',
				[{ text: 'Yes', value: true }, { text: 'No', value: false }])
		.then(function(confirm) {
			if(confirm) {
				personnelService.removePersonnel(personnelId).done(function(result) {
					self.refreshPersonnelList();
					app.showMessage(result.message);
				});
			}
		})
    };
    
    Personnel.prototype.search = function() {
    	var self = this;
    	
    	self.currentPage(1);
    	self.refreshPersonnelList();
    };
    
    return Personnel;
});