define(['plugins/dialog', 'durandal/app', 'knockout', 'modules/documentservice', 'modules/fileservice', 'viewmodels/manage/personnel/documentform'],
		function (dialog, app, ko, documentService, fileService, DocumentForm) {
    var Document = function(personnel) {
    	this.personnel = personnel;
    	this.title = ko.observable();
    	
    	this.documentList = ko.observable();
    	
    	this.searchKey = ko.observable();
    	
    	this.itemsPerPage = ko.observable(app.user.itemsPerPage);
		this.totalItems = ko.observable();
		this.currentPage = ko.observable(1);
		this.currentPageSubscription = null;
    };
    
    Document.prototype.activate = function() {
    	var self = this;
    	
    	self.title('Edit ' + self.personnel.namePrefix + ' ' + self.personnel.lastName + '\'s Documents');
    	
    	self.currentPage(1);
		self.currentPageSubscription = self.currentPage.subscribe(function() {
			self.refreshDocumentList();
		});
		
		self.searchKey.subscribe(function(searchKey) {
			if(searchKey.length >= 3) {
				self.search();
			}
		});
		
		self.refreshDocumentList();
    };
    
    Document.show = function(personnel) {
    	return dialog.show(new Document(personnel));
    };
    
    Document.prototype.refreshDocumentList = function() {
    	var self = this;
    	
    	documentService.getDocumentList(self.currentPage(), self.personnel.id, self.searchKey()).done(function(data) {
    		self.documentList(data.list);
    		self.totalItems(data.total);
    	});
    };
    
    Document.prototype.add = function() {
    	var self = this;
    	
    	DocumentForm.show(self.personnel, new Object(),  'Add Document').done(function() {
    		self.refreshDocumentList();
    	});
    };
    
    Document.prototype.edit = function(documentId) {
    	var self = this;
    	
    	documentService.getDocument(documentId).done(function(document) {
    		DocumentForm.show(self.personnel, document,  'Edit Document').done(function() {
        		self.refreshDocumentList();
        	});
    	});
    };
    
    Document.prototype.remove = function(documentId, documentType) {
    	var self = this;
    	
    	app.showMessage('<p>Are you sure you want to remove Document <span class="text-primary">' + documentType + '</span>?</p>',
				'<p class="text-danger">Confirm Remove</p>',
				[{ text: 'Yes', value: true }, { text: 'No', value: false }])
		.then(function(confirm) {
			if(confirm) {
				documentService.removeDocument(documentId).done(function(result) {
					self.refreshDocumentList();
					app.showMessage(result.message);
				});
			}
		})
    };
    
    Document.prototype.download = function(fileName) {
    	var self = this;
    	
    	fileService.downloadDocumentByFileName(fileName);
    };
    
    Document.prototype.search = function() {
    	var self = this;
    	
    	self.currentPage(1);
    	self.refreshDocumentList();
    };
    
    Document.prototype.cancel = function() {
        dialog.close(this);
    };
    
    return Document;
});