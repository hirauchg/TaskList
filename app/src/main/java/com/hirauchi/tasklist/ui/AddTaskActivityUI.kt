package com.hirauchi.tasklist.ui

import com.hirauchi.tasklist.R
import com.hirauchi.tasklist.activity.AddTaskActivity
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.verticalLayout

class AddTaskActivityUI : AnkoComponent<AddTaskActivity> {

    override fun createView(ui: AnkoContext<AddTaskActivity>) = with(ui) {
        verticalLayout {
            id = R.id.Container
        }
    }
}