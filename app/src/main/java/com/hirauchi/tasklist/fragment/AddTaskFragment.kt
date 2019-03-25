package com.hirauchi.tasklist.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.hirauchi.tasklist.ui.AddTaskFragmentUI
import org.jetbrains.anko.AnkoContext

class AddTaskFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return AddTaskFragmentUI().createView(AnkoContext.create(inflater.context, this, false))
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

    fun finish() {
        activity.finish()
    }
}