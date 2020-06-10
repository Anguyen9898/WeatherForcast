package com.anguyen.weatherforecast_demo.commons

import android.content.Context
import android.widget.Toast

fun showToast(from: Context, messageId: Int) = Toast.makeText(from
    , from.getString(messageId)
    , Toast.LENGTH_LONG).show()

fun showToast(from: Context, message: String) = Toast.makeText(from
    , message, Toast.LENGTH_LONG).show()
