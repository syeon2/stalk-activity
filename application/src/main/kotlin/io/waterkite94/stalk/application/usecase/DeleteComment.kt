package io.waterkite94.stalk.application.usecase

interface DeleteComment {
    fun deleteComment(
        commentId: String,
        memberId: String
    )
}
