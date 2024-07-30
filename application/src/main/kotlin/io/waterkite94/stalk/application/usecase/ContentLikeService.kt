package io.waterkite94.stalk.application.usecase

import io.waterkite94.stalk.application.port.LikePersistencePort
import io.waterkite94.stalk.domain.model.vo.CommentLike
import io.waterkite94.stalk.domain.model.vo.PostLike
import org.springframework.stereotype.Service

@Service
class ContentLikeService(
    private val likePersistencePort: LikePersistencePort
) : ContentLike {
    override fun likePost(
        memberId: String,
        postId: String
    ) {
        likePersistencePort.savePostLike(PostLike(null, memberId, postId))
    }

    override fun unlikePost(
        memberId: String,
        postId: String
    ) {
        likePersistencePort.deletePostLike(memberId, postId)
    }

    override fun likeComment(
        memberId: String,
        commentId: String
    ) {
        likePersistencePort.saveCommentLike(CommentLike(null, memberId, commentId))
    }

    override fun unlikeComment(
        memberId: String,
        commentId: String
    ) {
        likePersistencePort.deleteCommentLike(memberId, commentId)
    }
}
