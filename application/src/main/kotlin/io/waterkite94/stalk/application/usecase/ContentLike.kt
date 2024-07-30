package io.waterkite94.stalk.application.usecase

interface ContentLike {
    fun likePost(
        memberId: String,
        postId: String
    )

    fun unlikePost(
        memberId: String,
        postId: String
    )

    fun likeComment(
        memberId: String,
        commentId: String
    )

    fun unlikeComment(
        memberId: String,
        commentId: String
    )
}
