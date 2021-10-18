package com.omegar.domain.entity.api

import java.io.Serializable

interface State : Serializable {
    val name: String
    val value: StateValue?
}