package me.snowlight.springmvcarticle.service

import me.snowlight.springmvcarticle.model.Article
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
//@ExtendWith(MockitoExtension::class)
//@Transactional
//@Rollback
class ArticleServiceTest(
    @Autowired private val service: ArticleService,
//    @Mock private val service: ArticleService
) {

    @Test
    fun `create and get`() {
//        Mockito.`when`(service.get(1)).thenReturn(
//            Article(1, "title", "blabla", 123)
//        )

        val article = service.create(ReqCreate(
            title = "title",
            body = "blabla",
            authorId = 123
        )).let { service.get(it.id )}

//        Assertions.assertThat(article.id).isEqualTo(1)
        Assertions.assertThat(article.title).isEqualTo("title")
        Assertions.assertThat(article.body).isEqualTo("blabla")
        Assertions.assertThat(article.authorId).isEqualTo(123)
    }

    @Test
    fun getAll() {

        repeat(5) {i ->
            service.create(ReqCreate(
                title = "title $i",
                body = "blabla $i",
                authorId = 123
            ))
        }

        assertEquals(5, service.getAll().size)
    }

    @Test
    fun create() {
    }

    @Test
    fun update() {
        val create = service.create(ReqCreate(
            title = "title",
            body = "blabla",
            authorId = 123
        ))

        val update = service.update(create.id, ReqUpdate(title = "riradf"))
        val get = service.get(update.id)
        assertEquals("riradf", get.title)
    }

    @Test
    fun delete() {
        val create = service.create(ReqCreate(
            title = "title",
            body = "blabla",
            authorId = 123
        ))
        val prev = service.getAll().size
        service.delete(create.id)
        val cur = service.getAll().size

        assertNotEquals(cur, prev)
    }
}