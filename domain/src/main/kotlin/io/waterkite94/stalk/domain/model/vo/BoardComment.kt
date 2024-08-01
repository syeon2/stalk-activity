package io.waterkite94.stalk.domain.model.vo

data class BoardComment(
    val article: String,
    val username: String? = null,
    val memberId: String
) {
    fun withUsername(username: String): BoardComment = this.copy(username = username)
}
