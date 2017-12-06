define(['jquery'], function ($) {
    return {
    	getVersion: function() {
    		return $.ajax({
    			url: '/services/constants/version'
    		});
    	}
    };
});