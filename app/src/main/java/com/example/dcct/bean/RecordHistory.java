package com.example.dcct.bean;

public class RecordHistory {
    private int gaugingKind;
    private String[] thingName;
    private String gaugingTime;

    public RecordHistory() { }

    public RecordHistory(int gaugingKind, String[] thingName, String gaugingTime) {
        this.gaugingKind = gaugingKind;
        this.thingName = thingName;
        this.gaugingTime = gaugingTime;
    }

    public void setGaugingKind(int gaugingKind) {
        this.gaugingKind = gaugingKind;
    }

    public void setThingName(String[] thingName) {
        this.thingName = thingName;
    }

    public void setGaugingTime(String gaugingTime) {
        this.gaugingTime = gaugingTime;
    }

    public int getGaugingKind() {
        return gaugingKind;
    }

    public String[] getThingName() {
        return thingName;
    }

    public String getGaugingTime() {
        return gaugingTime;
    }
}
