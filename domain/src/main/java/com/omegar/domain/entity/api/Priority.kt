package com.omegar.domain.entity.api

import java.io.Serializable

interface Priority : Serializable {
    val name: String
    val value: PriorityValue?
}