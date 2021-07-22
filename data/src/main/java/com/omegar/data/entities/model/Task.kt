package com.omegar.data.entities.model

import com.omega_r.libs.omegatypes.Text
import com.omegar.domain.entity.TaskInterface

data class Task(
    override val name: Text
) : TaskInterface