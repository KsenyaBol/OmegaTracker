package com.omegar.data.entities.api

import com.omegar.domain.entity.api.CustomFields

data class ResponseUnknownEntity(
    override val name: String = "Unknown"
) : CustomFields
