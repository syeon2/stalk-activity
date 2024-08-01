package io.waterkite94.stalk.domain.model.vo

data class BoardPost(
    val title: String? = null,
    val article: String? = null,
    val username: String? = null,
    val postLikeCount: Int? = null,
    val commentCount: Int? = null,
    val isFollowing: Boolean? = null,
    val memberId: String? = null
) {
    fun withUsername(username: String) = this.copy(username = username)
}
