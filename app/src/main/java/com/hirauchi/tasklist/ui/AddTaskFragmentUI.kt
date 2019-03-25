package com.hirauchi.tasklist.ui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import com.hirauchi.tasklist.fragment.AddTaskFragment
import org.jetbrains.anko.*
import com.hirauchi.tasklist.R
import com.hirauchi.tasklist.controller.TaskController
import java.text.SimpleDateFormat
import java.util.*

class AddTaskFragmentUI : AnkoComponent<AddTaskFragment> {

    lateinit var mEditText: EditText
    lateinit var mSpinner: Spinner
    var mDeadline: Long = 0

    @SuppressLint("SimpleDateFormat")
    override fun createView(ui: AnkoContext<AddTaskFragment>) = with(ui) {
        scrollView {
            lparams(width = matchParent, height = matchParent)

            setOnTouchListener { _, _ ->
                val imm = ctx.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(windowToken, 0)
            }

            verticalLayout {
                padding = dip( 24)
                textView(R.string.add_content_title) {
                    textSize = 18F
                }

                mEditText = editText {
                    setSingleLine(false)
                    imeOptions = EditorInfo.IME_FLAG_NO_ENTER_ACTION
                }.lparams(width = matchParent) {
                    bottomMargin = dip(40)
                }

                linearLayout {
                    textView(R.string.add_importance_title) {
                        textSize = 18F
                    }

                    mSpinner = spinner {
                        adapter = ArrayAdapter.createFromResource(ctx, R.array.importance_list, android.R.layout.simple_spinner_dropdown_item)
                        setSelection(2)
                    }.lparams {
                        marginStart = dip(30)
                    }
                }.lparams {
                    bottomMargin = dip(40)
                }

                linearLayout {
                    textView(R.string.add_deadline_title) {
                        textSize = 18F
                    }

                    verticalLayout {
                        textView(R.string.add_deadline_message) {
                            setOnClickListener {
                                val calendar = Calendar.getInstance()
                                DatePickerDialog(ctx, DatePickerDialog.OnDateSetListener { _, years, month, day ->
                                    calendar.set(years, month, day)
                                    mDeadline = calendar.timeInMillis
                                    text = SimpleDateFormat(ctx.getString(R.string.deadline_format)).format(calendar.time)
                                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
                            }
                        }.lparams(width = wrapContent)

                        view {
                            backgroundColor = ContextCompat.getColor(ctx, R.color.colorAccent)
                        }.lparams(width = matchParent, height = 2)
                    }.lparams(width = wrapContent, height = wrapContent) {
                        marginStart = dip(30)
                        topMargin = dip(2)
                    }
                }

                button(R.string.add_button) {
                    setOnClickListener { it ->
                        val content = mEditText.text.toString()
                        if(content.trim().isEmpty()) {
                            alert(R.string.add_dialog_content_empty){
                                yesButton {}
                            }.show()
                            return@setOnClickListener
                        }

                        val importnace = mSpinner.selectedItem.toString()

                        TaskController(ctx).addTask(content, importnace, mDeadline)
                        ui.owner.finish()
                    }
                }.lparams {
                    topMargin = dip(50)
                    bottomMargin = dip(10)
                    gravity = Gravity.CENTER_HORIZONTAL
                }
            }.lparams(width = matchParent, height = matchParent)
        }
    }
}