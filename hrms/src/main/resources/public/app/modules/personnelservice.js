define(['jquery'], function ($) {
	return {
		getPersonnel: function(personnelId) {
			return $.ajax({
				url: '/services/personnel/get',
				data: {
					personnelId: personnelId
				}
			});
		},
		
		getPersonnelList: function(currentPage, searchKey) {
			return $.ajax({
				url: '/services/personnel/list',
				data: {
					pageNumber: currentPage - 1,
					searchKey: searchKey
				}
			});
		},
		
		savePersonnel: function(personnelFormData) {
    		return $.ajax({
    			url: '/services/personnel/save',
    			method: 'POST',
    			data: {
    				personnelFormData: personnelFormData
    			} 
    		});
    	},
    	
    	removePersonnel: function(personnelId) {
    		return $.ajax({
    			url: '/services/personnel/remove',
    			method: 'POST',
    			data: {
    				personnelId: personnelId
    			}
    		});
    	}
	};
});