package com.omegar.data.entities.model

import com.omegar.domain.entity.Task
import com.omegar.domain.entity.api.Priority
import com.omegar.domain.entity.api.SpentTime
import com.omegar.domain.entity.api.State

data class TaskImpl(
    override val name: String,
    override val priority: Priority?,
    override val state: State?,
    override val spentTime: SpentTime?
) : Task