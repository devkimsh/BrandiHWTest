package com.example.brandihwtest.util

import android.content.Context

class CommonUtils {

    companion object{
        fun pxToDp(context: Context, fPx:Float):Float{
            val resources = context.resources
            val metrics = resources.displayMetrics
            return fPx / (metrics.densityDpi / 160f)
        }
    }
}