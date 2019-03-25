package com.hirauchi.tasklist.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.hirauchi.tasklist.R
import com.hirauchi.tasklist.fragment.AddTaskFragment
import com.hirauchi.tasklist.ui.AddTaskActivityUI
import org.jetbrains.anko.setContentView

class AddTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AddTaskActivityUI().setContentView(this)

        supportActionBar?.title = getString(R.string.add_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        fragmentManager.beginTransaction().replace(R.id.Container, AddTaskFragment()).commit()
    }

    override fun onSupportNavigateUp(): Boolean {

        finish()
        return super.onSupportNavigateUp()
    }
}