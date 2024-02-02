package me.snowlight.springmvcarticle.service

import me.snowlight.springmvcarticle.exception.NoArticleFound
import me.snowlight.springmvcarticle.model.Article
import me.snowlight.springmvcarticle.repository.ArticleRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ArticleService(
    private val repository: ArticleRepository,
) {
    fun get(id: Long):Article {
        return repository.findByIdOrNull(id) ?: throw NoArticleFound("No article found (id:$id)")
    }

    fun getAll(title: String?): List<Article> {
        return if (title.isNullOrEmpty()) {
            repository.findAll()
        } else {
            repository.findAllByTitleContains("$title")
        }
    }

    @Transactional
    fun create(request: ReqCreate): Article {
        return repository.save(Article(
            title = request.title,
            body = request.body,
            authorId = request.authorId,
        ))
    }

    // LEARN 요청하는 파라미터 에 존재하는 필드만 업데이트한다.
    @Transactional
    fun update(id: Long, request: ReqUpdate): Article {
        return repository.findByIdOrNull(id)?.let { article ->
            request.title?.let { article.title = it }
            request.body?.let { article.body = it }
            request.authorId?.let { article.authorId = it }
            article
        } ?: throw NoArticleFound("No article found (id:$id)")
    }

    @Transactional
    fun delete(id: Long) {
        repository.deleteById(id)
    }
}