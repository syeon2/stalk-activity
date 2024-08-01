package io.waterkite94.stalk.domain.model.vo

data class CommentDto(
    val article: String,
    val username: String? = null,
    val memberId: String
) {
    fun withUsername(username: String): CommentDto = this.copy(username = username)
}
