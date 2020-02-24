package com.example.dcct.base;

public interface RegisterAndUnRegister<T> {

    void registerCallBack(T t);

    void unregisterCallBack(T t);
}
