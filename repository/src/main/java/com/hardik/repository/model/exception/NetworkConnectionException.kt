package com.hardik.repository.model.exception

import java.io.IOException

class NetworkConnectionException : IOException() {
    override val message: String
        get() = "Please check your internet connection"
}