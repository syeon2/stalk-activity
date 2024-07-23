package io.waterkite94.stalk.application.usecase

import io.waterkite94.stalk.application.port.CommentPersistencePort
import io.waterkite94.stalk.domain.model.vo.Comment
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CreateCommentService(
    private val commentPersistencePort: CommentPersistencePort
) : CreateComment {
    override fun createComment(comment: Comment): Comment = commentPersistencePort.save(initializeComment(comment))

    private fun initializeComment(comment: Comment): Comment = comment.withCommentId(generateCommentId())

    private fun generateCommentId(): String = UUID.randomUUID().toString()
}
