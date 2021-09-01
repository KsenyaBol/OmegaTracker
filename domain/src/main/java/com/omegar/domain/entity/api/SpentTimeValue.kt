package com.omegar.domain.entity.api

import java.io.Serializable

interface SpentTimeValue : Serializable {
    val presentation: String?
    val minutes: Long?
}