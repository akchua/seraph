define(['jquery'], function ($) {
	return {
		getEmployment: function(employmentId) {
			return $.ajax({
				url: '/services/employment/get',
				data: {
					employmentId: employmentId
				}
			});
		},
		
		getEmploymentList: function(currentPage, personnelId, searchKey) {
			return $.ajax({
				url: '/services/employment/list',
				async: false,
				data: {
					pageNumber: currentPage - 1,
					personnelId: personnelId,
					searchKey: searchKey
				}
			});
		},
		
		saveEmployment: function(employmentFormData, personnelId) {
    		return $.ajax({
    			url: '/services/employment/save',
    			method: 'POST',
    			data: {
    				employmentFormData: employmentFormData,
    				personnelId: personnelId
    			} 
    		});
    	},
    	
    	removeEmployment: function(employmentId) {
    		return $.ajax({
    			url: '/services/employment/remove',
    			method: 'POST',
    			data: {
    				employmentId: employmentId
    			}
    		});
    	}
	};
});