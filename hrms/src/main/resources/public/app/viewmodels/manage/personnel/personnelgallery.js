define(['plugins/dialog', 'durandal/app', 'knockout', 'modules/personnelservice'], 
		function (dialog, app, ko, personnelService) {
    var PersonnelGallery = function(personnelId) {
    	this.personnelId = personnelId;
    	this.personnelImageList = ko.observableArray();
    	
    	this.personnelModel = {
    		formattedName: ko.observable(),
			imagePath: ko.observable()
    	}
    };
    
    PersonnelGallery.prototype.activate = function() {
    	var self = this;
    	
    	self.refreshPersonnel();
    	self.refreshPersonnelImageList();
    };
    
    PersonnelGallery.show = function(personnelId) {
    	return dialog.show(new PersonnelGallery(personnelId));
    };
    
    PersonnelGallery.prototype.refreshPersonnel = function() {
    	var self = this;
    	
    	personnelService.getPersonnel(self.personnelId).done(function(personnel) {
    		self.personnelModel.formattedName(personnel.namePrefix + ' ' + personnel.lastName);
    		self.personnelModel.imagePath(personnelService.getPersonnelImageByFileName(personnel.image));
    	});
    };
    
    PersonnelGallery.prototype.refreshPersonnelImageList = function() {
    	var self = this;
    	
    	personnelService.getPersonnelImageList(self.personnelId).done(function(personnelImageList) {
    		for(i = 0; i < personnelImageList.length; i++) {
    			personnelImageList[i].filePath = personnelService.getPersonnelImageByFileName(personnelImageList[i].fileName);
    		}
    		self.personnelImageList(personnelImageList);
    	});
    };
    
    PersonnelGallery.prototype.uploadPersonnelImage = function(imageFile) {
    	var self = this;
    	
    	personnelService.uploadPersonnelImage(self.personnelId, imageFile).done(function(result) {
    		if(result.success) {
    			self.refreshPersonnel();
    			self.refreshPersonnelImageList();
    		}
    		app.showMessage(result.message);
    	});
    };
    
    PersonnelGallery.prototype.setPersonnelImageAsThumbnail = function(personnelImageId) {
    	var self = this;
    	
    	personnelService.setPersonnelImageAsThumbnail(personnelImageId).done(function(result) {
    		if(result.success) {
    			self.refreshPersonnel();
    		} else {
    			app.showMessage(result.message);
    		}
    	});
    };
    
    PersonnelGallery.prototype.removePersonnelImage = function(personnelImageId) {
    	var self = this;
    	
    	app.showMessage('<p>Are you sure you want to remove this image?</p>',
				'<p class="text-danger">Confirm Remove</p>',
				[{ text: 'Yes', value: true }, { text: 'No', value: false }])
		.then(function(confirm) {
			if(confirm) {
				personnelService.removePersonnelImage(personnelImageId).done(function(result) {
					self.refreshPersonnel();
					self.refreshPersonnelImageList();
					app.showMessage(result.message);
				});
			}
		})
    };
    
    PersonnelGallery.prototype.cancel = function() {
        dialog.close(this);
    };
    
    return PersonnelGallery;
});