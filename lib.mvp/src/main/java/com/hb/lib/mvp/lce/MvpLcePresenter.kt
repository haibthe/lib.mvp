package com.hb.lib.mvp.lce

import com.hb.lib.mvp.base.MvpBasePresenter

abstract class MvpLcePresenter<V : MvpLceContract.View<*>>
    : MvpBasePresenter<V>(), MvpLceContract.Presenter