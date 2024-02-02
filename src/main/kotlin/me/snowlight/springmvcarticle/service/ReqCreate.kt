package me.snowlight.springmvcarticle.service

data class ReqCreate(
    val title: String,
    val body: String?,
    val authorId: Long?,
)
