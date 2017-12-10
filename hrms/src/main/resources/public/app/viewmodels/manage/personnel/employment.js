define(['plugins/dialog', 'durandal/app', 'knockout', 'modules/employmentservice', 'viewmodels/manage/personnel/employmentform'],
		function (dialog, app, ko, employmentService, EmploymentForm) {
    var Employment = function(personnel) {
    	this.personnel = personnel;
    	this.title = ko.observable();
    	
    	this.employmentList = ko.observable();
    	
    	this.searchKey = ko.observable();
    	
    	this.itemsPerPage = ko.observable(app.user.itemsPerPage);
		this.totalItems = ko.observable();
		this.currentPage = ko.observable(1);
		this.currentPageSubscription = null;
    };
    
    Employment.prototype.activate = function() {
    	var self = this;
    	
    	self.title('Edit ' + self.personnel.namePrefix + ' ' + self.personnel.lastName + '\'s Previous Employments');
    	
    	self.currentPage(1);
		self.currentPageSubscription = self.currentPage.subscribe(function() {
			self.refreshEmploymentList();
		});
		
		self.searchKey.subscribe(function(searchKey) {
			if(searchKey.length >= 3) {
				self.search();
			}
		});
		
		self.refreshEmploymentList();
    };
    
    Employment.show = function(personnel) {
    	return dialog.show(new Employment(personnel));
    };
    
    Employment.prototype.refreshEmploymentList = function() {
    	var self = this;
    	
    	employmentService.getEmploymentList(self.currentPage(), self.personnel.id, self.searchKey()).done(function(data) {
    		self.employmentList(data.list);
    		self.totalItems(data.total);
    	});
    };
    
    Employment.prototype.add = function() {
    	var self = this;
    	
    	EmploymentForm.show(self.personnel, new Object(),  'Add Employment').done(function() {
    		self.refreshEmploymentList();
    	});
    };
    
    Employment.prototype.edit = function(employmentId) {
    	var self = this;
    	
    	employmentService.getEmployment(employmentId).done(function(employment) {
    		EmploymentForm.show(self.personnel, employment,  'Edit Employment').done(function() {
        		self.refreshEmploymentList();
        	});
    	});
    };
    
    Employment.prototype.remove = function(employmentId, companyName) {
    	var self = this;
    	
    	app.showMessage('<p>Are you sure you want to remove Employment <span class="text-primary">' + companyName + '</span>?</p>',
				'<p class="text-danger">Confirm Remove</p>',
				[{ text: 'Yes', value: true }, { text: 'No', value: false }])
		.then(function(confirm) {
			if(confirm) {
				employmentService.removeEmployment(employmentId).done(function(result) {
					self.refreshEmploymentList();
					app.showMessage(result.message);
				});
			}
		})
    };
    
    Employment.prototype.search = function() {
    	var self = this;
    	
    	self.currentPage(1);
    	self.refreshEmploymentList();
    };
    
    Employment.prototype.cancel = function() {
        dialog.close(this);
    };
    
    return Employment;
});