package io.waterkite94.stalk.application.port

import io.waterkite94.stalk.domain.model.vo.BoardPost
import io.waterkite94.stalk.domain.model.vo.Post

interface PostPersistencePort {
    fun save(post: Post): Post

    fun findPostByPostId(postId: String): Post?

    fun deleteByPostId(postId: String)

    fun findBoardPosts(
        stockId: String,
        offset: Int,
        limit: Int
    ): List<BoardPost>
}
