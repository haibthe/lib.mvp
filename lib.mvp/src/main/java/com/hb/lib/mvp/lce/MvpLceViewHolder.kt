package com.hb.lib.mvp.lce

import android.text.Html
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.hb.lib.mvp.R


/**
 * Created by haibt3 on 12/19/2016.
 */

class MvpLceViewHolder<CV : View>(val view: View, listenerErrorView: View.OnClickListener) {

    lateinit var contentView: CV
    lateinit var loadingView: View
    lateinit var errorView: View
    lateinit var errorMessageView: TextView

    init {
        initAllViews(listenerErrorView)
    }

    private fun initAllViews(listener: View.OnClickListener) {
        contentView = findView(R.id.contentView)
        loadingView = findView(R.id.loadingView)
        errorView = findView(R.id.errorView)
        errorMessageView = findView(R.id.errorMessage)

        if (loadingView == null) {
            throw NullPointerException(
                    "Loading view is null! Have you specified a loading view in your layout xml file?" + " You have to give your loading View the id R.id.loadingView")
        }

        if (contentView == null) {
            throw NullPointerException(
                    "Content view is null! Have you specified a content view in your layout xml file?" + " You have to give your content View the id R.id.contentView")
        }

        if (errorView == null) {
            throw NullPointerException(
                    "Error view is null! Have you specified a content view in your layout xml file?" + " You have to give your error View the id R.id.errorView")
        }

        errorView.setOnClickListener(listener)
    }

    fun setTextColor(color: Int) {
        errorMessageView.setTextColor(color)

        val vg = loadingView as ViewGroup?
        val n = vg!!.childCount
        for (i in 0 until n) {
            val v = vg.getChildAt(i)
            if (v is TextView) {
                v.setTextColor(color)
            }
        }
    }


    fun animateLoadingViewIn() {
        LceAnimator.showLoading(loadingView, contentView, errorView)
    }

    fun animateContentViewIn() {
        LceAnimator.showContent(loadingView, contentView, errorView)
    }

    fun animateErrorViewIn() {
        LceAnimator.showErrorView(loadingView, contentView, errorView)
    }

    fun setError(msg: String) {
        errorMessageView.text = Html.fromHtml(msg)
    }

    fun showLightError(msg: String) {
        Toast.makeText(view.context, msg, Toast.LENGTH_LONG).show()
    }

    fun <T : View> findView(viewId: Int): T {
        return findView(view, viewId)
    }

    fun <T : View> findView(view: View, viewId: Int): T {
        return view.findViewById(viewId) as T
    }
}
