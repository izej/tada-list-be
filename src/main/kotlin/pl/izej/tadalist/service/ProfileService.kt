package pl.izej.tadalist.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pl.izej.tadalist.api.v1.dto.EditProfileRequest
import pl.izej.tadalist.api.v1.dto.ProfileDto
import pl.izej.tadalist.api.v1.mapper.toDto
import pl.izej.tadalist.api.v1.mapper.toProfile
import pl.izej.tadalist.domain.entity.Profile
import pl.izej.tadalist.domain.repository.ProfileRepository
import pl.izej.tadalist.domain.repository.UserRepository
import java.util.UUID

@Service
class ProfileService(private val profileRepository: ProfileRepository, private val userRepository: UserRepository) {

    @Transactional
    fun editProfile(userId: UUID, request: EditProfileRequest): ProfileDto {
        val user = userRepository.findById(userId)
            .orElseThrow { NoSuchElementException("User with id $userId not found") }

        val profile = profileRepository.findByUserId(user.id).orElseGet {
            Profile(user = user)
        }

        request.toProfile(profile)
        profileRepository.save(profile)

        return profile.toDto()
    }

    fun getProfileByUserId(userId: UUID): Profile = profileRepository.findByUserId(userId)
        .orElseThrow { NoSuchElementException("Profile with user id $userId not found") }

}
