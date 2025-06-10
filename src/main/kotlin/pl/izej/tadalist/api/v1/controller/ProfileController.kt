package pl.izej.tadalist.api.v1.controller

import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.izej.tadalist.api.v1.dto.EditProfileRequest
import pl.izej.tadalist.api.v1.dto.ProfileDto
import pl.izej.tadalist.api.v1.mapper.toDto
import pl.izej.tadalist.service.ProfileService
import pl.izej.tadalist.service.UserService
import java.security.Principal

@RestController
@RequestMapping("/api/v1/profile")
class ProfileController(
    private val profileService: ProfileService,
    private val userService: UserService
) {

    @PreAuthorize("hasRole('ROLE_USER')")
    @PatchMapping("/edit")
    fun editProfile(@RequestBody request: EditProfileRequest, principal: Principal): ResponseEntity<ProfileDto> {
        val user = userService.findByEmail(principal.name)
            ?: throw NoSuchElementException("User with email ${principal.name} not found")

        val profileDto = profileService.editProfile(user.id, request)

        return ResponseEntity.ok(profileDto)
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    fun getCurrentProfile(principal: Principal): ProfileDto {
        val user = userService.findByEmail(principal.name)
            ?: throw NoSuchElementException("User with email ${principal.name} not found")

        return profileService.getProfileByUserId(user.id).toDto()
    }
}
