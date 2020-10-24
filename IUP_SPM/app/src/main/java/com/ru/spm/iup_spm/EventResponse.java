package com.ru.spm.iup_spm;

public class EventResponse {
    private String Name;
    private int MaxPeople;
    private String Host;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getMaxPeople() {
        return MaxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        MaxPeople = maxPeople;
    }

    public String getHost() {
        return Host;
    }

    public void setHost(String host) {
        Host = host;
    }
}
