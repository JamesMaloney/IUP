package com.ru.spm.iup_spm;


//Events for Arraylist in Home
public class Event {

    private String image;
    private String name;
    private double distance;
    private String hostName;
    private int maxPeople;
    private int participants;
    private String imgHostEvent;
    private boolean hidden;

    public Event(String image, String name, double distance, String hostName, int maxPeople, int participants, String imgHostEvent, boolean hidden) {
        this.image = image;
        this.name = name;
        this.distance = distance;
        this.hostName = hostName;
        this.maxPeople = maxPeople;
        this.participants = participants;
        this.imgHostEvent = imgHostEvent;
        this.hidden = hidden;
    }

    public Event(String image, String name, int maxPeople, int participants) {
        this.image = image;
        this.name = name;
        this.distance = 0;
        this.hostName = " ";
        this.maxPeople = maxPeople;
        this.participants = participants;
        this.imgHostEvent = " ";
        this.hidden = false;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public boolean isHidden() {
        return hidden;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public String getImgHostEvent() {
        return imgHostEvent;
    }

    public void setImgHostEvent(String imgHostEvent) {
        this.imgHostEvent = imgHostEvent;
    }

    public boolean getHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}
