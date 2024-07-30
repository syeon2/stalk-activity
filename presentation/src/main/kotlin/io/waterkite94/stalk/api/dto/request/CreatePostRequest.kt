package io.waterkite94.stalk.api.dto.request

import io.waterkite94.stalk.domain.model.vo.Post

data class CreatePostRequest(
    val title: String,
    val article: String,
    val memberId: String
) {
    fun toDomain() =
        Post(
            title = title,
            article = article,
            memberId = memberId
        )
}
