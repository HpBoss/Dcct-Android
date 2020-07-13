package com.example.dcct.bean

class PostQueryEntity(
        /**
         * drugOne : 粉葛
         * drugTwo : 白石英
         */
        private val drugOne: String, private val drugTwo: String, private val uid: Long) {
    override fun toString(): String {
        return "PostQueryEntity{" +
                "drugOne='" + drugOne + '\'' +
                ", drugTwo='" + drugTwo + '\'' +
                ", uid=" + uid +
                '}'
    }

}