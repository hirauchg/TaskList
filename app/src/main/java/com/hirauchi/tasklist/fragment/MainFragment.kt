package com.hirauchi.tasklist.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hirauchi.tasklist.R
import com.hirauchi.tasklist.adapter.TaskRecyclerViewAdapter
import com.hirauchi.tasklist.controller.TaskController
import com.hirauchi.tasklist.ui.MainFragmentUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.alert
import org.jetbrains.anko.yesButton

class MainFragment : Fragment(), TaskRecyclerViewAdapter.TaskListener {

    lateinit var mUI: MainFragmentUI
    lateinit var mAdapter: TaskRecyclerViewAdapter
    lateinit var mTaskController: TaskController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mUI = MainFragmentUI()
        return mUI.createView(AnkoContext.create(inflater.context, this, false))
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mTaskController = TaskController(activity)

        mAdapter = TaskRecyclerViewAdapter(activity, this)
        mAdapter.setTaskList(mTaskController.getTaskList())
        mUI.mRecyclerView.adapter = mAdapter
    }

    fun reloadTaskList() {
        mAdapter.setTaskList(mTaskController.getTaskList())
        mAdapter.notifyDataSetChanged()
    }

    override fun onDeleteTask(id: Int) {
        val content = mTaskController.getTask(id).content
        alert {
            message = activity.getString(R.string.main_dialog_delete, content)
            yesButton {
                mTaskController.deleteTask(id)
                reloadTaskList()
            }
        }.show()
    }

    override fun onEditTask(id: Int) {
        // TODO
    }
}