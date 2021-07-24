package com.omegar.data.entities.model

import com.omegar.domain.entity.TaskInterface

data class Task(
    override val name: String,
    override val priority: String?,
    override val state: String?,
    override val spentTime: String?
) : TaskInterface