//import jakarta.persistence.Column
//import jakarta.persistence.Entity
//import jakarta.persistence.GeneratedValue
//import jakarta.persistence.GenerationType
//import jakarta.persistence.Id
//import jakarta.persistence.JoinColumn
//import jakarta.persistence.OneToOne
//import jakarta.persistence.Table
//
//import java.time.LocalDateTime
//import java.util.UUID
//
//@Entity
//@Table(name = "signup_verifications")
//data class SignupVerification(
//
//    @Id @GeneratedValue(strategy = GenerationType.UUID)
//    @Column(nullable = false) var id: UUID = UUID.randomUUID(),
//
//    @OneToOne
//    @JoinColumn(name = "user_id", nullable = false) var user: User,
//
//    @Column(nullable = false) var validFrom: LocalDateTime = LocalDateTime.now(),
//)
