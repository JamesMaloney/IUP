package com.ru.spm.iup_spm;

import java.util.Date;

public class RegisterRequest {

    private String Kennitala;
    private String Name;
    private String Surname;
    private String Birthday;
    private String Password;

    public String getKennitala() {
        return Kennitala;
    }

    public void setKennitala(String Kennitala) {
        this.Kennitala = Kennitala;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        this.Surname = surname;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        this.Birthday = birthday;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }
}
