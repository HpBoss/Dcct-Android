package com.example.dcct.model.internet.model;

import java.io.Serializable;

public class ReportSerializable implements Serializable {

    /**
     * drugOne : 粉葛
     * drugTwo : 白石英
     * result : 当白石英与粉葛组合使用时，可能会增加不良反应的风险或严重性。
     * score : 0.824
     */

    private String drugOne1;
    private String drugOne2;
    private String drugTwo1;
    private String drugTwo2;
    private String result1;
    private String result2;
    private double score1;
    private double score2;

    public ReportSerializable(String drugOne1, String drugOne2, String drugTwo1, String drugTwo2, String result1, String result2, double score1, double score2) {
        this.drugOne1 = drugOne1;
        this.drugOne2 = drugOne2;
        this.drugTwo1 = drugTwo1;
        this.drugTwo2 = drugTwo2;
        this.result1 = result1;
        this.result2 = result2;
        this.score1 = score1;
        this.score2 = score2;
    }

    public String getDrugOne1() {
        return drugOne1;
    }

    public String getDrugOne2() {
        return drugOne2;
    }

    public String getDrugTwo1() {
        return drugTwo1;
    }

    public String getDrugTwo2() {
        return drugTwo2;
    }

    public String getResult1() {
        return result1;
    }

    public String getResult2() {
        return result2;
    }

    public double getScore1() {
        return score1;
    }

    public double getScore2() {
        return score2;
    }
}
