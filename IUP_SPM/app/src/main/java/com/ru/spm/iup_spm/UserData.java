package com.ru.spm.iup_spm;

public class UserData {
    private String name;
    private String surname;
    private String birthday;
    private String image;
    private String bio;
    private float averageReview;

    public UserData(String name, String surname, String birthday, String image, String bio, float averageReview) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.image = image;
        this.bio = bio;
        this.averageReview = averageReview;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public float getAverageReview() {
        return averageReview;
    }

    public void setAverageReview(float averageReview) {
        this.averageReview = averageReview;
    }
}
