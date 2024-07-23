package io.waterkite94.stalk.application.port

import io.waterkite94.stalk.domain.model.vo.Comment

interface CommentPersistencePort {
    fun save(comment: Comment): Comment

    fun findByCommentId(commentId: String): Comment?

    fun deleteByCommentId(commentId: String)
}
