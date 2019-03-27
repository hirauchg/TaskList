package com.hirauchi.tasklist.ui

import com.hirauchi.tasklist.R
import com.hirauchi.tasklist.activity.SettingActivity
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.verticalLayout

class SettingActivityUI : AnkoComponent<SettingActivity> {

    override fun createView(ui: AnkoContext<SettingActivity>) = with(ui) {
        verticalLayout {
            id = R.id.Container
        }
    }
}