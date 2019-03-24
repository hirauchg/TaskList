package com.hirauchi.tasklist.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class TaskDBHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, DB_NAME, null, DB_VERSION) {

    companion object {
        const val DB_NAME = "db_test"
        const val DB_VERSION = 1
        const val TABLE_NAME = "table_task"
        const val CULM_ID = "id"
        const val CULM_CONTENT = "content"
        const val CULM_IMPORTANCE = "importance"
        const val CULM_DEADLINE = "deadline"

        private var instance: TaskDBHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): TaskDBHelper {
            if (instance == null) {
                instance = TaskDBHelper(ctx.getApplicationContext())
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(TABLE_NAME, true,
                CULM_ID to INTEGER + PRIMARY_KEY + UNIQUE,
                CULM_CONTENT to TEXT,
                CULM_IMPORTANCE to TEXT,
                CULM_DEADLINE to INTEGER)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(TABLE_NAME, true)
    }
}