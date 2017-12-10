define(['plugins/dialog', 'durandal/app', 'knockout', 'modules/dependentservice', 'viewmodels/manage/personnel/dependentform'],
		function (dialog, app, ko, dependentService, DependentForm) {
    var Dependent = function(personnel) {
    	this.personnel = personnel;
    	this.title = ko.observable();
    	
    	this.dependentList = ko.observable();
    	
    	this.searchKey = ko.observable();
    	
    	this.itemsPerPage = ko.observable(app.user.itemsPerPage);
		this.totalItems = ko.observable();
		this.currentPage = ko.observable(1);
		this.currentPageSubscription = null;
    };
    
    Dependent.prototype.activate = function() {
    	var self = this;
    	
    	self.title('Edit ' + self.personnel.namePrefix + ' ' + self.personnel.lastName + '\'s Dependents');
    	
    	self.currentPage(1);
		self.currentPageSubscription = self.currentPage.subscribe(function() {
			self.refreshDependentList();
		});
		
		self.searchKey.subscribe(function(searchKey) {
			if(searchKey.length >= 3) {
				self.search();
			}
		});
		
		self.refreshDependentList();
    };
    
    Dependent.show = function(personnel) {
    	return dialog.show(new Dependent(personnel));
    };
    
    Dependent.prototype.refreshDependentList = function() {
    	var self = this;
    	
    	dependentService.getDependentList(self.currentPage(), self.personnel.id, self.searchKey()).done(function(data) {
    		self.dependentList(data.list);
    		self.totalItems(data.total);
    	});
    };
    
    Dependent.prototype.add = function() {
    	var self = this;
    	
    	DependentForm.show(self.personnel, new Object(),  'Add Dependent').done(function() {
    		self.refreshDependentList();
    	});
    };
    
    Dependent.prototype.edit = function(dependentId) {
    	var self = this;
    	
    	dependentService.getDependent(dependentId).done(function(dependent) {
    		DependentForm.show(self.personnel, dependent,  'Edit Dependent').done(function() {
        		self.refreshDependentList();
        	});
    	});
    };
    
    Dependent.prototype.remove = function(dependentId, formattedName) {
    	var self = this;
    	
    	app.showMessage('<p>Are you sure you want to remove Dependent <span class="text-primary">' + formattedName + '</span>?</p>',
				'<p class="text-danger">Confirm Remove</p>',
				[{ text: 'Yes', value: true }, { text: 'No', value: false }])
		.then(function(confirm) {
			if(confirm) {
				dependentService.removeDependent(dependentId).done(function(result) {
					self.refreshDependentList();
					app.showMessage(result.message);
				});
			}
		})
    };
    
    Dependent.prototype.search = function() {
    	var self = this;
    	
    	self.currentPage(1);
    	self.refreshDependentList();
    };
    
    Dependent.prototype.cancel = function() {
        dialog.close(this);
    };
    
    return Dependent;
});