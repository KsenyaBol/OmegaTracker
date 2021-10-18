package com.omegar.domain.entity

import com.omegar.domain.entity.api.Priority
import com.omegar.domain.entity.api.SpentTime
import com.omegar.domain.entity.api.State
import java.io.Serializable

interface Task : Serializable {
    val name: String
    val priority: Priority?
    val state: State?
    val spentTime: SpentTime?
}