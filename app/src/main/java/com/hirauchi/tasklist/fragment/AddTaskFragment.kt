package com.hirauchi.tasklist.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.hirauchi.tasklist.activity.MainActivity.Companion.KEY_ID
import com.hirauchi.tasklist.controller.TaskController
import com.hirauchi.tasklist.ui.AddTaskFragmentUI
import org.jetbrains.anko.AnkoContext

class AddTaskFragment : Fragment() {

    lateinit var mUI: AddTaskFragmentUI

    var mID: Int = -1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mUI = AddTaskFragmentUI()
        return mUI.createView(AnkoContext.create(inflater.context, this, false))
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mID = arguments.getInt(KEY_ID)
        if(mID != -1) {
            setUpEditView()
        }

        activity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

    fun setUpEditView() {
        mUI.setUpEditView(TaskController(activity).getTask(mID))
    }

    fun clickAddButton(content: String, importnace: String, deadline: Long) {
        if(mID == -1) {
            TaskController(activity).addTask(content, importnace, deadline)
        } else {
            TaskController(activity).updateTask(mID, content, importnace, deadline)
        }

        activity.finish()
    }
}