package com.ru.spm.iup_spm;


//Events for Arraylist in Home
public class Event {
    private int imgEvent;
    private String nameEvent;
    private String distanceEvent;
    private String nameHostEvent;
    private int imgHostEvent;

    public Event(int imgEvent, String nameEvent, String distanceEvent, String nameHostEvent, int imgHostEvent) {
        this.imgEvent = imgEvent;
        this.nameEvent = nameEvent;
        this.distanceEvent = distanceEvent;
        this.nameHostEvent = nameHostEvent;
        this.imgHostEvent = imgHostEvent;
    }


    public int getImgEvent() {
        return imgEvent;
    }

    public void setImgEvent(int imgEvent) {
        this.imgEvent = imgEvent;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public String getDistanceEvent() {
        return distanceEvent;
    }

    public void setDistanceEvent(String distanceEvent) {
        this.distanceEvent = distanceEvent;
    }

    public String getNameHostEvent() {
        return nameHostEvent;
    }

    public void setNameHostEvent(String nameHostEvent) {
        this.nameHostEvent = nameHostEvent;
    }

    public int getImgHostEvent() {
        return imgHostEvent;
    }

    public void setImgHostEvent(int imgHostEvent) {
        this.imgHostEvent = imgHostEvent;
    }
}
