package com.idf.kids.exception

import org.springframework.http.HttpStatus

class BusinessException(
    override val message: String,
    val errorCode: String? = null,
    val status: HttpStatus = HttpStatus.BAD_REQUEST
) : RuntimeException(message)
