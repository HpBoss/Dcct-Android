package com.example.dcct.bean

data class BackResultData<T>(val isState: Boolean, val data: T, val msg: String, val resultCode: Any)