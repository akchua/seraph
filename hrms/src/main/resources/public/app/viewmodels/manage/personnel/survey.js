define(['plugins/dialog', 'durandal/app', 'knockout', 'modules/surveyresponseservice'],
		function (dialog, app, ko, surveyResponseService) {
    var Survey = function(personnel) {
    	this.personnel = personnel;
    	this.title = ko.observable();
    	
    	this.index = ko.observable(0);
    	this.prevIndex = 0;
    	this.totalQuestions = ko.observable();
    	
    	this.question = ko.observable();
    	this.answerGuide = ko.observable();
    	
    	this.showPrevious = ko.observable(false);
    	this.showNext = ko.observable(true);
    	this.closeModal = false;
    	
    	this.surveyResponseFormModel = {
    		id: ko.observable(),
    		
    		surveyQuestion: ko.observable(),
    		response: ko.observable()
    	};
    	
    	this.errors = {
    		response: ko.observable()	
    	};
    };
    
    Survey.prototype.activate = function() {
    	var self = this;
    	
    	self.title('Personnel Survey');
		
		self.refreshSurveyList();
    };
    
    Survey.show = function(personnel) {
    	return dialog.show(new Survey(personnel));
    };
    
    Survey.prototype.refreshSurveyList = function() {
    	var self = this;
    		
    	surveyResponseService.getSurveyResponseByPersonnelAndIndex(self.personnel.id, self.index()).done(function(surveyResponseWrapper) {
    		self.question(surveyResponseWrapper.surveyResponse.surveyQuestion.question);
    		self.answerGuide(surveyResponseWrapper.surveyResponse.surveyQuestion.answerGuide);
    		self.totalQuestions(surveyResponseWrapper.totalQuestions);
    		self.errors.response('');
    		
    		self.surveyResponseFormModel.id(surveyResponseWrapper.surveyResponse.id);
    		self.surveyResponseFormModel.surveyQuestion(surveyResponseWrapper.surveyResponse.surveyQuestion.name);
    		self.surveyResponseFormModel.response(surveyResponseWrapper.surveyResponse.response);
    	});
    };
    
    Survey.prototype.previous = function() {
    	var self = this;
    	
    	if(self.index() > 0) {
    		self.prevIndex = self.index();
    		self.index(self.index() - 1);
    	}
    	
    	self.save();
    };
    
    Survey.prototype.next = function() {
    	var self = this;
    	
    	if(self.index() < self.totalQuestions() - 1) {
    		self.prevIndex = self.index();
    		self.index(self.index() + 1);
    	} else {
    		self.prevIndex = self.index();
    		self.closeModal = true;
    	}
    	
    	self.save();
    };
    
    Survey.prototype.showButtons = function() {
    	var self = this;
    	
    	if(self.index() > 0) {
    		self.showPrevious(true);
    	} else {
    		self.showPrevious(false);
    	}
    	
    	if(self.index() < self.totalQuestions() - 1) {
    		self.showNext(true);
    	} else {
    		self.showNext(false);
    	}
    };
    
    Survey.prototype.save = function() {
    	var self = this;
    	
    	surveyResponseService.saveSurveyResponse(ko.toJSON(self.surveyResponseFormModel), self.personnel.id).done(function(result) {
    		if(result.success) {
    			self.refreshSurveyList();
    			self.showButtons();
    			if(self.closeModal) self.cancel();
    		} else {
    			self.index(self.prevIndex);
    			self.closeModal = false;
    			self.errors.response(result.extras.errors.response);
    		}
    	});
    };
    
    Survey.prototype.cancel = function() {
        dialog.close(this);
    };
    
    return Survey;
});