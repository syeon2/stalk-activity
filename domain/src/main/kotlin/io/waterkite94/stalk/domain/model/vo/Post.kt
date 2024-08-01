package io.waterkite94.stalk.domain.model.vo

import java.time.LocalDateTime

data class Post(
    val id: Long? = null,
    val postId: String? = null,
    val title: String,
    val article: String,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null,
    val memberId: String,
    val stockId: String
) {
    fun withPostId(postId: String): Post = copy(postId = postId)
}
