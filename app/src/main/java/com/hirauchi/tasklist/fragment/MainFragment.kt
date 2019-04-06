package com.hirauchi.tasklist.fragment

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hirauchi.tasklist.R
import com.hirauchi.tasklist.activity.MainActivity
import com.hirauchi.tasklist.adapter.TaskRecyclerViewAdapter
import com.hirauchi.tasklist.controller.TaskController
import com.hirauchi.tasklist.ui.MainFragmentUI
import com.hirauchi.tasklist.ui.SettingFragmentUI.Companion.KEY_PREFERENCES
import com.hirauchi.tasklist.ui.SettingFragmentUI.Companion.KEY_PREF_ORDER
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
        mUI.mRecyclerView.adapter = mAdapter

        loadTaskList()
    }

    fun loadTaskList() {
        val preferences = activity.getSharedPreferences(KEY_PREFERENCES, Context.MODE_PRIVATE)
        val taskList = mTaskController.getTaskList((preferences.getInt(KEY_PREF_ORDER, 0)))
        mAdapter.setTaskList(taskList)
        mAdapter.notifyDataSetChanged()
    }

    override fun onDeleteTask(id: Int) {
        val content = mTaskController.getTask(id).content
        alert {
            message = activity.getString(R.string.main_dialog_delete, content)
            yesButton {
                mTaskController.deleteTask(id)
                loadTaskList()
            }
        }.show()
    }

    override fun onEditTask(id: Int) {
        (activity as MainActivity).editTask(id)
    }
}