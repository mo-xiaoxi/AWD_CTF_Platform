package com.bupt.domain;

import java.io.Serializable;

public class Profile implements Serializable {
    public int id;
    public int user_id;
    public String name;
    public String gender;
    public String birthday;
    public String province;
    public String nation;
    public String politics_status;
    public String IDnumber;
    public String type;
    public String school;
    public String grade;
    public String address;
    public String postcode;
    public String phone;
    public String height;
    public String weight;
    public String mail;
    public String father_name;
    public String father_job;
    public String father_phone;
    public String mother_name;
    public String mother_job;
    public String mother_phone;
    public String hobby;
    public String description;
    public String photo;

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                ", province='" + province + '\'' +
                ", nation='" + nation + '\'' +
                ", politics_status='" + politics_status + '\'' +
                ", type='" + type + '\'' +
                ", school='" + school + '\'' +
                ", grade='" + grade + '\'' +
                ", address='" + address + '\'' +
                ", postcode='" + postcode + '\'' +
                ", phone='" + phone + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", mail='" + mail + '\'' +
                ", father_name='" + father_name + '\'' +
                ", father_job='" + father_job + '\'' +
                ", father_phone='" + father_phone + '\'' +
                ", mother_name='" + mother_name + '\'' +
                ", mother_job='" + mother_job + '\'' +
                ", mother_phone='" + mother_phone + '\'' +
                ", hobby='" + hobby + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getIDnumber() {
        return IDnumber;
    }

    public void setIDnumber(String IDnumber) {
        this.IDnumber = IDnumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getPolitics_status() {
        return politics_status;
    }

    public void setPolitics_status(String politics_status) {
        this.politics_status = politics_status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public String getFather_job() {
        return father_job;
    }

    public void setFather_job(String father_job) {
        this.father_job = father_job;
    }

    public String getFather_phone() {
        return father_phone;
    }

    public void setFather_phone(String father_phone) {
        this.father_phone = father_phone;
    }

    public String getMother_name() {
        return mother_name;
    }

    public void setMother_name(String mother_name) {
        this.mother_name = mother_name;
    }

    public String getMother_job() {
        return mother_job;
    }

    public void setMother_job(String mother_job) {
        this.mother_job = mother_job;
    }

    public String getMother_phone() {
        return mother_phone;
    }

    public void setMother_phone(String mother_phone) {
        this.mother_phone = mother_phone;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
