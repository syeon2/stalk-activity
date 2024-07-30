package io.waterkite94.stalk.application.port

import io.waterkite94.stalk.domain.model.vo.CommentLike
import io.waterkite94.stalk.domain.model.vo.PostLike

interface LikePersistencePort {
    fun savePostLike(postLike: PostLike)

    fun deletePostLike(
        memberId: String,
        postId: String
    )

    fun saveCommentLike(commentLike: CommentLike)

    fun deleteCommentLike(
        memberId: String,
        commentId: String
    )
}
