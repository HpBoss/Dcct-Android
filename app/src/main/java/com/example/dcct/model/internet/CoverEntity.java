package com.example.dcct.model.internet;

public class CoverEntity {

    /**
     * imageUrl : http://192.168.1.48:8080/img/022020220.png
     * pid : 1
     * describes : 想来想去
     */

    private String imageUrl;
    private int pid;
    private String describes;

    @Override
    public String toString() {
        return "CoverEntity{" +
                "imageUrl='" + imageUrl + '\'' +
                ", pid=" + pid +
                ", describes='" + describes + '\'' +
                '}';
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }
}
