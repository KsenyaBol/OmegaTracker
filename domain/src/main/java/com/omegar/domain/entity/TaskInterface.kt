package com.omegar.domain.entity

import java.io.Serializable

interface TaskInterface: Serializable {
    val name: String
    val priority: String?
    val state: String?
    val spentTime: String?
}