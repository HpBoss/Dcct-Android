package com.example.dcct.model.internet;

public class PostQueryEntity {

    /**
     * drugOne : 粉葛
     * drugTwo : 白石英
     */

    private String drugOne;
    private String drugTwo;
    private long uid;

    public PostQueryEntity(String drugOne, String drugTwo,long uid) {
        this.drugOne = drugOne;
        this.drugTwo = drugTwo;
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "PostQueryEntity{" +
                "drugOne='" + drugOne + '\'' +
                ", drugTwo='" + drugTwo + '\'' +
                ", uid=" + uid +
                '}';
    }

}
