define(['plugins/dialog', 'durandal/app', 'knockout', 'modules/referenceservice', 'modules/constantsservice'], 
		function (dialog, app, ko, referenceService, constantsService) {
    var ReferenceForm = function(personnel, reference, title) {
    	this.personnel = personnel;
    	this.reference = reference;
    	this.title = title;
    	
    	this.referenceFormModel = {
    		id: ko.observable(),
    		
    		firstName: ko.observable(),
	    	lastName: ko.observable(),
	    	contactNumber: ko.observable()
	    };
    	
    	this.errors = {
			firstName: ko.observable(),
	    	lastName: ko.observable(),
	    	contactNumber: ko.observable()
    	};
    };
    
    ReferenceForm.prototype.activate = function() {
    	var self = this;
    	
    	self.referenceFormModel.id(self.reference.id);
    	self.referenceFormModel.firstName(self.reference.firstName);
    	self.referenceFormModel.lastName(self.reference.lastName);
    	self.referenceFormModel.contactNumber(self.reference.contactNumber);
    };
    
    ReferenceForm.show = function(personnel, reference, title) {
    	return dialog.show(new ReferenceForm(personnel, reference, title));
    };
    
    ReferenceForm.prototype.save = function() {
    	var self = this;
    	
        referenceService.saveReference(ko.toJSON(self.referenceFormModel), self.personnel.id).done(function(result) {
        	if(result.success) {
        		dialog.close(self);
        	} else {
        		self.errors.firstName(result.extras.errors.firstName);
        		self.errors.lastName(result.extras.errors.lastName);
        		self.errors.contactNumber(result.extras.errors.contactNumber);
        	}
        	if(result.message) app.showMessage(result.message);
        });
    };
    
    ReferenceForm.prototype.cancel = function() {
        dialog.close(this);
    };
    
    return ReferenceForm;
});