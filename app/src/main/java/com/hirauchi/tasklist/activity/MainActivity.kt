package com.hirauchi.tasklist.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.hirauchi.tasklist.R
import com.hirauchi.tasklist.fragment.MainFragment
import com.hirauchi.tasklist.ui.MainActivityUI
import org.jetbrains.anko.setContentView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)

        fragmentManager.beginTransaction().replace(R.id.Container, MainFragment()).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // メニュー選択時の処理を実装
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_add -> Log.d("MainActivity", "Add") // TODO
            R.id.menu_setting -> Log.d("MainActivity", "Setting") // TODO
        }
        return super.onOptionsItemSelected(item)
    }
}
