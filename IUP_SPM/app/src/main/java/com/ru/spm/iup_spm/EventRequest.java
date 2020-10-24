package com.ru.spm.iup_spm;

public class EventRequest {
    private String Name;
    private String Description;
    private String DateStart;
    private String DateEnd;
    private int MaxPeople;
    private double Latitude;
    private double Longitude;
    private String Host;
    private String Image;
    private String HostName;

    /*TODO from position to langitude and latitude*/
    public void setPosition(String s){
        setLatitude(0.132343);
        setLongitude(1.232323);
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDateStart() {
        return DateStart;
    }

    public void setDateStart(String dateStart) {
        DateStart = dateStart;
    }

    public String getDateEnd() {
        return DateEnd;
    }

    public void setDateEnd(String dateEnd) {
        DateEnd = dateEnd;
    }

    public int getMaxPeople() {
        return MaxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        MaxPeople = maxPeople;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public String getHost() {
        return Host;
    }

    public void setHost(String host) {
        Host = host;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getHostName() {
        return HostName;
    }

    public void setHostName(String hostName) {
        HostName = hostName;
    }
}
