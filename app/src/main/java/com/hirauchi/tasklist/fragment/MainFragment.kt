package com.hirauchi.tasklist.fragment

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hirauchi.tasklist.adapter.TaskRecyclerViewAdapter
import com.hirauchi.tasklist.model.Task
import com.hirauchi.tasklist.ui.MainFragmentUI
import org.jetbrains.anko.AnkoContext

class MainFragment : Fragment() {

    lateinit var mContext: Context
    lateinit var mUI: MainFragmentUI

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mContext = inflater.context
        mUI = MainFragmentUI()
        return mUI.createView(AnkoContext.create(inflater.context, this, false))
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val task = Task(id = 0, content = "タスクの内容が入ります。", importance = "A", deadline = System.currentTimeMillis())
        val mTaskList = arrayListOf(task, task, task, task, task, task, task, task, task, task)
        mUI.mRecyclerView.adapter = TaskRecyclerViewAdapter(mContext, mTaskList)
    }
}