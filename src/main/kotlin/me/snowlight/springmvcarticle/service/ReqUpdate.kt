package me.snowlight.springmvcarticle.service

data class ReqUpdate (
    val title: String? = null,
    val body: String? = null,
    val authorId: Long? = null,
)
