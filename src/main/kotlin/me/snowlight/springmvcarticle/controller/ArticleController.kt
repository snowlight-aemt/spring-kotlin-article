package me.snowlight.springmvcarticle.controller

import me.snowlight.springmvcarticle.service.ArticleService
import me.snowlight.springmvcarticle.service.ReqUpdate
import me.snowlight.springmvcarticle.service.ReqCreate
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/article")
class ArticleController(
    private val service: ArticleService,
) {
    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = service.get(id)

    @GetMapping("/all")
    fun getAll(@RequestParam title: String?) = service.getAll(title)

    @PostMapping
    fun create(@RequestBody article: ReqCreate) = service.create(article)

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody article: ReqUpdate
    ) = service.update(id, article)

    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable id: Long
    ) = service.delete(id)
}