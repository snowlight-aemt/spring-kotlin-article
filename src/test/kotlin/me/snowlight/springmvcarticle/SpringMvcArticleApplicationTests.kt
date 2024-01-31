package me.snowlight.springmvcarticle

import me.snowlight.springmvcarticle.model.Article
import me.snowlight.springmvcarticle.repository.ArticleRepository
import mu.KotlinLogging
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.platform.commons.logging.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

private val logger = KotlinLogging.logger { }

@SpringBootTest
class SpringMvcArticleApplicationTests(
    @Autowired private val articleRepository: ArticleRepository,
) {
    @Test
    fun contextLoads() {
        val prev = articleRepository.count()

        val save = articleRepository.save(Article(
                title = "title",
                body = "body",
                authorId = 1234,
            )).let { logger.debug { it } }
        var curr = articleRepository.count()
        logger.debug { ">> prev: $prev , curr: $curr" }
        Assertions.assertEquals(prev + 1, curr)
    }

}
