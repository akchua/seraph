define(['plugins/dialog', 'durandal/app', 'knockout', 'modules/documentservice', 'modules/constantsservice'], 
		function (dialog, app, ko, documentService, constantsService) {
    var DocumentForm = function(personnel, document, title) {
    	this.personnel = personnel;
    	this.document = document;
    	this.title = title;
    	
    	this.file = ko.observable();
    	
    	this.documentTypeList = ko.observable();
    	
    	this.documentFormModel = {
    		id: ko.observable(),
    		
    		documentType: ko.observable(),
    		expirationDate: ko.observable(),
	    	identificationNumber: ko.observable(),
	    	remarks: ko.observable()
	    };
    	
    	this.errors = {
			documentType: ko.observable(),
    		expirationDate: ko.observable(),
	    	identificationNumber: ko.observable(),
	    	remarks: ko.observable()
    	};
    };
    
    DocumentForm.prototype.activate = function() {
    	var self = this;
    	
    	self.documentFormModel.id(self.document.id);
    	if(self.document.documentType) self.documentFormModel.documentType(self.document.documentType.name);
    	self.documentFormModel.expirationDate(self.document.formattedExpirationDate);
    	self.documentFormModel.identificationNumber(self.document.identificationNumber);
    	self.documentFormModel.remarks(self.document.remarks);
    	
    	constantsService.getDocumentTypeList().done(function(documentTypeList) {
    		self.documentTypeList(documentTypeList);
    		if(self.document.documentType) self.documentFormModel.documentType(self.document.documentType.name);
    	});
    };
    
    DocumentForm.show = function(personnel, document, title) {
    	return dialog.show(new DocumentForm(personnel, document, title));
    };
    
    DocumentForm.prototype.save = function() {
    	var self = this;
    	
        documentService.saveDocument(ko.toJSON(self.documentFormModel), self.personnel.id, self.file()).done(function(result) {
        	if(result.success) {
        		dialog.close(self);
        	} else {
        		self.errors.documentType(result.extras.errors.documentType);
        		self.errors.expirationDate(result.extras.errors.expirationDate);
        		self.errors.identificationNumber(result.extras.errors.identificationNumber);
        		self.errors.remarks(result.extras.errors.remarks);
        	}
        	if(result.message) app.showMessage(result.message);
        });
    };
    
    DocumentForm.prototype.cancel = function() {
        dialog.close(this);
    };
    
    return DocumentForm;
});