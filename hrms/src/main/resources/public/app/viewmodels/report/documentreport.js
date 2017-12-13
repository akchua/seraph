define(['plugins/dialog', 'durandal/app', 'knockout', 'modules/documentservice', 'modules/constantsservice', 'modules/fileservice'],
		function (dialog, app, ko, documentService, constantsService, fileService) {
    var DocumentReport = function() {
    	this.documentList = ko.observable();
    	this.documentTypeList = ko.observable();
    	
    	this.documentType = ko.observable();
    	this.searchKey = ko.observable();
    	
    	this.itemsPerPage = ko.observable(app.user.itemsPerPage);
		this.totalItems = ko.observable();
		this.currentPage = ko.observable(1);
		this.currentPageSubscription = null;
    };
    
    DocumentReport.prototype.activate = function() {
    	var self = this;
    	
    	self.currentPage(1);
		self.currentPageSubscription = self.currentPage.subscribe(function() {
			self.refreshDocumentReportList();
		});
		
		self.searchKey.subscribe(function(searchKey) {
			if(searchKey.length >= 3) {
				self.search();
			}
		});
		
		constantsService.getDocumentTypeList().done(function(documentTypeList) {
			self.documentTypeList(documentTypeList);
		});
		
		self.refreshDocumentList();
    };
    
    DocumentReport.show = function() {
    	return dialog.show(new DocumentReport());
    };
    
    DocumentReport.prototype.refreshDocumentList = function() {
    	var self = this;
    	
    	documentService.getDocumentListByExpirationDate(self.currentPage(), self.documentType(), self.searchKey()).done(function(data) {
    		self.documentList(data.list);
    		self.totalItems(data.total);
    	});
    };
    
    DocumentReport.prototype.download = function(fileName) {
    	var self = this;
    	
    	fileService.downloadDocumentByFileName(fileName);
    };
    
    DocumentReport.prototype.search = function() {
    	var self = this;
    	
    	self.currentPage(1);
    	self.refreshDocumentList();
    };
    
    DocumentReport.prototype.cancel = function() {
        dialog.close(this);
    };
    
    return DocumentReport;
});