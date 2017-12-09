define(['jquery'], function ($) {
	return {
		getDependent: function(dependentId) {
			return $.ajax({
				url: '/services/dependent/get',
				data: {
					dependentId: dependentId
				}
			});
		},
		
		getDependentList: function(currentPage, personnelId, searchKey) {
			return $.ajax({
				url: '/services/dependent/list',
				data: {
					pageNumber: currentPage - 1,
					personnelId: personnelId,
					searchKey: searchKey
				}
			});
		},
		
		saveDependent: function(dependentFormData, personnelId) {
    		return $.ajax({
    			url: '/services/dependent/save',
    			method: 'POST',
    			data: {
    				dependentFormData: dependentFormData,
    				personnelId: personnelId
    			} 
    		});
    	},
    	
    	removeDependent: function(dependentId) {
    		return $.ajax({
    			url: '/services/dependent/remove',
    			method: 'POST',
    			data: {
    				dependentId: dependentId
    			}
    		});
    	}
	};
});