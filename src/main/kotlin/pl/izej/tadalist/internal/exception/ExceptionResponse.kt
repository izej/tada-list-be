package pl.izej.tadalist.internal.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.time.Instant

data class ExceptionResponse(
    val message: String?,
    val status: Int,
    val timestamp: Long
)

infix fun HttpStatus.toResponseEntity(message: String?): ResponseEntity<ExceptionResponse> = ResponseEntity(
    ExceptionResponse(message = message, status = value(), timestamp = Instant.now().epochSecond), this
)
