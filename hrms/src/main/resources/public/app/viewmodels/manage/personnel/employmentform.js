define(['plugins/dialog', 'durandal/app', 'knockout', 'modules/employmentservice'], 
		function (dialog, app, ko, employmentService) {
    var EmploymentForm = function(personnel, employment, title) {
    	this.personnel = personnel;
    	this.employment = employment;
    	this.title = title;
    	
    	this.genderList = ko.observable();
    	
    	this.employmentFormModel = {
    		id: ko.observable(),
    		
    		companyName: ko.observable(),
	    	position: ko.observable(),
	    	salary: ko.observable(),
	    	years: ko.observable(),
	    	causeOfDischarge: ko.observable()
	    };
    	
    	this.errors = {
			companyName: ko.observable(),
	    	position: ko.observable(),
	    	salary: ko.observable(),
	    	years: ko.observable(),
	    	causeOfDischarge: ko.observable()
    	};
    };
    
    EmploymentForm.prototype.activate = function() {
    	var self = this;
    	
    	self.employmentFormModel.id(self.employment.id);
    	self.employmentFormModel.companyName(self.employment.companyName);
    	self.employmentFormModel.position(self.employment.position);
    	self.employmentFormModel.salary(self.employment.salary);
    	self.employmentFormModel.years(self.employment.years);
    	self.employmentFormModel.causeOfDischarge(self.employment.causeOfDischarge);
    };
    
    EmploymentForm.show = function(personnel, employment, title) {
    	return dialog.show(new EmploymentForm(personnel, employment, title));
    };
    
    EmploymentForm.prototype.save = function() {
    	var self = this;
    	
        employmentService.saveEmployment(ko.toJSON(self.employmentFormModel), self.personnel.id).done(function(result) {
        	if(result.success) {
        		dialog.close(self);
        	} else {
        		self.errors.companyName(result.extras.errors.companyName);
        		self.errors.position(result.extras.errors.position);
        		self.errors.salary(result.extras.errors.salary);
        		self.errors.years(result.extras.errors.years);
        		self.errors.causeOfDischarge(result.extras.errors.causeOfDischarge);
        	}
        	if(result.message) app.showMessage(result.message);
        });
    };
    
    EmploymentForm.prototype.cancel = function() {
        dialog.close(this);
    };
    
    return EmploymentForm;
});