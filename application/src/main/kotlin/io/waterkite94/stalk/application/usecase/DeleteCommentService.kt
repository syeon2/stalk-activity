package io.waterkite94.stalk.application.usecase

import io.waterkite94.stalk.application.port.CommentPersistencePort
import org.springframework.stereotype.Service

@Service
class DeleteCommentService(
    private val commentPersistencePort: CommentPersistencePort
) : DeleteComment {
    override fun deleteComment(
        commentId: String,
        memberId: String
    ) {
        validateCommentWriterMatch(commentId, memberId)

        commentPersistencePort.deleteByCommentId(commentId)
    }

    private fun validateCommentWriterMatch(
        commentId: String,
        memberId: String
    ) {
        val findComment = commentPersistencePort.findByCommentId(commentId)

        if (findComment == null) {
            throw RuntimeException("Comment with id $commentId not found")
        } else {
            if (findComment.memberId != memberId) {
                throw RuntimeException("Permission denied: Only the comment author can delete this comment")
            }
        }
    }
}
