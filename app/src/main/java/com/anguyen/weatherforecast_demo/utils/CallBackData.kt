package com.anguyen.weatherforecast_demo.utils

interface CallBackData<T> {
    fun onSuccess(t: T)
    fun onFail(message: String?)
}