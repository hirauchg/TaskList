package com.hirauchi.tasklist.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hirauchi.tasklist.ui.SettingFragmentUI
import org.jetbrains.anko.AnkoContext

class SettingFragment : Fragment() {

    lateinit var mUI: SettingFragmentUI

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mUI = SettingFragmentUI()
        return mUI.createView(AnkoContext.create(inflater.context, this, false))
    }
}