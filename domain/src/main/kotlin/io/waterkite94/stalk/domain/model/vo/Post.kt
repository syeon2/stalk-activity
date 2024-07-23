package io.waterkite94.stalk.domain.model.vo

import java.time.LocalDateTime

data class Post(
    val id: Long,
    val postId: String,
    val title: String,
    val article: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val memberId: String
)
