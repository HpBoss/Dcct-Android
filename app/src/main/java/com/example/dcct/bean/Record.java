package com.example.dcct.bean;

public class Record {
    private String queryName;
    private String queryTime;

    public Record(String queryName, String queryTime) {
        this.queryName = queryName;
        this.queryTime = queryTime;
    }

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public String getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(String queryTime) {
        this.queryTime = queryTime;
    }
}
