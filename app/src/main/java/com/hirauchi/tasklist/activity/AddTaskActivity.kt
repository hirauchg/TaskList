package com.hirauchi.tasklist.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hirauchi.tasklist.R
import com.hirauchi.tasklist.activity.MainActivity.Companion.KEY_ID
import com.hirauchi.tasklist.fragment.AddTaskFragment
import com.hirauchi.tasklist.ui.AddTaskActivityUI
import org.jetbrains.anko.setContentView

class AddTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AddTaskActivityUI().setContentView(this)

        val id = intent.getIntExtra(KEY_ID, -1)
        if(id == -1) {
            supportActionBar?.title = getString(R.string.add_title)
        } else {
            supportActionBar?.title = getString(R.string.edit_title)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val fragment = AddTaskFragment()
        val bundle = Bundle()
        bundle.putInt(KEY_ID, id)
        fragment.arguments = bundle
        fragmentManager.beginTransaction().replace(R.id.Container,fragment).commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}