package com.hb.lib.mvp.base

import androidx.annotation.NonNull

interface MvpContract {
    interface View {

    }

    interface Presenter {
        fun attach(@NonNull view: View)

        fun detach()

        fun isViewAttached() : Boolean

        fun getView(): View

        fun resume()

        fun pause()

        fun destroy()
    }
}