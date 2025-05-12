package pl.izej.tadalist.api.v1.dto

data class SignupRequestDto(
    val email: String,
    val password: String,
)

data class SignupResponseDto(
    val userId: String
)

data class LoginRequestDto(
    val email: String,
    val password: String
)

data class LoginResponseDto(
    val token: String
)

data class VerifySignupRequestDto(
    val verificationCode: String,
    val userId: String
)

data class VerifySignupResponseDto(
    val verified: Boolean
)
