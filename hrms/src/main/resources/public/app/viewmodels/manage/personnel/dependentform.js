define(['plugins/dialog', 'durandal/app', 'knockout', 'modules/dependentservice', 'modules/constantsservice'], 
		function (dialog, app, ko, dependentService, constantsService) {
    var DependentForm = function(personnel, dependent, title) {
    	this.personnel = personnel;
    	this.dependent = dependent;
    	this.title = title;
    	
    	this.genderList = ko.observable();
    	
    	this.dependentFormModel = {
    		id: ko.observable(),
    		
    		firstName: ko.observable(),
	    	lastName: ko.observable(),
	    	age: ko.observable(),
	    	gender: ko.observable(),
	    	relationship: ko.observable()
	    };
    	
    	this.errors = {
			firstName: ko.observable(),
	    	lastName: ko.observable(),
	    	age: ko.observable(),
	    	gender: ko.observable(),
	    	relationship: ko.observable()
    	};
    };
    
    DependentForm.prototype.activate = function() {
    	var self = this;
    	
    	self.dependentFormModel.id(self.dependent.id);
    	self.dependentFormModel.firstName(self.dependent.firstName);
    	self.dependentFormModel.lastName(self.dependent.lastName);
    	self.dependentFormModel.age(self.dependent.age);
    	if(self.dependent.gender) self.dependentFormModel.gender(self.dependent.gender.name);
    	self.dependentFormModel.relationship(self.dependent.relationship);
    	
    	constantsService.getGenderList().done(function(genderList) {
    		self.genderList(genderList);
    		if(self.dependent.gender) self.dependentFormModel.gender(self.dependent.gender.name);
    	});
    };
    
    DependentForm.show = function(personnel, dependent, title) {
    	return dialog.show(new DependentForm(personnel, dependent, title));
    };
    
    DependentForm.prototype.save = function() {
    	var self = this;
    	
        dependentService.saveDependent(ko.toJSON(self.dependentFormModel), self.personnel.id).done(function(result) {
        	if(result.success) {
        		dialog.close(self);
        	} else {
        		self.errors.firstName(result.extras.errors.firstName);
        		self.errors.lastName(result.extras.errors.lastName);
        		self.errors.age(result.extras.errors.age);
        		self.errors.gender(result.extras.errors.gender);
        		self.errors.relationship(result.extras.errors.relationship);
        	}
        	if(result.message) app.showMessage(result.message);
        });
    };
    
    DependentForm.prototype.cancel = function() {
        dialog.close(this);
    };
    
    return DependentForm;
});