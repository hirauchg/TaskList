package com.hirauchi.tasklist.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hirauchi.tasklist.R
import com.hirauchi.tasklist.fragment.SettingFragment
import com.hirauchi.tasklist.ui.SettingActivityUI
import org.jetbrains.anko.setContentView

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SettingActivityUI().setContentView(this)

        supportActionBar?.title = getString(R.string.setting_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fragmentManager.beginTransaction().replace(R.id.Container, SettingFragment()).commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}