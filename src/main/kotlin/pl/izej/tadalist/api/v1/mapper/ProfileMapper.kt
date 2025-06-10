package pl.izej.tadalist.api.v1.mapper

import pl.izej.tadalist.api.v1.dto.EditProfileRequest
import pl.izej.tadalist.api.v1.dto.ProfileDto
import pl.izej.tadalist.domain.entity.Profile

fun Profile.toDto(): ProfileDto = ProfileDto(
    id = id,
    name = name.takeIf { it.isNotBlank() },
    theme = theme,
    avatar = avatar
)

fun EditProfileRequest.toProfile(profile: Profile): Profile {
    name?.let { profile.name = it }
    theme?.let { profile.theme = it }
    avatar?.let { profile.avatar = it }
    return profile
}
