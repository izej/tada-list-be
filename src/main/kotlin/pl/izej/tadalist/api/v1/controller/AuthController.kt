package pl.izej.tadalist.api.v1.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.izej.tadalist.api.v1.dto.LoginRequestDto
import pl.izej.tadalist.api.v1.dto.LoginResponseDto
import pl.izej.tadalist.api.v1.dto.SignupRequestDto
import pl.izej.tadalist.api.v1.dto.SignupResponseDto
import pl.izej.tadalist.service.AuthService

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(private val authService: AuthService) {

    @PostMapping("/signup")
    fun signup(@RequestBody request: SignupRequestDto): SignupResponseDto =  authService.signup(request)


    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequestDto): LoginResponseDto = authService.login(request)

//    @PostMapping("/verify")
//    fun verify(@RequestBody request: VerifySignupRequestDto): LoginResponseDto = authService.verify(request)
}
