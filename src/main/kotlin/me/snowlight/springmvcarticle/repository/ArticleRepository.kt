package me.snowlight.springmvcarticle.repository

import me.snowlight.springmvcarticle.model.Article
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ArticleRepository : JpaRepository<Article, Long> {
    fun findAllByTitleContains(title: String): List<Article>
}