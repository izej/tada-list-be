package pl.izej.tadalist.api.v1.controller

import pl.izej.tadalist.api.v1.dto.UserDto
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.izej.tadalist.api.v1.mapper.toDto
import pl.izej.tadalist.service.UserService

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): UserDto = userService.getById(id).toDto()

//    @PreAuthorize("hasRole('ROLE_USER')")
//    @PutMapping("/{id}/details")
//    fun updateUserDetails(@PathVariable id: String, @RequestBody request: UpdateUserDetailsRequestDto) =
//        userService.updateDetails(id, request)
}
