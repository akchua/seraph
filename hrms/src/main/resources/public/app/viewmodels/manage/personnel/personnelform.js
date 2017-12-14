define(['plugins/dialog', 'durandal/app', 'knockout', 'jquery', 'modules/personnelservice', 'modules/constantsservice'], 
		function (dialog, app, ko, $, personnelService, constantsService) {
    var PersonnelForm = function(personnel, title) {
    	this.personnel = personnel;
    	this.title = title;
    	
    	this.personnelPositionList = ko.observable();
    	this.genderList = ko.observable();
    	this.civilStatusList = ko.observable();
    	
    	this.enableSave = ko.observable(true);
    	
    	this.personnelFormModel = {
    		id: ko.observable(),
    		
    		//PERSONAL
    		// basic information
    		personnelPosition: ko.observable(),
    		firstName: ko.observable(),
    		lastName: ko.observable(),
    		middleName: ko.observable(),
    		contactNumber: ko.observable(),
    		cityAddress: ko.observable(),
    		birthdate: ko.observable(),
    		gender: ko.observable(),
    		civilStatus: ko.observable(),
    		// physical information
    		height: ko.observable(),
    		weight: ko.observable(),
    		hairColor: ko.observable(),
    		eyeColor: ko.observable(),
    		complexion: ko.observable(),
    		identifyingMarks: ko.observable(),
    		// other information
    		motherMaidenName: ko.observable(),
    		birthplace: ko.observable(),
    		provincialAddress: ko.observable(),
    		dialectsSpoken: ko.observable(),
    		religion: ko.observable(),
    		//RELATIONS
    		// family
    		nameOfSpouse: ko.observable(),
    		occupationOfSpouse: ko.observable(),
    		nameOfFather: ko.observable(),
    		occupationOfFather: ko.observable(),
    		nameOfMother: ko.observable(),
    		occupationOfMother: ko.observable(),
    		recommendedBy: ko.observable(),
    		// in case of emergency
    		emergencyContactPerson: ko.observable(),
    		contactPersonRelation: ko.observable(),
    		contactPersonAddress: ko.observable(),
    		contactPersonNumber: ko.observable(),
    		//EDUCATION
    		highSchool: ko.observable(),
    		highSchoolGradYear: ko.observable(),
    		vocationalSchool: ko.observable(),
    		vocationalSchoolGradYear: ko.observable(),
    		college: ko.observable(),
    		collegeDegree: ko.observable(),
    		collegeGradYear: ko.observable(),
    		lastSecuritySchool: ko.observable(),
    		//OTHERS
    		organization: ko.observable(),
    		specialSkills: ko.observable(),
    		hobbies: ko.observable()
	    };
    	
    	this.errors = {
			//PERSONAL
    		// basic information
    		personnelPosition: ko.observable(),
    		firstName: ko.observable(),
    		lastName: ko.observable(),
    		middleName: ko.observable(),
    		contactNumber: ko.observable(),
    		cityAddress: ko.observable(),
    		birthdate: ko.observable(),
    		gender: ko.observable(),
    		civilStatus: ko.observable(),
    		// physical information
    		height: ko.observable(),
    		weight: ko.observable(),
    		hairColor: ko.observable(),
    		eyeColor: ko.observable(),
    		complexion: ko.observable(),
    		identifyingMarks: ko.observable(),
    		// other information
    		motherMaidenName: ko.observable(),
    		birthplace: ko.observable(),
    		provincialAddress: ko.observable(),
    		dialectsSpoken: ko.observable(),
    		religion: ko.observable(),
    		//RELATIONS
    		// family
    		nameOfSpouse: ko.observable(),
    		occupationOfSpouse: ko.observable(),
    		nameOfFather: ko.observable(),
    		occupationOfFather: ko.observable(),
    		nameOfMother: ko.observable(),
    		occupationOfMother: ko.observable(),
    		recommendedBy: ko.observable(),
    		// in case of emergency
    		emergencyContactPerson: ko.observable(),
    		contactPersonRelation: ko.observable(),
    		contactPersonAddress: ko.observable(),
    		contactPersonNumber: ko.observable(),
    		//EDUCATION
    		highSchool: ko.observable(),
    		highSchoolGradYear: ko.observable(),
    		vocationalSchool: ko.observable(),
    		vocationalSchoolGradYear: ko.observable(),
    		college: ko.observable(),
    		collegeDegree: ko.observable(),
    		collegeGradYear: ko.observable(),
    		lastSecuritySchool: ko.observable(),
    		//OTHERS
    		organization: ko.observable(),
    		specialSkills: ko.observable(),
    		hobbies: ko.observable()
    	};
    };
    
    PersonnelForm.prototype.activate = function() {
    	var self = this;
    	
    	self.personnelFormModel.id(self.personnel.id);
    	
    	if(self.personnel.personnelPosition) self.personnelFormModel.personnelPosition(self.personnel.personnelPosition.name);
    	self.personnelFormModel.firstName(self.personnel.firstName);
    	self.personnelFormModel.lastName(self.personnel.lastName);
    	self.personnelFormModel.middleName(self.personnel.middleName);
    	self.personnelFormModel.contactNumber(self.personnel.contactNumber);
    	self.personnelFormModel.cityAddress(self.personnel.cityAddress);
    	self.personnelFormModel.birthdate(self.personnel.formattedBirthdate);
    	if(self.personnel.gender) self.personnelFormModel.gender(self.personnel.gender.name);
    	if(self.personnel.civilStatus) self.personnelFormModel.civilStatus(self.personnel.civilStatus.name);
    	
    	self.personnelFormModel.height(self.personnel.height);
    	self.personnelFormModel.weight(self.personnel.weight);
    	self.personnelFormModel.hairColor(self.personnel.hairColor);
    	self.personnelFormModel.eyeColor(self.personnel.eyeColor);
    	self.personnelFormModel.complexion(self.personnel.complexion);
    	self.personnelFormModel.identifyingMarks(self.personnel.identifyingMarks);
    	
    	self.personnelFormModel.motherMaidenName(self.personnel.motherMaidenName);
    	self.personnelFormModel.birthplace(self.personnel.birthplace);
    	self.personnelFormModel.provincialAddress(self.personnel.provincialAddress);
    	self.personnelFormModel.dialectsSpoken(self.personnel.dialectsSpoken);
    	self.personnelFormModel.religion(self.personnel.religion);
    	
    	self.personnelFormModel.nameOfSpouse(self.personnel.nameOfSpouse);
    	self.personnelFormModel.occupationOfSpouse(self.personnel.occupationOfSpouse);
    	self.personnelFormModel.nameOfFather(self.personnel.nameOfFather);
    	self.personnelFormModel.occupationOfFather(self.personnel.occupationOfFather);
    	self.personnelFormModel.nameOfMother(self.personnel.nameOfMother);
    	self.personnelFormModel.occupationOfMother(self.personnel.occupationOfMother);
    	self.personnelFormModel.recommendedBy(self.personnel.recommendedBy);
    	
    	self.personnelFormModel.emergencyContactPerson(self.personnel.emergencyContactPerson);
    	self.personnelFormModel.contactPersonRelation(self.personnel.contactPersonRelation);
    	self.personnelFormModel.contactPersonAddress(self.personnel.contactPersonAddress);
    	self.personnelFormModel.contactPersonNumber(self.personnel.contactPersonNumber);
    	
    	self.personnelFormModel.highSchool(self.personnel.highSchool);
    	self.personnelFormModel.highSchoolGradYear(self.personnel.highSchoolGradYear);
    	self.personnelFormModel.vocationalSchool(self.personnel.vocationalSchool);
    	self.personnelFormModel.vocationalSchoolGradYear(self.personnel.vocationalSchoolGradYear);
    	self.personnelFormModel.college(self.personnel.college);
    	self.personnelFormModel.collegeDegree(self.personnel.collegeDegree);
    	self.personnelFormModel.collegeGradYear(self.personnel.collegeGradYear);
    	self.personnelFormModel.lastSecuritySchool(self.personnel.lastSecuritySchool);
    	
    	self.personnelFormModel.organization(self.personnel.organization);
    	self.personnelFormModel.specialSkills(self.personnel.specialSkills);
    	self.personnelFormModel.hobbies(self.personnel.hobbies);
    	
    	constantsService.getPersonnelPositionList().done(function(personnelPositionList) {
    		self.personnelPositionList(personnelPositionList);
    		if(self.personnel.personnelPosition) self.personnelFormModel.personnelPosition(self.personnel.personnelPosition.name);
    	});
    	
    	constantsService.getGenderList().done(function(genderList) {
    		self.genderList(genderList);
    		if(self.personnel.gender) self.personnelFormModel.gender(self.personnel.gender.name);
    	});
    	
    	constantsService.getCivilStatusList().done(function(civilStatusList) {
    		self.civilStatusList(civilStatusList);
    		if(self.personnel.civilStatus) self.personnelFormModel.civilStatus(self.personnel.civilStatus.name);
    	});
    };
    
    PersonnelForm.show = function(personnel, title) {
    	return dialog.show(new PersonnelForm(personnel, title));
    };
    
    PersonnelForm.prototype.save = function() {
    	var self = this;
    	
    	self.enableSave(false);
        personnelService.savePersonnel(ko.toJSON(self.personnelFormModel)).done(function(result) {
        	if(result.success) {
        		dialog.close(self);
        	} else {
        		$('#personnelFormModal').animate({ scrollTop: 0 }, 'slow');
        		self.enableSave(true);
        		if(result.extras && result.extras.errors) {
	        		self.errors.personnelPosition(result.extras.errors.personnelPosition);
	        		self.errors.firstName(result.extras.errors.firstName);
	        		self.errors.lastName(result.extras.errors.lastName);
	        		self.errors.middleName(result.extras.errors.middleName);
	        		self.errors.contactNumber(result.extras.errors.contactNumber);
	        		self.errors.cityAddress(result.extras.errors.cityAddress);
	        		self.errors.birthdate(result.extras.errors.birthdate);
	        		self.errors.gender(result.extras.errors.gender);
	        		self.errors.civilStatus(result.extras.errors.civilStatus);
	
	        		self.errors.height(result.extras.errors.height);
	        		self.errors.weight(result.extras.errors.weight);
	        		self.errors.hairColor(result.extras.errors.hairColor);
	        		self.errors.eyeColor(result.extras.errors.eyeColor);
	        		self.errors.complexion(result.extras.errors.complexion);
	        		self.errors.identifyingMarks(result.extras.errors.identifyingMarks);
	
	        		self.errors.motherMaidenName(result.extras.errors.motherMaidenName);
	        		self.errors.birthplace(result.extras.errors.birthplace);
	        		self.errors.provincialAddress(result.extras.errors.provincialAddress);
	        		self.errors.dialectsSpoken(result.extras.errors.dialectsSpoken);
	        		self.errors.religion(result.extras.errors.religion);
	
	        		self.errors.nameOfSpouse(result.extras.errors.nameOfSpouse);
	        		self.errors.occupationOfSpouse(result.extras.errors.occupationOfSpouse);
	        		self.errors.nameOfFather(result.extras.errors.nameOfFather);
	        		self.errors.occupationOfFather(result.extras.errors.occupationOfFather);
	        		self.errors.nameOfMother(result.extras.errors.nameOfMother);
	        		self.errors.occupationOfMother(result.extras.errors.occupationOfMother);
	        		self.errors.recommendedBy(result.extras.errors.recommendedBy);
	
	        		self.errors.emergencyContactPerson(result.extras.errors.emergencyContactPerson);
	        		self.errors.contactPersonRelation(result.extras.errors.contactPersonRelation);
	        		self.errors.contactPersonAddress(result.extras.errors.contactPersonAddress);
	        		self.errors.contactPersonNumber(result.extras.errors.contactPersonNumber);
	
	        		self.errors.highSchool(result.extras.errors.highSchool);
	        		self.errors.highSchoolGradYear(result.extras.errors.highSchoolGradYear);
	        		self.errors.vocationalSchool(result.extras.errors.vocationalSchool);
	        		self.errors.vocationalSchoolGradYear(result.extras.errors.vocationalSchoolGradYear);
	        		self.errors.college(result.extras.errors.college);
	        		self.errors.collegeDegree(result.extras.errors.collegeDegree);
	        		self.errors.collegeGradYear(result.extras.errors.collegeGradYear);
	        		self.errors.lastSecuritySchool(result.extras.errors.lastSecuritySchool);
	
	        		self.errors.organization(result.extras.errors.organization);
	        		self.errors.specialSkills(result.extras.errors.specialSkills);
	        		self.errors.hobbies(result.extras.errors.hobbies);
        		}
        	}
        	if(result.message) app.showMessage(result.message);
        });
    };
    
    PersonnelForm.prototype.cancel = function() {
        dialog.close(this);
    };
    
    return PersonnelForm;
});