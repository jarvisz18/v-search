package cn.ixan.search.domain;

import io.searchbox.annotations.JestId;

/**
 * 学生实体类
 * @author stack_zhang@outlook.com
 */
public class User {
    /**
     * 学号
     */
    @JestId
    private Integer studentId;
    /**
     * 学院
     */
    private String college;
    /**
     * 姓名
     */
    private String name;

    private String idCard;

    private String sex;

    private String education;

    private String profession;

    private String teacherFlag;

    private String normalStudentCategory;

    private String difficultStudentCategory;

    private String trainMethod;

    private String schoolSystem;

    private String nationality;

    private String politicalStatus;

    private String birthday;

    private String admissionTime;

    private String graduationTime;

    private String bachelorOfScience;

    private String direction;

    private String studentArea;

    private String isCountryArea;

    private String address;

    private String postcode;

    private String candidateNumber;

    private String mobile;

    private String mailbox;

    private String qqNumber;

    private String phone;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college == null ? null : college.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession == null ? null : profession.trim();
    }

    public String getTeacherFlag() {
        return teacherFlag;
    }

    public void setTeacherFlag(String teacherFlag) {
        this.teacherFlag = teacherFlag == null ? null : teacherFlag.trim();
    }

    public String getNormalStudentCategory() {
        return normalStudentCategory;
    }

    public void setNormalStudentCategory(String normalStudentCategory) {
        this.normalStudentCategory = normalStudentCategory == null ? null : normalStudentCategory.trim();
    }

    public String getDifficultStudentCategory() {
        return difficultStudentCategory;
    }

    public void setDifficultStudentCategory(String difficultStudentCategory) {
        this.difficultStudentCategory = difficultStudentCategory == null ? null : difficultStudentCategory.trim();
    }

    public String getTrainMethod() {
        return trainMethod;
    }

    public void setTrainMethod(String trainMethod) {
        this.trainMethod = trainMethod == null ? null : trainMethod.trim();
    }

    public String getSchoolSystem() {
        return schoolSystem;
    }

    public void setSchoolSystem(String schoolSystem) {
        this.schoolSystem = schoolSystem == null ? null : schoolSystem.trim();
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality == null ? null : nationality.trim();
    }

    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus == null ? null : politicalStatus.trim();
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    public String getAdmissionTime() {
        return admissionTime;
    }

    public void setAdmissionTime(String admissionTime) {
        this.admissionTime = admissionTime == null ? null : admissionTime.trim();
    }

    public String getGraduationTime() {
        return graduationTime;
    }

    public void setGraduationTime(String graduationTime) {
        this.graduationTime = graduationTime == null ? null : graduationTime.trim();
    }

    public String getBachelorOfScience() {
        return bachelorOfScience;
    }

    public void setBachelorOfScience(String bachelorOfScience) {
        this.bachelorOfScience = bachelorOfScience == null ? null : bachelorOfScience.trim();
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction == null ? null : direction.trim();
    }

    public String getStudentArea() {
        return studentArea;
    }

    public void setStudentArea(String studentArea) {
        this.studentArea = studentArea == null ? null : studentArea.trim();
    }

    public String getIsCountryArea() {
        return isCountryArea;
    }

    public void setIsCountryArea(String isCountryArea) {
        this.isCountryArea = isCountryArea == null ? null : isCountryArea.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }

    public String getCandidateNumber() {
        return candidateNumber;
    }

    public void setCandidateNumber(String candidateNumber) {
        this.candidateNumber = candidateNumber == null ? null : candidateNumber.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox == null ? null : mailbox.trim();
    }

    public String getQqNumber() {
        return qqNumber;
    }

    public void setQqNumber(String qqNumber) {
        this.qqNumber = qqNumber == null ? null : qqNumber.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }
}