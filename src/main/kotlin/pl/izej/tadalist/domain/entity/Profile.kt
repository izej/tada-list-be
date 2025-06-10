package pl.izej.tadalist.domain.entity

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "profile")
data class Profile(

    @Id
    @Column(nullable = false) var id: UUID = UUID.randomUUID(),

    @Column(nullable = true) var name: String = "",
    @Column(nullable = true) var theme: String = "light",
    @Column(nullable = true) var avatar: String? = null,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    var user: User
)
