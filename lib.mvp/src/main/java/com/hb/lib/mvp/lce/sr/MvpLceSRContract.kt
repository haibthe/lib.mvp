package com.hb.lib.mvp.lce.sr

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.recyclerview.widget.RecyclerView

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