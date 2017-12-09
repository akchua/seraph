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
		
		getPersonnelImageByFileName: function(fileName) {
			return '/services/personnel/getimage/' + fileName;
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
		
		getPersonnelImageList: function(personnelId) {
			return $.ajax({
				url: '/services/personnel/imagelist',
				async: false,
				data: {
					personnelId: personnelId
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
    	
    	uploadPersonnelImage: function(personnelId, imageFile) {
    		var formData = new FormData();
    		formData.append('imageFile', imageFile);
    		formData.append('personnelId', personnelId);
    		
    		return $.ajax({
    			url: '/services/personnel/uploadimage',
    			method: 'POST',
    			data: formData,
    			cache: false,
    			contentType: false,
    			processData: false
    		});
    	},
    	
    	setPersonnelImageAsThumbnail: function(personnelImageId) {
    		return $.ajax({
    			url: '/services/personnel/setthumbnail',
    			method: 'POST',
    			data: {
    				personnelImageId: personnelImageId
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
    	},
    	
    	removePersonnelImage: function(personnelImageId) {
    		return $.ajax({
    			url: '/services/personnel/removeimage',
    			method: 'POST',
    			data: {
    				personnelImageId: personnelImageId
    			}
    		});
    	}
	};
});