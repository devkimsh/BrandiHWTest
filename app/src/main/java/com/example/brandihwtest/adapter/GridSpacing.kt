package com.example.brandihwtest.adapter

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brandihwtest.util.CommonUtils
import io.reactivex.Observable
import java.util.*

class GridSpacing(val context: Context, val fSpacing:Float) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        Observable.just(outRect)
            .subscribe { outRect.right = fSpacing.toInt()
                outRect.left = fSpacing.toInt()
                outRect.top = fSpacing.toInt()
                outRect.bottom = fSpacing.toInt() }
    }
}