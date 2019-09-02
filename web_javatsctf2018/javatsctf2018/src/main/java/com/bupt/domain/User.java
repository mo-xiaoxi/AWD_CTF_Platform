package com.bupt.domain;

import java.io.Serializable;

/**
 */
public class User implements Serializable{
    public int id;
    public String username;
    public String password;
    public String email;
    public String salt;
    public String head_url;

    public int score;

    public String checkcode;
    public String time;
    public int first;
    public String pdd;

    public static final int UNSUBMITTED = 0;
    public static final int SUBMITTED = 1;
    public static final int ADMIN = 2;

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public String getCheckcode() {
        return checkcode;
    }

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHead_url() {
        return head_url;
    }

    public void setHead_url(String head_url) {
        this.head_url = head_url;
    }

    public String getPdd() {
        return pdd;
    }

    public void setPdd(String pdd) {
        this.pdd = pdd;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", salt='" + salt + '\'' +
                ", head_url='" + head_url + '\'' +
                ", score=" + score +
                ", checkcode='" + checkcode + '\'' +
                ", time='" + time + '\'' +
                ", first=" + first +
                ", pdd='" + pdd + '\'' +
                '}';
    }
}
