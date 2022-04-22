package com.omegar.data

import com.omega_r.base.errors.AppException

class GeneralAppException(devMessage: String?, cause: Throwable?) : AppException(devMessage)