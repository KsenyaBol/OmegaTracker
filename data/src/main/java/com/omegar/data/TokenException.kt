package com.omegar.data

import com.omega_r.base.errors.AppException

class TokenException(devMessage: String? = null, cause: Throwable? = null) :
    AppException(devMessage, cause) {

}