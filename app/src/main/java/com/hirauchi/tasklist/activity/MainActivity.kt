package com.hirauchi.tasklist.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.hirauchi.tasklist.R
import com.hirauchi.tasklist.fragment.MainFragment
import com.hirauchi.tasklist.ui.MainActivityUI
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivityForResult

class MainActivity : AppCompatActivity() {

    companion object {
        const val KEY_ID = "key_id"
    }

    val mMainFragment = MainFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)

        fragmentManager.beginTransaction().replace(R.id.Container, mMainFragment).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_add -> startActivityForResult<AddTaskActivity>(1)
            R.id.menu_setting -> startActivityForResult<SettingActivity>(1)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mMainFragment.reloadTaskList()
    }

    fun editTask(id: Int) {
        startActivityForResult<AddTaskActivity>(1, KEY_ID to id)
    }
}
