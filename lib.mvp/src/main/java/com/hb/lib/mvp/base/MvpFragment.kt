package com.hb.lib.mvp.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class MvpFragment<P : MvpContract.Presenter> : DaggerFragment(), MvpContract.View {

    @Inject
    lateinit var mPresenter: P

    @LayoutRes
    abstract fun getResLayoutId() : Int

    @NonNull
    abstract fun createPresenter(): P


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(getResLayoutId(), container, false)
        mPresenter = createPresenter()
        mPresenter.attach(this)
        return view
    }

    override fun onResume() {
        mPresenter.resume()
        super.onResume()
    }

    override fun onPause() {
        mPresenter.pause()
        super.onPause()

    }

    override fun onDestroyView() {
        mPresenter.destroy()
        super.onDestroyView()
    }

}