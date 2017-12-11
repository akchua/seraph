define(['jquery'], function ($) {
	return {
		getSurveyResponse: function(surveyResponseId) {
			return $.ajax({
				url: '/services/surveyresponse/get',
				data: {
					surveyResponseId: surveyResponseId
				}
			});
		},
		
		getSurveyResponseByPersonnelAndIndex: function(personnelId, index) {
			return $.ajax({
				url: '/services/surveyresponse/getindex',
				data: {
					personnelId: personnelId,
					index: index
				}
			});
		},
		
		getSurveyResponseListByPersonnel: function(personnelId) {
			return $.ajax({
				url: '/services/surveyresponse/list',
				data: {
					personnelId: personnelId
				}
			});
		},
		
		saveSurveyResponse: function(surveyResponseFormData, personnelId) {
    		return $.ajax({
    			url: '/services/surveyresponse/save',
    			method: 'POST',
    			data: {
    				surveyResponseFormData: surveyResponseFormData,
    				personnelId: personnelId
    			} 
    		});
    	}
	};
});