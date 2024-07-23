package io.waterkite94.stalk.domain.model.vo

import java.time.LocalDateTime

data class Comment(
    val id: Long? = null,
    val commentId: String? = null,
    val article: String,
    val postId: String,
    val memberId: String,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null
) {
    fun withCommentId(commentId: String): Comment = copy(commentId = commentId)
}
