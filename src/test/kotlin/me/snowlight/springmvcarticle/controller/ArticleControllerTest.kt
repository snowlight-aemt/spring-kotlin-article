package me.snowlight.springmvcarticle.controller

import me.snowlight.springmvcarticle.service.ArticleService
import me.snowlight.springmvcarticle.service.ReqCreate
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.Mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put

@SpringBootTest
@AutoConfigureMockMvc
@Sql("classpath:db-init/test.sql")
class ArticleControllerTest(
    @Autowired private val mockMvc: MockMvc,
    @Autowired private val service: ArticleService,
) {
    @Test
    fun get() {
        mockMvc.get("/article/1") {
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            jsonPath("title") {value("title 1")}
        }
    }

    @Test
    fun getAll() {
        mockMvc.get("/article/all") {
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            jsonPath("$.length()") {value("1")}
        }
    }

    @Test
    fun create() {
        mockMvc.post("/article") {
            contentType = MediaType.APPLICATION_JSON
            content = """
            {
                "title": "title create",
                "body": "body create",
                "authorId": 999999
            }
            """.trimIndent()
        }.andExpect {
            status { isOk() }
            jsonPath("title") {value("title create")}
            jsonPath("body") {value("body create")}
            jsonPath("authorId") {value(999999)}
        }
    }

    @Test
    fun update() {
        mockMvc.put("/article/1") {
            contentType = MediaType.APPLICATION_JSON
            content = """
            {
                "title": "title update",
                "body": "body update",
                "authorId": 444
            }
            """.trimIndent()
        }.andExpect {
            status { isOk() }
            jsonPath("title") {value("title update")}
            jsonPath("body") {value("body update")}
            jsonPath("authorId") {value(444)}
        }
    }

    @Test
    fun delete() {
        val create = service.create(
            ReqCreate(
                title = "test",
                body = "body",
                authorId = 1234
            )
        )
        val prev = service.getAll().size

        mockMvc.delete("/article/1") {
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
        }
        val curr = service.getAll().size

        assertEquals(prev - 1, curr)
    }
}