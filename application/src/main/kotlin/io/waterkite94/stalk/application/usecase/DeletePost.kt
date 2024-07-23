package io.waterkite94.stalk.application.usecase

interface DeletePost {
    fun deletePost(
        postId: String,
        memberId: String
    )
}
