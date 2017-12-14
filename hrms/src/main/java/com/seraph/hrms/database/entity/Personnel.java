package com.seraph.hrms.database.entity;import java.text.SimpleDateFormat;import java.util.Date;import javax.persistence.Basic;import javax.persistence.Column;import javax.persistence.Entity;import javax.persistence.EnumType;import javax.persistence.Enumerated;import javax.persistence.FetchType;import javax.persistence.JoinColumn;import javax.persistence.ManyToOne;import javax.persistence.Table;import javax.persistence.Temporal;import javax.persistence.TemporalType;import javax.persistence.Transient;import javax.validation.constraints.NotNull;import org.hibernate.annotations.NotFound;import org.hibernate.annotations.NotFoundAction;import org.hibernate.annotations.Where;import com.seraph.hrms.database.entity.base.BaseObject;import com.seraph.hrms.enums.CivilStatus;import com.seraph.hrms.enums.Gender;import com.seraph.hrms.enums.PersonnelPosition;;/** * @author  Adrian Jasper K. Chua * @version 1.0 * @since   6 December 2017 */@Entity(name = "Personnel")@Table(name = Personnel.TABLE_NAME)public class Personnel extends BaseObject {	private static final long serialVersionUID = 3959260984546627217L;	public static final String TABLE_NAME = "personnel";		private User creator;		private String image;		private PersonnelPosition personnelPosition;		private String firstName;		private String lastName;		private String middleName;		private String contactNumber;		private String motherMaidenName;		private String cityAddress;		private String provincialAddress;		private String birthplace;		private Date birthdate;		private String height;		private String weight;		private Gender gender;		private CivilStatus civilStatus;		private String hairColor;		private String eyeColor;		private String complexion;		private String identifyingMarks;		private String dialectsSpoken;		private String religion;		private String nameOfSpouse;		private String occupationOfSpouse;		private String nameOfFather;		private String occupationOfFather;		private String nameOfMother;		private String occupationOfMother;		private String emergencyContactPerson;		private String contactPersonRelation;		private String contactPersonAddress;		private String contactPersonNumber;		private String recommendedBy;		private String highSchool;		private String highSchoolGradYear;		private String vocationalSchool;		private String vocationalSchoolGradYear;		private String college;		private String collegeDegree;		private String collegeGradYear;		private String lastSecuritySchool;		private String organization;		private String specialSkills;		private String hobbies;		@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)	@JoinColumn(name = "creator_id")	@Where(clause = "valid = 1")	@NotFound(action = NotFoundAction.IGNORE)	public User getCreator() {		return creator;	}	public void setCreator(User creator) {		this.creator = creator;	}	@Basic	@Column(name = "image")	public String getImage() {		return image;	}	public void setImage(String image) {		this.image = image;	}	@Enumerated(EnumType.STRING)	@Column(name = "personnel_position", length = 50)	public PersonnelPosition getPersonnelPosition() {		return personnelPosition;	}	public void setPersonnelPosition(PersonnelPosition personnelPosition) {		this.personnelPosition = personnelPosition;	}	@Basic	@NotNull	@Column(name = "first_name")	public String getFirstName() {		return firstName;	}	public void setFirstName(String firstName) {		this.firstName = firstName;	}	@Basic	@NotNull	@Column(name = "last_name")	public String getLastName() {		return lastName;	}	public void setLastName(String lastName) {		this.lastName = lastName;	}	@Basic	@NotNull	@Column(name = "middle_name")	public String getMiddleName() {		return middleName;	}		@Transient	public String getFormattedName() {		return lastName + ", " + firstName + " " + middleName;	}	public void setMiddleName(String middleName) {		this.middleName = middleName;	}	@Basic	@Column(name = "contact_number")	public String getContactNumber() {		return contactNumber;	}	public void setContactNumber(String contactNumber) {		this.contactNumber = contactNumber;	}	@Basic	@Column(name = "mother_maiden_name")	public String getMotherMaidenName() {		return motherMaidenName;	}	public void setMotherMaidenName(String motherMaidenName) {		this.motherMaidenName = motherMaidenName;	}	@Basic	@Column(name = "city_address")	public String getCityAddress() {		return cityAddress;	}	public void setCityAddress(String cityAddress) {		this.cityAddress = cityAddress;	}	@Basic	@Column(name = "provincial_address")	public String getProvincialAddress() {		return provincialAddress;	}	public void setProvincialAddress(String provincialAddress) {		this.provincialAddress = provincialAddress;	}	@Basic	@NotNull	@Column(name = "birthplace")	public String getBirthplace() {		return birthplace;	}	public void setBirthplace(String birthplace) {		this.birthplace = birthplace;	}	@Temporal(value = TemporalType.TIMESTAMP)	@NotNull	@Column(name = "birthdate")	public Date getBirthdate() {		return birthdate;	}		@Transient	public String getFormattedBirthdate() {		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");		return sdf.format(getBirthdate());	}	public void setBirthdate(Date birthdate) {		this.birthdate = birthdate;	}	@Basic	@NotNull	@Column(name = "height")	public String getHeight() {		return height;	}	public void setHeight(String height) {		this.height = height;	}	@Basic	@NotNull	@Column(name = "weight")	public String getWeight() {		return weight;	}	public void setWeight(String weight) {		this.weight = weight;	}	@Enumerated(EnumType.STRING)	@NotNull	@Column(name = "gender")	public Gender getGender() {		return gender;	}		@Transient	public String getNamePrefix() {		return gender.equals(Gender.MALE) ? "Mr." : civilStatus.equals(CivilStatus.MARRIED) ? "Mrs." : "Ms.";	}	public void setGender(Gender gender) {		this.gender = gender;	}	@Enumerated(EnumType.STRING)	@NotNull	@Column(name = "civil_status")	public CivilStatus getCivilStatus() {		return civilStatus;	}	public void setCivilStatus(CivilStatus civilStatus) {		this.civilStatus = civilStatus;	}	@Basic	@Column(name = "hair_color")	public String getHairColor() {		return hairColor;	}	public void setHairColor(String hairColor) {		this.hairColor = hairColor;	}	@Basic	@Column(name = "eye_color")	public String getEyeColor() {		return eyeColor;	}	public void setEyeColor(String eyeColor) {		this.eyeColor = eyeColor;	}	@Basic	@Column(name = "complexion")	public String getComplexion() {		return complexion;	}	public void setComplexion(String complexion) {		this.complexion = complexion;	}	@Basic	@NotNull	@Column(name = "identifying_marks")	public String getIdentifyingMarks() {		return identifyingMarks;	}	public void setIdentifyingMarks(String identifyingMarks) {		this.identifyingMarks = identifyingMarks;	}	@Basic	@NotNull	@Column(name = "dialects_spoken")	public String getDialectsSpoken() {		return dialectsSpoken;	}	public void setDialectsSpoken(String dialectsSpoken) {		this.dialectsSpoken = dialectsSpoken;	}	@Basic	@NotNull	@Column(name = "religion")	public String getReligion() {		return religion;	}	public void setReligion(String religion) {		this.religion = religion;	}	@Basic	@Column(name = "name_of_spouse")	public String getNameOfSpouse() {		return nameOfSpouse;	}	public void setNameOfSpouse(String nameOfSpouse) {		this.nameOfSpouse = nameOfSpouse;	}	@Basic	@Column(name = "occupation_of_spouse")	public String getOccupationOfSpouse() {		return occupationOfSpouse;	}	public void setOccupationOfSpouse(String occupationOfSpouse) {		this.occupationOfSpouse = occupationOfSpouse;	}	@Basic	@NotNull	@Column(name = "name_of_father")	public String getNameOfFather() {		return nameOfFather;	}	public void setNameOfFather(String nameOfFather) {		this.nameOfFather = nameOfFather;	}	@Basic	@NotNull	@Column(name = "occupation_of_father")	public String getOccupationOfFather() {		return occupationOfFather;	}	public void setOccupationOfFather(String occupationOfFather) {		this.occupationOfFather = occupationOfFather;	}	@Basic	@NotNull	@Column(name = "name_of_mother")	public String getNameOfMother() {		return nameOfMother;	}	public void setNameOfMother(String nameOfMother) {		this.nameOfMother = nameOfMother;	}	@Basic	@NotNull	@Column(name = "occupation_of_mother")	public String getOccupationOfMother() {		return occupationOfMother;	}	public void setOccupationOfMother(String occupationOfMother) {		this.occupationOfMother = occupationOfMother;	}	@Basic	@NotNull	@Column(name = "emergency_contact_person")	public String getEmergencyContactPerson() {		return emergencyContactPerson;	}	public void setEmergencyContactPerson(String emergencyContactPerson) {		this.emergencyContactPerson = emergencyContactPerson;	}	@Basic	@NotNull	@Column(name = "contact_person_relation")	public String getContactPersonRelation() {		return contactPersonRelation;	}	public void setContactPersonRelation(String contactPersonRelation) {		this.contactPersonRelation = contactPersonRelation;	}	@Basic	@NotNull	@Column(name = "contact_person_address")	public String getContactPersonAddress() {		return contactPersonAddress;	}	public void setContactPersonAddress(String contactPersonAddress) {		this.contactPersonAddress = contactPersonAddress;	}	@Basic	@NotNull	@Column(name = "contact_person_number")	public String getContactPersonNumber() {		return contactPersonNumber;	}	public void setContactPersonNumber(String contactPersonNumber) {		this.contactPersonNumber = contactPersonNumber;	}	@Basic	@NotNull	@Column(name = "recommended_by")	public String getRecommendedBy() {		return recommendedBy;	}	public void setRecommendedBy(String recommendedBy) {		this.recommendedBy = recommendedBy;	}	@Basic	@NotNull	@Column(name = "high_school")	public String getHighSchool() {		return highSchool;	}	public void setHighSchool(String highSchool) {		this.highSchool = highSchool;	}	@Basic	@NotNull	@Column(name = "high_school_grad_year")	public String getHighSchoolGradYear() {		return highSchoolGradYear;	}	public void setHighSchoolGradYear(String highSchoolGradYear) {		this.highSchoolGradYear = highSchoolGradYear;	}	@Basic	@Column(name = "vocational_school")	public String getVocationalSchool() {		return vocationalSchool;	}	public void setVocationalSchool(String vocationalSchool) {		this.vocationalSchool = vocationalSchool;	}	@Basic	@Column(name = "vocational_school_grad_year")	public String getVocationalSchoolGradYear() {		return vocationalSchoolGradYear;	}	public void setVocationalSchoolGradYear(String vocationalSchoolGradYear) {		this.vocationalSchoolGradYear = vocationalSchoolGradYear;	}	@Basic	@NotNull	@Column(name = "college")	public String getCollege() {		return college;	}	public void setCollege(String college) {		this.college = college;	}	@Basic	@NotNull	@Column(name = "college_degree")	public String getCollegeDegree() {		return collegeDegree;	}	public void setCollegeDegree(String collegeDegree) {		this.collegeDegree = collegeDegree;	}	@Basic	@NotNull	@Column(name = "college_grad_year")	public String getCollegeGradYear() {		return collegeGradYear;	}	public void setCollegeGradYear(String collegeGradYear) {		this.collegeGradYear = collegeGradYear;	}	@Basic	@NotNull	@Column(name = "last_security_school")	public String getLastSecuritySchool() {		return lastSecuritySchool;	}	public void setLastSecuritySchool(String lastSecuritySchool) {		this.lastSecuritySchool = lastSecuritySchool;	}	@Basic	@Column(name = "organization")	public String getOrganization() {		return organization;	}	public void setOrganization(String organization) {		this.organization = organization;	}	@Basic	@NotNull	@Column(name = "special_skills")	public String getSpecialSkills() {		return specialSkills;	}	public void setSpecialSkills(String specialSkills) {		this.specialSkills = specialSkills;	}	@Basic	@NotNull	@Column(name = "hobbies")	public String getHobbies() {		return hobbies;	}	public void setHobbies(String hobbies) {		this.hobbies = hobbies;	}}