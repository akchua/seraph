define(['jquery'], function ($) {
	return {
		getDocument: function(documentId) {
			return $.ajax({
				url: '/services/document/get',
				data: {
					documentId: documentId
				}
			});
		},
		
		getDocumentList: function(currentPage, personnelId, searchKey) {
			return $.ajax({
				url: '/services/document/list',
				async: false,
				data: {
					pageNumber: currentPage - 1,
					personnelId: personnelId,
					searchKey: searchKey
				}
			});
		},
		
		saveDocument: function(documentFormData, personnelId, file) {
			var formData = new FormData();
    		formData.append('documentFormData', documentFormData);
    		formData.append('personnelId', personnelId);
    		formData.append('file', file);
    		
    		return $.ajax({
    			url: '/services/document/save',
    			method: 'POST',
    			data: formData,
    			cache: false,
    			contentType: false,
    			processData: false
    		});
    	},
    	
    	removeDocument: function(documentId) {
    		return $.ajax({
    			url: '/services/document/remove',
    			method: 'POST',
    			data: {
    				documentId: documentId
    			}
    		});
    	}
	};
});