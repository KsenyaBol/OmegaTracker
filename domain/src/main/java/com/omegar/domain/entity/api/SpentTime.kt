package com.omegar.domain.entity.api

import java.io.Serializable

interface SpentTime : Serializable {
    val name: String
    val value: SpentTimeValue?
}