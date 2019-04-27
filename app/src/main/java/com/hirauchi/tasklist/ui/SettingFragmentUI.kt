package com.hirauchi.tasklist.ui

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.RadioGroup
import com.hirauchi.tasklist.R
import com.hirauchi.tasklist.fragment.SettingFragment
import org.jetbrains.anko.*

class SettingFragmentUI : AnkoComponent<SettingFragment> {

    companion object {
        const val KEY_PREFERENCES = "key_Preferences"
        const val KEY_PREF_ORDER = "key_pref_order"
        const val KEY_PREF_COLOR = "key_pref_color"
    }

    override fun createView(ui: AnkoContext<SettingFragment>) = with(ui) {
        val preferences = ctx.getSharedPreferences(KEY_PREFERENCES, Context.MODE_PRIVATE)

        verticalLayout {
            padding = dip(24)

            textView(R.string.setting_row_title) {
                textSize = 20F
            }.lparams {
                bottomMargin = dip(16)
            }

            radioGroup {
                orientation = RadioGroup.HORIZONTAL
                setOnCheckedChangeListener { group, checkedId ->
                    val editor = preferences.edit()
                    editor.putInt(KEY_PREF_ORDER, checkedId)
                    editor.apply()
                }

                val buttonIdList = arrayOf(R.string.setting_row_default, R.string.setting_row_importance, R.string.setting_row_deadline)
                for (i in 0..2) {
                    radioButton {
                        id = i
                        setText(buttonIdList[i])
                        textSize = 16F
                    }.lparams {
                        leftMargin = dip(14)
                    }
                }

                check(preferences.getInt(KEY_PREF_ORDER, 0))
            }.lparams {
                bottomMargin = dip(28)
            }

            textView(R.string.setting_color_title) {
                textSize = 20F
            }.lparams {
                bottomMargin = dip(10)
            }

            val description = textView {
                textColor = ContextCompat.getColor(ctx, R.color.textColorSecondary)

                when (preferences.getInt(KEY_PREF_COLOR, 0)) {
                    0 -> visibility = View.GONE
                    1 -> textResource = R.string.setting_color_importance_desk
                    2 -> textResource = R.string.setting_color_deadline_desk
                }
            }.lparams(height = 20) {
                bottomMargin = dip(16)
                leftMargin = dip(10)
            }

            radioGroup {
                orientation = RadioGroup.HORIZONTAL
                setOnCheckedChangeListener { group, checkedId ->
                    val editor = preferences.edit()
                    editor.putInt(KEY_PREF_COLOR, checkedId)
                    editor.apply()

                    when (checkedId) {
                        0 -> description.visibility = View.GONE
                        1 -> {
                            description.visibility = View.VISIBLE
                            description.textResource = R.string.setting_color_importance_desk
                        }
                        2 -> {
                            description.visibility = View.VISIBLE
                            description.textResource = R.string.setting_color_deadline_desk
                        }
                    }
                }

                val buttonIdList = arrayOf(R.string.setting_color_default, R.string.setting_color_importance, R.string.setting_color_deadline)
                for (i in 0..2) {
                    radioButton {
                        id = i
                        setText(buttonIdList[i])
                        textSize = 16F
                    }.lparams {
                        leftMargin = dip(14)
                    }
                }
                check(preferences.getInt(KEY_PREF_COLOR, 0))
            }
        }
    }
}