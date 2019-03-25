package com.hirauchi.tasklist.fragment

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hirauchi.tasklist.adapter.TaskRecyclerViewAdapter
import com.hirauchi.tasklist.controller.TaskController
import com.hirauchi.tasklist.ui.MainFragmentUI
import org.jetbrains.anko.AnkoContext

class MainFragment : Fragment(), TaskRecyclerViewAdapter.TaskListener {

    lateinit var mContext: Context
    lateinit var mUI: MainFragmentUI
    lateinit var mAdapter: TaskRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mContext = inflater.context
        mUI = MainFragmentUI()
        return mUI.createView(AnkoContext.create(inflater.context, this, false))
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAdapter = TaskRecyclerViewAdapter(mContext, this)
        mAdapter.setTaskList(TaskController(mContext).getTaskList())
        mUI.mRecyclerView.adapter = mAdapter
    }

    fun reloadTaskList() {
        mAdapter.setTaskList(TaskController(mContext).getTaskList())
        mAdapter.notifyDataSetChanged()
    }

    override fun onDeleteTask(id: Int) {
        TaskController(mContext).deleteTask(id)
        reloadTaskList()
    }

    override fun onEditTask(id: Int) {
        // TODO
    }
}