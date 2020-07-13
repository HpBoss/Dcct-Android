package com.example.dcct.bean

class QueryResultEntity {
    /**
     * mid : 7
     * drugCodeEntity : {"id":7,"codeOne":"DB13182","codeTwo":"DB11132"}
     * drugNameEntity : {"id":7,"drugOne":"粉葛","drugTwo":"白石英"}
     * result : 当白石英与粉葛组合使用时，可能会增加不良反应的风险或严重性。
     * score : 0.824
     */
    var mid = 0
    var drugCodeEntity: DrugCodeEntityBean? = null
    var drugNameEntity: DrugNameEntityBean? = null
    var result: String? = null
    var score = 0.0
    override fun toString(): String {
        return "QueryResultEntity{" +
                "mid=" + mid +
                ", drugCodeEntity=" + drugCodeEntity +
                ", drugNameEntity=" + drugNameEntity +
                ", result='" + result + '\'' +
                ", score=" + score +
                '}'
    }

    class DrugCodeEntityBean {
        /**
         * id : 7
         * codeOne : DB13182
         * codeTwo : DB11132
         */
        var id = 0
        var codeOne: String? = null
        var codeTwo: String? = null
        override fun toString(): String {
            return "DrugCodeEntityBean{" +
                    "id=" + id +
                    ", codeOne='" + codeOne + '\'' +
                    ", codeTwo='" + codeTwo + '\'' +
                    '}'
        }

    }

    class DrugNameEntityBean {
        /**
         * id : 7
         * drugOne : 粉葛
         * drugTwo : 白石英
         */
        var id = 0
        var drugOne: String? = null
        var drugTwo: String? = null
        override fun toString(): String {
            return "DrugNameEntityBean{" +
                    "id=" + id +
                    ", drugOne='" + drugOne + '\'' +
                    ", drugTwo='" + drugTwo + '\'' +
                    '}'
        }

    }
}