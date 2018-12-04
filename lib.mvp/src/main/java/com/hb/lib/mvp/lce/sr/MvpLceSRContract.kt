package com.hb.lib.mvp.lce.sr

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView

interface MvpLceSRContract {
    interface View {

        fun setupRecylcerView(addItemDecoration : Boolean)

        fun getSwipeRefreshLayout(): SwipeRefreshLayout

        fun getRecyclerView(): RecyclerView

        fun <BA : RecyclerView.Adapter<*>> getAdapter(): BA
    }

    interface Presenter {

    }
}