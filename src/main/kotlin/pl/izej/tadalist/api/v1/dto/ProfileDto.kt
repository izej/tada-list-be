package pl.izej.tadalist.api.v1.dto

import java.util.UUID

data class ProfileDto(
    val id: UUID,
    val name: String?,
    val theme: String?,
    val avatar: String?
)

data class EditProfileRequest(
    val name: String?,
    val theme: String?,
    val avatar: String?
)
