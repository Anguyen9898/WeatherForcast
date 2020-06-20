package com.anguyen.weatherforecast_demo.commons

import android.content.Context
import com.kaopiz.kprogresshud.KProgressHUD

fun initProgressDialog(from: Context) = KProgressHUD.create(from)
    .setCancellable(true)
    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
    .setLabel("Please wait")
    .setDimAmount(0.5f)!!