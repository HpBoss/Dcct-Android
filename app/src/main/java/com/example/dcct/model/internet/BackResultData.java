package com.example.dcct.model.internet;

public class BackResultData<T> {

    /**
     * state : true
     * data : {"uid":1,"password":null,"nickname":"joson","email":"193220847@qq.com"}
     * msg : 登录成功
     * errCode : null
     */

    @Override
    public String toString() {
        return "BackResultData{" +
                "state=" + state +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                ", errCode=" + errCode +
                '}';
    }

    private boolean state;
    private T data;
    private String msg;
    private Object errCode;

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getErrCode() {
        return errCode;
    }

    public void setErrCode(Object errCode) {
        this.errCode = errCode;
    }

}
