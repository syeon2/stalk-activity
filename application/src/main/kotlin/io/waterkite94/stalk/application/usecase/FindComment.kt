package io.waterkite94.stalk.application.usecase

import io.waterkite94.stalk.domain.model.vo.CommentDto

interface FindComment {
    fun findCommentByPostId(
        postId: String,
        offset: Int,
        limit: Int
    ): List<CommentDto>
}
