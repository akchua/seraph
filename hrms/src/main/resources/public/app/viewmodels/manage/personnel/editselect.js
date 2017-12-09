define(['plugins/dialog', 'durandal/app', 'knockout', 'viewmodels/manage/personnel/personnelform', 'viewmodels/manage/personnel/personnelgallery'], 
		function (dialog, app, ko, PersonnelForm, PersonnelGallery) {
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
    
    EditSelect.prototype.cancel = function() {
        dialog.close(this);
    };
    
    return EditSelect;
});