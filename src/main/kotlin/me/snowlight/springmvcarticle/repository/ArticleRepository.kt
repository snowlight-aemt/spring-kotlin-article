package me.snowlight.springmvcarticle.repository

import me.snowlight.springmvcarticle.model.Article
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleRepository : JpaRepository<Article, Long>