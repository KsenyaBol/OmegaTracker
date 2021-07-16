package com.omegar.data

import com.omega_r.base.errors.AppException

class SessionAppException(devMessage: String?, cause: Throwable?) : AppException(devMessage, cause)