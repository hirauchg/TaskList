package com.hirauchi.tasklist.model

data class Task(
        val id: Int,
        val content: String,
        val importance: String,
        val deadline: Long)