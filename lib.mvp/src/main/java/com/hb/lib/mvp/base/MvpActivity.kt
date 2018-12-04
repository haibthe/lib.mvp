package com.hb.lib.mvp.base

import android.app.Activity
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.NonNull
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Toast
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class MvpActivity<P : MvpContract.Presenter> : DaggerAppCompatActivity(), MvpContract.View {

    private lateinit var activity: Activity

    @Inject
    lateinit var mPresenter: P

    @LayoutRes
    protected abstract fun getResLayoutId(): Int

    @NonNull
    protected abstract fun createPresenter(): P


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getResLayoutId())

        mPresenter = createPresenter()
        mPresenter.attach(this)
    }

    fun getActivity(): Activity = this

    fun getView(): View = findViewById(android.R.id.content)

    override fun onResume() {
        mPresenter.resume()
        super.onResume()
    }

    override fun onPause() {
        mPresenter.pause()
        super.onPause()
    }

    override fun onDestroy() {
        mPresenter.destroy()
        super.onDestroy()
    }


    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    fun showToast(resId: Int) {
        Toast.makeText(this, resId, Toast.LENGTH_LONG).show()
    }

    open fun showErrorDialog(msg: String, listener: View.OnClickListener? = null) {
        val popup = Snackbar.make(getView(), msg, Snackbar.LENGTH_INDEFINITE)
                .setAction("Đóng", listener)
        popup.show()
    }

    open fun showErrorDialog(resId: Int, listener: View.OnClickListener? = null) {
        val popup = Snackbar.make(getView(), resId, Snackbar.LENGTH_INDEFINITE)
                .setAction("Đóng", listener)
        popup.show()
    }

}