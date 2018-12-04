package com.hb.lib.mvp.lce

import com.hb.lib.mvp.base.MvpContract

interface MvpLceContract {

    interface View<M> : MvpContract.View {
        fun showLoading(pullToRefresh: Boolean)

        fun showContent()

        fun showError(e: Throwable, pullToRefresh: Boolean)

        fun setData(data: M)

        fun loadData(pullToRefresh: Boolean)
    }

    interface Presenter : MvpContract.Presenter {
        fun loadData(pullToRefresh: Boolean)
    }
}