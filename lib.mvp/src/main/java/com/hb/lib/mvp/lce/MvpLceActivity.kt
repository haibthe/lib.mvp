package com.hb.lib.mvp.lce

import android.os.Bundle
import android.view.View
import com.hb.lib.mvp.R
import com.hb.lib.mvp.base.MvpActivity

abstract class MvpLceActivity<CV : View, M, P : MvpLceContract.Presenter> : MvpActivity<P>(), MvpLceContract.View<M> {


    abstract fun getErrorMessage(e: Throwable, pullToRefresh: Boolean): String

    protected lateinit var mLceViewHolder: MvpLceViewHolder<CV>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLceViewHolder = MvpLceViewHolder(getView(), View.OnClickListener {
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
