package com.hb.lib.mvp.lce

import android.os.Bundle
import android.view.View
import com.hb.lib.mvp.R
import com.hb.lib.mvp.base.MvpFragment

abstract class MvpLceFragment<CV : View, M, P: MvpLceContract.Presenter> : MvpFragment<P>(), MvpLceContract.View<M> {


    protected abstract fun getErrorMessage(e: Throwable, pullToRefresh: Boolean): String

    protected lateinit var mLceViewHolder: MvpLceViewHolder<CV>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mLceViewHolder = MvpLceViewHolder(view, View.OnClickListener {
            onErrorViewClicked()
        })
    }

    private fun onErrorViewClicked() {
        loadData(false)
    }

    override fun showLoading(pullToRefresh: Boolean) {
        if (!pullToRefresh) {
            mLceViewHolder.animateLoadingViewIn()
        }
    }

    override fun showContent() {
        mLceViewHolder.animateContentViewIn()
    }


    override fun showError(e: Throwable, pullToRefresh: Boolean) {

        val errorMsg = getErrorMessage(e, pullToRefresh)

        if (pullToRefresh) {
            mLceViewHolder.showLightError(errorMsg)
        } else {
            val msg = getString(R.string.error_message, errorMsg)
            mLceViewHolder.setError(msg)
            mLceViewHolder.animateErrorViewIn()
        }
    }
}