package me.snowlight.springmvcarticle.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

// LEARN `@ResponseStatus(HttpStatus.NOT_FOUND)` 활용 방법
@ResponseStatus(HttpStatus.NOT_FOUND)
class NoArticleFound(message: String?) : RuntimeException(message)