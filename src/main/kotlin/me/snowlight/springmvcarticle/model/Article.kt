package me.snowlight.springmvcarticle.model

import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
class Article (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var title: String,
    var body: String? = null,
    var authorId: Long? = null,
): BaseEntity() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Article

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "Article(id=$id, title='$title', body='$body', authorId=$authorId, ${super.toString()}))"
    }
}

// LEARN @EntityListeners, @CreatedDate, @LastModifiedDate 부가적으로 Main 에 다음과 같은 어노테이션 추가 @EnableJpaAuditing
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class BaseEntity(
    @CreatedDate
    var createdAt: LocalDateTime? = null,
    @LastModifiedDate
    var updatedAT: LocalDateTime? = null,
) {
    override fun toString(): String {
        return "createdAt=$createdAt, updatedAT=$updatedAT"
    }
}