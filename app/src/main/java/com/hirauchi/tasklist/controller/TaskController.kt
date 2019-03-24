package com.hirauchi.tasklist.controller

import android.content.Context
import com.hirauchi.tasklist.database.TaskDBHelper
import com.hirauchi.tasklist.model.Task
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.update

class TaskController(ctx: Context) {

    private val mDB = TaskDBHelper.getInstance(ctx)

    fun getTaskList() : List<Task> {
        lateinit var taskList : List<Task>
        mDB.use {
            taskList = select(TaskDBHelper.TABLE_NAME).parseList(classParser())
        }
        return taskList
    }

    fun getTask(id: Int) : Task {
        lateinit var task : Task
        mDB.use {
            task = select(TaskDBHelper.TABLE_NAME).whereArgs("(id = {Id})", "Id" to id).parseSingle(classParser())
        }
        return task
    }

    fun addTask(content: String, importance: String, deadline: Long) {
        mDB.use {
            insert(TaskDBHelper.TABLE_NAME,
                    TaskDBHelper.CULM_CONTENT to content,
                    TaskDBHelper.CULM_IMPORTANCE to importance,
                    TaskDBHelper.CULM_DEADLINE to deadline)
        }
    }

    fun updateTask(id: Int, content: String, importance: String, deadline: Long) {
        mDB.use {
            update(TaskDBHelper.TABLE_NAME,
                    TaskDBHelper.CULM_CONTENT to content,
                    TaskDBHelper.CULM_IMPORTANCE to importance,
                    TaskDBHelper.CULM_DEADLINE to deadline)
                    .whereSimple(TaskDBHelper.CULM_ID + " = ?", id.toString()).exec()
        }
    }

    fun deleteTask(id: Int) {
        mDB.use {
            delete(TaskDBHelper.TABLE_NAME, TaskDBHelper.CULM_ID + " = ?", arrayOf(id.toString()))
        }
    }
}