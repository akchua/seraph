define(['plugins/dialog', 'durandal/app', 'knockout', 'viewmodels/manage/personnel/personnelform', 'viewmodels/manage/personnel/personnelgallery', 'viewmodels/manage/personnel/dependent', 'viewmodels/manage/personnel/employment', 'viewmodels/manage/personnel/reference', 'viewmodels/manage/personnel/survey'], 
		function (dialog, app, ko, PersonnelForm, PersonnelGallery, Dependent, Employment, Reference, Survey) {
    var EditSelect = function(personnel) {
    	this.personnel = personnel;
    	
    	this.name = ko.observable();
    };
    
    EditSelect.prototype.activate = function() {
    	var self = this;
    	
    	self.name(self.personnel.namePrefix + ' ' + self.personnel.lastName);
    };
    
    EditSelect.show = function(personnel) {
    	return dialog.show(new EditSelect(personnel));
    };
    
    EditSelect.prototype.editInformation = function() {
    	var self = this;
    	
		PersonnelForm.show(self.personnel,  'Update ' + self.name() + '\'s Information');
    };
    
    EditSelect.prototype.editPhoto = function() {
    	var self = this;

    	PersonnelGallery.show(self.personnel.id);
    };
    
    EditSelect.prototype.editDependents = function() {
    	var self = this;

    	Dependent.show(self.personnel);
    };
    
    EditSelect.prototype.editEmployment = function() {
    	var self = this;
    	
    	Employment.show(self.personnel);
    };
    
    EditSelect.prototype.editReference = function() {
    	var self = this;
    	
    	Reference.show(self.personnel);
    };
    
    EditSelect.prototype.survey = function() {
    	var self = this;

    	Survey.show(self.personnel);
    };
    
    EditSelect.prototype.cancel = function() {
        dialog.close(this);
    };
    
    return EditSelect;
});