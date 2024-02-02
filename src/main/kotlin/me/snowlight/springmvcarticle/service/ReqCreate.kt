package me.snowlight.springmvcarticle.service

data class ReqCreate(
    val title: String,
    val body: String? = null,
    val authorId: Long? = null,
)
