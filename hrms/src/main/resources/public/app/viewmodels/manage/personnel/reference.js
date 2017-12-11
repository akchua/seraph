define(['plugins/dialog', 'durandal/app', 'knockout', 'modules/referenceservice', 'viewmodels/manage/personnel/referenceform'],
		function (dialog, app, ko, referenceService, ReferenceForm) {
    var Reference = function(personnel) {
    	this.personnel = personnel;
    	this.title = ko.observable();
    	
    	this.referenceList = ko.observable();
    	
    	this.searchKey = ko.observable();
    	
    	this.itemsPerPage = ko.observable(app.user.itemsPerPage);
		this.totalItems = ko.observable();
		this.currentPage = ko.observable(1);
		this.currentPageSubscription = null;
    };
    
    Reference.prototype.activate = function() {
    	var self = this;
    	
    	self.title('Edit ' + self.personnel.namePrefix + ' ' + self.personnel.lastName + '\'s References');
    	
    	self.currentPage(1);
		self.currentPageSubscription = self.currentPage.subscribe(function() {
			self.refreshReferenceList();
		});
		
		self.searchKey.subscribe(function(searchKey) {
			if(searchKey.length >= 3) {
				self.search();
			}
		});
		
		self.refreshReferenceList();
    };
    
    Reference.show = function(personnel) {
    	return dialog.show(new Reference(personnel));
    };
    
    Reference.prototype.refreshReferenceList = function() {
    	var self = this;
    	
    	referenceService.getReferenceList(self.currentPage(), self.personnel.id, self.searchKey()).done(function(data) {
    		self.referenceList(data.list);
    		self.totalItems(data.total);
    	});
    };
    
    Reference.prototype.add = function() {
    	var self = this;
    	
    	ReferenceForm.show(self.personnel, new Object(),  'Add Reference').done(function() {
    		self.refreshReferenceList();
    	});
    };
    
    Reference.prototype.edit = function(referenceId) {
    	var self = this;
    	
    	referenceService.getReference(referenceId).done(function(reference) {
    		ReferenceForm.show(self.personnel, reference,  'Edit Reference').done(function() {
        		self.refreshReferenceList();
        	});
    	});
    };
    
    Reference.prototype.remove = function(referenceId, formattedName) {
    	var self = this;
    	
    	app.showMessage('<p>Are you sure you want to remove Reference <span class="text-primary">' + formattedName + '</span>?</p>',
				'<p class="text-danger">Confirm Remove</p>',
				[{ text: 'Yes', value: true }, { text: 'No', value: false }])
		.then(function(confirm) {
			if(confirm) {
				referenceService.removeReference(referenceId).done(function(result) {
					self.refreshReferenceList();
					app.showMessage(result.message);
				});
			}
		})
    };
    
    Reference.prototype.search = function() {
    	var self = this;
    	
    	self.currentPage(1);
    	self.refreshReferenceList();
    };
    
    Reference.prototype.cancel = function() {
        dialog.close(this);
    };
    
    return Reference;
});