package com.hirauchi.tasklist.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import org.jetbrains.anko.AnkoContext
import java.text.SimpleDateFormat
import java.util.*
import com.hirauchi.tasklist.R
import com.hirauchi.tasklist.model.Task
import com.hirauchi.tasklist.ui.TaskRecyclerViewAdapterUI
import org.jetbrains.anko.backgroundColor

class TaskRecyclerViewAdapter(val mContext: Context, val mListener: TaskListener): RecyclerView.Adapter<TaskRecyclerViewAdapter.ViewHolder>() {

    interface TaskListener {
        fun onDeleteTask(id: Int)
        fun onEditTask(id: Int)
    }

    companion object {
        const val KEY_PREFERENCES = "key_Preferences"
        const val KEY_PREF_ROW = "key_pref_row"
        const val KEY_PREF_COLOR = "key_pref_color"
    }

    lateinit var mTaskList: List<Task>

    fun setTaskList(taskList: List<Task>) {
        mTaskList = taskList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TaskRecyclerViewAdapterUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int {
        return mTaskList.size
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mTaskList.get(position)

        val preferences = mContext.getSharedPreferences(KEY_PREFERENCES, Context.MODE_PRIVATE)
        holder.colorLine.backgroundColor = when(preferences.getInt(KEY_PREF_COLOR, 0)) {
            0 -> {
                ContextCompat.getColor(mContext, R.color.white)
            }
            1 -> {
                when(item.importance) {
                    "A" -> ContextCompat.getColor(mContext, R.color.importanceA)
                    "B" -> ContextCompat.getColor(mContext, R.color.importanceB)
                    "C" -> ContextCompat.getColor(mContext, R.color.importanceC)
                    "D" -> ContextCompat.getColor(mContext, R.color.importanceD)
                    else -> ContextCompat.getColor(mContext, R.color.importanceE)
                }
            }
            else -> {
                val todayCalendar = Calendar.getInstance()
                todayCalendar.set(Calendar.HOUR_OF_DAY, 0)
                todayCalendar.set(Calendar.MINUTE, 0)
                todayCalendar.set(Calendar.SECOND, 0)
                todayCalendar.set(Calendar.MILLISECOND, 0)
                val today = todayCalendar.time as Date
                val deadlineDate = Date(item.deadline)
                val deadlineCalendar = Calendar.getInstance()
                deadlineCalendar.timeInMillis = item.deadline

                when(deadlineDate.compareTo(today)) {
                    -1 -> {
                        if(item.deadline != 0L) {
                            ContextCompat.getColor(mContext, R.color.deadlinePast)
                        } else {
                            ContextCompat.getColor(mContext, R.color.deadlineNone)
                        }
                    }
                    0 -> ContextCompat.getColor(mContext, R.color.deadlineToday)
                    else -> {
                        if (todayCalendar.get(Calendar.DAY_OF_WEEK) > deadlineCalendar.get(Calendar.DAY_OF_WEEK)) {
                            ContextCompat.getColor(mContext, R.color.deadlineWeek)
                        } else {
                            ContextCompat.getColor(mContext, R.color.deadlineOverWeek)
                        }
                    }
                }
            }
        }

        holder.content.text = item.content
        holder.importance.text = mContext.getString(R.string.main_importance, item.importance)
        if(item.deadline != 0L) {
            holder.deadline.text = mContext.getString(R.string.main_deadline,
                    SimpleDateFormat(mContext.getString(R.string.deadline_format)).format(Date(item.deadline)))
        } else {
            holder.deadline.text = mContext.getString(R.string.main_deadline, mContext.getString(R.string.main_deadline_none))
        }

        holder.delete.setOnClickListener {
            mListener.onDeleteTask(item.id)
        }

        holder.edit.setOnClickListener {
            mListener.onEditTask(item.id)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val colorLine: View = view.findViewById(R.id.TaskColorLine)
        val content: TextView = view.findViewById(R.id.TaskContent)
        val importance: TextView = view.findViewById(R.id.TaskImportnace)
        val deadline: TextView = view.findViewById(R.id.TaskDeadline)
        val delete: RelativeLayout = view.findViewById(R.id.TaskDelete)
        val edit: RelativeLayout = view.findViewById(R.id.TaskEdit)
    }
}
