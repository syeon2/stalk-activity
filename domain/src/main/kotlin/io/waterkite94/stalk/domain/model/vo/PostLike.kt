package io.waterkite94.stalk.domain.model.vo

data class PostLike(
    val id: Long? = null,
    val memberId: String,
    val postId: String
)
