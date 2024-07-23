package io.waterkite94.stalk.api.dto.response

import io.waterkite94.stalk.domain.model.vo.Post

data class CreatePostResponse(
    val postId: String? = null,
    val title: String,
    val article: String,
    val memberId: String
) {
    companion object {
        fun toResponse(domain: Post): CreatePostResponse =
            CreatePostResponse(
                postId = domain.postId,
                title = domain.title,
                article = domain.article,
                memberId = domain.memberId
            )
    }
}
