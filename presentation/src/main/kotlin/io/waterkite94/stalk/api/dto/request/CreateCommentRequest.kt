package io.waterkite94.stalk.api.dto.request

import io.waterkite94.stalk.domain.model.vo.Comment

data class CreateCommentRequest(
    val article: String,
    val postId: String,
    val memberId: String
) {
    fun toDomain(): Comment =
        Comment(
            article = article,
            postId = postId,
            memberId = memberId
        )
}
