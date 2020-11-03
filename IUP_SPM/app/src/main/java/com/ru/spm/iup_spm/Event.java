package com.ru.spm.iup_spm;

//Events for Arraylist
public class Event  {

    private String eventID;
    private String image;
    private String name;
    private double distance;
    private String hostName;
    private int maxPeople;
    private int participants;
    private String imgHostEvent;
    private boolean hidden;
    private String dateStart;
    private String dateEnd;
    private String description;

    public Event(String eventID, String image, String name, double distance, String hostName, int maxPeople, int participants, String imgHostEvent, String dateStart, String dateEnd, String description) {
        this.eventID = eventID;
        this.image = image;
        this.name = name;
        this.distance = distance;
        this.hostName = hostName;
        this.maxPeople = maxPeople;
        this.participants = participants;
        this.imgHostEvent = imgHostEvent;
        this.hidden = false;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.description = description;
    }

    public Event(String eventID, String image, String name, double distance, String hostName, int maxPeople, int participants, String imgHostEvent, boolean hidden) {
        this.eventID = eventID;
        this.image = image;
        this.name = name;
        this.distance = distance;
        this.hostName = hostName;
        this.maxPeople = maxPeople;
        this.participants = participants;
        this.imgHostEvent = imgHostEvent;
        this.hidden = hidden;
        this.dateEnd=" ";
        this.dateStart=" ";
        this.description=" ";
    }

    public Event(String eventID, String image, String name, int maxPeople, int participants) {
        this.eventID = eventID;
        this.image = image;
        this.name = name;
        this.distance = 0;
        this.hostName = " ";
        this.maxPeople = maxPeople;
        this.participants = participants;
        this.imgHostEvent = " ";
        this.hidden = false;
        this.dateEnd=" ";
        this.dateStart=" ";
        this.description=" ";
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
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

    @Override
    public String toString() {
        return "Event{" +
                "eventID='" + eventID + '\'' +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", distance=" + distance +
                ", hostName='" + hostName + '\'' +
                ", maxPeople=" + maxPeople +
                ", participants=" + participants +
                ", imgHostEvent='" + imgHostEvent + '\'' +
                ", hidden=" + hidden +
                ", dateStart='" + dateStart + '\'' +
                ", dateEnd='" + dateEnd + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
