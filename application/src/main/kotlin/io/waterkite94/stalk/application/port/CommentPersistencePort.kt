package io.waterkite94.stalk.application.port

import io.waterkite94.stalk.domain.model.vo.Comment
import io.waterkite94.stalk.domain.model.vo.CommentDto

interface CommentPersistencePort {
    fun save(comment: Comment): Comment

    fun findByCommentId(commentId: String): Comment?

    fun findCommentsByPostId(
        postId: String,
        offset: Int,
        limit: Int
    ): List<CommentDto>

    fun deleteByCommentId(commentId: String)
}
