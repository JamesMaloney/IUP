package com.ru.spm.iup_spm;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    private String kennitala;
    private String token;
    private  String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKennitala() {
        return kennitala;
    }

    public void setKennitala(String kennitala) {
        kennitala = kennitala;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
