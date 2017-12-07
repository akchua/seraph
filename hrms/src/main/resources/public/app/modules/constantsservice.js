define(['jquery'], function ($) {
    return {
    	getVersion: function() {
    		return $.ajax({
    			url: '/services/constants/version'
    		});
    	},
    	
    	getUserTypeList: function() {
    		return $.ajax({
    			url: '/services/constants/usertype'
    		});
    	},
    	
    	getGenderList: function() {
    		return $.ajax({
    			url: '/services/constants/gender'
    		});
    	},
    	
    	getPersonnelPositionList: function() {
    		return $.ajax({
    			url: '/services/constants/personnelposition'
    		});
    	},
    	
    	getCivilStatusList: function() {
    		return $.ajax({
    			url: '/services/constants/civilstatus'
    		});
    	}
    };
});