package com.example.dcct.model.internet;

import android.os.Parcel;
import android.os.Parcelable;

public class ReportParcelable implements Parcelable {

    /**
     * drugOne : 粉葛
     * drugTwo : 白石英
     * result : 当白石英与粉葛组合使用时，可能会增加不良反应的风险或严重性。
     * score : 0.824
     */

    private String drugOne1;
    private String drugTwo1;
    private String result1;
    private String result2;
    private double score1;
    private double score2;

    public ReportParcelable(String drugOne1, String drugTwo1, String result1, String result2,
                            double score1, double score2) {
        this.drugOne1 = drugOne1;
        this.drugTwo1 = drugTwo1;
        this.result1 = result1;
        this.result2 = result2;
        this.score1 = score1;
        this.score2 = score2;
    }

    public String getDrugOne1() {
        return drugOne1;
    }

    public String getDrugTwo1() {
        return drugTwo1;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString( drugOne1 );
        dest.writeString( drugTwo1 );
        dest.writeString( result1 );
        dest.writeString( result2 );
        dest.writeDouble( score1 );
        dest.writeDouble( score2 );
    }

    public static final Parcelable.Creator<ReportParcelable> CREATOR = new Parcelable.Creator<ReportParcelable>(){

        @Override
        public ReportParcelable createFromParcel(Parcel source) {
            return new ReportParcelable( source.readString(), source.readString(),source.readString(),
                    source.readString(),source.readDouble(),source.readDouble());
        }

        @Override
        public ReportParcelable[] newArray(int size) {
            return new ReportParcelable[0];
        }
    };
}
