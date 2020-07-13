package com.example.dcct.bean

import android.os.Parcel
import android.os.Parcelable

class ReportParcelable(
        /**
         * drugOne : 粉葛
         * drugTwo : 白石英
         * result : 当白石英与粉葛组合使用时，可能会增加不良反应的风险或严重性。
         * score : 0.824
         */
        val drugOne1: String?, val drugTwo1: String?, val result1: String?, val result2: String?,
        val score1: Double, val score2: Double) : Parcelable {

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(drugOne1)
        dest.writeString(drugTwo1)
        dest.writeString(result1)
        dest.writeString(result2)
        dest.writeDouble(score1)
        dest.writeDouble(score2)
    }

    companion object {
        val CREATOR: Parcelable.Creator<ReportParcelable?> = object : Parcelable.Creator<ReportParcelable?> {
            override fun createFromParcel(source: Parcel): ReportParcelable? {
                return ReportParcelable(source.readString(), source.readString(), source.readString(),
                        source.readString(), source.readDouble(), source.readDouble())
            }

            override fun newArray(size: Int): Array<ReportParcelable?> {
                return arrayOfNulls(0)
            }
        }
    }

}