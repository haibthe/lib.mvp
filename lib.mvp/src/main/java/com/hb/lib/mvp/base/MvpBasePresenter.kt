package com.hb.lib.mvp.base

import java.lang.ref.WeakReference


open class MvpBasePresenter<V : MvpContract.View> : MvpContract.Presenter {


    private var viewRef: WeakReference<V>? = null

    @Suppress("UNCHECKED_CAST")
    override fun attach(view: MvpContract.View) {
        viewRef = WeakReference(view as V)
    }

    override fun detach() {
        if (viewRef != null) {
            viewRef!!.clear()
            viewRef = null
        }
    }

    override fun isViewAttached(): Boolean {
        return viewRef != null && viewRef!!.get() != null
    }

    override fun getView(): V = viewRef!!.get() as V

    override fun resume() {
    }

    override fun pause() {
    }

    override fun destroy() {
    }
}