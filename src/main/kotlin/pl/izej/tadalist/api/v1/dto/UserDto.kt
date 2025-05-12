package pl.izej.tadalist.api.v1.dto

import java.util.UUID

data class UserDto(
    val id: UUID,

    val email: String,
)

data class UpdateUserDetailsRequestDto(
    val username: String?,

    val firstName: String?,
    val lastName: String?,
)
