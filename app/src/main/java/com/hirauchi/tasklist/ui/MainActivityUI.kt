package com.hirauchi.tasklist.ui

import com.hirauchi.tasklist.activity.MainActivity
import org.jetbrains.anko.AnkoContext
import com.hirauchi.tasklist.R
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.verticalLayout

class MainActivityUI : AnkoComponent<MainActivity> {

    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        verticalLayout {
            id = R.id.Container
        }
    }
}