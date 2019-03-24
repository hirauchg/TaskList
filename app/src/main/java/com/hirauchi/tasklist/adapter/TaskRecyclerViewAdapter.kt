package com.hirauchi.tasklist.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import org.jetbrains.anko.AnkoContext
import java.text.SimpleDateFormat
import java.util.*
import com.hirauchi.tasklist.R
import com.hirauchi.tasklist.model.Task
import com.hirauchi.tasklist.ui.TaskRecyclerViewAdapterUI
import org.jetbrains.anko.backgroundColor

class TaskRecyclerViewAdapter(val mContext: Context, private var mTaskList: List<Task>): RecyclerView.Adapter<TaskRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TaskRecyclerViewAdapterUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int {
        return mTaskList.size
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mTaskList.get(position)

        holder.colorLine.backgroundColor = ContextCompat.getColor(mContext, R.color.importanceA)

        holder.content.text = item.content
        holder.importance.text = mContext.getString(R.string.main_importance, item.importance)
        holder.deadline.text = mContext.getString(R.string.main_deadline,
                SimpleDateFormat(mContext.getString(R.string.deadline_format)).format(Date(item.deadline)))

        holder.delete.setOnClickListener {
            Log.d("TaskRecyclerViewAdapter", "delete")
        }

        holder.edit.setOnClickListener {
            Log.d("TaskRecyclerViewAdapter", "edit")
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
