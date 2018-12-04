package com.hb.lib.mvp.lce.sr

import android.content.Context
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import com.hb.lib.mvp.R
import com.hb.lib.mvp.lce.MvpLceContract
import com.hb.lib.mvp.lce.MvpLceFragment

abstract class MvpLceSRFragment<M, P : MvpLceContract.Presenter>
    : MvpLceFragment<SwipeRefreshLayout, M, P>(), SwipeRefreshLayout.OnRefreshListener, MvpLceSRContract.View {

    abstract fun createAdapter(context: Context, recyclerView: RecyclerView): RecyclerView.Adapter<*>

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: RecyclerView.Adapter<*>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecylcerView(true)
        loadData(false)

        mLceViewHolder.contentView.setOnRefreshListener(this)
    }

    override fun setupRecylcerView(addItemDecoration: Boolean) {
        mRecyclerView = view!!.findViewById(R.id.recyclerview_list_items)
        if (mRecyclerView == null) {
            throw NullPointerException(
                    "Recycler view is null! Have you specified a loading view in your layout xml file?"
                            + " You have to give your loading View the id R.id.recyclerview_list_items")
        }

        val context: Context = this.context!!
        mAdapter = createAdapter(context, mRecyclerView)
        mRecyclerView.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context)
        }

        if (addItemDecoration) {
            val itemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
            mRecyclerView.addItemDecoration(itemDecoration)
        }
    }

    override fun getRecyclerView(): RecyclerView {
        return mRecyclerView
    }

    override fun getSwipeRefreshLayout(): SwipeRefreshLayout {
        return mLceViewHolder.contentView
    }

    @Suppress("UNCHECKED_CAST")
    override fun <BA : RecyclerView.Adapter<*>> getAdapter(): BA {
        return mAdapter as BA
    }

    override fun getErrorMessage(e: Throwable, pullToRefresh: Boolean): String {
        if (!TextUtils.isEmpty(e.message)) {
            return e.message.toString()
        }
        return getString(R.string.error_default)
    }

    override fun onRefresh() {
        loadData(true)
    }

    override fun loadData(pullToRefresh: Boolean) {
        mPresenter.loadData(pullToRefresh)
    }

    override fun showContent() {
        super.showContent()
        mLceViewHolder.contentView.isRefreshing = false
    }

    override fun showError(e: Throwable, pullToRefresh: Boolean) {
        super.showError(e, pullToRefresh)
        mLceViewHolder.contentView.isRefreshing = false
    }
}