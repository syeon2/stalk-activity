package io.waterkite94.stalk.api.dto.request

data class DeleteCommentRequest(
    val commentId: String,
    val memberId: String
)
