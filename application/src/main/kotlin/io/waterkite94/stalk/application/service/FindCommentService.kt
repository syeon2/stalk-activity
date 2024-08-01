package io.waterkite94.stalk.application.service

import io.waterkite94.stalk.application.client.MemberServiceClient
import io.waterkite94.stalk.application.port.CommentPersistencePort
import io.waterkite94.stalk.application.usecase.FindComment
import io.waterkite94.stalk.domain.model.vo.BoardComment
import org.springframework.stereotype.Service

@Service
class FindCommentService(
    private val commentPersistencePort: CommentPersistencePort,
    private val memberServiceClient: MemberServiceClient
) : FindComment {
    override fun findCommentByPostId(
        postId: String,
        offset: Int,
        limit: Int
    ): List<BoardComment> {
        val findComments = commentPersistencePort.findCommentsByPostId(postId, offset, limit)

        val comments: MutableList<BoardComment> = ArrayList()

        for (comment in findComments) {
            val member = memberServiceClient.getMember(comment.memberId)

            comments.add(comment.withUsername(member.username))
        }

        return comments
    }
}
