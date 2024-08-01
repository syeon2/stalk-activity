package io.waterkite94.stalk.application.port

import io.waterkite94.stalk.domain.model.vo.BoardComment
import io.waterkite94.stalk.domain.model.vo.Comment

interface CommentPersistencePort {
    fun save(comment: Comment): Comment

    fun findByCommentId(commentId: String): Comment?

    fun findCommentsByPostId(
        postId: String,
        offset: Int,
        limit: Int
    ): List<BoardComment>

    fun deleteByCommentId(commentId: String)
}
