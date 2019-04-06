package com.hirauchi.tasklist.controller

import android.content.Context
import com.hirauchi.tasklist.database.TaskDBHelper
import com.hirauchi.tasklist.model.Task
import org.jetbrains.anko.db.*

class TaskController(ctx: Context) {

    private val mDB = TaskDBHelper.getInstance(ctx)

    fun getTaskList(order: Int) : List<Task> {
        lateinit var taskList : List<Task>
        mDB.use {
            taskList = when(order) {
                0 -> select(TaskDBHelper.TABLE_NAME).orderBy(TaskDBHelper.CULM_ID, SqlOrderDirection.DESC).parseList(classParser())
                1 -> select(TaskDBHelper.TABLE_NAME).orderBy(TaskDBHelper.CULM_IMPORTANCE).parseList(classParser())
                else -> {
                    val existDeadlineTaskList = select(TaskDBHelper.TABLE_NAME)
                            .whereSimple(TaskDBHelper.CULM_DEADLINE + " != ?", "0")
                            .orderBy(TaskDBHelper.CULM_DEADLINE).parseList(classParser<Task>())
                    val noDeadlineTaskList = select(TaskDBHelper.TABLE_NAME)
                            .whereSimple(TaskDBHelper.CULM_DEADLINE + " = ?", "0")
                            .parseList(classParser<Task>())

                    val _taskList: ArrayList<Task> = arrayListOf()
                    _taskList.addAll(existDeadlineTaskList)
                    _taskList.addAll(noDeadlineTaskList)
                    _taskList
                }
            }
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