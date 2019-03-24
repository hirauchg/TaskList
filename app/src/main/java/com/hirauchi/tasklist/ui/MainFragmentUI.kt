package com.hirauchi.tasklist.ui

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.hirauchi.tasklist.fragment.MainFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainFragmentUI : AnkoComponent<MainFragment> {

    lateinit var mRecyclerView: RecyclerView

    override fun createView(ui: AnkoContext<MainFragment>) = with(ui) {
        verticalLayout {
            mRecyclerView = recyclerView {
                layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
            }.lparams(width = matchParent) {
                topMargin = dip(8)
            }
        }
    }
}