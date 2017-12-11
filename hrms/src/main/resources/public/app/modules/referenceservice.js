define(['jquery'], function ($) {
	return {
		getReference: function(referenceId) {
			return $.ajax({
				url: '/services/reference/get',
				data: {
					referenceId: referenceId
				}
			});
		},
		
		getReferenceList: function(currentPage, personnelId, searchKey) {
			return $.ajax({
				url: '/services/reference/list',
				data: {
					pageNumber: currentPage - 1,
					personnelId: personnelId,
					searchKey: searchKey
				}
			});
		},
		
		saveReference: function(referenceFormData, personnelId) {
    		return $.ajax({
    			url: '/services/reference/save',
    			method: 'POST',
    			data: {
    				referenceFormData: referenceFormData,
    				personnelId: personnelId
    			} 
    		});
    	},
    	
    	removeReference: function(referenceId) {
    		return $.ajax({
    			url: '/services/reference/remove',
    			method: 'POST',
    			data: {
    				referenceId: referenceId
    			}
    		});
    	}
	};
});