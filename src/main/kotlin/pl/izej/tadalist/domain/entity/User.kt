package pl.izej.tadalist.domain.entity

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "users")
data class User(

    @Id
    @Column(nullable = false)
    var id: UUID = UUID.randomUUID(),

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var role: UserRole = UserRole.USER,

    @Column(nullable = false, unique = true)
    var email: String,

    @Column(nullable = false)
    var password: String,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    var tasks: MutableList<Task> = mutableListOf(),

    @OneToOne(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    var profile: Profile? = null
)

enum class UserRole {
    USER, ADMIN
}
