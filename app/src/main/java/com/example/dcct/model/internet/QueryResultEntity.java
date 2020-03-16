package com.example.dcct.model.internet;

public class QueryResultEntity  {

    /**
     * mid : 7
     * drugCodeEntity : {"id":7,"codeOne":"DB13182","codeTwo":"DB11132"}
     * drugNameEntity : {"id":7,"drugOne":"粉葛","drugTwo":"白石英"}
     * result : 当白石英与粉葛组合使用时，可能会增加不良反应的风险或严重性。
     * score : 0.824
     */

    private int mid;
    private DrugCodeEntityBean drugCodeEntity;
    private DrugNameEntityBean drugNameEntity;
    private String result;
    private double score;

    @Override
    public String toString() {
        return "QueryResultEntity{" +
                "mid=" + mid +
                ", drugCodeEntity=" + drugCodeEntity +
                ", drugNameEntity=" + drugNameEntity +
                ", result='" + result + '\'' +
                ", score=" + score +
                '}';
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public DrugCodeEntityBean getDrugCodeEntity() {
        return drugCodeEntity;
    }

    public void setDrugCodeEntity(DrugCodeEntityBean drugCodeEntity) {
        this.drugCodeEntity = drugCodeEntity;
    }

    public DrugNameEntityBean getDrugNameEntity() {
        return drugNameEntity;
    }

    public void setDrugNameEntity(DrugNameEntityBean drugNameEntity) {
        this.drugNameEntity = drugNameEntity;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public static class DrugCodeEntityBean {
        /**
         * id : 7
         * codeOne : DB13182
         * codeTwo : DB11132
         */

        private int id;
        private String codeOne;
        private String codeTwo;

        @Override
        public String toString() {
            return "DrugCodeEntityBean{" +
                    "id=" + id +
                    ", codeOne='" + codeOne + '\'' +
                    ", codeTwo='" + codeTwo + '\'' +
                    '}';
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCodeOne() {
            return codeOne;
        }

        public void setCodeOne(String codeOne) {
            this.codeOne = codeOne;
        }

        public String getCodeTwo() {
            return codeTwo;
        }

        public void setCodeTwo(String codeTwo) {
            this.codeTwo = codeTwo;
        }
    }

    public static class DrugNameEntityBean {
        /**
         * id : 7
         * drugOne : 粉葛
         * drugTwo : 白石英
         */

        private int id;
        private String drugOne;
        private String drugTwo;

        @Override
        public String toString() {
            return "DrugNameEntityBean{" +
                    "id=" + id +
                    ", drugOne='" + drugOne + '\'' +
                    ", drugTwo='" + drugTwo + '\'' +
                    '}';
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDrugOne() {
            return drugOne;
        }

        public void setDrugOne(String drugOne) {
            this.drugOne = drugOne;
        }

        public String getDrugTwo() {
            return drugTwo;
        }

        public void setDrugTwo(String drugTwo) {
            this.drugTwo = drugTwo;
        }
    }
}
