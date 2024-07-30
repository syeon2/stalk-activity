package io.waterkite94.stalk.domain.model.vo

data class CommentLike(
    val id: Long? = null,
    val memberId: String,
    val commentId: String
)
